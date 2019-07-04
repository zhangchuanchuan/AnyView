package com.zhangchuanchuan.anyview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.zhangchuanchuan.anyview.base.BaseAnyView;

import java.util.ArrayList;
import java.util.List;

public abstract class AnyViewFragment extends Fragment implements AnyViewAdapter.IEventCallback {
    private String TAG = "AnyViewFragment";
    protected RecyclerView recyclerView;
    protected AnyViewAdapter mAdapter;
    protected List<Object> mDataList = new ArrayList<>();
    private SparseArray<BaseAnyView> mRequestItemViews;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getRecyclerView(view);
        buildData(mDataList);
        mAdapter = new AnyViewAdapter(this);
        mAdapter.setEventCallback(this);
        mAdapter.setListData(mDataList);
        recyclerView.setLayoutManager(getLayoutManger());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult" + requestCode + ", " + resultCode + "," + data);
        BaseAnyView requestItem = getRequestItem(requestCode);
        if (requestItem != null) {
            requestItem.onActivityResult(requestCode & 0xff, resultCode, data);
        }
    }

    public void startActivityForResult(BaseAnyView itemView, Intent intent, int requestCode) {
        //support包下，最大的int为0xffff，item的最大int为0xff 最大为低16位。
        //这里占用高四位ff，255来决定调用的来源,需要从1开始
        if (requestCode > 0xff) {
            throw new RuntimeException("item的request code不能大于0xff");
        }
        int itemRequestCode = getRequestCode(itemView, requestCode);
        super.startActivityForResult(intent, itemRequestCode);
    }

    private int getRequestCode(BaseAnyView itemView, int requestCode) {
        if (mRequestItemViews == null) {
            mRequestItemViews = new SparseArray<>();
        }
        if (mRequestItemViews.size() > 0xfe) {
            throw new RuntimeException("ItemView startActivityForResult 超过了最大的请求数据");
        }
        int key = -1;
        for (int i = 1; i < 0xff; i++) {
            if (mRequestItemViews.get(i) == null) {
                mRequestItemViews.put(i, itemView);
                key = i;
                break;
            }
        }
        return requestCode | (key << 8);
    }

    private BaseAnyView getRequestItem(int requestCode) {
        if (mRequestItemViews != null) {
            int key = requestCode >> 8;
            return mRequestItemViews.get(key);
        }
        return null;
    }

    public RecyclerView.LayoutManager getLayoutManger() {
        return new LinearLayoutManager(getContext());
    }

    public abstract RecyclerView getRecyclerView(View view);

    public abstract void buildData(List<Object> dataList);

    @Override
    public void eventCallback(BaseAnyView itemView, int eventType, Object data) {
    }
}
