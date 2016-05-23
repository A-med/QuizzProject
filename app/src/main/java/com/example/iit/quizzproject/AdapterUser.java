package com.example.iit.quizzproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hamdy on 24/04/16.
 */
public class AdapterUser extends BaseAdapter {
    ListView mListView;

    private ArrayList<FriendsListItemWarpper> mFriendsList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public AdapterUser(final Context context, ArrayList<FriendsListItemWarpper> list) {
        mContext =  context;
        mFriendsList = list;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        int count = 0;
        if(mFriendsList!=null){
            count = mFriendsList.size();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        Object object = null;

        if(mFriendsList!=null){
            object = mFriendsList.get(position);
        }
        return object;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {


        final ViewHolder viewHolder;

        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.list_item, null);
            viewHolder.titleViewname = (TextView) convertView.findViewById(com.example.iit.quizzproject.R.id.person_name);
            viewHolder.imageView = (ImageView) convertView.findViewById(com.example.iit.quizzproject.R.id.person_photo);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(com.example.iit.quizzproject.R.id.cbSelected);
            final View finalConvertView = convertView;
            mListView= (ListView) convertView.findViewById(R.id.list);
            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mFriendsList.get(viewHolder.position).setChecked(isChecked);


                }
            });
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final FriendsListItemWarpper entry = mFriendsList.get(position);

        viewHolder.position = position;
        viewHolder.titleViewname.setText(entry.getUser().getName());
        viewHolder.imageView.setImageResource(entry.getUser().getPhotoId());
        viewHolder.checkBox.setChecked(entry.isChecked());

        return convertView;
    }

    private static class ViewHolder {
        public int position;
        public TextView titleViewname;
        public ImageView imageView;
        public CheckBox checkBox;
    }
}
