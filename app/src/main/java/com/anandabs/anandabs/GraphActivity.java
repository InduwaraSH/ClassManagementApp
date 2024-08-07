package com.anandabs.anandabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    private BarChart bar_chart;
    private String IdNum;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private ArrayList<TestMarksAddModule> BindData = new ArrayList<>();
    private ArrayList<BarEntry> ChartEntry = new ArrayList<>();
    private ArrayList<String> Label = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        bar_chart = findViewById(R.id.bar_chart);

        IdNum = getIntent().getStringExtra("idNum");

        databaseReference = FirebaseDatabase.getInstance().getReference("Test Result");
        databaseReference.child(IdNum).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<TestMarksAddModule> BindData = new ArrayList<>();

                for (DataSnapshot item : snapshot.getChildren()) {

                    TestMarksAddModule testMarksAddModule = item.getValue(TestMarksAddModule.class);
                    BindData.add(testMarksAddModule);


                }


                for (int x = 0; x < BindData.size(); x++) {
                    int Marks = Integer.parseInt(BindData.get(x).marks);
                    String TestNo = BindData.get(x).test_num;

                    ChartEntry.add(new BarEntry(x, Marks));
                    Label.add(TestNo);
                }

                BarDataSet barDataSet = new BarDataSet(ChartEntry, "Marks");
                barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);


                BarData barData = new BarData(barDataSet);
                bar_chart.setData(barData);
                XAxis xAxis = bar_chart.getXAxis();

                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setValueFormatter(new IndexAxisValueFormatter(Label));
                bar_chart.animateXY(2700, 3900, Easing.EaseOutQuad, Easing.EaseOutSine);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}