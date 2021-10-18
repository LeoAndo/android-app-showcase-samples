package com.example.basicappsample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basicappsample.databinding.ActivityMainBinding;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

public class MainActivity extends AppCompatActivity implements IButton, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setOnClickListener(binding.getRoot(), this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_simple_example:
                intent = new Intent(this, SimpleSingleExampleActivity.class);
                break;
            case R.id.btn_custom_example:
                intent = new Intent(this, CustomExampleActivity.class);
                break;
            case R.id.btn_sequence_example:
                intent = new Intent(this, SequenceExampleActivity.class);
                break;
            case R.id.btn_tooltip_example:
                intent = new Intent(this, TooltipExampleActivity.class);
                break;
            case R.id.btn_reset_all:
                MaterialShowcaseView.resetAll(this);
                Toast.makeText(this, "All Showcases reset", Toast.LENGTH_SHORT).show();
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
