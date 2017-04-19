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
import me.memory7734.moving.bean.CircleItem;
import me.memory7734.moving.bean.TipItem;
import me.memory7734.moving.viewitem.CircleCardView;
import me.memory7734.moving.viewitem.TipCardView;

/**
 * Created by Elijah on 16/11/24.
 */

public class TipRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int CIRCLE_ITEM = 0;
    private static final int TIP_ITEM = 1;

    private List<CircleItem> mCircleItems = new ArrayList<>();
    private List<TipItem> mTipItems = new ArrayList<>();
    private CardView circleCard;
    private CardView tipCard;

    public TipRecyclerAdapter(Context context, List<CircleItem> circleItems, List<TipItem> tipItems) {
        this.mCircleItems = circleItems;
        this.mTipItems = tipItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == CIRCLE_ITEM) {
            View circleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_circle_card, parent, false);
            circleCard = (CardView) circleView.findViewById(R.id.circle_card);
            return new CircleItemViewHolder(circleView, circleCard);
        } else {
            View tipView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_tip_card, parent, false);
            tipCard = (CardView) tipView.findViewById(R.id.tip_card);
            return new TipItemViewHolder(tipView, tipCard);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CircleItemViewHolder) {
            ((CircleItemViewHolder) holder).mCircleCardView.showView(mCircleItems.get(position));
        } else if (holder instanceof TipItemViewHolder) {
            ((TipItemViewHolder) holder).mTipCardView.showView(mTipItems.get(position - mCircleItems.size()));
        }
    }

    @Override
    public int getItemCount() {
        return (mCircleItems != null ? mCircleItems.size() : 0) + (mTipItems != null ? mTipItems.size() : 0);
    }

    @Override
    public int getItemViewType(int position) {
        return position < mCircleItems.size() ? CIRCLE_ITEM : TIP_ITEM;
    }

    public class CircleItemViewHolder extends RecyclerView.ViewHolder {
        private CircleCardView mCircleCardView;

        public CircleItemViewHolder(View itemView, CardView card) {
            super(itemView);
            mCircleCardView = new CircleCardView(card);
        }
    }

    public class TipItemViewHolder extends RecyclerView.ViewHolder {
        private TipCardView mTipCardView;

        public TipItemViewHolder(View itemView, CardView card) {
            super(itemView);
            mTipCardView = new TipCardView(card);
        }
    }
}
