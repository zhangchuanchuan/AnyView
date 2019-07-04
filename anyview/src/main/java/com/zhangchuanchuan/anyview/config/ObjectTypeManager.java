package com.zhangchuanchuan.anyview.config;

import android.util.SparseArray;

import com.zhangchuanchuan.anyview.base.BaseAnyView;
import com.zhangchuanchuan.anyview.base.ErrorAnyView;
import com.zhangchuanchuan.anyview.base.StringAnyView;
import com.zhangchuanchuan.anyview.base.UnKnowAnyView;

import java.util.HashMap;
import java.util.Map;

public class ObjectTypeManager {

    public static Map<Class, Class<? extends BaseAnyView>> objectClassMap = new HashMap<>();

    static {
        objectClassMap.put(String.class, StringAnyView.class);
    }

    public static BaseAnyView getAnyViewByClass(Class clazz) {
        Class<? extends BaseAnyView> anyViewClass;
        anyViewClass = objectClassMap.get(clazz);
        if (anyViewClass == null) {
            anyViewClass = UnKnowAnyView.class;
        }
        try {
            return anyViewClass.newInstance();
        } catch (IllegalAccessException e) {
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return new ErrorAnyView();
    }
}
