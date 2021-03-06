package com.example.dg.myapplication;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;


public class MyPage extends Fragment {

    public static final float MAX = 5, MIN = 0;
    public static final int NB_QUARLITIES = 5;
    private RadarChart chart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = getLayoutInflater().inflate(R.layout.fragment_my_page, container, false);

        chart = (RadarChart) v.findViewById(R.id.chart);

        drawChart();

        return v;
    }

    private void drawChart(){

        // configure the radar chart
        chart.setBackgroundColor(Color.rgb(60,65,82));
        chart.getDescription().setEnabled(false);
        chart.setWebLineWidth(1f);
        // export graph
        chart.setWebColor(Color.WHITE);
        chart.setWebLineWidth(1f);
        chart.setWebColorInner(Color.WHITE);
        chart.setWebAlpha(100);

        setData();

        // animate the chart
        chart.animateXY(1400, 1400, Easing.EasingOption.EaseInOutQuad, Easing.EasingOption.EaseInOutQuad);

        // define axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setTextSize(18f);
        xAxis.setYOffset(0);
        xAxis.setXOffset(0);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            // compare qualities
            private String[] qualities = new String[] {"1항목", "2항목", "3항목", "4항목", "5항목"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return qualities[(int) value % qualities.length];
            }
        });

        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = chart.getYAxis();
        yAxis.setLabelCount(NB_QUARLITIES, false);
        yAxis.setTextSize(10f);
        yAxis.setAxisMinimum(MIN);
        yAxis.setAxisMaximum(MAX); //min MAX
        yAxis.setDrawLabels(false);

        //configure legend for our radar chart
        Legend l = chart.getLegend();
        l.setTextSize(30f);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setXEntrySpace(0);
        l.setYEntrySpace(0);
        l.setTextColor(Color.WHITE);
    }

    private void setData(){
        ArrayList<RadarEntry> User = new ArrayList<>();

        //1항목 데이터
        User.add(new RadarEntry(5));
        //2항목
        User.add(new RadarEntry(5));
        //3항목
        User.add(new RadarEntry(5));
        //4항목
        User.add(new RadarEntry(1));
        //5항목
        User.add(new RadarEntry(5));

        RadarDataSet set1 = new RadarDataSet(User, "User");
        set1.setColor(Color.GREEN);
        set1.setFillColor(Color.GREEN);
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightIndicators(false);
        set1.setDrawHighlightCircleEnabled(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);

        RadarData data = new RadarData(sets);
        data.setValueTextSize(10f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        chart.setData(data);
        chart.invalidate();;
    }

}
