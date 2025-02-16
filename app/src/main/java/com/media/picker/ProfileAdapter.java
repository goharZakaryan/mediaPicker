package com.media.picker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    private List<Profile> profileList;
    private Context context;

    public ProfileAdapter(List<Profile> profileList, Context context) {
        this.profileList = profileList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);

        GifImageView feedheart = view.findViewById(R.id.feedheart);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Profile profile = profileList.get(position);
        holder.nameTextView.setText(profile.getName());



        // Load profile image
        Glide.with(context)
                .load(profile.getImageUrl())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.profileImageView);

        List<String> postImages = profile.getImageUrl();  // Ensure this method returns a List<String>

        if (postImages != null && !postImages.isEmpty()) {
            PostImageAdapter imageAdapter = new PostImageAdapter(postImages, context);
            holder.postImageViewPager.setAdapter(imageAdapter);

            // Fix: Ensure TabLayout exists and attach it
            new TabLayoutMediator(holder.imageIndicator, holder.postImageViewPager,
                    (tab, index) -> {}).attach();
        }
    }


    private void loadImage(ImageView holder, String imageUrl) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(holder);
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, postTime;
        ImageView profileImageView;
        ViewPager2 postImageViewPager;
        TabLayout imageIndicator;  // Add this


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIndicator = itemView.findViewById(R.id.imageIndicator);
            nameTextView = itemView.findViewById(R.id.userName);
            postTime = itemView.findViewById(R.id.postTime);
            profileImageView = itemView.findViewById(R.id.profileImage);
            postImageViewPager = itemView.findViewById(R.id.postImageViewPager);
        }
    }

}
