package com.example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bean.DatasBean;
import com.example.second.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataAdapter extends RecyclerView.Adapter {

    List<DatasBean> data=new ArrayList<>();
    Context context;

    public static final int ONE=0;
    public static final int TWO=1;
    public DataAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<DatasBean> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==0){
            return ONE;
        }else{
            return TWO;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i){
            case ONE:
                View inflate = LayoutInflater.from(context).inflate(R.layout.one, viewGroup, false);
                return new OneViewHolder(inflate);
            case TWO:
                View inflate1 = LayoutInflater.from(context).inflate(R.layout.two, viewGroup, false);
                return new TwoViewHolder(inflate1);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        final DatasBean datasBean = data.get(i);
        switch (itemViewType){
            case ONE:
               OneViewHolder one= (OneViewHolder) viewHolder;
               one.textView.setText(datasBean.getTitle());
                RequestOptions requestOptions = RequestOptions.circleCropTransform();
                Glide.with(context).load(datasBean.getEnvelopePic()).apply(requestOptions).into(one.imageView);
                break;
            case TWO:
                TwoViewHolder two= (TwoViewHolder) viewHolder;
                two.textView.setText(datasBean.getTitle());
                break;
        }

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(clickItem!=null){
                    clickItem.onClick(datasBean);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class OneViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mImg_one)
        ImageView imageView;
        @BindView(R.id.mTitle_one)
        TextView textView;
        public OneViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class TwoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mTitle_two)
        TextView textView;
        public TwoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    ClickItem clickItem;

    public void setClickItem(ClickItem clickItem) {
        this.clickItem = clickItem;
    }

    public interface ClickItem{
        void onClick(DatasBean bean);
    }

}
