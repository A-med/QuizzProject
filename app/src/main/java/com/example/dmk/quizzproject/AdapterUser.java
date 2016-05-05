package com.example.dmk.quizzproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hamdy on 24/04/16.
 */
public class AdapterUser extends ArrayAdapter<User> {

    private final int newsItemLayoutResource;

    public AdapterUser(final Context context, final int newsItemLayoutResource) {
        super(context, 0);
        this.newsItemLayoutResource = newsItemLayoutResource;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        final View view = getWorkingView(convertView);
        final ViewHolder viewHolder = getViewHolder(view);
        final User entry = getItem(position);

        viewHolder.titleViewname.setText(entry.getName());
        viewHolder.titleViewage.setText(entry.getAge());
        viewHolder.imageView.setImageResource(entry.getPhotoId());
        viewHolder.checkBox.setTag(entry);
        return view;
    }


    private View getWorkingView(final View convertView) {

        View workingView = null;

        if(null == convertView) {
            final Context context = getContext();
            final LayoutInflater inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);

            workingView = inflater.inflate(newsItemLayoutResource, null);
        } else {
            workingView = convertView;
        }

        return workingView;
    }

    private ViewHolder getViewHolder(final View workingView) {

        final Object tag = workingView.getTag();
        ViewHolder viewHolder = null;


        if(null == tag || !(tag instanceof ViewHolder)) {
            viewHolder = new ViewHolder();

            viewHolder.titleViewname = (TextView) workingView.findViewById(R.id.person_name);
            viewHolder.titleViewage = (TextView) workingView.findViewById(R.id.person_age);
            viewHolder.imageView = (ImageView) workingView.findViewById(R.id.person_photo);
            viewHolder.checkBox = (CheckBox) workingView.findViewById(R.id.cbSelected);

            workingView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) tag;
        }

        return viewHolder;
    }

    private static class ViewHolder {
        public TextView titleViewname;
        public TextView titleViewage;
        public ImageView imageView;
        public CheckBox checkBox;
    }



}
