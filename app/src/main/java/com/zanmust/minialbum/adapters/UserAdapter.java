package com.zanmust.minialbum.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zanmust.minialbum.R;

import java.util.ArrayList;

import utils.DataHelper;

public class UserAdapter extends ArrayAdapter<DataHelper.User> {
    public UserAdapter(Context context, ArrayList<DataHelper.User> users) {
        super(context, 0, users);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DataHelper.User user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_item, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
        tvName.setText(getContext().getString(R.string.Name_)+" "+user.name);
        tvUsername.setText(getContext().getString(R.string.Username_)+" "+user.username);
        return convertView;
    }
}
