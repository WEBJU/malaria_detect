package com.malaria.utils;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;


import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.malaria.R;


public class ChartUtils {

    public static final int NO_DATA_TEXT_RESOURCE_ID = R.string.no_data;
    public static final int NO_DATA_COLOR_RESOURCE_ID = R.color.grey_primary_dark;

    public static final float LINE_WIDTH = 2;

    private static final float VIEW_PORT_OFFSET = 10;
    private static final float TEXT_SIZE = 14;

    public static void setChartDefaultStyle(BarLineChartBase chart) {
        Context context = chart.getContext();
        int textColor = ContextCompat.getColor(context, R.color.grey_primary);
        int gridColor = ContextCompat.getColor(context, android.R.color.darker_gray);

        // General
        chart.setDrawGridBackground(false);
        chart.setBackgroundColor(Color.TRANSPARENT);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        chart.setExtraLeftOffset(VIEW_PORT_OFFSET);
        chart.setExtraBottomOffset(VIEW_PORT_OFFSET);

        // Text
        chart.getLegend().setEnabled(false);
        chart.setDescription(null);
        chart.getXAxis().setTextSize(TEXT_SIZE);
        chart.getAxisLeft().setTextSize(TEXT_SIZE);
        chart.setNoDataText(context.getString(NO_DATA_TEXT_RESOURCE_ID));
        chart.getPaint(Chart.PAINT_INFO).setColor(ContextCompat.getColor(context, NO_DATA_COLOR_RESOURCE_ID));

        // Axes
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setDrawAxisLine(false);
        chart.getXAxis().setGridColor(gridColor);
        chart.getXAxis().setTextColor(textColor);
        chart.getXAxis().setAxisMinimum(1);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getAxisLeft().setGridColor(gridColor);
        chart.getAxisLeft().setTextColor(textColor);
        chart.getAxisLeft().setDrawLimitLinesBehindData(true);
//        float yAxisMinValue = PreferenceStore.getInstance().getExtrema(category)[0] * .9f;
//        float yAxisMinCustomValue = PreferenceStore.getInstance().formatDefaultToCustomUnit(category, yAxisMinValue);
//        chart.getAxisLeft().setAxisMinimum(yAxisMinCustomValue);
//        chart.getAxisLeft().setValueFormatter((value, axis) -> FloatUtils.parseFloat(value));
    }

    public static LimitLine getLimitLine(Context context, float yValue, @ColorRes int color) {
        LimitLine limitLine = new LimitLine(yValue, null);
        limitLine.setLineColor(ContextCompat.getColor(context, color));
        limitLine.setLabel(null);
        return limitLine;
    }
}
