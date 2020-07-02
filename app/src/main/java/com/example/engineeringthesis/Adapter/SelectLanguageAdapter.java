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


public class SelectLanguageAdapter extends ArrayAdapter<String> {
    String languageNames[];
    Integer languageImages[];
    Context mContext;
    public SelectLanguageAdapter(@NonNull Context context,String[] languageNames,Integer[] languageImages) {
        super(context, R.layout.select_language_list);
        this.languageImages = languageImages;
        this.languageNames = languageNames;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return languageNames.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       ViewHolder viewHolder = new ViewHolder();
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.select_language_list, parent, false);
            viewHolder.iconLang = (ImageView) convertView.findViewById(R.id.languageIcon);
            viewHolder.nameLang = (TextView) convertView.findViewById(R.id.languageName);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
            viewHolder.iconLang.setImageResource(languageImages[position]);
            viewHolder.nameLang.setText(languageNames[position]);


        return convertView;
    }
    static class ViewHolder
    {
        TextView nameLang;
        ImageView iconLang;
    }
}
