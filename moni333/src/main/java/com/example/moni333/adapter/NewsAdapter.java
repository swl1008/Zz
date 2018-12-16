package com.example.moni333.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moni333.R;
import com.example.moni333.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsBean.DataBean> data;
    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }
    public void setData(List<NewsBean.DataBean> datas){
        data.clear();
        if(datas!=null){
            data.addAll(datas);
        }
        notifyDataSetChanged();
    }
    public void delData(int i) {
        data.remove(i);
        notifyItemRemoved(i);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder vh = null;
        View view = LayoutInflater.from(context).inflate(R.layout.item_news,viewGroup,false);
        vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
            ViewHolder holder = viewHolder;
            holder.title.setText(data.get(i).getTitle());
            Glide.with(context).load(data.get(i).getThumbnail_pic_s()).into(holder.imageView);
            viewHolder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = viewHolder.getLayoutPosition();
                    clickCallBack.setItemOnClick(position);
                }
            });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title,del;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_text);
            imageView = itemView.findViewById(R.id.item_image);
            del = itemView.findViewById(R.id.item_delete);
        }
    }

    private onClickCallBack clickCallBack;
    public interface onClickCallBack{
        void setItemOnClick(int i);
    }
    public void setItemOnClickListener(onClickCallBack clickCallBack){
        this.clickCallBack = clickCallBack;
    }
}
