package com.malaria;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.malaria.models.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class StatisticActivity extends AppCompatActivity {
    private String uid;
//    BarChart treatmentTrend;
    LineChart treatmentTrend;
    ArrayList<Entry> ydata;
    private ArrayList<String> labels;
    private BarData barData;
    private BarDataSet barDataSet;
    private Long now_ms;
    FirebaseAuth auth;
    DatabaseReference patientRef;
    ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        if (user != null){
            uid=user.getUid();
        }
//        entries=new ArrayList<>();
        labels=new ArrayList<>();
        treatmentTrend=findViewById(R.id.statistics_chart_trend);
        treatmentTrend.setTouchEnabled(true);
        treatmentTrend.setDragEnabled(true);
        treatmentTrend.setScaleEnabled(true);
        patientRef=FirebaseDatabase.getInstance().getReference("Patients");
        patientRef.addValueEventListener(valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 ydata=new ArrayList<>();
                 int i=0;
                 for (DataSnapshot dataSnapshot :snapshot.getChildren()){
                     i++;
                     long childCount=dataSnapshot.getChildrenCount();
                     ydata.add(new Entry(i,childCount));

                 }
                 final LineDataSet lineDataSet=new LineDataSet(ydata,"Patient Treatment");
                 LineData lineData=new LineData(lineDataSet);
                 treatmentTrend.setData(lineData);
                 treatmentTrend.notifyDataSetChanged();
                 treatmentTrend.invalidate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        float yValues [] = {10, 20, 30, 40, 50};
//        String xValues [] = {"first", "second", "third", "four", "five"};
//
//        XAxis xAxis = treatmentTrend.getXAxis();
//        xAxis.setGranularity(1f);
//        xAxis.setGranularityEnabled(true);
//        drawBarGraph(yValues,xValues);
//        patientRef= FirebaseDatabase.getInstance().getReference("Patients");

    }

//    private void drawBarGraph(float[] yValues, String[] xValues) {
//        ArrayList<BarEntry> yData = new ArrayList<>();
//        for (int i = 0; i < yValues.length; i++){
//            yData.add(new BarEntry(i,yValues[i]));
//        }
//
//
//        BarDataSet barDataSet = new BarDataSet(yData, "Cells");
//        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//
//
//        BarData barData =  new BarData(barDataSet);
//
//        barData.setValueTextSize(13f);
//        barData.setValueTextColor(Color.MAGENTA);
//
//        treatmentTrend.getXAxis().setValueFormatter(new LabelFormatter(xValues));
//        treatmentTrend.setData(barData);
//        treatmentTrend.invalidate();
//
//    }
    public class LabelFormatter implements IAxisValueFormatter {
        private final String[ ] mLabels;

        public LabelFormatter(String[] labels) {
            mLabels = labels;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mLabels[(int) value];
        }
    }
    private void initLayout() {
        initializeCharts();
    }

    private void initializeCharts() {
//        ChartUtils.setChartDefaultStyle(chartTrend, category);


    }
}