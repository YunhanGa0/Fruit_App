package com.example.mc_ass2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FruitAdapter extends BaseAdapter {
    private Context context;
    private String[] fruits;
    private int[] images;

    public FruitAdapter(Context context, String[] fruits, int[] images) {
        this.context = context;
        this.fruits = fruits;
        this.images = images;
    }

    @Override
    public int getCount() {
        return fruits.length;
    }

    @Override
    public Object getItem(int position) {
        return fruits[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.fruitImage);
        TextView textView = convertView.findViewById(R.id.fruitName);

        // 设置图片大小
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.width = 700; // 设置宽度为200
        params.height = 700; // 设置高度为200
        imageView.setLayoutParams(params);

        imageView.setImageResource(images[position]);
        textView.setText(fruits[position]);

        return convertView;
    }
}
