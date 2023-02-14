package com.example.fa_vijaybharathreddy_c0872418_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Products> {
    public ProductAdapter(Context context, List<Products> notes)
    {
        super(context, 0, notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Products products = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_cell, parent, false);

        TextView title = convertView.findViewById(R.id.cellname);
        TextView pID = convertView.findViewById(R.id.cellId);

        title.setText(products.getProductName());
        pID.setText(products.getId());

        return convertView;
    }
}
