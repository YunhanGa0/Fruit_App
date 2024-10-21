package com.example.mc_ass2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageButton;
import android.util.Log;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatActivity;

public class FruitDetailActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer; // Declare a MediaPlayer instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_detail);

        String fruitName = getIntent().getStringExtra("fruitName"); // Get the fruit name from the intent
        int fruitImage = getIntent().getIntExtra("fruitImage", 0); // Get the fruit image from the intent
        
        Log.d("FruitDetailActivity", "Received fruit name: " + fruitName); // Log the received fruit name

        // Use string resources to set the fruit description
        updateFruitDetails(fruitName, fruitImage); // Update fruit details

        // Set the click event for the sound button
        ImageButton soundButton = findViewById(R.id.soundButton); // Get the sound button
        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("FruitDetailActivity", "Sound button clicked for: " + fruitName.toLowerCase());
                playSound(fruitName.toLowerCase()); // Play the sound for the fruit
            }
        });
    }

    private void updateFruitDetails(String fruitName, int fruitImage) {
        // Use string resources to set the fruit name and description
        TextView nameTextView = findViewById(R.id.fruitName);
        ImageView imageView = findViewById(R.id.fruitImage);
        TextView initialTextView = findViewById(R.id.fruitInitial);
        TextView descriptionTextView = findViewById(R.id.fruitDescription);

        nameTextView.setText(getFruitName(fruitName)); // Use string resources to set the fruit name
        imageView.setImageResource(fruitImage);
        initialTextView.setText(fruitName.substring(0, 1).toUpperCase()); // Set the initial letter of the fruit name
        descriptionTextView.setText(getFruitDescription(fruitName)); // Set the fruit description

        // Set text color and size
        nameTextView.setTextColor(Color.parseColor("#FF5722")); // Bright orange color
        descriptionTextView.setTextColor(Color.parseColor("#4CAF50")); // Bright green color
    }

    private String getFruitName(String fruitName) {
        // Get the fruit name based on the provided fruit name
        switch (fruitName) {
            case "Apple": return getString(R.string.apple_name);
            case "Banana": return getString(R.string.banana_name);
            case "Cherry": return getString(R.string.cherry_name);
            case "Lemon": return getString(R.string.lemon_name);
            case "Litchi": return getString(R.string.litchi_name);
            case "Mango": return getString(R.string.mango_name);
            case "Mangosteen": return getString(R.string.mangosteen_name);
            case "Orange": return getString(R.string.orange_name);
            case "Pear": return getString(R.string.pear_name);
            case "Pineapple": return getString(R.string.pineapple_name);
            case "Strawberry": return getString(R.string.strawberry_name);
            case "Watermelon": return getString(R.string.watermelon_name);
            default: return fruitName; // Return the original name by default
        }
    }

    private String getFruitDescription(String fruitName) {
        // Get the fruit description based on the provided fruit name
        switch (fruitName) {
            case "Apple":
            case "苹果":
                return getString(R.string.apple_description);
            case "Banana":
            case "香蕉":
                return getString(R.string.banana_description);
            case "Cherry":
            case "樱桃":
                return getString(R.string.cherry_description);
            case "Lemon":
            case "柠檬":
                return getString(R.string.lemon_description);
            case "Litchi":
            case "荔枝":
                return getString(R.string.litchi_description);
            case "Mango":
            case "芒果":
                return getString(R.string.mango_description);
            case "Mangosteen":
            case "山竹":
                return getString(R.string.mangosteen_description);
            case "Orange":
            case "橘子":
                return getString(R.string.orange_description);
            case "Pear":
            case "梨":
                return getString(R.string.pear_description);
            case "Pineapple":
            case "菠萝":
                return getString(R.string.pineapple_description);
            case "Strawberry":
            case "草莓":
                return getString(R.string.strawberry_description);
            case "Watermelon":
            case "西瓜":
                return getString(R.string.watermelon_description);
            default:
                return getString(R.string.default_fruit_description); // Return default description
        }
    }

    private void playSound(String fruit) {
        // Release the previous MediaPlayer
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        // Load the corresponding audio file based on the fruit name
        int soundResId = getResources().getIdentifier(fruit, "raw", getPackageName());
        Log.d("FruitDetailActivity", "Sound resource ID for " + fruit + ": " + soundResId); // Log the sound resource ID

        if (soundResId != 0) { // Ensure the resource ID is valid
            mediaPlayer = MediaPlayer.create(this, soundResId);
            if (mediaPlayer != null) {
                mediaPlayer.start(); // Start playing the sound
                Log.d("FruitDetailActivity", "Playing sound for: " + fruit); // Log the sound playback

                // Release resources after playback is complete
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        mediaPlayer = null; // Set mediaPlayer to null after release
                        Log.d("FruitDetailActivity", "Sound playback completed for: " + fruit); // Log completion
                    }
                });
            } else {
                Log.e("FruitDetailActivity", "MediaPlayer creation failed for: " + fruit);
            }
        } else {
            Log.e("FruitDetailActivity", "Sound resource not found for: " + fruit);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release MediaPlayer resources
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Update fruit details when configuration changes (e.g., language change)
        String fruitName = getIntent().getStringExtra("fruitName"); // Get the fruit name from the intent
        int fruitImage = getIntent().getIntExtra("fruitImage", 0); // Get the fruit image from the intent
        updateFruitDetails(fruitName, fruitImage); // Update fruit details
    }
}
