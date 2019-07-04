package com.zhangchuanchuan.anyview.config;

import android.util.SparseArray;

import com.zhangchuanchuan.anyview.base.BaseAnyView;
import com.zhangchuanchuan.anyview.base.ErrorAnyView;
import com.zhangchuanchuan.anyview.base.StringAnyView;
import com.zhangchuanchuan.anyview.base.UnKnowAnyView;

import java.util.HashMap;
import java.util.Map;

public class ObjectTypeManager {

    private static Map<Class, Class<? extends BaseAnyView>> objectClassMap = new HashMap<>();
    private static SparseArray<Class<? extends BaseAnyView>> sparseArray = new SparseArray<>();

    static {
        objectClassMap.put(String.class, StringAnyView.class);
    }

    public static BaseAnyView getAnyViewByObject(Object object) {
        Class<? extends BaseAnyView> anyViewClass;
        if (object instanceof Integer) {
            anyViewClass = sparseArray.get((Integer) object);
        } else {
            anyViewClass = objectClassMap.get(object.getClass());
        }
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
