package com.zanmust.minialbum.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zanmust.minialbum.R;

import java.util.ArrayList;

import utils.DataHelper;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<DataHelper.Photo> mPhotos;
    private LayoutInflater mInflater;


    public PhotoAdapter(Context context, ArrayList<DataHelper.Photo> photos){
        mContext=context;
        mPhotos=photos;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            img = (ImageView) view.findViewById(R.id.img);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(mPhotos.get(position).thumbnailUrl).into(holder.img);
        holder.title.setText(mPhotos.get(position).title);
    }


    @Override
    public int getItemCount() {
        return mPhotos.size();
    }
}
