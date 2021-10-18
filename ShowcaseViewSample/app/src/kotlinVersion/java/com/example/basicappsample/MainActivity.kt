package com.example.basicappsample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import smartdevelop.ir.eram.showcaseviewlib.GuideView
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType
import smartdevelop.ir.eram.showcaseviewlib.config.Gravity
import smartdevelop.ir.eram.showcaseviewlib.config.PointerType
import com.example.basicappsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mGuideView: GuideView? = null
    private var builder: GuideView.Builder? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        builder = GuideView.Builder(this)
            .setTitle("Guide Title Text")
            .setContentText("Guide Description Text\n .....Guide Description Text\n .....Guide Description Text .....")
            .setGravity(Gravity.center)
            .setDismissType(DismissType.anywhere)
            .setPointerType(PointerType.arrow)
            .setTargetView(binding.view1)
            .setGuideListener { view: View ->
                when (view.id) {
                    R.id.view1 -> builder!!.setTargetView(binding.view2).build()
                    R.id.view2 -> builder!!.setTargetView(binding.view3).build()
                    R.id.view3 -> builder!!.setTargetView(binding.view4).build()
                    R.id.view4 -> builder!!.setTargetView(binding.view5).build()
                    R.id.view5 -> builder!!.setTargetView(binding.view6).build()
                    R.id.view6 -> return@setGuideListener
                }
                mGuideView = builder!!.build()
                mGuideView?.show()
            }
        mGuideView = builder?.build()
        mGuideView?.show()
        updatingForDynamicLocationViews(binding)
    }

    private fun updatingForDynamicLocationViews(binding: ActivityMainBinding) {
        binding.view4.setOnFocusChangeListener { _, _ -> mGuideView?.updateGuideViewLocation() }
    }
}