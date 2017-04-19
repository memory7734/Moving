package me.memory7734.moving.viewitem;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.CardView;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.sefford.circularprogressdrawable.CircularProgressDrawable;

import me.memory7734.moving.R;
import me.memory7734.moving.bean.CircleItem;

/**
 * Created by Elijah on 16/11/23.
 */

public class CircleCardView {

    private final CardView card;
    private final TextView circleTitle;
    private final TextView circleGoal;
    private final TextView circleResult;
    private final TextView circlePersent;
    private final TextView circleFinished;
    private final ImageView circleImg;
    private CircularProgressDrawable drawable;



    public CircleCardView(CardView card) {
        this.card = card;
        circleTitle = (TextView) card.findViewById(R.id.circle_title);
        circleGoal = (TextView) card.findViewById(R.id.circle_goal);
        circleResult = (TextView) card.findViewById(R.id.circle_result);
        circlePersent = (TextView) card.findViewById(R.id.circle_percent);
        circleFinished = (TextView) card.findViewById(R.id.circle_finished);
        circleImg = (ImageView) card.findViewById(R.id.circle_img);
        drawable = new CircularProgressDrawable.Builder()
                .setRingWidth(card.getResources().getDimensionPixelSize(R.dimen.drawable_ring_size))
                .setOutlineColor(card.getResources().getColor(android.R.color.darker_gray))
                .setRingColor(card.getResources().getColor(android.R.color.holo_green_light))
                .setCenterColor(card.getResources().getColor(android.R.color.holo_blue_dark))
                .create();
        circleImg.setImageDrawable(drawable);
    }


    public void showView(CircleItem item) {
        float fin = item.getCirclePercent();
        circleTitle.setText(item.getCircleTitle());
        circleGoal.setText("目标：" + item.getCircleGoal());
        circleResult.setText("完成：" + item.getCircleResult());
        String f = String.valueOf(fin * 100);
        if (f.length() >= 4) {
            circlePersent.setText("占比：" + f.substring(0, 4) + "%");
        } else {
            circlePersent.setText("占比：" + f + "%");
        }
        circleFinished.setText(item.isCircleFinished() ? "恭喜您！" : "加油哦！");
        if (fin > 1) {
            fin = 1;
        }
        AnimatorSet animation = new AnimatorSet();
        ObjectAnimator invertedProgress = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.PROGRESS_PROPERTY, 0f, fin);
        invertedProgress.setDuration(1200);
        invertedProgress.setInterpolator(new OvershootInterpolator((fin < 0.85f) ? 2 : 13 * (1 - fin)));

        Animator invertedCircle = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_SCALE_PROPERTY, 0f, fin);
        invertedCircle.setDuration(1200);
        invertedCircle.setInterpolator(new OvershootInterpolator((fin < 0.85f) ? 2 : 13 * (1 - fin)));

        animation.playTogether(invertedProgress, invertedCircle);
        animation.start();
    }
}
