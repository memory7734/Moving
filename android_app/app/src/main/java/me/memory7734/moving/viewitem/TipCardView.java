package me.memory7734.moving.viewitem;

import android.support.v7.widget.CardView;
import android.widget.TextView;

import me.memory7734.moving.R;
import me.memory7734.moving.bean.TipItem;

/**
 * Created by Elijah on 16/11/23.
 */

public class TipCardView {

    private final CardView card;


    public TipCardView(CardView card) {
        this.card = card;
    }

    public void showView(TipItem item) {
        TextView tipTitle = (TextView) card.findViewById(R.id.tip_title);
        TextView tipDescribe = (TextView) card.findViewById(R.id.tip_describe);
        tipTitle.setText(item.getTipTitle());
        tipDescribe.setText(item.getTipDescribe());
    }

}
