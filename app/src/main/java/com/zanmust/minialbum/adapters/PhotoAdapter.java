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
import utils.ImageUtils;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<DataHelper.Photo> mPhotos;


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public PhotoAdapter(Context context, ArrayList<DataHelper.Photo> photos, OnItemClickListener listener){
        mContext=context;
        mPhotos=photos;
        onItemClickListener=listener;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView img;

        public MyViewHolder(final View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            img = (ImageView) view.findViewById(R.id.img);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(view, getAdapterPosition());
                }
            });

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
        ImageUtils.setImageGallery(mPhotos.get(position).thumbnailUrl,holder.img,mContext);
        holder.title.setText(mPhotos.get(position).title);

    }


    @Override
    public int getItemCount() {
        return mPhotos.size();
    }
}
