package com.example.dmk.quizzproject.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dmk.quizzproject.core.Person;
import com.example.dmk.quizzproject.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by dmk on 30/04/16.
 */
public class PersonAdapter extends BaseAdapter {

    //    private Context mContext;
    private ArrayList<Person> mPersonsList;
    private LayoutInflater mInflater;

    public PersonAdapter(Context context, ArrayList<Person> personsList) {
        mPersonsList = personsList;
//        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {

        return mPersonsList != null ? mPersonsList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mPersonsList != null ? mPersonsList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.v("iit", "getView");
        long timestamp = Calendar.getInstance().getTimeInMillis();
        ItemViewHolder itemViewHolder;
        if (convertView == null) {
            Log.v("iit", "getView: convert view = null:"+position);
            convertView = mInflater.inflate(R.layout.listview_activity, null);
            itemViewHolder = new ItemViewHolder();
            itemViewHolder.mName = (TextView) convertView.findViewById(R.id.item_name);
            itemViewHolder.mAge = (TextView) convertView.findViewById(R.id.item_last_name);
           // itemViewHolder.mImage = Integer.toString(convertView.findViewById(R.id.item_image));
            convertView.setTag(itemViewHolder);
        } else {
            Log.v("iit", "getView: convert view != null:"+position);
            itemViewHolder = (ItemViewHolder) convertView.getTag();
        }
        Person person = mPersonsList.get(position);
        itemViewHolder.mName.setText(person.getName());
        itemViewHolder.mAge.setText(String.valueOf(person.getRank()));
        itemViewHolder.mImage=R.drawable.ic_account_box_black_24dp;
        Log.v("iit", "getView duration = " + (Calendar.getInstance().getTimeInMillis() - timestamp));
        return convertView;


    }


    private static class ItemViewHolder {

        int mImage;
        TextView mName;
        TextView mAge;

    }


}

