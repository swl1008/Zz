package com.example.examination.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.examination.R;
import com.example.examination.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<NewsBean.DataBean> list;
    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }
    public void setData(List<NewsBean.DataBean> lists){
        list.clear();
        if(lists!=null){
            list.addAll(lists);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder vh = null;
        View view = LayoutInflater.from(context).inflate(R.layout.item_linear,viewGroup,false);
        vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder viewHolder, int i) {

            viewHolder.title.setText(list.get(i).getTitle());
            viewHolder.name.setText(list.get(i).getAuthor_name());
            Glide.with(context).load(list.get(i).getThumbnail_pic_s()).into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title,name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            imageView = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.item_name);
        }
    }


}
