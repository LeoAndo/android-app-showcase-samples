package com.example.basicappsample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.basicappsample.databinding.ActivitySequenceExampleBinding
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence.OnSequenceItemShownListener
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig

class SequenceExampleActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySequenceExampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySequenceExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnOne.setOnClickListener(this)
        binding.btnTwo.setOnClickListener(this)
        binding.btnThree.setOnClickListener(this)
        binding.btnReset.setOnClickListener(this)
        presentShowcaseSequence() // one second delay
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_one || v.id == R.id.btn_two || v.id == R.id.btn_three) {
            presentShowcaseSequence()
        } else if (v.id == R.id.btn_reset) {
            MaterialShowcaseView.resetSingleUse(this, SHOWCASE_ID)
            Toast.makeText(this, "Showcase reset", Toast.LENGTH_SHORT).show()
        }
    }

    private fun presentShowcaseSequence() {
        val config = ShowcaseConfig()
        config.delay = 500 // half second between each showcase view
        val sequence = MaterialShowcaseSequence(this, SHOWCASE_ID)
        sequence.setOnItemShownListener { itemView, position ->
            Toast.makeText(
                itemView.context,
                "Item #$position",
                Toast.LENGTH_SHORT
            ).show()
        }
        sequence.setConfig(config)
        sequence.addSequenceItem(binding.btnOne, "This is button one", "GOT IT")
        sequence.addSequenceItem(
            MaterialShowcaseView.Builder(this)
                .setSkipText("SKIP")
                .setTarget(binding.btnTwo)
                .setDismissText("GOT IT")
                .setContentText("This is button two")
                .withRectangleShape(true)
                .build()
        )
        sequence.addSequenceItem(
            MaterialShowcaseView.Builder(this)
                .setTarget(binding.btnThree)
                .setDismissText("GOT IT")
                .setContentText("This is button three")
                .withRectangleShape()
                .build()
        )
        sequence.start()
    }

    companion object {
        private const val SHOWCASE_ID = "sequence example"
    }
}