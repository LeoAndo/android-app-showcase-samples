package com.example.basicappsample;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basicappsample.databinding.ActivityCustomExampleBinding;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;


public class CustomExampleActivity extends AppCompatActivity {
    private static final String SHOWCASE_ID = "custom example";
    private ActivityCustomExampleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomExampleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnShow.setOnClickListener((v) -> presentShowcaseView(0));
        binding.btnReset.setOnClickListener((v) -> {
            MaterialShowcaseView.resetSingleUse(this, SHOWCASE_ID);
            Toast.makeText(this, "Showcase reset", Toast.LENGTH_SHORT).show();
        });
        presentShowcaseView(1000); // one second delay
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_custom_example, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_sample_action) {
            View view = findViewById(R.id.menu_sample_action);
            new MaterialShowcaseView.Builder(this)
                    .setTarget(view)
                    .setShapePadding(96)
                    .setDismissText("GOT IT")
                    .setContentText("Example of how to setup a MaterialShowcaseView for menu items in action bar.")
                    .setContentTextColor(getColor(R.color.green))
                    .setMaskColour(getColor(R.color.purple))
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void presentShowcaseView(int withDelay) {
        new MaterialShowcaseView.Builder(this)
                .setTarget(binding.btnShow)
                .setContentText("This is some amazing feature you should know about")
                .setDismissText("GOT IT")
                .setDismissOnTouch(true)
                .setContentTextColor(getResources().getColor(R.color.green))
                .setMaskColour(getResources().getColor(R.color.purple))
                .setDelay(withDelay) // optional but starting animations immediately in onCreate can make them choppy
                .singleUse(SHOWCASE_ID) // provide a unique ID used to ensure it is only shown once
                .show();
    }
}
