package com.zhangchuanchuan.anyview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zhangchuanchuan.anyview.base.BaseAnyView;
import com.zhangchuanchuan.anyview.config.ObjectTypeManager;

import java.util.List;

public class AnyViewAdapter extends RecyclerView.Adapter<AnyViewHolder> {

    private List<Object> listData;

    @NonNull
    @Override
    public AnyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        BaseAnyView anyViewByObject = ObjectTypeManager.getAnyViewByObject(listData.get(i));
        return new AnyViewHolder(anyViewByObject.onCreateView(viewGroup), anyViewByObject);
    }

    @Override
    public void onBindViewHolder(@NonNull AnyViewHolder anyViewHolder, int i) {
        anyViewHolder.anyView.onBindView(listData.get(i));
    }

    public void setListData(List<Object> listData) {
        this.listData = listData;
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
