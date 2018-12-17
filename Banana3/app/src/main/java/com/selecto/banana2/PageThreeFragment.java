package com.selecto.banana2;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PageThreeFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    View view;
    GoogleMap mMap;
    MapView mapView;
    Button button;
    StringBuilder sb;
    double latitude = 37.543489;
    double longitude = 126.995717;
    String NM = null, ADDR_OLD = null, ADDR = null, TEL = null, XCODE = null, YCODE = null;
    Marker mypos;
    CameraPosition cp;
    private GpsInfo gps;
    ArrayList<Shoplistitem> data;
    ImageView btn_refresh;
    int tg_mapReady = 0;
    ViewPager customViewPager;
    boolean pageFocus;

    public PageThreeFragment() {

        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        } else {
            view = inflater.inflate(R.layout.fragment_page_three, null);
        }

        gps = new GpsInfo(getActivity());
        if(gps.isGetLocation) {
            this.latitude = gps.getLatitude();
            this.longitude = gps.getLongitude();
        }

        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tg_mapReady == 1) {
                    try {
                        jsonTest();
                    } catch (JSONException e) {
                        Toast.makeText(getContext(), "파싱에러(1)", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                    if (data.isEmpty()) {
                        Toast.makeText(getContext().getApplicationContext(), "검색된 주변 판매소가 없습니다.", Toast.LENGTH_LONG).show();
                    }

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.hide(PageThreeFragment.this);
                    fragmentTransaction.add(R.id.fragment_shop_list, new ShopListFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                else{
                    Toast.makeText(getContext().getApplicationContext(), "좌측 상단버튼을 통해 위치를 갱신해 주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_refresh = (ImageView)view.findViewById(R.id.refresh);
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GpsInfo(getActivity());

                if(!gps.isGetLocation)
                {
                    //Log.d("kdh : Test", "gps ok / tg_mapReady 1");
                    tg_mapReady = 0;
                    gps.showSettingsAlert();
                    Toast.makeText(getContext().getApplicationContext(), "GPS 설정후 '내 위치' 버튼을 누르세요.", Toast.LENGTH_LONG).show();
                }
                else {
                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);

                    if(tg_mapReady == 1) {
                        onMapClick(latLng);
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                    }
                    else{
                        onMapReady(mMap);
                        //onMapClick(latLng);
                        Toast.makeText(getContext().getApplicationContext(), "GPS 위치 수신속도에 따라 화면이 나오지 않을 수 있습니다. 그럴 경우 다시시도해 주세요.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        customViewPager = ((MainActivity)getActivity()).mViewPager;

        customViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 3) {
                    Toast.makeText(getContext().getApplicationContext(), "주변판매소 위치정보를 조회합니다. 좌측 상단 버튼을 클릭해주세요.", Toast.LENGTH_LONG).show();
                    pageFocus = true;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }

    /*통합데이터 불러오기 및 필요데이터 추출, 추출데이터 JSON생성*/
    public void jsonTest() throws JSONException {

        GeoPoint gp;
        double distance;
        sb = new StringBuilder();
        String str = ((MainActivity)getActivity()).loadJSONFromAsset("Shoplist.json");
        JSONObject dataObj = new JSONObject(str);
        MarkerOptions markerOptions = new MarkerOptions();
        data = new ArrayList<>();
        Shoplistitem shoplistitem;

        try {
            JSONArray jsonArray = dataObj.getJSONArray("DATA");

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                XCODE = jsonObject.getString("xcode");
                YCODE = jsonObject.getString("ycode");

                if (("".equals(XCODE))||("".equals(YCODE))||(XCODE == "null")||(YCODE == "null")){
                    continue;
                }
                else {
                    gp = new GeoPoint(Double.parseDouble(XCODE), Double.parseDouble(YCODE));
                    gp = GeoTrans.convert(3, 0, gp);
                    distance = calDistance(latitude, longitude, gp.y, gp.x);

                    if (distance < 1000) {
                        NM = jsonObject.getString("nm");
                        ADDR_OLD = jsonObject.getString("addr_old");
                        ADDR = jsonObject.getString("addr");
                        TEL = jsonObject.getString("tel");

                        shoplistitem = new Shoplistitem(NM, ADDR_OLD, ADDR, TEL);
                        data.add(shoplistitem);

                        markerOptions.title(NM);
                        markerOptions.position(new LatLng(gp.y, gp.x));
                        if((ADDR_OLD == "null")&&(ADDR == "null"))
                            markerOptions.snippet("주소정보없음");
                        else if(ADDR_OLD == "null")
                            markerOptions.snippet(ADDR);
                        else
                            markerOptions.snippet(ADDR_OLD);

                        mMap.addMarker(markerOptions);
                        //                  mMap.setOnInfoWindowClickListener(infoWindowClickListener);
                    }
                }
            }
            ((MainActivity)getActivity()).setData(data);

        }catch(JSONException e){
            Toast.makeText(getContext(), "파싱에러(2)", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    @Override
    public void onMapReady(final GoogleMap googleMap)
    {
        MapsInitializer.initialize(this.getActivity());
        mMap = googleMap;

        if(!gps.isGetLocation) {
            tg_mapReady = 0;    //GPS 기능 꺼짐
        }
        else{
            tg_mapReady = 1;    //GPS 기능과 함께 onMapReady실행
        }

        mMap.setOnMapClickListener(this);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));

        if((pageFocus == true)&&(tg_mapReady==1)) {
            /*카메라 초기위치*/
            LatLng latLng = new LatLng(latitude, longitude);   //기준좌표
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            mMap.addCircle(new CircleOptions().center(latLng).radius(1000).strokeColor(Color.parseColor("#884169e1")).fillColor(Color.parseColor("#5587cefa")));

            BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.myposition2);
            Bitmap b = bitmapDrawable.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, 100, 100, false);

            mypos = mMap.addMarker(new MarkerOptions().position(latLng).title("사용자 지정위치")
                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {

        mMap.clear();
        if((pageFocus==true)&&(tg_mapReady==1)&&(mypos!=null))
            mypos.remove();

        BitmapDrawable bitmapDrawable = (BitmapDrawable)getResources().getDrawable(R.drawable.myposition2);
        Bitmap b=bitmapDrawable.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 100, 100, false);

        mypos = mMap.addMarker(new MarkerOptions().position(latLng).title("사용자 지정위치")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        mMap.addCircle(new CircleOptions().center(latLng).radius(1000).strokeColor(Color.parseColor("#884169e1")).fillColor(Color.parseColor("#5587cefa")));

        latitude = latLng.latitude;
        longitude = latLng.longitude;

        try {
            jsonTest();
        } catch (JSONException e) {
            Toast.makeText(getContext(), "파싱에러(1)", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public double calDistance(double lat1, double lon1, double lat2, double lon2){

        double theta, dist;
        theta = lon1 - lon2;
        dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);

        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;    // 단위 mile 에서 km 변환.
        dist = dist * 1000.0;      // 단위  km 에서 m 로 변환

        return dist;
    }

    // 주어진 도(degree) 값을 라디언으로 변환
    private double deg2rad(double deg){
        return (double)(deg * Math.PI / (double)180d);
    }

    // 주어진 라디언(radian) 값을 도(degree) 값으로 변환
    private double rad2deg(double rad){
        return (double)(rad * (double)180d / Math.PI);
    }

    public static PageThreeFragment newInstance() {
        Bundle args = new Bundle();

        PageThreeFragment fragment = new PageThreeFragment();
        fragment.setArguments(args);
        return fragment;
    }

}