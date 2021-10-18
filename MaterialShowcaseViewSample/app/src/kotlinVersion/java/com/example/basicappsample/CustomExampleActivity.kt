package com.example.basicappsample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.basicappsample.databinding.ActivityCustomExampleBinding
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView

class CustomExampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomExampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnShow.setOnClickListener { presentShowcaseView(0) }
        binding.btnReset.setOnClickListener {
            MaterialShowcaseView.resetSingleUse(this, SHOWCASE_ID)
            Toast.makeText(this, "Showcase reset", Toast.LENGTH_SHORT).show()
        }
        presentShowcaseView(1000) // one second delay
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_custom_example, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_sample_action) {
            val view = findViewById<View>(R.id.menu_sample_action)
            MaterialShowcaseView.Builder(this)
                .setTarget(view)
                .setShapePadding(96)
                .setDismissText("GOT IT")
                .setContentText("Example of how to setup a MaterialShowcaseView for menu items in action bar.")
                .setContentTextColor(getColor(R.color.green))
                .setMaskColour(getColor(R.color.purple))
                .show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun presentShowcaseView(withDelay: Int) {
        MaterialShowcaseView.Builder(this)
            .setTarget(binding.btnShow)
            .setContentText("This is some amazing feature you should know about")
            .setDismissText("GOT IT")
            .setDismissOnTouch(true)
            .setContentTextColor(resources.getColor(R.color.green))
            .setMaskColour(resources.getColor(R.color.purple))
            .setDelay(withDelay) // optional but starting animations immediately in onCreate can make them choppy
            .singleUse(SHOWCASE_ID) // provide a unique ID used to ensure it is only shown once
            .show()
    }

    companion object {
        private const val SHOWCASE_ID = "custom example"
    }
}