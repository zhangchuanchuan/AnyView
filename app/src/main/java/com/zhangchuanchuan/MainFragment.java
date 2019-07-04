package com.zhangchuanchuan;


import android.content.Intent;

import com.zhangchuanchuan.bean.TestVo;

import java.util.List;

public class MainFragment extends BaseAnyViewFragment {

    @Override
    public void buildData(List<Object> dataList) {
        dataList.add("你好啊");
        dataList.add("哈哈哈");
        dataList.add(new TestVo());
    }
}
