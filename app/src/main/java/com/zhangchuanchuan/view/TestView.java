package com.zhangchuanchuan.view;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangchuanchuan.SecondActivity;
import com.zhangchuanchuan.anyview.base.BaseAnyView;
import com.zhangchuanchuan.bean.TestVo;

public class TestView extends BaseAnyView<TestVo> {
    @Override
    public View onCreateView(ViewGroup group) {
        TextView textView = new TextView(group.getContext());
        textView.setText("我是测试TestView");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SecondActivity.class);
                startActivityForResult(intent, 0xff);
            }
        });
        return textView;
    }


    @Override
    public void onBindView(TestVo data) {

    }
}
