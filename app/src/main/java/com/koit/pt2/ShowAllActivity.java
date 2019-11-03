package com.koit.pt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowAllActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);
        listView = findViewById(R.id.listView);
        ArrayList<Product> list = new ArrayList<>();
        Intent intent = getIntent();
        int size = intent.getIntExtra("size", 0);
        for (int i = 0; i < size; i++) {
            Product p = (Product) intent.getSerializableExtra("product" + i);
            list.add(p);
        }

        ProductAdapter adapter = new ProductAdapter(this, R.layout.product_layout, list);
        listView.setAdapter(adapter);
    }
}
