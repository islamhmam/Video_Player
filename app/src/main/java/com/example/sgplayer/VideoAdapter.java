package com.example.sgplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder> {
    private Context context ;
    private List<File> fileList;
    private SelectListener listener;

    public VideoAdapter(Context context, List<File> fileList , SelectListener listener) {
        this.context = context;
        this.fileList = fileList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new VideoViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_video,parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.tv.setText(fileList.get(position).getName());

        Bitmap bitmap= ThumbnailUtils.createVideoThumbnail(fileList.get(position)
                .getAbsolutePath(), MediaStore.Images.Thumbnails.MINI_KIND);
        holder.iv.setImageBitmap(bitmap);

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelectListener(fileList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }
}
