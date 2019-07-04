package com.zhangchuanchuan.anyview.base;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.zhangchuanchuan.anyview.AnyViewAdapter;
import com.zhangchuanchuan.anyview.AnyViewFragment;

public abstract class BaseAnyView<T> {
    private String TAG = this.getClass().getSimpleName();

    private View mView;
    private int mViewOriginHeight;
    private int mViewOriginWidth;

    protected AnyViewAdapter mAnyViewAdapter;
    protected AnyViewFragment mAnyViewFragment;

    public void init(AnyViewAdapter adapter, AnyViewFragment fragment) {
        this.mAnyViewAdapter = adapter;
        this.mAnyViewFragment = fragment;
    }

    public View createView(ViewGroup group) {
        mView = onCreateView(group);
        if (mView.getLayoutParams() == null) {
            mView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        mViewOriginHeight = mView.getLayoutParams().height;
        mViewOriginWidth = mView.getLayoutParams().width;
        return mView;
    }

    public abstract View onCreateView(ViewGroup group);

    public abstract void onBindView(T data);

    public Context getContext() {
        return mView.getContext();
    }

    /**
     * 数据交互用
     * @param eventType 事件类型，建议在自己的View定义
     * @param data 当前的数据。
     */
    public void sendEvent(int eventType, T data) {
        mAnyViewAdapter.sendEvent(this, eventType, data);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        mAnyViewFragment.startActivityForResult(this, intent, requestCode);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult：" + requestCode + ", " + resultCode + ", "  + data);
    }

    protected void setSelfVisible(boolean isVisible){
        if (isVisible) {
            mView.getLayoutParams().height = mViewOriginHeight;
            mView.getLayoutParams().width = mViewOriginWidth;
        } else {
            mView.getLayoutParams().height = 0;
            mView.getLayoutParams().width = 0;
        }
        mView.setLayoutParams(mView.getLayoutParams());
    }


}
