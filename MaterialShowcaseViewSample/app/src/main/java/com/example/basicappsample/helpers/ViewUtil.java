package com.example.basicappsample.helpers;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class ViewUtil {
    private ViewUtil() {
        throw new AssertionError();
    }

    public static List<View> getViewTree(@Nullable final View view) {
        List<View> views = new ArrayList<>();
        if ((view instanceof ViewGroup)) {
            int childNum = ((ViewGroup) view).getChildCount();
            for (int count = childNum; 0 <= count; count--) {
                View child = ((ViewGroup) view).getChildAt(count - 1);
                views.addAll(getViewTree(child));
            }
        }
        if (view != null) {
            views.add(view);
        }
        return views;
    }
}
