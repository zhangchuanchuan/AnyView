package com.zhangchuanchuan.anyview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.zhangchuanchuan.anyview.base.BaseAnyView;
import com.zhangchuanchuan.anyview.config.ObjectTypeManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnyViewAdapter extends RecyclerView.Adapter<AnyViewHolder> {

    private List<Object> listData;

    private IEventCallback mEventCallback;

    private AnyViewFragment mAnyViewFragment;

    private List<Class> viewTypeClassList = new ArrayList<>();

    public AnyViewAdapter(AnyViewFragment fragment) {
        this.mAnyViewFragment = fragment;
    }

    @NonNull
    @Override
    public AnyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        BaseAnyView anyViewByObject = ObjectTypeManager.getAnyViewByClass(viewTypeClassList.get(viewType));
        AnyViewHolder anyViewHolder = new AnyViewHolder(anyViewByObject.createView(viewGroup), anyViewByObject);
        anyViewByObject.init(this, mAnyViewFragment);
        return anyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AnyViewHolder anyViewHolder, int position) {
        anyViewHolder.anyView.onBindView(listData.get(position));
    }

    public void setListData(List<Object> listData) {
        this.listData = listData;
    }

    public void setEventCallback(IEventCallback eventCallback) {
        this.mEventCallback = eventCallback;
    }

    @Override
    public int getItemViewType(int position) {
        Class aClass = listData.get(position).getClass();
        if (!viewTypeClassList.contains(aClass)) {
            viewTypeClassList.add(aClass);
        }
        return viewTypeClassList.indexOf(aClass);
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    public void sendEvent(BaseAnyView itemView, int eventType, Object data) {
        if (mEventCallback != null) {
            mEventCallback.eventCallback(itemView, eventType, data);
        }
    }

    public interface IEventCallback {
        void eventCallback(BaseAnyView itemView, int eventType, Object data);
    }
}
