package com.example.appbanhangonline.activities.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapters.admin.BillAdminAdapter;
import com.example.appbanhangonline.databinding.ActivityAdminBillBinding;
import com.example.appbanhangonline.databinding.ActivityAdminRevenueBinding;
import com.example.appbanhangonline.dbhandler.BillHandler;
import com.example.appbanhangonline.models.Bill;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RevenueActivity extends Activity {
    private ActivityAdminRevenueBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Khởi tạo binding
        binding = ActivityAdminRevenueBinding.inflate(getLayoutInflater());
        // Lấy ra root view từ binding
        View rootView = binding.getRoot();
        // Gán layout cho Activity
        setContentView(rootView);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), MenuAdminActivity.class));
                finish();
            }
        });
        // Setup and display the chart
        setupChart();
    }

    private void setupChart() {
        // Get the revenue data from Bill
        List<BarEntry> revenueData = getRevenueDataFromBill();

        // Create a dataset from the entries
        BarDataSet dataSet = new BarDataSet(revenueData, "Revenue");

        // Customize the dataset appearance (optional)
        int color = ContextCompat.getColor(this, R.color.curious_blue); // Replace `your_color` with your color resource
        dataSet.setColor(color);

        // Create the bar data and set it to the chart
        BarData barData = new BarData(dataSet);
        binding.chart.setData(barData);

        // Customize the chart appearance
        binding.chart.setDrawBarShadow(false);
        binding.chart.setDrawValueAboveBar(true);
        binding.chart.getDescription().setEnabled(false);

        // Customize the X axis (category axis)
        XAxis xAxis = binding.chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getXAxisLabels())); // Replace with your own X axis labels

        // Customize the Y axis (value axis)
        YAxis yAxisLeft = binding.chart.getAxisLeft();
        yAxisLeft.setGranularity(1f);
        yAxisLeft.setGranularityEnabled(true);

        // Refresh the chart
        binding.chart.invalidate();
    }

    // Retrieve revenue data from bills
    private List<BarEntry> getRevenueDataFromBill() {
        List<BarEntry> revenueData = new ArrayList<>();

        // Use BillHandler to retrieve the list of bills from the database
        BillHandler billHandler = new BillHandler(this);
        List<Bill> bills = billHandler.getAllBill();

        // Process and calculate revenue from the list of bills
        // Example: Total revenue by month
        Map<Integer, Integer> revenueByMonth = new HashMap<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Bill bill : bills) {
            try {
                Date createdAt = dateFormat.parse(bill.getCreatedAt());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(createdAt);
                int month = calendar.get(Calendar.MONTH);
                int totalPrice = bill.getBillTotalPrice();

                // Accumulate revenue by month
                if (revenueByMonth.containsKey(month)) {
                    int totalRevenue = revenueByMonth.get(month) + totalPrice;
                    revenueByMonth.put(month, totalRevenue);
                } else {
                    revenueByMonth.put(month, totalPrice);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // Create Entry list from revenue data
        for (Map.Entry<Integer, Integer> entry : revenueByMonth.entrySet()) {
            int month = entry.getKey();
            int revenue = entry.getValue();

            revenueData.add(new BarEntry(month, revenue));
        }

        return revenueData;
    }

    // Replace this with your own X axis labels
    private List<String> getXAxisLabels() {
        List<String> labels = new ArrayList<>();
        labels.add("Jan");
        labels.add("Feb");
        labels.add("Mar");
        labels.add("Apr");
        labels.add("May");
        labels.add("Jun");
        labels.add("Jul");
        labels.add("Aug");
        labels.add("Sep");
        labels.add("Oct");
        labels.add("Nov");
        labels.add("Dec");

        return labels;
    }
}
