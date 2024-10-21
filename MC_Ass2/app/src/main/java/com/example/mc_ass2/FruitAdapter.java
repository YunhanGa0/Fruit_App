package com.example.mc_ass2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FruitAdapter extends BaseAdapter {
    private Context context; // Declare context
    private int[] images; // Declare an array for images
    private String[] fruitNames; // Declare an array for fruit names

    public FruitAdapter(Context context, String[] fruitNames, int[] images) {
        this.context = context; // Initialize context
        this.fruitNames = fruitNames; // Initialize fruit names array
        this.images = images; // Initialize images array
    }

    @Override
    public int getCount() {
        return images.length; // Return the number of fruits
    }

    @Override
    public Object getItem(int position) {
        return fruitNames[position]; // Return the fruit name at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position; // Return the position as the item ID
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.fruitImage);
        TextView textView = convertView.findViewById(R.id.fruitName);

        // Set image size
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.width = 700; // Set width to 700
        params.height = 700; // Set height to 700
        imageView.setLayoutParams(params);

        imageView.setImageResource(images[position]); // Set the image resource

        // Get the fruit name based on the position
        String fruitName = fruitNames[position]; // Directly use the fruit names array
        textView.setText(fruitName); // Set the fruit name

        return convertView; // Return the completed view
    }
}
