package com.selecto.banana2;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class noticeDialog extends Dialog {
    TextView title;
    TextView content;
    CheckBox checkBox;
    TextView confirm;

    public noticeDialog(Context context) {
        super(context);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.notice_dialog);
        title = (TextView) findViewById(R.id.textView5);
        content = (TextView) findViewById(R.id.textView6);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        confirm = (TextView) findViewById(R.id.textView7);

        title.setText("세계 제일의 재활용도시 서울");
        title.setPadding(10, 20, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextSize(30);

        //content.setText(R.string.notice);
        content.setText("오늘날 인류는 역사상 유래 없는 물질적인 풍요를 누리고 있습니다. 하지만 우리가 이룩한 고도성장의 영광 뒤엔 환경파괴는 그림자처럼 따라왔고, 이제는 환경을 생각하지 않고서는 더 이상의 성장은 기대할 수 없는 상황입니다. 앞으로는 우리는 매립되는 폐기물을 더욱 줄여, 궁극적으로는 매립쓰레기를 제로(Zero)화 해야 하는 과제를 안고 있습니다.\n" +
                "\n" +
                "서울시는 1천만 인구가 집중된 대도시로서 여기서 소비하는 에너지와 발생하는 폐기물은 어마어마하고, 자원의 대부분은 수입에 의존하고 있습니다. 매립쓰레기 제로화와 자원선순환 도시 구현이라는 시대적 사명을 다하기 위해서는 재활용 가능 자원을 원천 분리 · 배출하는 것이 하나의 방법입니다. 재활용 분리배출은 시민들의 솔선참여가 꼭 필요합니다.\n" +
                "\n" +
                "본 '서울시 분리배출 길라잡이'는 재활용품 분리배출 기준에 대해 궁금했던 부분을 검색하여 분리배출 방법을 쉽게 알 수 있도록 제작되었습니다. 본 어플이 재활용품 분리배출에 대한 통일성 있는 기준으로 시민들이 분리배출을 실천하는 데에 조금이나마 기여하기를 기대합니다.");
        content.setMovementMethod(new ScrollingMovementMethod());
        content.setPadding(10, 20, 10, 10);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    dismiss();
                }
            }
        });

    }
}
