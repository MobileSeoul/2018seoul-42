package com.selecto.banana2;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import com.github.chrisbanes.photoview.PhotoView;
import java.util.ArrayList;

public class PageOneFragment extends Fragment implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    private SearchView search;
    private MyListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<Continent> continentList = new ArrayList<Continent>();

    View view;
    TextView textView;
    String nameMsg;

    public PageOneFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        } else {
            view = inflater.inflate(R.layout.fragment_page_one, container, false);
        }

        textView = view.findViewById(R.id.textView4);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setScrollbarFadingEnabled(false);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        search = view.findViewById(R.id.search);
        search.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);
        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    textView.setText("");
                } else {
                    collapseAll();
                }
            }
        });
        loadData();
        myList = view.findViewById(R.id.expandableList);
        listAdapter = new MyListAdapter(getActivity(), continentList);
        myList.setAdapter(listAdapter);
        myList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.d("tag","onChildClick() start");
                TextView name = v.findViewById(R.id.name);
                nameMsg = name.getText().toString();
                Log.d("tag", "nameMsg : " + nameMsg);
                Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.custom_dialog);
                PhotoView photoView = dialog.findViewById(R.id.childImage);
                photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);

                if (nameMsg.equals("· 썩은 채소류")) {
                    photoView.setImageResource(R.drawable.a01);
                } else if (nameMsg.equals("· 채소류 뿌리 또는 껍질")) {
                    photoView.setImageResource(R.drawable.a02);
                } else if (nameMsg.equals("· 파 뿌리")) {
                    photoView.setImageResource(R.drawable.a03);
                } else if (nameMsg.equals("· 미나리 뿌리")) {
                    photoView.setImageResource(R.drawable.a04);
                } else if (nameMsg.equals("· 마늘 껍질")) {
                    photoView.setImageResource(R.drawable.a05);
                } else if (nameMsg.equals("· 양파 껍질")) {
                    photoView.setImageResource(R.drawable.a06);
                } else if (nameMsg.equals("· 고추 씨")) {
                    photoView.setImageResource(R.drawable.a07);
                } else if (nameMsg.equals("· 고추대(대량)")) {
                    photoView.setImageResource(R.drawable.a08);
                } else if (nameMsg.equals("· 옥수수대")) {
                    photoView.setImageResource(R.drawable.a09);
                } else if (nameMsg.equals("· 과일류 껍질")) {
                    photoView.setImageResource(R.drawable.a10);
                } else if (nameMsg.equals("· 코코넛 껍질")) {
                    photoView.setImageResource(R.drawable.a11);
                } else if (nameMsg.equals("· 파인애플 껍질")) {
                    photoView.setImageResource(R.drawable.a12);
                } else if (nameMsg.equals("· 핵과류의 씨(과일 씨)")) {
                    photoView.setImageResource(R.drawable.a13);
                } else if (nameMsg.equals("· 복숭아 씨")) {
                    photoView.setImageResource(R.drawable.a14);
                } else if (nameMsg.equals("· 감 씨")) {
                    photoView.setImageResource(R.drawable.a15);
                } else if (nameMsg.equals("· 체리 씨")) {
                    photoView.setImageResource(R.drawable.a16);
                } else if (nameMsg.equals("· 망고 씨")) {
                    photoView.setImageResource(R.drawable.a17);
                } else if (nameMsg.equals("· 견과류 껍질")) {
                    photoView.setImageResource(R.drawable.a18);
                } else if (nameMsg.equals("· 호두 껍질")) {
                    photoView.setImageResource(R.drawable.a19);
                } else if (nameMsg.equals("· 밤 껍질")) {
                    photoView.setImageResource(R.drawable.a20);
                } else if (nameMsg.equals("· 땅콩 껍질")) {
                    photoView.setImageResource(R.drawable.a21);
                } else if (nameMsg.equals("· 곡류 껍질")) {
                    photoView.setImageResource(R.drawable.a22);
                } else if (nameMsg.equals("· 보리 겨(껍질)")) {
                    photoView.setImageResource(R.drawable.a23);
                } else if (nameMsg.equals("· 쌀 겨(껍질)")) {
                    photoView.setImageResource(R.drawable.a24);
                } else if (nameMsg.equals("· 콩 겨(껍질)")) {
                    photoView.setImageResource(R.drawable.a25);
                } else if (nameMsg.equals("· 육류 뼈")) {
                    photoView.setImageResource(R.drawable.a26);
                } else if (nameMsg.equals("· 육류 비계")) {
                    photoView.setImageResource(R.drawable.a27);
                } else if (nameMsg.equals("· 육류 내장")) {
                    photoView.setImageResource(R.drawable.a28);
                } else if (nameMsg.equals("· 소 뼈")) {
                    photoView.setImageResource(R.drawable.a29);
                } else if (nameMsg.equals("· 돼지 뼈")) {
                    photoView.setImageResource(R.drawable.a30);
                } else if (nameMsg.equals("· 닭 뼈")) {
                    photoView.setImageResource(R.drawable.a31);
                } else if (nameMsg.equals("· 어패류 껍데기")) {
                    photoView.setImageResource(R.drawable.a32);
                } else if (nameMsg.equals("· 조개 껍데기")) {
                    photoView.setImageResource(R.drawable.a33);
                } else if (nameMsg.equals("· 소라 껍데기")) {
                    photoView.setImageResource(R.drawable.a34);
                } else if (nameMsg.equals("· 전복 껍데기")) {
                    photoView.setImageResource(R.drawable.a35);
                } else if (nameMsg.equals("· 꼬막 껍데기")) {
                    photoView.setImageResource(R.drawable.a36);
                } else if (nameMsg.equals("· 굴 껍데기")) {
                    photoView.setImageResource(R.drawable.a37);
                } else if (nameMsg.equals("· 게 껍데기")) {
                    photoView.setImageResource(R.drawable.a38);
                } else if (nameMsg.equals("· 가재 껍데기")) {
                    photoView.setImageResource(R.drawable.a39);
                } else if (nameMsg.contains("· 생선 통가시")) {
                    photoView.setImageResource(R.drawable.a40);
                } else if (nameMsg.contains("· 복어 내장")) {
                    photoView.setImageResource(R.drawable.a41);
                } else if (nameMsg.equals("· 알 껍데기")) {
                    photoView.setImageResource(R.drawable.a42);
                } else if (nameMsg.equals("· 계란 껍데기")) {
                    photoView.setImageResource(R.drawable.a43);
                } else if (nameMsg.equals("· 메추리알 껍데기")) {
                    photoView.setImageResource(R.drawable.a44);
                } else if (nameMsg.equals("· 차 찌꺼기")) {
                    photoView.setImageResource(R.drawable.a45);
                } else if (nameMsg.equals("· 녹차 티백 찌꺼기")) {
                    photoView.setImageResource(R.drawable.a46);
                } else if (nameMsg.equals("· 보리차 티백 찌꺼기")) {
                    photoView.setImageResource(R.drawable.a47);
                } else if (nameMsg.equals("· 한약재 찌꺼기")) {
                    photoView.setImageResource(R.drawable.a48);
                } else if (nameMsg.equals("· 통짜 큰 과일")) {
                    photoView.setImageResource(R.drawable.a49);
                } else if (nameMsg.equals("· 통짜 큰 채소")) {
                    photoView.setImageResource(R.drawable.a50);
                } else if (nameMsg.equals("· 통 수박")) {
                    photoView.setImageResource(R.drawable.a51);
                } else if (nameMsg.equals("· 통 무")) {
                    photoView.setImageResource(R.drawable.a52);
                } else if (nameMsg.equals("· 통 호박")) {
                    photoView.setImageResource(R.drawable.a53);
                } else if (nameMsg.equals("· 통 배추")) {
                    photoView.setImageResource(R.drawable.a54);
                } else if (nameMsg.equals("· 장류")) {
                    photoView.setImageResource(R.drawable.a55);
                } else if (nameMsg.equals("· 된장")) {
                    photoView.setImageResource(R.drawable.a56);
                } else if (nameMsg.equals("· 고추장")) {
                    photoView.setImageResource(R.drawable.a57);
                } else if (nameMsg.equals("· 간장")) {
                    photoView.setImageResource(R.drawable.a58);
                } else if (nameMsg.equals("· 양념된 음식")) {
                    photoView.setImageResource(R.drawable.a59);
                } else if (nameMsg.equals("· 김치")) {
                    photoView.setImageResource(R.drawable.a60);
                } else if (nameMsg.equals("· 젓갈")) {
                    photoView.setImageResource(R.drawable.a61);
                } else if (nameMsg.equals("· 깐 마늘")) {
                    photoView.setImageResource(R.drawable.a62);
                } else if (nameMsg.equals("· 깐 양파")) {
                    photoView.setImageResource(R.drawable.a63);
                } else if (nameMsg.equals("· 고구마 껍질")) {
                    photoView.setImageResource(R.drawable.a64);
                } else if (nameMsg.equals("· 감자 껍질")) {
                    photoView.setImageResource(R.drawable.a65);
                } else if (nameMsg.equals("· 자른 수박 껍질")) {
                    photoView.setImageResource(R.drawable.a66);
                } else if (nameMsg.equals("· 귤 껍질")) {
                    photoView.setImageResource(R.drawable.a67);
                } else if (nameMsg.equals("· 바나나 껍질")) {
                    photoView.setImageResource(R.drawable.a68);
                } else if (nameMsg.equals("· 사과 껍질")) {
                    photoView.setImageResource(R.drawable.a70);
                } else if (nameMsg.equals("· 배 껍질")) {
                    photoView.setImageResource(R.drawable.a71);
                } else if (nameMsg.equals("· 오렌지 껍질")) {
                    photoView.setImageResource(R.drawable.a72);
                } else if (nameMsg.equals("· 레몬 껍질")) {
                    photoView.setImageResource(R.drawable.a73);
                } else if (nameMsg.equals("· 한라봉 껍질")) {
                    photoView.setImageResource(R.drawable.a74);
                } else if (nameMsg.equals("· 복숭아 껍질")) {
                    photoView.setImageResource(R.drawable.a75);
                } else if (nameMsg.equals("· 감 껍질")) {
                    photoView.setImageResource(R.drawable.a76);
                } else if (nameMsg.equals("· 키위 껍질")) {
                    photoView.setImageResource(R.drawable.a77);
                } else if (nameMsg.equals("· 포도 껍질")) {
                    photoView.setImageResource(R.drawable.a78);
                } else if (nameMsg.equals("· 당근 껍질")) {
                    photoView.setImageResource(R.drawable.a79);
                } else if (nameMsg.equals("· 참외 껍질")) {
                    photoView.setImageResource(R.drawable.a80);
                } else if (nameMsg.equals("· 사과 씨")) {
                    photoView.setImageResource(R.drawable.a81);
                } else if (nameMsg.equals("· 배 씨")) {
                    photoView.setImageResource(R.drawable.a82);
                } else if (nameMsg.equals("· 포도 씨")) {
                    photoView.setImageResource(R.drawable.a83);
                } else if (nameMsg.equals("· 신문지")) {
                    photoView.setImageResource(R.drawable.b01);
                } else if (nameMsg.equals("· 책자")) {
                    photoView.setImageResource(R.drawable.b02);
                } else if (nameMsg.equals("· 노트")) {
                    photoView.setImageResource(R.drawable.b03);
                } else if (nameMsg.equals("· 전단지")) {
                    photoView.setImageResource(R.drawable.b04);
                } else if (nameMsg.equals("· 쇼핑백")) {
                    photoView.setImageResource(R.drawable.b05);
                } else if (nameMsg.equals("· 달력")) {
                    photoView.setImageResource(R.drawable.b06);
                } else if (nameMsg.equals("· 포장지")) {
                    photoView.setImageResource(R.drawable.b07);
                } else if (nameMsg.equals("· 상자")) {
                    photoView.setImageResource(R.drawable.b08);
                } else if (nameMsg.equals("· 골판지 상자")) {
                    photoView.setImageResource(R.drawable.b09);
                } else if (nameMsg.equals("· 종이 조각")) {
                    photoView.setImageResource(R.drawable.b10);
                } else if (nameMsg.equals("· 영수증")) {
                    photoView.setImageResource(R.drawable.b11);
                } else if (nameMsg.equals("· 고지서")) {
                    photoView.setImageResource(R.drawable.b12);
                } else if (nameMsg.equals("· 핸드타월")) {
                    photoView.setImageResource(R.drawable.b13);
                } else if (nameMsg.equals("· 휴지")) {
                    photoView.setImageResource(R.drawable.b14);
                } else if (nameMsg.equals("· 광고지")) {
                    photoView.setImageResource(R.drawable.b15);
                } else if (nameMsg.equals("· 사진")) {
                    photoView.setImageResource(R.drawable.b16);
                } else if (nameMsg.equals("· 코팅된 종이")) {
                    photoView.setImageResource(R.drawable.b17);
                } else if (nameMsg.equals("· 스프링 제본된 책자류")) {
                    photoView.setImageResource(R.drawable.b18);
                } else if (nameMsg.equals("· 종이컵")) {
                    photoView.setImageResource(R.drawable.c1);
                } else if (nameMsg.equals("· 종이팩")) {
                    photoView.setImageResource(R.drawable.c2);
                } else if (nameMsg.equals("· 철 캔")) {
                    photoView.setImageResource(R.drawable.d1);
                } else if (nameMsg.equals("· 알루미늄 캔")) {
                    photoView.setImageResource(R.drawable.d2);
                } else if (nameMsg.equals("· 부탄가스 용기")) {
                    photoView.setImageResource(R.drawable.d3);
                } else if (nameMsg.equals("· 살충제 용기")) {
                    photoView.setImageResource(R.drawable.d4);
                } else if (nameMsg.equals("· 공구류")) {
                    photoView.setImageResource(R.drawable.d5);
                } else if (nameMsg.equals("· 철사")) {
                    photoView.setImageResource(R.drawable.d6);
                } else if (nameMsg.equals("· 못")) {
                    photoView.setImageResource(R.drawable.d7);
                } else if (nameMsg.equals("· 전선")) {
                    photoView.setImageResource(R.drawable.d8);
                } else if (nameMsg.equals("· 알루미늄 고철")) {
                    photoView.setImageResource(R.drawable.d9);
                } else if (nameMsg.equals("· 스텐 고철")) {
                    photoView.setImageResource(R.drawable.d10);
                } else if (nameMsg.equals("· 우산")) {
                    photoView.setImageResource(R.drawable.d11);
                } else if (nameMsg.equals("· 음료수 병")) {
                    photoView.setImageResource(R.drawable.e1);
                } else if (nameMsg.equals("· 소주 병")) {
                    photoView.setImageResource(R.drawable.e2);
                } else if (nameMsg.equals("· 맥주 병")) {
                    photoView.setImageResource(R.drawable.e3);
                } else if (nameMsg.equals("· 기타 병류")) {
                    photoView.setImageResource(R.drawable.e4);
                } else if (nameMsg.equals("· 깨진 유리")) {
                    photoView.setImageResource(R.drawable.e5);
                } else if (nameMsg.equals("· 유리잔")) {
                    photoView.setImageResource(R.drawable.e6);
                } else if (nameMsg.equals("· 맥주컵")) {
                    photoView.setImageResource(R.drawable.e7);
                } else if (nameMsg.equals("· 내열 유리 용기(밀폐 용기)")) {
                    photoView.setImageResource(R.drawable.e8);
                } else if (nameMsg.equals("· 내열 유리 냄비")) {
                    photoView.setImageResource(R.drawable.e9);
                } else if (nameMsg.equals("· 페트병")) {
                    photoView.setImageResource(R.drawable.f1);
                } else if (nameMsg.equals("· 플라스틱 용기류")) {
                    photoView.setImageResource(R.drawable.f2);
                } else if (nameMsg.equals("· 폐스티로폼")) {
                    photoView.setImageResource(R.drawable.f3);
                } else if (nameMsg.equals("· 고무대야")) {
                    photoView.setImageResource(R.drawable.f4);
                } else if (nameMsg.equals("· CD(콤팩트 디스크)")) {
                    photoView.setImageResource(R.drawable.f5);
                } else if (nameMsg.equals("· 비디오 테이프")) {
                    photoView.setImageResource(R.drawable.f6);
                } else if (nameMsg.equals("· 오디오 테이프")) {
                    photoView.setImageResource(R.drawable.f7);
                } else if (nameMsg.equals("· 화분")) {
                    photoView.setImageResource(R.drawable.f8);
                } else if (nameMsg.equals("· 유아용 볼풀공")) {
                    photoView.setImageResource(R.drawable.f9);
                } else if (nameMsg.equals("· 완구류")) {
                    photoView.setImageResource(R.drawable.f10);
                } else if (nameMsg.equals("· 유모차")) {
                    photoView.setImageResource(R.drawable.f11);
                } else if (nameMsg.equals("· 유아용 그네")) {
                    photoView.setImageResource(R.drawable.f12);
                } else if (nameMsg.equals("· 유아용 자동차")) {
                    photoView.setImageResource(R.drawable.f13);
                } else if (nameMsg.equals("· 유아용 목마")) {
                    photoView.setImageResource(R.drawable.f14);
                } else if (nameMsg.equals("· 송곳")) {
                    photoView.setImageResource(R.drawable.f15);
                } else if (nameMsg.equals("· 노끈")) {
                    photoView.setImageResource(R.drawable.f16);
                } else if (nameMsg.equals("· 알약 포장재")) {
                    photoView.setImageResource(R.drawable.f17);
                } else if (nameMsg.equals("· 부서진 스티로폼 조각")) {
                    photoView.setImageResource(R.drawable.f18);
                } else if (nameMsg.equals("· 1회용 그릇")) {
                    photoView.setImageResource(R.drawable.f19);
                } else if (nameMsg.equals("· 면도기")) {
                    photoView.setImageResource(R.drawable.f20);
                } else if (nameMsg.equals("· 칫솔")) {
                    photoView.setImageResource(R.drawable.f21);
                } else if (nameMsg.equals("· 컵라면 용기")) {
                    photoView.setImageResource(R.drawable.f22);
                } else if (nameMsg.equals("· 1회용 그릇")) {
                    photoView.setImageResource(R.drawable.f19);
                } else if (nameMsg.equals("· 과자 봉지")) {
                    photoView.setImageResource(R.drawable.g1);
                } else if (nameMsg.equals("· 라면 봉지")) {
                    photoView.setImageResource(R.drawable.g2);
                } else if (nameMsg.equals("· 빵 봉지")) {
                    photoView.setImageResource(R.drawable.g3);
                } else if (nameMsg.equals("· 1회용 비닐 봉투")) {
                    photoView.setImageResource(R.drawable.g4);
                } else if (nameMsg.equals("· 각종 비닐류")) {
                    photoView.setImageResource(R.drawable.g5);
                } else if (nameMsg.contains("· 뽁뽁이")) {
                    photoView.setImageResource(R.drawable.g6);
                } else if (nameMsg.equals("· 형광등")) {
                    photoView.setImageResource(R.drawable.h1);
                } else if (nameMsg.equals("· 깨진 형광등")) {
                    photoView.setImageResource(R.drawable.h2);
                } else if (nameMsg.equals("· 백열전구")) {
                    photoView.setImageResource(R.drawable.h3);
                } else if (nameMsg.equals("· LED 전구")) {
                    photoView.setImageResource(R.drawable.h4);
                } else if (nameMsg.equals("· 건전지")) {
                    photoView.setImageResource(R.drawable.i1);
                } else if (nameMsg.equals("· 충전용 전지")) {
                    photoView.setImageResource(R.drawable.i2);
                } else if (nameMsg.equals("· 헌옷")) {
                    photoView.setImageResource(R.drawable.j1);
                } else if (nameMsg.equals("· 신발")) {
                    photoView.setImageResource(R.drawable.j2);
                } else if (nameMsg.equals("· 가방")) {
                    photoView.setImageResource(R.drawable.j3);
                } else if (nameMsg.equals("· 담요")) {
                    photoView.setImageResource(R.drawable.j4);
                } else if (nameMsg.equals("· 누비 이불")) {
                    photoView.setImageResource(R.drawable.j5);
                } else if (nameMsg.equals("· 커튼")) {
                    photoView.setImageResource(R.drawable.j6);
                } else if (nameMsg.equals("· 카페트")) {
                    photoView.setImageResource(R.drawable.j7);
                } else if (nameMsg.equals("· 여행용 가방")) {
                    photoView.setImageResource(R.drawable.j8);
                } else if (nameMsg.equals("· 베개")) {
                    photoView.setImageResource(R.drawable.j10);
                } else if (nameMsg.equals("· 방석")) {
                    photoView.setImageResource(R.drawable.j11);
                } else if (nameMsg.equals("· 솜이불")) {
                    photoView.setImageResource(R.drawable.j9);
                } else if (nameMsg.equals("· 걸레")) {
                    photoView.setImageResource(R.drawable.j12);
                } else if (nameMsg.equals("· 수건")) {
                    photoView.setImageResource(R.drawable.j13);
                } else if (nameMsg.contains("· 소형 폐가전")) {
                    photoView.setImageResource(R.drawable.k1);
                } else if (nameMsg.equals("· 컴퓨터")) {
                    photoView.setImageResource(R.drawable.k2);
                } else if (nameMsg.equals("· 모니터")) {
                    photoView.setImageResource(R.drawable.k12);
                } else if (nameMsg.equals("· 노트북")) {
                    photoView.setImageResource(R.drawable.k13);
                } else if (nameMsg.equals("· 전기 밥솥")) {
                    photoView.setImageResource(R.drawable.k3);
                } else if (nameMsg.equals("· 선풍기")) {
                    photoView.setImageResource(R.drawable.k4);
                } else if (nameMsg.contains("· 대형 폐가전")) {
                    photoView.setImageResource(R.drawable.k5);
                } else if (nameMsg.equals("· 세탁기")) {
                    photoView.setImageResource(R.drawable.k6);
                } else if (nameMsg.equals("· 에어컨")) {
                    photoView.setImageResource(R.drawable.k7);
                } else if (nameMsg.equals("· 냉장고")) {
                    photoView.setImageResource(R.drawable.k8);
                } else if (nameMsg.equals("· TV")) {
                    photoView.setImageResource(R.drawable.k9);
                } else if (nameMsg.equals("· 전기 담요")) {
                    photoView.setImageResource(R.drawable.k10);
                } else if (nameMsg.equals("· 전기 방석")) {
                    photoView.setImageResource(R.drawable.k11);
                } else if (nameMsg.equals("· 폐식용유")) {
                    photoView.setImageResource(R.drawable.l1);
                } else if (nameMsg.equals("· 폐목재류")) {
                    photoView.setImageResource(R.drawable.m1);
                } else if (nameMsg.equals("· 아이스팩")) {
                    photoView.setImageResource(R.drawable.m2);
                } else if (nameMsg.equals("· 연탄재")) {
                    photoView.setImageResource(R.drawable.m3);
                } else if (nameMsg.equals("· 페인트통")) {
                    photoView.setImageResource(R.drawable.m4);
                } else if (nameMsg.equals("· 양초")) {
                    photoView.setImageResource(R.drawable.m5);
                } else if (nameMsg.equals("· 스펀지")) {
                    photoView.setImageResource(R.drawable.m6);
                } else if (nameMsg.equals("· 폐의약품")) {
                    photoView.setImageResource(R.drawable.m7);
                } else if (nameMsg.equals("· 고무장갑")) {
                    photoView.setImageResource(R.drawable.m8);
                } else if (nameMsg.equals("· 슬리퍼")) {
                    photoView.setImageResource(R.drawable.m9);
                } else if (nameMsg.equals("· 음식물류 분리배출요령")) {
                    photoView.setImageResource(R.drawable.a0);
                } else if (nameMsg.equals("· 종이류 분리배출요령")) {
                    photoView.setImageResource(R.drawable.b0);
                } else if (nameMsg.equals("· 종이컵/종이팩 분리배출요령")) {
                    photoView.setImageResource(R.drawable.c0);
                } else if (nameMsg.equals("· 캔류/고철류 분리배출요령")) {
                    photoView.setImageResource(R.drawable.d0);
                } else if (nameMsg.equals("· 유리병류 분리배출요령")) {
                    photoView.setImageResource(R.drawable.e0);
                } else if (nameMsg.equals("· 플라스틱류 분리배출요령")) {
                    photoView.setImageResource(R.drawable.f0);
                } else if (nameMsg.equals("· 비닐류 분리배출요령")) {
                    photoView.setImageResource(R.drawable.g0);
                } else if (nameMsg.equals("· 형광등 분리배출요령")) {
                    photoView.setImageResource(R.drawable.h0);
                } else if (nameMsg.equals("· 폐전지 분리배출요령")) {
                    photoView.setImageResource(R.drawable.i0);
                } else if (nameMsg.equals("· 섬유류 분리배출요령")) {
                    photoView.setImageResource(R.drawable.j0);
                } else if (nameMsg.equals("· 폐전자제품 분리배출요령")) {
                    photoView.setImageResource(R.drawable.k0);
                } else if (nameMsg.equals("· 폐식용유 분리배출요령")) {
                    photoView.setImageResource(R.drawable.l0);
                }

                dialog.show();
                Display display = getActivity().getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                Window window = dialog.getWindow();
                int x = (int) (size.x * 0.85f);
                int y = (int) (size.y * 0.5f);
                window.setLayout(x, y);

                return false;
            }
        });
        return view;

    }

    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            myList.expandGroup(i);
        }
        Log.d("tag", "expandAll()");
    }

    public void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            myList.collapseGroup(i);
        }
        Log.d("tag", "collapseAll()");
    }

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        Log.d("tag", "onClose()");
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        if (query.length() > 0) {
            listAdapter.filterData(query);
            expandAll();
        } else if (query.length() == 0) {
            listAdapter.filterData(query);
            collapseAll();
        }
        Log.d("tag", "onQueryTextChange()");
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (query.length() > 0) {
            listAdapter.filterData(query);
            expandAll();
        }
        Log.d("tag", "onQueryTextSubmit()");
        return false;
    }

    public void loadData() {
        ArrayList<Country> countryList;
        Country country;
        Continent continent;

        countryList = new ArrayList<Country>();
        country = new Country("", "· 음식물류 분리배출요령", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 썩은 채소류", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 채소류 뿌리 또는 껍질", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 파 뿌리", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 미나리 뿌리", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 고구마 껍질", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 감자 껍질", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 당근 껍질", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 깐 마늘", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 깐 양파", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 마늘 껍질", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 양파 껍질", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 고추 씨", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 고추대(대량)", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 옥수수대", "");
        countryList.add(country);
        country = new Country("음식물쓰레기/일반종량제봉투", "· 과일류 껍질", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 사과 껍질", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 배 껍질", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 오렌지 껍질", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 자른 수박 껍질", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 귤 껍질", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 바나나 껍질", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 레몬 껍질", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 한라봉 껍질", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 복숭아 껍질", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 감 껍질", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 키위 껍질", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 포도 껍질", "");
        countryList.add(country);
        country = new Country("음식물쓰레기", "· 참외 껍질", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 코코넛 껍질", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 파인애플 껍질", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 핵과류의 씨(과일 씨)", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 사과 씨", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 배 씨", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 포도 씨", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 복숭아 씨", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 감 씨", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 체리 씨", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 망고 씨", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 통짜 큰 과일", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 통짜 큰 채소", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 통 수박", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 통 무", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 통 호박", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 통 배추", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 견과류 껍질", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 호두 껍질", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 밤 껍질", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 땅콩 껍질", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 곡류 껍질", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 보리 겨(껍질)", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 쌀 겨(껍질)", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 콩 겨(껍질)", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 육류 뼈", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 육류 비계", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 육류 내장", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 소 뼈", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 돼지 뼈", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 닭 뼈", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 어패류 껍데기", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 조개 껍데기", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 소라 껍데기", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 전복 껍데기", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 꼬막 껍데기", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 굴 껍데기", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 게 껍데기", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 가재 껍데기", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 생선 통가시", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 복어 내장", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 알 껍데기", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 계란 껍데기", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 메추리알 껍데기", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 차 찌꺼기", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 녹차 티백 찌꺼기", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 보리차 티백 찌꺼기", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 한약재 찌꺼기", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 장류", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 된장", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 고추장", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 간장", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 양념된 음식", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 김치", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 젓갈", "");
        countryList.add(country);


        continent = new Continent("음식물류", "", countryList);
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("", "· 종이류 분리배출요령", "");
        countryList.add(country);
        country = new Country("종이류","· 신문지", "");
        countryList.add(country);
        country = new Country("종이류","· 책자", "");
        countryList.add(country);
        country = new Country("종이류","· 노트", "");
        countryList.add(country);
        country = new Country("종이류","· 전단지", "");
        countryList.add(country);
        country = new Country("종이류","· 쇼핑백", "");
        countryList.add(country);
        country = new Country("종이류","· 달력", "");
        countryList.add(country);
        country = new Country("종이류","· 포장지", "");
        countryList.add(country);
        country = new Country("종이류","· 상자", "");
        countryList.add(country);
        country = new Country("종이류","· 골판지 상자", "");
        countryList.add(country);
        country = new Country("종이류","· 종이 조각", "");
        countryList.add(country);
        country = new Country("종이류","· 영수증", "");
        countryList.add(country);
        country = new Country("종이류","· 고지서", "");
        countryList.add(country);
        country = new Country("일반종량제봉투/종이류","· 핸드타월", "");
        countryList.add(country);
        country = new Country("일반종량제봉투","· 휴지", "");
        countryList.add(country);
        country = new Country("일반종량제봉투","· 광고지", "");
        countryList.add(country);
        country = new Country("일반종량제봉투","· 사진", "");
        countryList.add(country);
        country = new Country("일반종량제봉투","· 코팅된 종이", "");
        countryList.add(country);
        country = new Country("종이류","· 스프링 제본된 책자류", "");
        countryList.add(country);
        continent = new Continent("종이류", "", countryList);
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("", "· 종이컵/종이팩 분리배출요령", "");
        countryList.add(country);
        country = new Country("종이팩","· 종이컵", "");
        countryList.add(country);
        country = new Country("종이팩","· 종이팩", "");
        countryList.add(country);
        continent = new Continent("종이컵 · 종이팩", "", countryList);
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("", "· 캔류/고철류 분리배출요령", "");
        countryList.add(country);
        country = new Country("캔류","· 철 캔", "");
        countryList.add(country);
        country = new Country("캔류","· 알루미늄 캔", "");
        countryList.add(country);
        country = new Country("캔류","· 부탄가스 용기", "");
        countryList.add(country);
        country = new Country("캔류","· 살충제 용기", "");
        countryList.add(country);
        country = new Country("고철류","· 공구류", "");
        countryList.add(country);
        country = new Country("고철류","· 철사", "");
        countryList.add(country);
        country = new Country("고철류","· 못", "");
        countryList.add(country);
        country = new Country("고철류","· 전선", "");
        countryList.add(country);
        country = new Country("고철류","· 알루미늄 고철", "");
        countryList.add(country);
        country = new Country("고철류","· 스텐 고철", "");
        countryList.add(country);
        country = new Country("일반종량제봉투/고철류","· 우산", "");
        countryList.add(country);
        continent = new Continent("캔류 · 고철류", "", countryList);
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("", "· 유리병류 분리배출요령", "");
        countryList.add(country);
        country = new Country("유리병류","· 음료수 병", "");
        countryList.add(country);
        country = new Country("유리병류","· 소주 병", "");
        countryList.add(country);
        country = new Country("유리병류","· 맥주 병", "");
        countryList.add(country);
        country = new Country("유리병류","· 기타 병류", "");
        countryList.add(country);
        country = new Country("일반종량제봉투","· 깨진 유리", "");
        countryList.add(country);
        country = new Country("유리병류","· 유리잔", "");
        countryList.add(country);
        country = new Country("유리병류","· 맥주컵", "");
        countryList.add(country);
        country = new Country("특수규격마대","· 내열 유리 용기(밀폐 용기)", "");
        countryList.add(country);
        country = new Country("특수규격마대","· 내열 유리 냄비", "");
        countryList.add(country);
        continent = new Continent("유리병류", "", countryList);
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("", "· 플라스틱류 분리배출요령", "");
        countryList.add(country);
        country = new Country("플라스틱류", "· 페트병", "");
        countryList.add(country);
        country = new Country("플라스틱류", "· 플라스틱 용기류", "");
        countryList.add(country);
        country = new Country("일반종량제봉투/플라스틱류", "· 폐스티로폼", "");
        countryList.add(country);
        country = new Country("일반종량제봉투/대형폐기물", "· 고무대야", "");
        countryList.add(country);
        country = new Country("플라스틱류", "· CD(콤팩트 디스크)", "");
        countryList.add(country);
        country = new Country("일반종량제봉투/플라스틱류", "· 비디오 테이프", "");
        countryList.add(country);
        country = new Country("일반종량제봉투/플라스틱류", "· 오디오 테이프", "");
        countryList.add(country);
        country = new Country("일반종량제봉투/대형폐기물", "· 화분", "");
        countryList.add(country);
        country = new Country("플라스틱류", "· 유아용 볼풀공", "");
        countryList.add(country);
        country = new Country("재질별배출", "· 완구류", "");
        countryList.add(country);
        country = new Country("대형폐기물", "· 유모차", "");
        countryList.add(country);
        country = new Country("대형폐기물", "· 유아용 그네", "");
        countryList.add(country);
        country = new Country("대형폐기물", "· 유아용 자동차", "");
        countryList.add(country);
        country = new Country("대형폐기물", "· 유아용 목마", "");
        countryList.add(country);
        country = new Country("고철류/플라스틱류", "· 송곳", "");
        countryList.add(country);
        country = new Country("비닐류/플라스틱류", "· 노끈", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 알약 포장재", "");
        countryList.add(country);
        country = new Country("플라스틱류", "· 부서진 스티로폼 조각", "");
        countryList.add(country);
        country = new Country("재질별배출", "· 1회용 그릇", "");
        countryList.add(country);
        country = new Country("일반종량제봉투/플라스틱류", "· 면도기", "");
        countryList.add(country);
        country = new Country("일반종량제봉투/플라스틱류", "· 칫솔", "");
        countryList.add(country);
        country = new Country("플라스틱류", "· 컵라면 용기", "");
        countryList.add(country);
        continent = new Continent("플라스틱류", "", countryList);
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("", "· 비닐류 분리배출요령", "");
        countryList.add(country);
        country = new Country("비닐류", "· 과자 봉지", "");
        countryList.add(country);
        country = new Country("비닐류", "· 라면 봉지", "");
        countryList.add(country);
        country = new Country("비닐류", "· 빵 봉지", "");
        countryList.add(country);
        country = new Country("비닐류", "· 1회용 비닐 봉투", "");
        countryList.add(country);
        country = new Country("비닐류", "· 각종 비닐류", "");
        countryList.add(country);
        country = new Country("비닐류", "· 뽁뽁이(버블랩/에어캡)", "");
        countryList.add(country);
        continent = new Continent("비닐류", "", countryList);
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("", "· 형광등 분리배출요령", "");
        countryList.add(country);
        country = new Country("전용수거함", "· 형광등", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 깨진 형광등", "");
        countryList.add(country);
        country = new Country("일반종량제봉투/특수규격마대", "· 백열전구", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· LED 전구", "");
        countryList.add(country);
        continent = new Continent("형광등", "", countryList);
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("", "· 폐전지 분리배출요령", "");
        countryList.add(country);
        country = new Country("전용수거함", "· 건전지", "");
        countryList.add(country);
        country = new Country("전용수거함", "· 충전용 전지", "");
        countryList.add(country);
        continent = new Continent("폐전지", "", countryList);
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("", "· 섬유류 분리배출요령", "");
        countryList.add(country);
        country = new Country("의류수거함", "· 헌옷", "");
        countryList.add(country);
        country = new Country("의류수거함", "· 신발", "");
        countryList.add(country);
        country = new Country("의류수거함", "· 가방", "");
        countryList.add(country);
        country = new Country("의류수거함", "· 담요", "");
        countryList.add(country);
        country = new Country("의류수거함", "· 누비 이불", "");
        countryList.add(country);
        country = new Country("의류수거함", "· 커튼", "");
        countryList.add(country);
        country = new Country("의류수거함", "· 카페트", "");
        countryList.add(country);
        country = new Country("대형폐기물", "· 여행용 가방", "");
        countryList.add(country);
        country = new Country("대형폐기물", "· 솜이불", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 베개", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 방석", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 걸레", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 수건", "");
        countryList.add(country);
        continent = new Continent("섬유류", "", countryList);
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("", "· 폐전자제품 분리배출요령", "");
        countryList.add(country);
        country = new Country("전용수거함", "· 소형 폐가전(1m 미만 가전 제품)", "");
        countryList.add(country);
        country = new Country("전용수거함", "· 컴퓨터", "");
        countryList.add(country);
        country = new Country("전용수거함", "· 모니터", "");
        countryList.add(country);
        country = new Country("전용수거함", "· 노트북", "");
        countryList.add(country);
        country = new Country("전용수거함", "· 전기 밥솥", "");
        countryList.add(country);
        country = new Country("전용수거함", "· 선풍기", "");
        countryList.add(country);
        country = new Country("방문수거", "· 대형 폐가전(1m 이상 가전 제품)", "");
        countryList.add(country);
        country = new Country("방문수거", "· 세탁기", "");
        countryList.add(country);
        country = new Country("방문수거", "· 에어컨", "");
        countryList.add(country);
        country = new Country("방문수거", "· 냉장고", "");
        countryList.add(country);
        country = new Country("방문수거", "· TV", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 전기 담요", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 전기 방석", "");
        countryList.add(country);
        continent = new Continent("폐전자제품", "", countryList);
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("", "· 폐식용유 분리배출요령", "");
        countryList.add(country);
        country = new Country("폐유수거함", "· 폐식용유", "");
        countryList.add(country);
        continent = new Continent("폐식용유", "", countryList);
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("일반종량제봉투", "· 폐목재류", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 아이스팩", "");
        countryList.add(country);
        country = new Country("투명비닐봉투", "· 연탄재", "");
        countryList.add(country);
        country = new Country("특수규격마대", "· 페인트통", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 양초", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 스펀지", "");
        countryList.add(country);
        country = new Country("약국수거함", "· 폐의약품", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 고무장갑", "");
        countryList.add(country);
        country = new Country("일반종량제봉투", "· 슬리퍼", "");
        countryList.add(country);
        continent = new Continent("기타", "", countryList);
        continentList.add(continent);
    }
}
