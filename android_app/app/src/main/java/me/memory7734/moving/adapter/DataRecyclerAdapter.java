package me.memory7734.moving.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.memory7734.moving.R;
import me.memory7734.moving.bean.HealthItem;
import me.memory7734.moving.viewitem.LineCardView;

/**
 * Created by Elijah on 16/10/26.
 */

public class DataRecyclerAdapter extends RecyclerView.Adapter<DataRecyclerAdapter.LatestItemViewHolder> {

    private List<HealthItem> list = new ArrayList<>();
    private CardView cardView;

    public DataRecyclerAdapter(Context context, List<HealthItem> list) {
        this.list = list;
    }

    @Override
    public LatestItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_line_card, parent, false);
        cardView = (CardView) view.findViewById(R.id.health_card);
        return new LatestItemViewHolder(view, cardView);
    }

    @Override
    public void onBindViewHolder(LatestItemViewHolder holder, int position) {
        holder.lineCardView.showView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class LatestItemViewHolder extends RecyclerView.ViewHolder {

        private LineCardView lineCardView;
        private View view;

        public LatestItemViewHolder(View itemView, CardView cardView) {
            super(itemView);
            view = itemView;
            lineCardView = new LineCardView(cardView);
        }
    }
}
