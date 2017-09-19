package me.memory7734.moving.app;

/**
 * Created by Elijah on 2016-9-13.
 */
public class Constant {
    public static final String[] DATA_TYPE_NAME_ZH_CN = new String[]{
            "健身记录",
            "活动能量",
            "骑行距离",
            "锻炼分钟数",
            "步行+跑步距离",
            "步数",

            "血糖",
            "最大肺活量",
            "血液酒精浓度",

            "身高",
            "体重"
    };
    public static final boolean[] DATA_TYPE_IS_DECIMAL = new boolean[]{
            false,
            false,
            true,
            false,
            true,
            false,

            true,
            false,
            true,

            true,
            true
    };
    public static final String[] DATA_TYPE_MEASURINGUNIT_ZH_CN = new String[]{
            "分钟",
            "焦耳",
            "千米",
            "分钟",
            "千米",
            "步",

            "mmol/L",
            "毫升",
            "毫克",

            "厘米",
            "千克"
    };

    public static final String[] DATA_TYPE_NAME_EN = new String[]{
            "Activity",
            "Activitie Energy",
            "Cycling Distance",
            "Exercise Mintues",
            "Walking Running Distance",
            "Step",

            "Blood Glucose",
            "Forced Vital Vapacity",
            "Blood Alcohol Content",

            "Height",
            "Weight"
    };
    public static final String[] DATA_TYPE_NAME = new String[]{
            "activity",
            "activite_energy",
            "cycling_distance",
            "exercise_mintues",
            "walking_running_distance",
            "step",

            "blood_glucose",
            "forced_vital_vapacity",
            "blood_alcohol_content",

            "height",
            "weight"
    };

    public static final String DATABASE_NAME = "moving.db";
    public static final String DATABASE_TABLE_NAME = "data";

    //健康数据
    public static final int DATA_TYPE_FITNESS = 1;
    //医疗数据
    public static final int DATA_TYPE_MEDICAL = 2;
    //身体数据
    public static final int DATA_TYPE_BODY = 3;

    //健康数据数量
    public static final int DATA_TYPE_FITNESS_NUMBER = 6;
    //医疗数据数量
    public static final int DATA_TYPE_MEDICAL_NUMBER = 3;
    //身体数据数量
    public static final int DATA_TYPE_BODY_NUMBER = 2;

    public static final float[] DATA_TYPE_GOAL = {100, 600, 10, 100, 10, 8000, 100, 1, 1, 100, 50};
}
