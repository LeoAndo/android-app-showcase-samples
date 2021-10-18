package com.example.basicappsample

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.basicappsample.databinding.ActivityTooltipExampleBinding
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig
import uk.co.deanwild.materialshowcaseview.ShowcaseTooltip

class TooltipExampleActivity : Activity(), View.OnClickListener {
    private lateinit var binding: ActivityTooltipExampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTooltipExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnShow.setOnClickListener(this)
        binding.btnReset.setOnClickListener(this)
        binding.fab.setOnClickListener(this)
        presentShowcaseView() // one second delay
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_show) {
            presentShowcaseView()
        } else if (v.id == R.id.btn_reset) {
            MaterialShowcaseView.resetSingleUse(this, SHOWCASE_ID)
            Toast.makeText(this, "Showcase reset", Toast.LENGTH_SHORT).show()
        }
    }

    private fun presentShowcaseView() {
        val config = ShowcaseConfig()
        config.delay = 500
        val sequence = MaterialShowcaseSequence(this, SHOWCASE_ID)

        //sequence.setConfig(config);
        val toolTip1 = ShowcaseTooltip.build(this)
            .corner(30)
            .textColor(Color.parseColor("#007686"))
            .text("This is a <b>very funky</b> tooltip<br><br>This is a very long sentence to test how this tooltip behaves with longer strings. <br><br>Tap anywhere to continue")
        sequence.addSequenceItem(
            MaterialShowcaseView.Builder(this)
                .setTarget(binding.toolbar)
                .setToolTip(toolTip1)
                .withRectangleShape()
                .setTooltipMargin(30)
                .setShapePadding(50)
                .setDismissOnTouch(true)
                .setMaskColour(getColor(R.color.tooltip_mask))
                .build()
        )
        val toolTip2 = ShowcaseTooltip.build(this)
            .corner(30)
            .textColor(Color.parseColor("#007686"))
            .text("This is another <b>very funky</b> tooltip")
        sequence.addSequenceItem(
            MaterialShowcaseView.Builder(this)
                .setTarget(binding.fab)
                .setToolTip(toolTip2)
                .setTooltipMargin(30)
                .setShapePadding(50)
                .setDismissOnTouch(true)
                .setMaskColour(getColor(R.color.tooltip_mask))
                .build()
        )
        sequence.start()
    }

    companion object {
        private const val SHOWCASE_ID = "tooltip example"
    }
}