package com.workshop.onebytenpredictors.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.workshop.onebytenpredictors.R;
import com.workshop.onebytenpredictors.activity.WebViewActivity;
import com.workshop.onebytenpredictors.api.ModelClass;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<ModelClass> modelClassArrayList;

    public Adapter(Context context, ArrayList<ModelClass> modelClassArrayList) {
        this.context = context;
        this.modelClassArrayList = modelClassArrayList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_news,null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, WebViewActivity.class);
                intent.putExtra("url", modelClassArrayList.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        holder.mTime.setText(modelClassArrayList.get(position).getPublishedAt());
        holder.mAuthor.setText(modelClassArrayList.get(position).getAuthor());
        holder.mHeading.setText(modelClassArrayList.get(position).getTitle());
        Glide.with(context).load(modelClassArrayList.get(position).getUrlToImage()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return modelClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mHeading, mAuthor, mTime;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mHeading=itemView.findViewById(R.id.textNews);
            mAuthor=itemView.findViewById(R.id.textAuthor);
            mTime=itemView.findViewById(R.id.textTime);
            imageView=itemView.findViewById(R.id.imgNews);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
