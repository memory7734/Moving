package me.memory7734.moving.bean;

/**
 * Created by Elijah on 16/11/23.
 */

public class TipItem {
    private int tipKey;
    private String tipTitle;
    private String tipDescribe;

    public TipItem(int tipKey, String tipTitle, String tipDescribe) {
        this.tipKey = tipKey;
        this.tipTitle = tipTitle;
        this.tipDescribe = tipDescribe;
    }

    public int getTipKey() {
        return tipKey;
    }

    public void setTipKey(int tipKey) {
        this.tipKey = tipKey;
    }

    public String getTipTitle() {
        return tipTitle;
    }

    public void setTipTitle(String tipTitle) {
        this.tipTitle = tipTitle;
    }

    public String getTipDescribe() {
        return tipDescribe;
    }

    public void setTipDescribe(String tipDescribe) {
        this.tipDescribe = tipDescribe;
    }

    @Override
    public String toString() {
        return "TipItem{" +
                "tipKey=" + tipKey +
                ", tipTitle='" + tipTitle + '\'' +
                ", tipDescribe='" + tipDescribe + '\'' +
                '}';
    }
}
