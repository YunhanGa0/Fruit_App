package com.example.mc_ass2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String[] fruits; // 声明水果名称数组
    private int[] fruitImages = {
        R.drawable.apple, R.drawable.banana, R.drawable.cherry,
        R.drawable.lemon, R.drawable.litchi, R.drawable.mango, 
        R.drawable.mangosteen, R.drawable.orange, R.drawable.pear, 
        R.drawable.pineapple, R.drawable.strawberry, R.drawable.watermelon
    };

    private FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 在这里初始化水果名称数组
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
        adapter = new FruitAdapter(this, fruits, fruitImages); // 传递水果名称和图片数组
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, FruitDetailActivity.class);
                intent.putExtra("fruitName", fruits[position]); // 传递水果名称
                intent.putExtra("fruitImage", fruitImages[position]);
                startActivity(intent);
            }
        });

        // 添加语言切换按钮的点击事件
        Button languageButton = findViewById(R.id.languageButton);
        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleLanguage();
            }
        });
    }

    private void toggleLanguage() {
        // 获取当前语言
        Locale currentLocale = getResources().getConfiguration().locale;
        String language = currentLocale.getLanguage();
        Log.d("MainActivity", "Current language: " + language);

        // 切换语言
        if (language.equals("en")) {
            setLocale("zh"); // 切换到中文
            Log.d("MainActivity", "Switching to Chinese");
        } else {
            setLocale("en"); // 切换到英文
            Log.d("MainActivity", "Switching to English");
        }

        // 更新水果名称和描述
        updateFruitNamesAndDescriptions();

        // 重新启动活动以应用语言更改
        recreate();
    }

    private void updateFruitNamesAndDescriptions() {
        // 更新水果名称
        for (int i = 0; i < fruits.length; i++) {
            fruits[i] = getFruitName(fruits[i]);
        }
        adapter.notifyDataSetChanged(); // 通知适配器数据已更改
    }

    private String getFruitName(String fruitName) {
        if (Locale.getDefault().getLanguage().equals("zh")) {
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
        } else {
            return fruitName; // 认返回英文名称
        }
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        getResources().getConfiguration().setLocale(locale);
        getResources().updateConfiguration(getResources().getConfiguration(), getResources().getDisplayMetrics());
    }
}
