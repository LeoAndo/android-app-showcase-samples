package com.example.basicappsample;

import static com.example.basicappsample.helpers.ViewUtil.getViewTree;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public interface IButton {
    default void setOnClickListener(@NonNull final View vg, @Nullable View.OnClickListener listener) {
        final List<View> viewTree = getViewTree(vg);
        viewTree.stream().filter(view -> view instanceof Button).forEach(view -> ((Button) view).setOnClickListener(listener));
    }
}
