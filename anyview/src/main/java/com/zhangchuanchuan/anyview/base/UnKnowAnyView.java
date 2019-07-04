package com.zhangchuanchuan.anyview.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UnKnowAnyView extends BaseAnyView {
    @Override
    public View onCreateView(ViewGroup group) {
        TextView textView = new TextView(group.getContext());
        textView.setText("未知在类型，需要升级才能查看");
        return textView;
    }

    @Override
    public void onBindView(Object data) {

    }
}
