package com.example.basicappsample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.basicappsample.databinding.ActivityMainBinding
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView

class MainActivity : AppCompatActivity(), IButton, View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClickListener(binding.root, this)
    }

    override fun onClick(v: View) {
        var intent: Intent? = null
        when (v.id) {
            R.id.btn_simple_example -> intent =
                Intent(this, SimpleSingleExampleActivity::class.java)
            R.id.btn_custom_example -> intent = Intent(this, CustomExampleActivity::class.java)
            R.id.btn_sequence_example -> intent = Intent(this, SequenceExampleActivity::class.java)
            R.id.btn_tooltip_example -> intent = Intent(this, TooltipExampleActivity::class.java)
            R.id.btn_reset_all -> {
                MaterialShowcaseView.resetAll(this)
                Toast.makeText(this, "All Showcases reset", Toast.LENGTH_SHORT).show()
            }
        }
        intent?.let { startActivity(it) }
    }
}