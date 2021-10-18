package com.example.basicappsample;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basicappsample.databinding.ActivitySequenceExampleBinding;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;


public class SequenceExampleActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String SHOWCASE_ID = "sequence example";
    private ActivitySequenceExampleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySequenceExampleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnOne.setOnClickListener(this);
        binding.btnTwo.setOnClickListener(this);
        binding.btnThree.setOnClickListener(this);
        binding.btnReset.setOnClickListener(this);
        presentShowcaseSequence(); // one second delay
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_one || v.getId() == R.id.btn_two || v.getId() == R.id.btn_three) {
            presentShowcaseSequence();
        } else if (v.getId() == R.id.btn_reset) {
            MaterialShowcaseView.resetSingleUse(this, SHOWCASE_ID);
            Toast.makeText(this, "Showcase reset", Toast.LENGTH_SHORT).show();
        }
    }

    private void presentShowcaseSequence() {
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view
        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, SHOWCASE_ID);
        sequence.setOnItemShownListener(new MaterialShowcaseSequence.OnSequenceItemShownListener() {
            @Override
            public void onShow(MaterialShowcaseView itemView, int position) {
                Toast.makeText(itemView.getContext(), "Item #" + position, Toast.LENGTH_SHORT).show();
            }
        });
        sequence.setConfig(config);
        sequence.addSequenceItem(binding.btnOne, "This is button one", "GOT IT");
        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setSkipText("SKIP")
                        .setTarget(binding.btnTwo)
                        .setDismissText("GOT IT")
                        .setContentText("This is button two")
                        .withRectangleShape(true)
                        .build()
        );
        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(binding.btnThree)
                        .setDismissText("GOT IT")
                        .setContentText("This is button three")
                        .withRectangleShape()
                        .build()
        );
        sequence.start();
    }

}
