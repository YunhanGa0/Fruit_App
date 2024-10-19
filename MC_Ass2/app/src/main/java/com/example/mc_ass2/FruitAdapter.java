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
    private int[] images;
    private String[] fruitNames; // 添加水果名称数组

    public FruitAdapter(Context context, String[] fruitNames, int[] images) {
        this.context = context;
        this.fruitNames = fruitNames; // 初始化水果名称数组
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length; // 返回水果数量
    }

    @Override
    public Object getItem(int position) {
        return fruitNames[position]; // 返回水果名称
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
        params.width = 700; // 设置宽度为700
        params.height = 700; // 设置高度为700
        imageView.setLayoutParams(params);

        imageView.setImageResource(images[position]);

        // 根据位置获取水果名称
        String fruitName = fruitNames[position]; // 直接使用水果名称数组
        textView.setText(fruitName); // 设置水果名称

        return convertView;
    }
}
