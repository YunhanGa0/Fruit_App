package com.example.mc_ass2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FruitDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_detail);

        String fruitName = getIntent().getStringExtra("fruitName");
        int fruitImage = getIntent().getIntExtra("fruitImage", 0);

        TextView nameTextView = findViewById(R.id.fruitName);
        ImageView imageView = findViewById(R.id.fruitImage);

        nameTextView.setText(fruitName);
        imageView.setImageResource(fruitImage);
    }
}
