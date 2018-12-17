package com.selecto.banana2;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListPopupWindow;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageTwoFragment extends Fragment {
    View view;
    Button button_gps;
    Animation translateDown;
    LinearLayout page_gps;
    LinearLayout page_info;
    ArrayAdapter<String> spinnerAdapter;
    Button btnShowLocation;
    TextView txtAds;
    Geocoder geocoder;
    List<Address> addresses = null;
    TextView close;
    Animation translateUp;
    Spinner spinner;
    String fulladdr;
    ArrayList<String> guList;
    String gu;
    String[] arrString;
    double latitude;
    double longitude;

    LayoutInflater mInflater;
    LinearLayout mRootLinear ;
    ScrollView gangnam;
    ScrollView gangdong;
    ScrollView gangbuk;
    ScrollView gangseo;
    ScrollView gwanak;
    ScrollView gwangjin;
    ScrollView guro;
    ScrollView geumcheon;
    ScrollView nowon;
    ScrollView dobong;
    ScrollView dongdaemun;
    ScrollView dongjak;
    ScrollView mapo;
    ScrollView seodaemun;
    ScrollView seocho;
    ScrollView seongdong;
    ScrollView seongbuk;
    ScrollView songpa;
    ScrollView yangcheon;
    ScrollView yeongdeungpo;
    ScrollView yongsan;
    ScrollView eunpyeong;
    ScrollView jongno;
    ScrollView jung;
    ScrollView jungnang;

    String[] str = new String[]{"행정구역 선택", "강남구", "강동구", "강북구", "강서구", "관악구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"};


    PhotoView photoView;
    Dialog dialog;

    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
    private boolean isAccessFineLocation = false;
    private boolean isAccessCoarseLocation = false;
    private boolean isPermission = false;

    private GpsInfo gps;


    public PageTwoFragment() {
        // Required empty public constructor
    }

    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) v.setVisibility(View.GONE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        } else {
            view = inflater.inflate(R.layout.fragment_page_two, null);
        }

        SlidingAnimationListener listener = new SlidingAnimationListener();
        translateDown = AnimationUtils.loadAnimation(getActivity(), R.anim.translate_down);
        page_gps = view.findViewById(R.id.view_gps);
        page_info = view.findViewById(R.id.view_info);
        button_gps = view.findViewById(R.id.button_gps);
        spinner = view.findViewById(R.id.mySpinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.ward, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b_view) {
                page_gps.setVisibility(View.VISIBLE);
                page_gps.startAnimation(translateDown);

            }
        });

        btnShowLocation = view.findViewById(R.id.button3);
        txtAds = view.findViewById(R.id.textView6);
        translateUp = AnimationUtils.loadAnimation(getActivity(), R.anim.translate_up);
        translateUp.setAnimationListener(listener);
        close = view.findViewById(R.id.textView8);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View childview) {
                page_gps.startAnimation(translateUp);
            }
        });

        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                txtAds.setText("기기를 살짝 이동 후 버튼을 다시 눌러주세요.");
                if (!isPermission) {
                    callPermission();
                    return;
                }
                gps = new GpsInfo(getActivity());

                if (gps.isGetLocation) {
                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();
                    setAddresses();
                } else {
                    gps.showSettingsAlert();
                }
                gps.stopUsingGPS();
            }
        });
        callPermission();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View s_view, int i, long l) {
                mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                mRootLinear = view.findViewById(R.id.linear_root);
                Log.d("spinner_test", adapterView.getItemAtPosition(i).toString());
                String spinner_selected_gu = adapterView.getItemAtPosition(i).toString();

                Linkify.TransformFilter mTransform = new Linkify.TransformFilter() {
                    @Override
                    public String transformUrl(Matcher matcher, String s) {
                        return "";
                    }
                };

                if (!spinner_selected_gu.equals("행정구역 선택")) {
                    button_gps.setText(spinner_selected_gu + " 배출 정보");
                }
                mRootLinear.removeAllViews();

                dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.custom_dialog);
                photoView = dialog.findViewById(R.id.childImage);
                photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);

                if (spinner_selected_gu.equals("강남구")) {
                    gangnam = (ScrollView) mInflater.inflate(R.layout.gangnam, mRootLinear, false);
                    mRootLinear.addView(gangnam);
                    Button furniture = gangnam.findViewById(R.id.button6);
                    Button appliance = gangnam.findViewById(R.id.button5);
                    Button etc = gangnam.findViewById(R.id.button4);
                    Button  agent = gangnam.findViewById(R.id.button7);

                    furniture.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            photoView.setImageResource(R.drawable.furniture);
                            dialog.show();
                        }
                    });
                    appliance.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            photoView.setImageResource(R.drawable.appliance);
                            dialog.show();
                        }
                    });
                    etc.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            photoView.setImageResource(R.drawable.etc);
                            dialog.show();
                        }
                    });
                    agent.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            photoView.setImageResource(R.drawable.agent);
                            dialog.show();
                        }
                    });

                    TextView tvLinkify = gangnam.findViewById(R.id.textView66);
                    Pattern pattern1 = Pattern.compile("서울시 폐가전 무상배출예약시스템 바로가기");
                    Linkify.addLinks(tvLinkify, pattern1, "http://www.edtd.co.kr",null, mTransform);

                    TextView tvLinkify2 = gangnam.findViewById(R.id.textView58);
                    Pattern pattern2 = Pattern.compile("강남구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify2, pattern2, "http://www.gangnam.go.kr",null, mTransform);

                } else if (spinner_selected_gu.equals("강동구")) {
                    gangdong = (ScrollView) mInflater.inflate(R.layout.gangdong, mRootLinear, false);
                    mRootLinear.addView(gangdong);
                    Button furniture = gangdong.findViewById(R.id.button_gandong1);
                    Button appliance = gangdong.findViewById(R.id.button_gandong2);
                    Button etc = gangdong.findViewById(R.id.button_gandong3);
                    Button free = gangdong.findViewById(R.id.button_gandong4);

                    furniture.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            photoView.setImageResource(R.drawable.gangdong1);
                            dialog.show();
                        }
                    });
                    appliance.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            photoView.setImageResource(R.drawable.gangdong2);
                            dialog.show();
                        }
                    });
                    etc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            photoView.setImageResource(R.drawable.gangdong3);
                            dialog.show();
                        }
                    });
                    free.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            photoView.setImageResource(R.drawable.gangdong4);
                            dialog.show();
                        }
                    });



                    TextView tvLinkify = gangdong.findViewById(R.id.textView2);
                    Pattern pattern1 = Pattern.compile("대형생활폐기물수거신청 바로가기");
                    Linkify.addLinks(tvLinkify, pattern1, "https://gangdong.storyweb.co.kr",null, mTransform);

                    TextView tvLinkify2 = gangdong.findViewById(R.id.textView1);
                    Pattern pattern2 = Pattern.compile("강동구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify2, pattern2, "http://www.gangdong.go.kr",null, mTransform);

                } else if(spinner_selected_gu.equals("강북구")){
                    gangbuk = (ScrollView) mInflater.inflate(R.layout.gangbuk, mRootLinear, false);
                    mRootLinear.addView(gangbuk);

                    TextView tvLinkify = gangbuk.findViewById(R.id.textView350);
                    Pattern pattern1 = Pattern.compile("대형 폐기물 신청 바로가기");
                    Linkify.addLinks(tvLinkify, pattern1, "http://www.gangbuk.go.kr/www/contents.do?key=727",null, mTransform);

                    TextView tvLinkify2 = gangbuk.findViewById(R.id.textView351);
                    Pattern pattern2 = Pattern.compile("품목별 수수료 보기");
                    Linkify.addLinks(tvLinkify2, pattern2, "http://www.gangbuk.go.kr/merge/contents.do?key=8137",null, mTransform);

                    TextView tvLinkify3 = gangbuk.findViewById(R.id.textView229);
                    Pattern pattern3 = Pattern.compile("강북구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify3, pattern3, "http://www.gangbuk.go.kr",null, mTransform);

                } else if (spinner_selected_gu.equals("강서구")) {
                    gangseo = (ScrollView) mInflater.inflate(R.layout.gangseo, mRootLinear, false);
                    mRootLinear.addView(gangseo);
                    Button gangseo1 = gangseo.findViewById(R.id.button6);
                    Button gangseo2 = gangseo.findViewById(R.id.button16);
                    Button gangseo3 = gangseo.findViewById(R.id.button15);
                    Button gangseo4 = gangseo.findViewById(R.id.button5);
                    Button gangseo5 = gangseo.findViewById(R.id.button4);

                    gangseo1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            photoView.setImageResource(R.drawable.gangseo1);
                            dialog.show();
                        }
                    });
                    gangseo2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            photoView.setImageResource(R.drawable.gangseo2);
                            dialog.show();
                        }
                    });
                    gangseo3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            photoView.setImageResource(R.drawable.gangseo3);
                            dialog.show();
                        }
                    });
                    gangseo4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            photoView.setImageResource(R.drawable.gangseo4);
                            dialog.show();
                        }
                    });
                    gangseo5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            photoView.setImageResource(R.drawable.gangseo5);
                            dialog.show();
                        }
                    });

                    TextView tvLinkify = gangseo.findViewById(R.id.textView1);
                    Pattern pattern1 = Pattern.compile("대형폐기물 인터넷처리 바로가기");
                    Linkify.addLinks(tvLinkify, pattern1, "https://m.gangseo.seoul.kr/sub0302.html",null, mTransform);

                    TextView tvLinkify2 = gangseo.findViewById(R.id.textView39);
                    Pattern pattern2 = Pattern.compile("강서구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify2, pattern2, "https://www.gangseo.seoul.kr",null, mTransform);


                } else if (spinner_selected_gu.equals("관악구")) {
                    gwanak = (ScrollView) mInflater.inflate(R.layout.gwanak, mRootLinear, false);
                    mRootLinear.addView(gwanak);

                    TextView tvLinkify = gwanak.findViewById(R.id.textView1);
                    Pattern pattern1 = Pattern.compile("대형폐기물 인터넷처리 바로가기");
                    Linkify.addLinks(tvLinkify, pattern1, "https://www.gwanak.go.kr/site/gwanak/07/10703010500002016051206.jsp",null, mTransform);

                    TextView tvLinkify2 = gwanak.findViewById(R.id.textView2);
                    Pattern pattern2 = Pattern.compile("품목별 수수료 보기");
                    Linkify.addLinks(tvLinkify2, pattern2, "https://www.gwanak.go.kr/site/gwanak/04/10402020300002016071302.jsp",null, mTransform);

                    TextView tvLinkify3 = gwanak.findViewById(R.id.textView3);
                    Pattern pattern3 = Pattern.compile("관악구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify3, pattern3, "http://www.gwanak.go.kr/site/gwanak/main.do",null, mTransform);
                } else if (spinner_selected_gu.equals("광진구")) {
                    gwangjin = (ScrollView) mInflater.inflate(R.layout.gwangjin, mRootLinear, false);
                    mRootLinear.addView(gwangjin);

                    TextView tvLinkify = gwangjin.findViewById(R.id.textView1);
                    Pattern pattern = Pattern.compile("광진구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify, pattern, "https://www.gwangjin.go.kr",null, mTransform);

                } else if (spinner_selected_gu.equals("구로구")) {
                    guro = (ScrollView) mInflater.inflate(R.layout.guro, mRootLinear, false);
                    mRootLinear.addView(guro);

                    TextView tvLinkify = guro.findViewById(R.id.textView1);
                    Pattern pattern = Pattern.compile("대형생활폐기물수거신청 바로가기");
                    Linkify.addLinks(tvLinkify, pattern, "https://clean.guro.go.kr",null, mTransform);

                    TextView tvLinkify2 = guro.findViewById(R.id.textView2);
                    Pattern pattern2 = Pattern.compile("배출 품목 규격 및 부과금액 바로가기");
                    Linkify.addLinks(tvLinkify2, pattern2, "https://clean.guro.go.kr/cost",null, mTransform);

                    Linkify.TransformFilter mTransform1 = new Linkify.TransformFilter() {
                        @Override
                        public String transformUrl(Matcher matcher, String s) {
                            return "http://www.guro.go.kr/www/NR_index.do";
                        }
                    };

                    TextView tvLinkify3 = guro.findViewById(R.id.textView3);
                    Pattern pattern3 = Pattern.compile("구로구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify3, pattern3,"",null, mTransform1);

                } else if(spinner_selected_gu.equals("금천구")) {
                    geumcheon = (ScrollView) mInflater.inflate(R.layout.geumcheon, mRootLinear, false);
                    mRootLinear.addView(geumcheon);

                    TextView tvLinkify = (TextView) geumcheon.findViewById(R.id.textView135) ;
                    Pattern pattern1 = Pattern.compile("[지역별 지정된 요일 보기]");
                    Linkify.addLinks(tvLinkify, pattern1, "https://www.geumcheon.go.kr/html/001/001003005001003001.html",null, mTransform);

                    TextView tvLinkify2 = (TextView) geumcheon.findViewById(R.id.textView239) ;
                    Pattern pattern2 = Pattern.compile("대형폐기물 신청하러가기");
                    Linkify.addLinks(tvLinkify2, pattern2, "https://www.geumcheon.go.kr/html/001/001003005004.html",null, mTransform);

                    TextView tvLinkify3 = (TextView) geumcheon.findViewById(R.id.textView240) ;
                    Pattern pattern3 = Pattern.compile("금천구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify3, pattern3, "https://m.geumcheon.go.kr/html/046/046.jsp",null, mTransform);


                } else if(spinner_selected_gu.equals("노원구")){
                    nowon = (ScrollView) mInflater.inflate(R.layout.nowon, mRootLinear, false);
                    mRootLinear.addView(nowon);

                    TextView tvLinkify = (TextView) nowon.findViewById(R.id.textView254) ;
                    Pattern pattern = Pattern.compile("대형폐기물 처리 신청 바로가기");
                    Linkify.addLinks(tvLinkify, pattern, "http://nowon.storyweb.co.kr",null, mTransform);

                    TextView tvLinkify2 = (TextView) nowon.findViewById(R.id.textView228) ;
                    Pattern pattern2 = Pattern.compile("대형폐기물 수수료 보기");
                    Linkify.addLinks(tvLinkify2, pattern2, "http://nowon.storyweb.co.kr/size_index.php?index=1",null, mTransform);

                    TextView tvLinkify1 = (TextView) nowon.findViewById(R.id.textView262) ;
                    Pattern pattern1 = Pattern.compile("노원구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify1, pattern1, "http://mw.nowon.kr/main/index.do",null, mTransform);

                } else if (spinner_selected_gu.equals("도봉구")) {
                    dobong = (ScrollView) mInflater.inflate(R.layout.dobong, mRootLinear, false);
                    mRootLinear.addView(dobong);

                    TextView tvLinkify1 = (TextView) dobong.findViewById(R.id.textView1) ;
                    Pattern pattern1 = Pattern.compile("가정용 전용용기 판매소 바로가기");
                    Linkify.addLinks(tvLinkify1, pattern1, "http://www.dobong.go.kr/subsite/waste/contents.asp?code=10007376",null, mTransform);

                    TextView tvLinkify2 = (TextView) dobong.findViewById(R.id.textView2) ;
                    Pattern pattern2 = Pattern.compile("종량제 봉투 판매소 현황 바로가기");
                    Linkify.addLinks(tvLinkify2, pattern2, "http://www.dobong.go.kr/subsite/waste/Contents.asp?code=10007356",null, mTransform);

                    TextView tvLinkify3 = (TextView) dobong.findViewById(R.id.textView3) ;
                    Pattern pattern3 = Pattern.compile("품목 및 수수료 확인 바로가기");
                    Linkify.addLinks(tvLinkify3, pattern3, "http://www.dobong.go.kr/WDB_DEV/WASTE/item_list.asp",null, mTransform);

                    TextView tvLinkify4 = (TextView) dobong.findViewById(R.id.textView4) ;
                    Pattern pattern4 = Pattern.compile("대형폐기물 배출 신청 바로가기");
                    Linkify.addLinks(tvLinkify4, pattern4, "http://www.dobong.go.kr/WDB_DEV/WASTE/item_list.asp",null, mTransform);

                    TextView tvLinkify5 = (TextView) dobong.findViewById(R.id.textView5) ;
                    Pattern pattern5 = Pattern.compile("도봉구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify5, pattern5, "http://m.dobong.go.kr",null, mTransform);

                } else if (spinner_selected_gu.equals("동대문구")) {
                    dongdaemun = (ScrollView) mInflater.inflate(R.layout.dongdaemun, mRootLinear, false);
                    mRootLinear.addView(dongdaemun);

                    Linkify.TransformFilter mTransform1 = new Linkify.TransformFilter() {
                        @Override
                        public String transformUrl(Matcher matcher, String s) {
                            return "http://www.ddm.go.kr/civil/trashFee.jsp";
                        }
                    };

                    TextView tvLinkify = (TextView) dongdaemun.findViewById(R.id.textView529) ;
                    Pattern pattern1 = Pattern.compile("[대형폐기물 품목별 수수료 보기]");
                    Linkify.addLinks(tvLinkify, pattern1, "",null, mTransform1);

                    Linkify.TransformFilter mTransform2 = new Linkify.TransformFilter() {
                        @Override
                        public String transformUrl(Matcher matcher, String s) {
                            return "http://www.ddm.go.kr/civil/trashInfo.jsp";
                        }
                    };
                    TextView tvLinkify2 = (TextView)  dongdaemun.findViewById(R.id.textView275) ;
                    Pattern pattern2 = Pattern.compile("[동대문구 대형폐기물 처리 신청 바로가기]");
                    Linkify.addLinks(tvLinkify2, pattern2, "",null, mTransform2);

                    TextView tvLinkify3 = (TextView)  dongdaemun.findViewById(R.id.textView265) ;
                    Pattern pattern3 = Pattern.compile("동대문구 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify3, pattern3, "http://www.ddm.go.kr",null, mTransform);

                } else if (spinner_selected_gu.equals("동작구")) {
                    dongjak = (ScrollView) mInflater.inflate(R.layout.dongjak, mRootLinear, false);
                    mRootLinear.addView(dongjak);

                    Linkify.TransformFilter mTransform1 = new Linkify.TransformFilter() {
                        @Override
                        public String transformUrl(Matcher matcher, String s) {
                            return "http://www.dongjak.go.kr/portal/wste/wsteInfo/info.do?menuNo=200735";
                        }
                    };
                    TextView tvLinkify = (TextView)  dongjak.findViewById(R.id.textView324) ;
                    Pattern pattern = Pattern.compile("[대형폐기물처리 신청 바로가기]");
                    Linkify.addLinks(tvLinkify, pattern, "",null, mTransform1);

                    Linkify.TransformFilter mTransform2 = new Linkify.TransformFilter() {
                        @Override
                        public String transformUrl(Matcher matcher, String s) {
                            return "http://www.dongjak.go.kr/portal/wste/wsteInfo/list.do?menuNo=200737";
                        }
                    };
                    TextView tvLinkify1 = (TextView) dongjak.findViewById(R.id.textView325) ;
                    Pattern pattern1 = Pattern.compile("[품목별 수거기준 및 비용 보기]");
                    Linkify.addLinks(tvLinkify1, pattern1, "",null, mTransform2);

                    TextView tvLinkify2 = (TextView) dongjak.findViewById(R.id.textView326) ;
                    Pattern pattern2 = Pattern.compile("동작구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify2, pattern2, "http://www.dongjak.go.kr/portal/main/main.do",null, mTransform);

                } else if (spinner_selected_gu.equals("마포구")) {
                    mapo = (ScrollView) mInflater.inflate(R.layout.mapo, mRootLinear, false);
                    mRootLinear.addView(mapo);

                    TextView tvLinkify = (TextView) mapo.findViewById(R.id.textView1) ;
                    Pattern pattern = Pattern.compile("마포구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify, pattern, "http://www.mapo.go.kr/CmsWeb/viewPage.req?idx=PG0000002183",null, mTransform);

                } else if (spinner_selected_gu.equals("서대문구")) {
                    seodaemun = (ScrollView) mInflater.inflate(R.layout.seodaemoon, mRootLinear, false);
                    mRootLinear.addView(seodaemun);

                    TextView tvLinkify = (TextView) seodaemun.findViewById(R.id.textView350) ;
                    Pattern pattern1 = Pattern.compile("[대형 폐기물 신청 바로가기]");
                    Linkify.addLinks(tvLinkify, pattern1, "http://www.sdm.go.kr/civil/print/waste/reg.do",null, mTransform);

                    TextView tvLinkify2 = (TextView) seodaemun.findViewById(R.id.textView351) ;
                    Pattern pattern2 = Pattern.compile("[품목별 수수료 보기]");
                    Linkify.addLinks(tvLinkify2, pattern2, "http://www.sdm.go.kr/civil/print/waste/standards.do",null, mTransform);

                    TextView tvLinkify3 = (TextView) seodaemun.findViewById(R.id.textView352) ;
                    Pattern pattern3 = Pattern.compile("서대문구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify3, pattern3, "http://www.sdm.go.kr",null, mTransform);

                } else if (spinner_selected_gu.equals("서초구")) {
                    seocho = (ScrollView) mInflater.inflate(R.layout.seocho, mRootLinear, false);
                    mRootLinear.addView(seocho);

                    Linkify.TransformFilter mTransform1 = new Linkify.TransformFilter() {
                        @Override
                        public String transformUrl(Matcher matcher, String s) {
                            return "http://www.seocho.go.kr/site/seocho/ex/bigWaste/BigWasteUserFStart.do";
                        }
                    };
                    TextView tvLinkify = (TextView) seocho.findViewById(R.id.textView350) ;
                    Pattern pattern1 = Pattern.compile("[대형 폐기물 신청 바로가기]");
                    Linkify.addLinks(tvLinkify, pattern1, "",null, mTransform1);

                    Linkify.TransformFilter mTransform2 = new Linkify.TransformFilter() {
                        @Override
                        public String transformUrl(Matcher matcher, String s) {
                            return "http://www.seocho.go.kr/common/files/Download.do?cfIdx=CF00001672&cfGroup=COMMON&cfRename=e6d64168-4db3-4bbb-9f72-eab4933530a5.hwp";
                        }
                    };
                    TextView tvLinkify2 = (TextView) seocho.findViewById(R.id.textView351) ;
                    Pattern pattern2 = Pattern.compile("[품목별 수수료 다운로드]");
                    Linkify.addLinks(tvLinkify2, pattern2, "",null, mTransform2);

                    TextView tvLinkify3 = (TextView) seocho.findViewById(R.id.textView367) ;
                    Pattern pattern3 = Pattern.compile("서초구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify3, pattern3, "http://www.seocho.go.kr",null, mTransform);

                } else if (spinner_selected_gu.equals("성동구")) {
                    seongdong = (ScrollView) mInflater.inflate(R.layout.seongdong, mRootLinear, false);
                    mRootLinear.addView(seongdong);

                    TextView tvLinkify1 = (TextView) seongdong.findViewById(R.id.textView1) ;
                    Pattern pattern1 = Pattern.compile("[ 배출품목 보기 ]");
                    Linkify.addLinks(tvLinkify1, pattern1, "http://www.sd.go.kr/jsp/pop/pop_waste.jsp",null, mTransform);

                    TextView tvLinkify2 = (TextView) seongdong.findViewById(R.id.textView2) ;
                    Pattern pattern2 = Pattern.compile("성동구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify2, pattern2, "http://www.sd.go.kr/",null, mTransform);

                } else if (spinner_selected_gu.equals("성북구")) {
                    seongbuk = (ScrollView) mInflater.inflate(R.layout.seongbuk, mRootLinear, false);
                    mRootLinear.addView(seongbuk);

                    TextView tvLinkify = (TextView) seongbuk.findViewById(R.id.textView350) ;
                    Pattern pattern1 = Pattern.compile("[대형 폐기물 신청 바로가기]");
                    Linkify.addLinks(tvLinkify, pattern1, "http://www.doori114.com",null, mTransform);

                    Linkify.TransformFilter mTransform1 = new Linkify.TransformFilter() {
                        @Override
                        public String transformUrl(Matcher matcher, String s) {
                            return "http://www.seongbuk.go.kr/mobile/PageLink.do";
                        }
                    };
                    TextView tvLinkify2 = (TextView) seongbuk.findViewById(R.id.textView351);
                    Pattern pattern2 = Pattern.compile("[품목별 수수료 보기]");
                    Linkify.addLinks(tvLinkify2, pattern2, "",null, mTransform1);

                    TextView tvLinkify3 = (TextView) seongbuk.findViewById(R.id.textView413);
                    Pattern pattern3 = Pattern.compile("성북구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify3, pattern3, "http://www.seongbuk.go.kr",null, mTransform);

                } else if (spinner_selected_gu.equals("송파구")) {
                    songpa = (ScrollView) mInflater.inflate(R.layout.songpa, mRootLinear, false);
                    mRootLinear.addView(songpa);

                    Linkify.TransformFilter mTransform1 = new Linkify.TransformFilter() {
                        @Override
                        public String transformUrl(Matcher matcher, String s) {
                            return "http://www.songpa.go.kr/user.kdf?a=songpa.menu.MenuApp&c=1001&cate_id=AA0605001000";
                        }
                    };
                    TextView tvLinkify = (TextView) songpa.findViewById(R.id.textView1);
                    Pattern pattern = Pattern.compile("대형폐기물 품목별 수수료 보기");
                    Linkify.addLinks(tvLinkify, pattern, "",null, mTransform1);

                    Linkify.TransformFilter mTransform2 = new Linkify.TransformFilter() {
                        @Override
                        public String transformUrl(Matcher matcher, String s) {
                            return "http://www.songpa.go.kr/user.kdf?a=songpa.menu.MenuApp&c=1001&cate_id=AA0605002000";
                        }
                    };
                    TextView tvLinkify2 = (TextView) songpa.findViewById(R.id.textView2);
                    Pattern pattern2 = Pattern.compile("품목별 수거기준 및 비용 보기");
                    Linkify.addLinks(tvLinkify2, pattern2, "",null, mTransform2);

                    TextView tvLinkify3 = (TextView) songpa.findViewById(R.id.textView3);
                    Pattern pattern3 = Pattern.compile("송파구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify3, pattern3, "http://www.songpa.go.kr",null, mTransform);

                } else if (spinner_selected_gu.equals("양천구")) {
                    yangcheon = (ScrollView) mInflater.inflate(R.layout.yangcheon, mRootLinear, false);
                    mRootLinear.addView(yangcheon);

                    TextView tvLinkify = (TextView) yangcheon.findViewById(R.id.textView350) ;
                    Pattern pattern1 = Pattern.compile("[ 대형 폐기물 신청 바로가기 ]");
                    Linkify.addLinks(tvLinkify, pattern1, "http://www.yangcheon.go.kr/site/yangcheon/05/10504010100002016081013.jsp",null, mTransform);

                    TextView tvLinkify2 = (TextView) yangcheon.findViewById(R.id.textView351) ;
                    Pattern pattern2 = Pattern.compile("[품목별 수수료 보기]");
                    Linkify.addLinks(tvLinkify2, pattern2, "http://www.yangcheon.go.kr/site/yangcheon/05/10504010200002016081013.jsp",null, mTransform);

                    Linkify.TransformFilter mTransform1 = new Linkify.TransformFilter() {
                        @Override
                        public String transformUrl(Matcher matcher, String s) {
                            return "http://www.yangcheon.go.kr/common/files/Download.do?cfIdx=CF00003808&cfGroup=COMMON&cfRename=2dc2dce8-68d4-4631-9ecc-9c8507872026.hwp";
                        }
                    };
                    TextView tvLinkify3 = (TextView) yangcheon.findViewById(R.id.textView446) ;
                    Pattern pattern3 = Pattern.compile("품목 다운로드");
                    Linkify.addLinks(tvLinkify3, pattern3, "",null, mTransform1);

                    TextView tvLinkify4 = (TextView) yangcheon.findViewById(R.id.textView435) ;
                    Pattern pattern4 = Pattern.compile("수거정보 바로가기");
                    Linkify.addLinks(tvLinkify4, pattern4, "http://www.yangcheon.go.kr/site/yangcheon/05/10504010100002016081013.jsp",null, mTransform);

                    TextView tvLinkify5 = (TextView) yangcheon.findViewById(R.id.textView447) ;
                    Pattern pattern5 = Pattern.compile("양천구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify5, pattern5, "http://www.yangcheon.go.kr",null, mTransform);

                } else if (spinner_selected_gu.equals("영등포구")) {
                    yeongdeungpo = (ScrollView) mInflater.inflate(R.layout.yeongdeungpo, mRootLinear, false);
                    mRootLinear.addView(yeongdeungpo);

                    TextView tvLinkify1 = (TextView) yeongdeungpo.findViewById(R.id.textView1) ;
                    Pattern pattern1 = Pattern.compile("수거 비용 검색 바로가기");
                    Linkify.addLinks(tvLinkify1, pattern1, "http://www.ydp.go.kr/waste/apply/main.do?op=section&mCode=b010000000&target=self&class1=01",null, mTransform);

                    TextView tvLinkify2 = (TextView) yeongdeungpo.findViewById(R.id.textView2) ;
                    Pattern pattern2 = Pattern.compile("영등포구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify2, pattern2, "http://www.ydp.go.kr/main/page.do?mCode=A030050020",null, mTransform);


                } else if (spinner_selected_gu.equals("용산구")) {
                    yongsan = (ScrollView) mInflater.inflate(R.layout.yongsan, mRootLinear, false);
                    mRootLinear.addView(yongsan);

                    TextView tvLinkify = (TextView) yongsan.findViewById(R.id.textView350) ;
                    Pattern pattern1 = Pattern.compile("[대형 폐기물 신청 바로가기]");
                    Linkify.addLinks(tvLinkify, pattern1, "http://clean.yongsan.go.kr/index",null, mTransform);

                    TextView tvLinkify2 = (TextView) yongsan.findViewById(R.id.textView351) ;
                    Pattern pattern2 = Pattern.compile("[품목별 수수료 보기]");
                    Linkify.addLinks(tvLinkify2, pattern2, "http://clean.yongsan.go.kr/cost",null, mTransform);

                    TextView tvLinkify3 = (TextView) yongsan.findViewById(R.id.textView457) ;
                    Pattern pattern3 = Pattern.compile("용산구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify3, pattern3, "http://www.yongsan.go.kr",null, mTransform);

                } else if (spinner_selected_gu.equals("은평구")) {
                    eunpyeong = (ScrollView) mInflater.inflate(R.layout.eunpyeong, mRootLinear, false);
                    mRootLinear.addView(eunpyeong);

                    TextView tvLinkify = (TextView) eunpyeong.findViewById(R.id.textView350) ;
                    Pattern pattern1 = Pattern.compile("[대형 폐기물 신청 바로가기]");
                    Linkify.addLinks(tvLinkify, pattern1, "http://ai-waste.ep.go.kr/",null, mTransform);

                    Linkify.TransformFilter mTransform1 = new Linkify.TransformFilter() {
                        @Override
                        public String transformUrl(Matcher matcher, String s) {
                            return "http://safecity.ep.go.kr/CmsWeb/viewPage.req?idx=PG0000003991&page=121";
                        }
                    };
                    TextView tvLinkify2 = (TextView) eunpyeong.findViewById(R.id.textView351) ;
                    Pattern pattern2 = Pattern.compile("[품목별 수수료 보기]");
                    Linkify.addLinks(tvLinkify2, pattern2, "",null, mTransform1);

                    TextView tvLinkify3 = (TextView) eunpyeong.findViewById(R.id.textView485) ;
                    Pattern pattern3 = Pattern.compile("은평구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify3, pattern3, "http://m.ep.go.kr/main.do",null, mTransform);

                } else if (spinner_selected_gu.equals("종로구")) {
                    jongno = (ScrollView) mInflater.inflate(R.layout.jongno, mRootLinear, false);
                    mRootLinear.addView(jongno);

                    Linkify.TransformFilter mTransform1 = new Linkify.TransformFilter() {
                        @Override
                        public String transformUrl(Matcher matcher, String s) {
                            return "http://www.jongno.go.kr/portal/garbage/selectWasteEmissionsList.do?menuId=110339&menuNo=110339";
                        }
                    };
                    TextView tvLinkify = (TextView) jongno.findViewById(R.id.textView488) ;
                    Pattern pattern = Pattern.compile("배출일시 검색 바로가기");
                    Linkify.addLinks(tvLinkify, pattern, "",null, mTransform1);

                    Linkify.TransformFilter mTransform2 = new Linkify.TransformFilter() {
                        @Override
                        public String transformUrl(Matcher matcher, String s) {
                            return "http://www.jongno.go.kr/Main.do;jsessionid=2Tm1Gc9M1WO8VmzNIvE05Cmiamp2O8OInacqXrjeF9RGQbGLQrJmeGq56m1mqRZ1.was_servlet_engine1?menuNo=1169";
                        }
                    };
                    TextView tvLinkify1 = (TextView) jongno.findViewById(R.id.textView350) ;
                    Pattern pattern1 = Pattern.compile("[대형 폐기물 신청 바로가기]");
                    Linkify.addLinks(tvLinkify1, pattern1, "",null, mTransform2);

                    Linkify.TransformFilter mTransform3 = new Linkify.TransformFilter() {
                        @Override
                        public String transformUrl(Matcher matcher, String s) {
                            return "http://www.jongno.go.kr/bigclean/bcExpense/bcExpense.jsp";
                        }
                    };
                    TextView tvLinkify2 = (TextView) jongno.findViewById(R.id.textView351) ;
                    Pattern pattern2 = Pattern.compile("[품목별 수수료 보기]");
                    Linkify.addLinks(tvLinkify2, pattern2, "",null, mTransform3);


                    TextView tvLinkify3 = (TextView) jongno.findViewById(R.id.textView507) ;
                    Pattern pattern3 = Pattern.compile("종로구청 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify3, pattern3, "http://www.jongno.go.kr",null, mTransform);



                } else if (spinner_selected_gu.equals("중구")) {
                    jung = (ScrollView) mInflater.inflate(R.layout.jung, mRootLinear, false);
                    mRootLinear.addView(jung);

                    TextView tvLinkify = (TextView) jung.findViewById(R.id.textView350) ;
                    Pattern pattern1 = Pattern.compile("[대형 폐기물 신청 바로가기]");
                    Linkify.addLinks(tvLinkify, pattern1, "http://www.junggu.seoul.kr/content.do?cmsid=11774",null, mTransform);

                    TextView tvLinkify2 = (TextView) jung.findViewById(R.id.textView351) ;
                    Pattern pattern2 = Pattern.compile("[품목별 수수료 보기]");
                    Linkify.addLinks(tvLinkify2, pattern2, "http://www.junggu.seoul.kr/content.do?cmsid=11772",null, mTransform);

                    TextView tvLinkify3 = (TextView) jung.findViewById(R.id.textView528) ;
                    Pattern pattern3 = Pattern.compile("중구 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify3, pattern3, "http://www.junggu.seoul.kr",null, mTransform);

                } else if (spinner_selected_gu.equals("중랑구")) {
                    jungnang = (ScrollView) mInflater.inflate(R.layout.jungnang, mRootLinear, false);
                    mRootLinear.addView(jungnang);

                    TextView tvLinkify = (TextView) jungnang.findViewById(R.id.textView350) ;
                    Pattern pattern1 = Pattern.compile("[대형 폐기물 신청 바로가기]");
                    Linkify.addLinks(tvLinkify, pattern1, "http://www.clean114.org/web/",null, mTransform);

                    TextView tvLinkify2 = (TextView) jungnang.findViewById(R.id.textView351) ;
                    Pattern pattern2 = Pattern.compile("[품목별 수수료 보기]");
                    Linkify.addLinks(tvLinkify2, pattern2, "http://www.clean114.org/web/price.php",null, mTransform);

                    TextView tvLinkify3 = (TextView) jungnang.findViewById(R.id.textView528) ;
                    Pattern pattern3 = Pattern.compile("중랑구 홈페이지 바로가기");
                    Linkify.addLinks(tvLinkify3, pattern3, "http://www.jungnang.go.kr/portal/main.do",null, mTransform);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("spinner_test", "not");
            }

        });
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_ACCESS_FINE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            isAccessFineLocation = true;

        } else if (requestCode == PERMISSIONS_ACCESS_COARSE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            isAccessCoarseLocation = true;
        }

        if (isAccessFineLocation && isAccessCoarseLocation) {
            isPermission = true;
        }
    }

    private void callPermission() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_ACCESS_FINE_LOCATION);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_ACCESS_COARSE_LOCATION);
        } else {
            isPermission = true;
        }
    }


    public void setAddresses() {
        geocoder = new Geocoder(getActivity(), Locale.KOREAN);
        Log.d("gpstest", "lat2 ->" + latitude);
        Log.d("gpstest", "lng2 ->" + longitude);
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if(addresses == null || addresses.size() == 0) {
                txtAds.setText("기기를 살짝 이동 후 버튼을 다시 눌러주세요.");
            } else {
                fulladdr = addresses.get(0).getAddressLine(0);
                txtAds.setText(fulladdr);
                Log.d("gpstest", "fulladdr : " + fulladdr);
                String[] words = fulladdr.split("\\s");
                Log.d("gpstest", "words -> " + words[2]);
                gu = words[2];
                Log.d("gpstest", "gu -> " + gu);
                button_gps.setText(gu + " 배출 정보");
                arrString = getResources().getStringArray(R.array.ward);
                guList = new ArrayList<String>();
                for (String s : arrString) {
                    guList.add(s);
                }
                if (guList.contains(gu)) {
                    spinner.setSelection(guList.indexOf(gu));
                } else {
                    spinner.setSelection(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class SlidingAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            page_gps.setVisibility(View.INVISIBLE);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }



}
