package me.memory7734.moving.bean;

import java.util.Arrays;

import me.memory7734.moving.app.Constant;

/**
 * Created by Elijah on 16/09/07.
 */
public class HealthItem {
    private int dataType;
    private String dataTypeName;
    private String dataTypeMeasuringUnit;
    private float[] values;
    private boolean isDecimal;

    public HealthItem(int dataType, float[] values) {
        this.dataType = dataType;
        this.dataTypeName = Constant.DATA_TYPE_NAME_ZH_CN[dataType];
        this.dataTypeMeasuringUnit = Constant.DATA_TYPE_MEASURINGUNIT_ZH_CN[dataType];
        this.values = values;
        this.isDecimal = Constant.DATA_TYPE_IS_DECIMAL[dataType];
    }

    public HealthItem(int dataType, float[] values, boolean isDecimal) {
        this.dataType = dataType;
        this.dataTypeName = Constant.DATA_TYPE_NAME_ZH_CN[dataType];
        this.dataTypeMeasuringUnit = Constant.DATA_TYPE_MEASURINGUNIT_ZH_CN[dataType];
        this.values = values;
        this.isDecimal = isDecimal;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public String getDataTypeMeasuringUnit() {
        return dataTypeMeasuringUnit;
    }

    public void setDataTypeMeasuringUnit(String dataTypeMeasuringUnit) {
        this.dataTypeMeasuringUnit = dataTypeMeasuringUnit;
    }

    public float[] getValues() {
        return values;
    }

    public void setValues(float[] values) {
        this.values = values;
    }

    public boolean isDecimal() {
        return isDecimal;
    }

    public void setDecimal(boolean decimal) {
        isDecimal = decimal;
    }

    @Override
    public String toString() {
        return "HealthItem{" +
                "dataType=" + dataType +
                ", dataTypeName='" + dataTypeName + '\'' +
                ", dataTypeMeasuringUnit='" + dataTypeMeasuringUnit + '\'' +
                ", values=" + Arrays.toString(values) +
                ", isDecimal=" + isDecimal +
                '}';
    }
}
