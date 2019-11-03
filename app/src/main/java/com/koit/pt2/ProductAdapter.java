package com.koit.pt2;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private Activity activity;
    private int layout;
    private List<Product> list;

    public ProductAdapter() {
    }

    public ProductAdapter(Activity activity, int layout, List<Product> list) {
        this.activity = activity;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final TextView tvName, tvPrice;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(layout, null);
            tvName = view.findViewById(R.id.tvName);
            tvPrice = view.findViewById(R.id.tvPrice);

            view.setTag(R.id.tvName, tvName);
            view.setTag(R.id.tvPrice, tvPrice);


        } else {
            tvName = (TextView) view.getTag(R.id.tvName);
            tvPrice = (TextView) view.getTag(R.id.tvPrice);

        }

        final Product p = list.get(i);
        tvName.setText(p.getName());
        tvPrice.setText(p.getPrice()+"");
        return view;
    }
}
