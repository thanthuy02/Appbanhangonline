package com.example.appbanhangonline.activities.admin;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activities.login.LoginActivity;
import com.example.appbanhangonline.activities.user.HomeUserActivity;
import com.example.appbanhangonline.databinding.ActivityAdminCategoryBinding;
import com.example.appbanhangonline.databinding.ActivityAdminMainBinding;

public class MenuAdminActivity extends Activity {

    // Khai báo biến
    // billAdminAdapter: Đối tượng ActivityAdminMainBinding để quản lý danh sách hóa đơn trong RecyclerView.
    private ActivityAdminMainBinding binding;

    // Phương thức onCreate: Được gọi khi activity được tạo.
    // Trong phương thức này, các bước khởi tạo và thiết lập giao diện và xử lý sự kiện được thực hiện.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tạo đối tượng binding bằng cách inflate layout ActivityAdminMainBinding và gán cho biến binding.
        // Điều này liên kết layout XML "activity_admin_main" với activity.
        binding = ActivityAdminMainBinding.inflate(getLayoutInflater());

        // Lấy ra root view từ binding.
        // Root view là view gốc của layout activity.
        View rootView = binding.getRoot();

        // Thiết lập layout gốc cho activity bằng cách sử dụng root view.
        setContentView(rootView);

        // Xử lý sự kiện khi nút "Logout" được click:
        // Gắn một OnClickListener cho nút "Logout" (binding.btnLogout).
        // Khi nút được click, một Intent được tạo để chuyển đến LoginActivity, và sau đó kết thúc activity hiện tại.
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Đăng xuất");
                builder.setMessage("Bạn có muốn đăng xuất không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // Xử lý sự kiện khi thẻ "Category" được click:
        // Gắn một OnClickListener cho thẻ "Category" (binding.cardCategory).
        // Khi thẻ được click, tạo một Intent để chuyển đến CategoryActivity và bắt đầu một activity mới.
        binding.cardCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(i);
            }
        });

        // các xự kiện onClick bên dưới tương tự như trên
        binding.cardProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProductActivity.class));
            }
        });

        binding.cardCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CustomerActivity.class);
                startActivity(i);
            }
        });

        binding.cardBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BillActivity.class);
                startActivity(i);
            }
        });

        binding.cardChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RevenueActivity.class);
                startActivity(i);
            }
        });

    }
}
