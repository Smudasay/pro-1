package com.app7.say.application;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 23/9/2561.
 */

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.Holder>{

    List<String> thList;
    List<String> arList;

    public TextAdapter(List<String> thList, List<String> arList){
        this.thList = thList;
        this.arList = arList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return thList.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView tvTh;
        TextView tvAr;

        public Holder(View itemView) {
            super(itemView);
            tvTh = itemView.findViewById(R.id.tv_th);
            tvAr = itemView.findViewById(R.id.tv_ar);
        }

        public void bindData(int position){
            tvTh.setText(thList.get(position));
            tvAr.setText(arList.get(position));
        }
    }
}
