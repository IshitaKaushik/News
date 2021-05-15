package com.example.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private ArrayList<News> arrayList=new ArrayList<>();
    private onItemClicked clicked;
    public NewsListAdapter(onItemClicked clicked){
        this.clicked=clicked;
    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
         final NewsViewHolder newsViewHolder= new NewsViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked.onItemClick(arrayList.get(newsViewHolder.getAdapterPosition()));
            }
        });

        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
                News s=arrayList.get(position);
                holder.textView.setText(s.getTitle());
                holder.textView1.setText(s.getAuthor());
                Glide.with(holder.itemView.getContext()).load(s.getImageurl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void update(ArrayList<News> items){
        arrayList.clear();
        arrayList.addAll(items);
        notifyDataSetChanged();
    }
}
class NewsViewHolder extends RecyclerView.ViewHolder{

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
    }
TextView textView=(TextView)itemView.findViewById(R.id.title);
ImageView imageView=(ImageView)itemView.findViewById(R.id.image);
TextView textView1=(TextView)itemView.findViewById(R.id.author);
}

interface onItemClicked{
    void onItemClick(News s);
}