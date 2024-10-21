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
        
        Log.d("FruitDetailActivity", "Received fruit name: " + fruitName); // 添加日志

        // 使用字符串资源设置水果描述
        String fruitDescription = getFruitDescription(fruitName);

        TextView nameTextView = findViewById(R.id.fruitName);
        ImageView imageView = findViewById(R.id.fruitImage);
        TextView initialTextView = findViewById(R.id.fruitInitial);
        TextView descriptionTextView = findViewById(R.id.fruitDescription);
        ImageButton soundButton = findViewById(R.id.soundButton); // 获取喇叭按钮

        nameTextView.setText(getFruitName(fruitName)); // 使用字符串资源设置水果名称
        imageView.setImageResource(fruitImage);
        initialTextView.setText(fruitName.substring(0, 1).toUpperCase());
        descriptionTextView.setText(fruitDescription); // Set the fruit description

        // 设置字体颜色和大小
        nameTextView.setTextColor(Color.parseColor("#FF5722")); // 明亮的橙色
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

    private String getFruitName(String fruitName) {
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
            default: return fruitName; // 默认返回原名称
        }
    }

    private String getFruitDescription(String fruitName) {
        switch (fruitName) {
            case "Apple":
                return getString(R.string.apple_description);
            case "Banana":
                return getString(R.string.banana_description);
            case "Cherry":
                return getString(R.string.cherry_description);
            case "Lemon":
                return getString(R.string.lemon_description);
            case "Litchi":
                return getString(R.string.litchi_description);
            case "Mango":
                return getString(R.string.mango_description);
            case "Mangosteen":
                return getString(R.string.mangosteen_description);
            case "Orange":
                return getString(R.string.orange_description);
            case "Pear":
                return getString(R.string.pear_description);
            case "Pineapple":
                return getString(R.string.pineapple_description);
            case "Strawberry":
                return getString(R.string.strawberry_description);
            case "Watermelon":
                return getString(R.string.watermelon_description);
            default:
                return getString(R.string.default_fruit_description);
        }
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
