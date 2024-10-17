package com.example.mc_ass2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageButton;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class FruitDetailActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_detail);

        String fruitName = getIntent().getStringExtra("fruitName");
        int fruitImage = getIntent().getIntExtra("fruitImage", 0);
        
        // Add fruit descriptions
        String fruitDescription;
        switch (fruitName) {
            case "Apple":
                fruitDescription = "An apple is a crisp and sweet fruit, rich in vitamins and fiber.";
                break;
            case "Banana":
                fruitDescription = "A banana is a soft fruit that is high in potassium and energy, perfect for post-workout.";
                break;
            case "Cherry":
                fruitDescription = "A cherry is a small, sweet fruit often used in desserts and jams.";
                break;
            case "Lemon":
                fruitDescription = "A lemon is a sour fruit commonly used in drinks and flavoring, rich in vitamin C.";
                break;
            case "Litchi":
                fruitDescription = "A lychee is a tropical fruit with juicy, tender flesh and a sweet flavor.";
                break;
            case "Mango":
                fruitDescription = "The mango is known as the 'king of fruits' for its deliciously sweet flesh.";
                break;
            case "Mangosteen":
                fruitDescription = "The mangosteen is a purple fruit with a white interior, known for its unique and sweet flavor.";
                break;
            case "Orange":
                fruitDescription = "An orange is a juicy fruit rich in vitamin C, great for eating fresh or juicing.";
                break;
            case "Pear":
                fruitDescription = "A pear is a water-rich fruit with a crisp texture, perfect for eating fresh or in desserts.";
                break;
            case "Pineapple":
                fruitDescription = "A pineapple is a tropical fruit that is sweet and tangy, often used in salads and drinks.";
                break;
            case "Strawberry":
                fruitDescription = "A strawberry is a small red fruit that is sweet and rich in antioxidants.";
                break;
            case "Watermelon":
                fruitDescription = "A watermelon is a summer fruit that is hydrating and refreshing.";
                break;
            default:
                fruitDescription = "This is a delicious fruit.";
                break;
        }

        TextView nameTextView = findViewById(R.id.fruitName);
        ImageView imageView = findViewById(R.id.fruitImage);
        TextView initialTextView = findViewById(R.id.fruitInitial);
        TextView descriptionTextView = findViewById(R.id.fruitDescription);
        ImageButton soundButton = findViewById(R.id.soundButton); // 获取喇叭按钮

        nameTextView.setText(fruitName);
        imageView.setImageResource(fruitImage);
        initialTextView.setText(fruitName.substring(0, 1).toUpperCase());
        descriptionTextView.setText(fruitDescription); // Set the fruit description

        // 设置字体颜色和大小
        nameTextView.setTextSize(28); // 增大字体
        nameTextView.setTextColor(Color.parseColor("#FF5722")); // 明亮的橙色
        descriptionTextView.setTextSize(20); // 增大字体
        descriptionTextView.setTextColor(Color.parseColor("#4CAF50")); // 明亮的绿色

        // 设置喇叭按钮的点击事件
        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("FruitDetailActivity", "Sound button clicked for: " + fruitName.toLowerCase());
                playSound(fruitName.toLowerCase());
            }
        });
    }

    private void playSound(String fruit) {
        // 释放之前的 MediaPlayer
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        // 根据水果名称加载对应的音频文件
        int soundResId = getResources().getIdentifier(fruit, "raw", getPackageName());
        Log.d("FruitDetailActivity", "Sound resource ID for " + fruit + ": " + soundResId); // 添加日志

        if (soundResId != 0) { // 确保资源ID有效
            mediaPlayer = MediaPlayer.create(this, soundResId);
            if (mediaPlayer != null) {
                mediaPlayer.start();
                Log.d("FruitDetailActivity", "Playing sound for: " + fruit); // 添加日志

                // 播放完成后释放资源
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        mediaPlayer = null; // 释放后将 mediaPlayer 设置为 null
                        Log.d("FruitDetailActivity", "Sound playback completed for: " + fruit); // 添加日志
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
        // 释放 MediaPlayer 资源
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
