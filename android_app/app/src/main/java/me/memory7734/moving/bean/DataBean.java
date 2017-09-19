package me.memory7734.moving.bean;

/**
 * Created by Elijah on 2016-9-23.
 */

public class DataBean {
    private int userkey;
    private int date_time;
    private float activity;
    private float activite_energy;
    private float cycling_distance;
    private float exercise_mintues;
    private float walking_running_distance;
    private float step;
    private float blood_glucose;
    private float forced_vital_vapacity;
    private float blood_alcohol_content;
    private float height;
    private float weight;

    public DataBean(int date_time) {
        this.date_time = date_time;
    }

    public DataBean(int date_time, float[] values) {
        this.date_time = date_time;
        this.activity = values[0];
        this.activite_energy = values[1];
        this.cycling_distance = values[2];
        this.exercise_mintues = values[3];
        this.walking_running_distance = values[4];
        this.step = values[5];
        this.blood_glucose = values[6];
        this.forced_vital_vapacity = values[7];
        this.blood_alcohol_content = values[8];
        this.height = values[9];
        this.weight = values[10];
    }

    public DataBean(int date_time, float activity, float activite_energy, float cycling_distance, float exercise_mintues, float walking_running_distance, float step, float blood_glucose, float forced_vital_vapacity, float blood_alcohol_content, float height, float weight) {
        this.date_time = date_time;
        this.activity = activity;
        this.activite_energy = activite_energy;
        this.cycling_distance = cycling_distance;
        this.exercise_mintues = exercise_mintues;
        this.walking_running_distance = walking_running_distance;
        this.step = step;
        this.blood_glucose = blood_glucose;
        this.forced_vital_vapacity = forced_vital_vapacity;
        this.blood_alcohol_content = blood_alcohol_content;
        this.height = height;
        this.weight = weight;
    }

    public float[] getValues() {
        return new float[]{activity, activite_energy, cycling_distance, exercise_mintues, walking_running_distance, step, blood_glucose, forced_vital_vapacity, blood_alcohol_content, height, weight};
    }

    public int getDate_time() {
        return date_time;
    }

    public void setUserkey(int userkey) {
        this.userkey = userkey;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "date_time=" + date_time +
                ", activite_energy=" + activite_energy +
                ", activity=" + activity +
                ", cycling_distance=" + cycling_distance +
                ", exercise_mintues=" + exercise_mintues +
                ", walking_running_distance=" + walking_running_distance +
                ", step=" + step +
                ", blood_glucose=" + blood_glucose +
                ", forced_vital_vapacity=" + forced_vital_vapacity +
                ", blood_alcohol_content=" + blood_alcohol_content +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
