package com.example.engineeringthesis.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.engineeringthesis.R;

public class CategoryAdapter extends ArrayAdapter<String> {
    String categoryNames[];
    Integer categoryIcon[];
    Context mContext;

    public CategoryAdapter(@NonNull Context context, String[] categoryNames,Integer[] categoryIcon) {
        super(context, R.layout.select_category_list);
        this.categoryNames = categoryNames;
        this.categoryIcon = categoryIcon;
        this.mContext = context;
    }
    @Override
    public int getCount() {
        return categoryNames.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CategoryAdapter.ViewHolder viewHolder = new CategoryAdapter.ViewHolder();
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.select_category_list, parent, false);
            viewHolder.iconCat = (ImageView) convertView.findViewById(R.id.categoryIcon);
            viewHolder.nameCat = (TextView) convertView.findViewById(R.id.categoryName);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (CategoryAdapter.ViewHolder)convertView.getTag();

        }

        viewHolder.iconCat.setImageResource(categoryIcon[position]);
        viewHolder.nameCat.setText(categoryNames[position]);



        return convertView;
    }
    static class ViewHolder
    {
        TextView nameCat;
        ImageView iconCat;
    }
}
