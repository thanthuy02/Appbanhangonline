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

    // Khai báo biến binding.
    // Đây là đối tượng được tạo tự động bởi Data Binding để liên kết các thành phần giao diện trong layout của activity.
    private ActivityAdminRevenueBinding binding;

    // Được gọi khi activity được tạo.
    // Trong phương thức này, các bước khởi tạo và thiết lập giao diện và xử lý sự kiện được thực hiện.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tạo đối tượng binding bằng cách inflate layout ActivityAdminRevenueBinding và gán cho biến binding.
        // Điều này liên kết layout XML với activity.
        binding = ActivityAdminRevenueBinding.inflate(getLayoutInflater());

        // Lấy ra root view từ binding.
        // Root view là view gốc của layout activity.
        View rootView = binding.getRoot();

        // Thiết lập layout gốc cho activity bằng cách sử dụng root view.
        setContentView(rootView);

        // Xử lý sự kiện nút "Back":
        // Khi nút "Back" được nhấp vào, chuyển đến MenuAdminActivity và kết thúc activity hiện tại.
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), MenuAdminActivity.class));
                finish();
            }
        });
        setupChart();
    }

    // Phương thức setupChart(): Được sử dụng để thiết lập và hiển thị biểu đồ.
    private void setupChart() {

        // getRevenueDataFromBill():
        // Phương thức này truy xuất dữ liệu doanh thu từ hóa đơn và trả về một danh sách các BarEntry đại diện cho dữ liệu doanh thu.
        List<BarEntry> revenueData = getRevenueDataFromBill();

        // Tạo một BarDataSet từ các BarEntry:
        // Đầu tiên, tạo một BarDataSet bằng cách truyền danh sách revenueData và một chuỗi nhãn cho dataset.
        BarDataSet dataSet = new BarDataSet(revenueData, "Revenue");

        // Tùy chỉnh giao diện của dataset: Lấy màu từ resource color và đặt màu cho dataSet.
        int color = ContextCompat.getColor(this, R.color.curious_blue);
        dataSet.setColor(color);

        // Tạo BarData và gán cho biểu đồ: Tạo một đối tượng BarData bằng cách truyền dataSet và gán cho biểu đồ.
        BarData barData = new BarData(dataSet);
        binding.chart.setData(barData);

        // Tùy chỉnh giao diện của biểu đồ:
        // Vô hiệu hóa hiển thị bóng của các thanh cột, hiển thị giá trị trên cột và vô hiệu hóa mô tả của biểu đồ.
        binding.chart.setDrawBarShadow(false);
        binding.chart.setDrawValueAboveBar(true);
        binding.chart.getDescription().setEnabled(false);

        // Tùy chỉnh trục X (trục tháng):
        // Lấy trục X (xAxis) từ biểu đồ, đặt các thuộc tính của trục X và đặt ValueFormatter để tạo nhãn cho trục X.
        XAxis xAxis = binding.chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getXAxisLabels()));

        // Tùy chỉnh trục Y (trục tổng doanh thu theo tháng):
        // Lấy trục Y (yAxisLeft) từ biểu đồ, đặt các thuộc tính của trục Y.
        YAxis yAxisLeft = binding.chart.getAxisLeft();
        yAxisLeft.setGranularity(1f);
        yAxisLeft.setGranularityEnabled(true);

        // Cập nhật biểu đồ: Gọi invalidate() để cập nhật biểu đồ.
        binding.chart.invalidate();
    }

    // Phương thức getRevenueDataFromBill():
    // Phương thức này lấy danh sách hóa đơn từ cơ sở dữ liệu và tính toán doanh thu dựa trên danh sách hóa đơn.
    // Nó sử dụng BillHandler để lấy danh sách hóa đơn và sau đó tính tổng doanh thu theo tháng và lưu vào một Map<Integer, Integer>.
    private List<BarEntry> getRevenueDataFromBill() {

        // Tạo danh sách revenueData từ dữ liệu doanh thu:
        // Duyệt qua các cặp key-value trong revenueByMonth, lấy tháng và doanh thu,
        // sau đó tạo một đối tượng BarEntry từ tháng và doanh thu và thêm vào danh sách revenueData.
        List<BarEntry> revenueData = new ArrayList<>();

        BillHandler billHandler = new BillHandler(this);
        List<Bill> bills = billHandler.getAllBill();

        Map<Integer, Integer> revenueByMonth = new HashMap<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Bill bill : bills) {
            try {
                Date createdAt = dateFormat.parse(bill.getCreatedAt());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(createdAt);
                int month = calendar.get(Calendar.MONTH);
                int totalPrice = bill.getBillTotalPrice();

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

        for (Map.Entry<Integer, Integer> entry : revenueByMonth.entrySet()) {
            int month = entry.getKey();
            int revenue = entry.getValue();

            revenueData.add(new BarEntry(month, revenue));
        }

        return revenueData;
    }

    // Phương thức getXAxisLabels():
    // Trả về một danh sách chuỗi tháng cho trục X.
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
