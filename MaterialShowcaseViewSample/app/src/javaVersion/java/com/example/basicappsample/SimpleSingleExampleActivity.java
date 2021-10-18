package com.example.basicappsample;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basicappsample.databinding.ActivitySimpleSingleExampleBinding;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.shape.OvalShape;

public class SimpleSingleExampleActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String SHOWCASE_ID = "simple example";
    private ActivitySimpleSingleExampleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySimpleSingleExampleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnShow.setOnClickListener(this);
        binding.btnReset.setOnClickListener(this);
        presentShowcaseView(1000); // one second delay
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_show) {
            presentShowcaseView(0);
        } else if (v.getId() == R.id.btn_reset) {
            MaterialShowcaseView.resetSingleUse(this, SHOWCASE_ID);
            Toast.makeText(this, "Showcase reset", Toast.LENGTH_SHORT).show();
        }
    }

    private void presentShowcaseView(int withDelay) {
        new MaterialShowcaseView.Builder(this)
                .setTarget(binding.btnShow)
                .setShape(new OvalShape())
                .setTitleText("Hello")
                .setDismissText("GOT IT")
                .setContentText("This is some amazing feature you should know about")
                .setDelay(withDelay) // optional but starting animations immediately in onCreate can make them choppy
                .singleUse(SHOWCASE_ID) // provide a unique ID used to ensure it is only shown once
//                .useFadeAnimation() // remove comment if you want to use fade animations for Lollipop & up
                .show();
    }
}
