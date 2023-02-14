package com.example.fa_vijaybharathreddy_c0872418_android;

import static com.example.fa_vijaybharathreddy_c0872418_android.SQLiteManager.instanceOfDatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView productListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        loadFromDBToMemory();
        setNoteAdapter();
        setOnClickListener();
    }

    private void initWidgets()
    {
        productListView = findViewById(R.id.productListView);
    }

    private void loadFromDBToMemory()
    {
        SQLiteManager sqLiteManager = instanceOfDatabase(this);
        sqLiteManager.populateProductListArray();
    }

    private void setNoteAdapter()
    {
        ProductAdapter noteAdapter = new ProductAdapter(getApplicationContext(), Products.nonDeletedNotes());
        productListView.setAdapter(noteAdapter);
    }


    private void setOnClickListener()
    {
        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Products selectedProduct = (Products) productListView.getItemAtPosition(position);
                Intent editProductIntent = new Intent(getApplicationContext(), ProductDetails.class);
                editProductIntent.putExtra(Products.PRODUCT_EDIT_EXTRA, selectedProduct.getId());
                startActivity(editProductIntent);
            }
        });
    }


    public void newProduct(View view)
    {
        Intent newNoteIntent = new Intent(this, ProductDetails.class);
        startActivity(newNoteIntent);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setNoteAdapter();
    }

}