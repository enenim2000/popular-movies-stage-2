package com.enenim.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enenim.movies.model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private List<Review> reviews;
    private Context context;

    public ReviewAdapter(Context context, List<Review> reviews) {
        this.context = context;
        this.reviews = reviews;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout_review, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        //viewHolder.textView.setText(reviews.get(i).getUserName());

        //int imageWidth = 150;
        //int imageHeight = 150;

        //Common.loadImageIntoView(users.get(i), viewHolder.imageView, context, imageWidth, imageHeight);


        //Picasso.with(context).load(users.get(i).getPhotoUrl()).resize(120, 60).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public ViewHolder(View view) {
            super(view);

            //textView = (TextView)view.findViewById(R.id.textView);
            //imageView = (ImageView)view.findViewById(R.id.imageView);
        }
    }
}
