package com.example.mc_ass2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String[] fruits = {
        "Apple", "Banana", "Cherry", 
        "Lemon", "Litchi", "Mango", 
        "Mangosteen", "Orange", "Pear", 
        "Pineapple", "Strawberry", "Watermelon"
    };

    private int[] fruitImages = {
        R.drawable.apple, R.drawable.banana, R.drawable.cherry,
        R.drawable.lemon, R.drawable.litchi, R.drawable.mango, 
        R.drawable.mangosteen, R.drawable.orange, R.drawable.pear, 
        R.drawable.pineapple, R.drawable.strawberry, R.drawable.watermelon
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.gridView);
        FruitAdapter adapter = new FruitAdapter(this, fruits, fruitImages);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, FruitDetailActivity.class);
                intent.putExtra("fruitName", fruits[position]);
                intent.putExtra("fruitImage", fruitImages[position]);
                startActivity(intent);
            }
        });
    }
}
