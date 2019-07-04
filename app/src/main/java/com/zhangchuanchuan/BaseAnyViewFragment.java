package com.zhangchuanchuan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zhangchuanchuan.anyview.AnyViewFragment;


public abstract class BaseAnyViewFragment extends AnyViewFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_any_view, container, false);
    }

    @Override
    public RecyclerView getRecyclerView(View view) {
        return view.findViewById(R.id.rv_base_recycler_view);
    }
}
