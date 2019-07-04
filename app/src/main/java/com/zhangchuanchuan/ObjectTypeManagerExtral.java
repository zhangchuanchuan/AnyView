package com.zhangchuanchuan;

import com.zhangchuanchuan.anyview.config.ObjectTypeManager;
import com.zhangchuanchuan.bean.TestVo;
import com.zhangchuanchuan.view.TestView;

public class ObjectTypeManagerExtral {
    public static void init() {
        ObjectTypeManager.objectClassMap.put(TestVo.class, TestView.class);
    }
}
