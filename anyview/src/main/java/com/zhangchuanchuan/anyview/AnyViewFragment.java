package com.zhangchuanchuan.anyview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class AnyViewFragment extends Fragment {

    protected RecyclerView recyclerView;
    protected AnyViewAdapter mAdapter;
    protected List<Object> mDataList = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getRecyclerView(view);
        buildData(mDataList);
        mAdapter = new AnyViewAdapter();
        mAdapter.setListData(mDataList);
        recyclerView.setLayoutManager(getLayoutManger());
        recyclerView.setAdapter(mAdapter);
    }

    public RecyclerView.LayoutManager getLayoutManger() {
        return new LinearLayoutManager(getContext());
    }

    public abstract RecyclerView getRecyclerView(View view);

    public abstract void buildData(List<Object> dataList);

}
