package com.example.gallerydemo;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import io.supercharge.shimmerlayout.ShimmerLayout;
import uk.co.senab.photoview.PhotoView;

public class PagerPhotoListAdapter extends ListAdapter<PhotoItem, PagerPhotoViewHolder> {
    public PagerPhotoListAdapter() {
        super(new DiffUtil.ItemCallback<PhotoItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull PhotoItem oldItem, @NonNull PhotoItem newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull PhotoItem oldItem, @NonNull PhotoItem newItem) {
                return oldItem.id == newItem.id;
            }
        });
    }

    @NonNull
    @Override
    public PagerPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_photo_view, parent, false);
        return new PagerPhotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PagerPhotoViewHolder holder, int position) {
        PhotoView imageView = (PhotoView) holder.itemView.findViewById(R.id.pagerPhoto);
        final ShimmerLayout shimmerLayout = holder.itemView.findViewById(R.id.pagerPhotoShimmerLayout);
        shimmerLayout.setShimmerColor(0x55FFFFFF);
        shimmerLayout.setShimmerAngle(0);
        shimmerLayout.startShimmerAnimation();
        Glide.with(holder.itemView)
                .load(getItem(position).largeImageURL)
                .placeholder(R.drawable.photo_placeholder)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (shimmerLayout != null) {
                            shimmerLayout.stopShimmerAnimation();
                        }
                        return false;
                    }
                })
                .into(imageView);
    }
}

class PagerPhotoViewHolder extends RecyclerView.ViewHolder {

    public PagerPhotoViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
