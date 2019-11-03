package com.koit.pt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etPrice;
    private SQLiteDatabase db = null;
    private Spinner spinner;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        spinner = findViewById(R.id.spinner);

        dbHelper = new DBHelper(this, "categories.db", null, 2);
        db = dbHelper.getWritableDatabase();

       // String insert_catagory = "insert into Category(name) values (?)";
        addCategories("AAA");
        addCategories("BBB");
        addCategories("CCC");
        ArrayList<Category> listCategories = getCatagories();
        List<String> list = new ArrayList<>();
        for (Category c : listCategories) {
            list.add(c.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);

    }


    public void addCategories(String name) {
        String insert_data = "INSERT INTO Category(name) VALUES(?)";
        db.execSQL(insert_data,new Object[]{name});
    }

    public ArrayList<Category> getCatagories() {
        ArrayList<Category> list = null;
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Category", new String[]{"id", "name"}, null, null, null, null, null);
        list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            Category category = new Category(id, name);
            list.add(category);
        }
        db.close();
        return list;
    }

    public int getCatIdByName(String name) {
        int id = 0;
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Category", new String[]{"id"}, "name=?", new String[]{name}, null, null, null);
        if (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex("id"));
        }
        db.close();
        return id;
    }

    public void add(View view) {
        String name = etName.getText().toString();
        float price = Float.parseFloat(etPrice.getText().toString());
        int id_cat = getCatIdByName((String) spinner.getSelectedItem());
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        values.put("id_cat", id_cat);
        db.insert("Product", null, values);
        db.close();
    }

    public ArrayList<Product> getProduct() {
        ArrayList<Product> list = null;
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Product", new String[]{"id", "name", "price", "id_cat"}, null, null, null, null, null);
        list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            float price = cursor.getFloat(cursor.getColumnIndex("price"));
            int id_cat = cursor.getInt(cursor.getColumnIndex("id_cat"));
            Product p = new Product(id,name,price,id_cat);
            list.add(p);
        }
        db.close();
        return list;
    }



    public void showAll(View view){
        Intent intent = new Intent(this, ShowAllActivity.class);
        ArrayList<Product> list = getProduct();
        for(int i = 0; i < list.size(); i++){
            intent.putExtra("product"+i, list.get(i));
        }
        intent.putExtra("size", list.size());
        startActivity(intent);
    }
}
