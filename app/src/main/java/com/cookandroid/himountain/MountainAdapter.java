package com.cookandroid.himountain;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MountainAdapter
        extends RecyclerView.Adapter<MountainAdapter.ViewHolder> {

    private List<Mountain> mountainList;

    public MountainAdapter(List<Mountain> mountainList) {
        this.mountainList = mountainList;
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imgMountain;

        TextView txtName;
        TextView txtHeight;
        TextView txtLocation;
        TextView txtDifficulty;
        TextView txtRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMountain =
                    itemView.findViewById(R.id.imgMountain);

            txtName =
                    itemView.findViewById(R.id.txtName);

            txtHeight =
                    itemView.findViewById(R.id.txtHeight);

            txtLocation =
                    itemView.findViewById(R.id.txtLocation);

            txtDifficulty =
                    itemView.findViewById(R.id.txtDifficulty);

            txtRating =
                    itemView.findViewById(R.id.txtRating);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_mountain,
                        parent,
                        false
                );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        Mountain mountain =
                mountainList.get(position);

        holder.imgMountain.setImageResource(
                mountain.getImageResId());

        holder.txtName.setText(
                mountain.getName());

        holder.txtHeight.setText(
                "🏔 해발 : " +
                        mountain.getHeight());

        holder.txtLocation.setText(
                "📍 위치 : " +
                        mountain.getLocation());

        holder.txtDifficulty.setText(
                "⭐ 난이도 : " +
                        mountain.getDifficulty());
        holder.txtRating.setText(
                "⭐ 평점 : "
                        + mountain.getRating());

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(
                    holder.itemView.getContext(),
                    DetailActivity.class);

            intent.putExtra(
                    "mountainName",
                    mountain.getName());

            holder.itemView.getContext()
                    .startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mountainList.size();
    }
}