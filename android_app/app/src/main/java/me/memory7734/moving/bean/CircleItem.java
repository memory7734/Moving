package me.memory7734.moving.bean;

/**
 * Created by Elijah on 16/11/23.
 */

public class CircleItem {
    private String circleTitle;
    private float circleGoal;
    private float circleResult;
    private float circlePercent;
    private boolean circleFinished;

    public CircleItem(String circleTitle, float circleGoal, float circleResult) {
        this.circleTitle = circleTitle;
        this.circleGoal = circleGoal;
        this.circleResult = circleResult;
        this.circlePercent = circleResult / circleGoal;
        this.circleFinished = (circleGoal <= circleResult);
    }

    public String getCircleTitle() {
        return circleTitle;
    }

    public float getCircleGoal() {
        return circleGoal;
    }

    public float getCircleResult() {
        return circleResult;
    }

    public float getCirclePercent() {
        return circlePercent;
    }

    public boolean isCircleFinished() {
        return circleFinished;
    }
}
