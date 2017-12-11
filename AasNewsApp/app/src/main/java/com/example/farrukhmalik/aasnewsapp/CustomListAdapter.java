package com.example.farrukhmalik.aasnewsapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.farrukhmalik.aasnewsapp.AdminArea.SchemaPics.SchemaForFileUploading;

import java.util.ArrayList;

/**
 * Created by Farrukh Malik on 26/09/2017.
 */


public class CustomListAdapter extends BaseAdapter {

    Context context;
    ArrayList<SchemaForFileUploading> list;
    LayoutInflater inflater;

    TextView title, description;

    ImageView imageView;



    public CustomListAdapter(Context context, ArrayList<SchemaForFileUploading> list) {


        this.context = context;
        this.list = list;

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.from(context).inflate(R.layout.list_design, parent, false);

        if(list.get(position).getFileType() == 1){



            title = (TextView) convertView.findViewById(R.id.title);

            description = (TextView) convertView.findViewById(R.id.description);

            title.setText(list.get(position).getTitle());

            description.setText(list.get(position).getDescription());

            imageView = (ImageView) convertView.findViewById(R.id.image);

            try {


                Glide.with(context)
                        .load(list.get(position).getDownloadLink())
                        .into(imageView);

            }catch (Exception e){


            }



        }

        return convertView;
    }


}
