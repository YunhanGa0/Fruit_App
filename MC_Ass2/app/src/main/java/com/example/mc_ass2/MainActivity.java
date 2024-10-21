package com.example.mc_ass2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String[] fruits; // Declare an array for fruit names
    private int[] fruitImages = {
        R.drawable.apple, R.drawable.banana, R.drawable.cherry,
        R.drawable.lemon, R.drawable.litchi, R.drawable.mango, 
        R.drawable.mangosteen, R.drawable.orange, R.drawable.pear, 
        R.drawable.pineapple, R.drawable.strawberry, R.drawable.watermelon
    };

    private FruitAdapter adapter; // Declare a FruitAdapter instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the title text
        TextView titleTextView = findViewById(R.id.title);
        updateTitleText(titleTextView); // Update the title text

        // Initialize the fruit name array
        fruits = new String[]{
            getString(R.string.apple_name),
            getString(R.string.banana_name),
            getString(R.string.cherry_name),
            getString(R.string.lemon_name),
            getString(R.string.litchi_name),
            getString(R.string.mango_name),
            getString(R.string.mangosteen_name),
            getString(R.string.orange_name),
            getString(R.string.pear_name),
            getString(R.string.pineapple_name),
            getString(R.string.strawberry_name),
            getString(R.string.watermelon_name)
        };

        GridView gridView = findViewById(R.id.gridView);
        adapter = new FruitAdapter(this, fruits, fruitImages); // Pass fruit names and image arrays to the adapter
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, FruitDetailActivity.class);
                intent.putExtra("fruitName", fruits[position]); // Pass the fruit name
                intent.putExtra("fruitImage", fruitImages[position]); // Pass the fruit image
                startActivity(intent);
            }
        });

        // Set the language switch listener
        Switch languageSwitch = findViewById(R.id.languageSwitch);
        languageSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setLocale("zh"); // Switch to Chinese
            } else {
                setLocale("en"); // Switch to English
            }
            updateFruitNames(); // Update fruit names
            updateTitleText(titleTextView); // Update title text
        });
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        getResources().getConfiguration().setLocale(locale);
        getResources().updateConfiguration(getResources().getConfiguration(), getResources().getDisplayMetrics());
    }

    private void updateFruitNames() {
        // Update fruit names
        fruits[0] = getString(R.string.apple_name);
        fruits[1] = getString(R.string.banana_name);
        fruits[2] = getString(R.string.cherry_name);
        fruits[3] = getString(R.string.lemon_name);
        fruits[4] = getString(R.string.litchi_name);
        fruits[5] = getString(R.string.mango_name);
        fruits[6] = getString(R.string.mangosteen_name);
        fruits[7] = getString(R.string.orange_name);
        fruits[8] = getString(R.string.pear_name);
        fruits[9] = getString(R.string.pineapple_name);
        fruits[10] = getString(R.string.strawberry_name);
        fruits[11] = getString(R.string.watermelon_name);
        adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    private void updateTitleText(TextView titleTextView) {
        // Directly use getString method to get the current language title
        titleTextView.setText(getString(R.string.fruit_list)); // Default to English title
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Update title text
        TextView titleTextView = findViewById(R.id.title);
        updateTitleText(titleTextView);
        updateFruitNames(); // Update fruit names when configuration changes
    }
}
