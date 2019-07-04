package com.zhangchuanchuan.anyview.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StringAnyView extends BaseAnyView<String> {
    TextView textView;
    @Override
    public View onCreateView(ViewGroup group) {
        textView = new TextView(group.getContext());
        return textView;
    }

    @Override
    public void onBindView(String data) {
        textView.setText(data);
    }
}
