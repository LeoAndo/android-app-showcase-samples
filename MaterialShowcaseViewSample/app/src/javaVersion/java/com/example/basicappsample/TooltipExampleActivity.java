package com.example.basicappsample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.basicappsample.databinding.ActivityTooltipExampleBinding;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;
import uk.co.deanwild.materialshowcaseview.ShowcaseTooltip;

public class TooltipExampleActivity extends Activity implements View.OnClickListener {

    private static final String SHOWCASE_ID = "tooltip example";
    private ActivityTooltipExampleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTooltipExampleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnShow.setOnClickListener(this);
        binding.btnReset.setOnClickListener(this);
        binding.fab.setOnClickListener(this);
        presentShowcaseView(); // one second delay
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_show) {

            presentShowcaseView();

        } else if (v.getId() == R.id.btn_reset) {

            MaterialShowcaseView.resetSingleUse(this, SHOWCASE_ID);
            Toast.makeText(this, "Showcase reset", Toast.LENGTH_SHORT).show();
        }
    }

    void presentShowcaseView() {


        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500);

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, SHOWCASE_ID);

        //sequence.setConfig(config);

        ShowcaseTooltip toolTip1 = ShowcaseTooltip.build(this)
                .corner(30)
                .textColor(Color.parseColor("#007686"))
                .text("This is a <b>very funky</b> tooltip<br><br>This is a very long sentence to test how this tooltip behaves with longer strings. <br><br>Tap anywhere to continue");


        sequence.addSequenceItem(

                new MaterialShowcaseView.Builder(this)
                        .setTarget(binding.toolbar)
                        .setToolTip(toolTip1)
                        .withRectangleShape()
                        .setTooltipMargin(30)
                        .setShapePadding(50)
                        .setDismissOnTouch(true)
                        .setMaskColour(getResources().getColor(R.color.tooltip_mask))
                        .build()
        );


        ShowcaseTooltip toolTip2 = ShowcaseTooltip.build(this)
                .corner(30)
                .textColor(Color.parseColor("#007686"))
                .text("This is another <b>very funky</b> tooltip");

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(binding.fab)
                        .setToolTip(toolTip2)
                        .setTooltipMargin(30)
                        .setShapePadding(50)
                        .setDismissOnTouch(true)
                        .setMaskColour(getResources().getColor(R.color.tooltip_mask))
                        .build()
        );

        sequence.start();
    }
}
