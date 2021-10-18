package com.example.basicappsample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basicappsample.databinding.ActivityMainBinding;

import smartdevelop.ir.eram.showcaseviewlib.GuideView;
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType;
import smartdevelop.ir.eram.showcaseviewlib.config.Gravity;
import smartdevelop.ir.eram.showcaseviewlib.config.PointerType;

public class MainActivity extends AppCompatActivity {
    private GuideView mGuideView;
    private GuideView.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        builder = new GuideView.Builder(this)
                .setTitle("Guide Title Text")
                .setContentText("Guide Description Text\n .....Guide Description Text\n .....Guide Description Text .....")
                .setGravity(Gravity.center)
                .setDismissType(DismissType.anywhere)
                .setPointerType(PointerType.arrow)
                .setTargetView(binding.view1)
                .setGuideListener(view -> {
                    switch (view.getId()) {
                        case R.id.view1:
                            builder.setTargetView(binding.view2).build();
                            break;
                        case R.id.view2:
                            builder.setTargetView(binding.view3).build();
                            break;
                        case R.id.view3:
                            builder.setTargetView(binding.view4).build();
                            break;
                        case R.id.view4:
                            builder.setTargetView(binding.view5).build();
                            break;
                        case R.id.view5:
                            builder.setTargetView(binding.view6).build();
                            break;
                        case R.id.view6:
                            return;
                    }
                    mGuideView = builder.build();
                    mGuideView.show();
                });

        mGuideView = builder.build();
        mGuideView.show();
        updatingForDynamicLocationViews(binding);
    }

    private void updatingForDynamicLocationViews(ActivityMainBinding binding) {
        binding.view4.setOnFocusChangeListener((view, b) -> mGuideView.updateGuideViewLocation());
    }
}
