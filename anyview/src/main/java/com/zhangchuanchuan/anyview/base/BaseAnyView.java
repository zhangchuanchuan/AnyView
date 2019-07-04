package com.zhangchuanchuan.anyview.base;

import android.view.View;
import android.view.ViewGroup;

public abstract class BaseAnyView<T> {

    public abstract View onCreateView(ViewGroup group);

    public abstract void onBindView(T data);
}
