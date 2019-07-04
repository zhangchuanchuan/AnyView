package com.zhangchuanchuan.anyview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhangchuanchuan.anyview.base.BaseAnyView;

public class AnyViewHolder extends RecyclerView.ViewHolder {

    protected BaseAnyView anyView;

    public AnyViewHolder(@NonNull View itemView, BaseAnyView anyView) {
        super(itemView);
        this.anyView = anyView;
    }

}
