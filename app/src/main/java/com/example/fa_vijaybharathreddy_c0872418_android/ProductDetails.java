package com.example.fa_vijaybharathreddy_c0872418_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class ProductDetails extends AppCompatActivity {
    private EditText nameEditText, descEditText,priceEditText,idEditText,latitudeEditText,longitudeEdittext;
    private Button deleteButton;
    private Products selectedProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        intitWidgets();
        checkForEditProducts();


    }

    private void checkForEditProducts() {
        Intent previousIntent = getIntent();

        int passedProductID = previousIntent.getIntExtra(Products.PRODUCT_EDIT_EXTRA, -1);
        selectedProducts = Products.getProductsForID(passedProductID);

        if (selectedProducts != null)
        {
            nameEditText.setText(selectedProducts.getProductName());
            descEditText.setText(selectedProducts.getDescription());
            idEditText.setText(selectedProducts.getId());
            priceEditText.setText(selectedProducts.getProductPrice());
            latitudeEditText.setText(selectedProducts.getLatitude().toString());
            longitudeEdittext.setText(selectedProducts.getLongitude().toString());
        }
        else
        {
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    public void saveProduct(View view)
    {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String name = String.valueOf(nameEditText.getText());
        String desc = descEditText.getText().toString();
        int price = Integer.parseInt(priceEditText.getText().toString());
        Double latitude = Double.parseDouble(latitudeEditText.getText().toString());
        Double longitude =Double.parseDouble( longitudeEdittext.getText().toString());
        int pID = Integer.parseInt(String.valueOf(idEditText.getText()));



        if(selectedProducts == null)
        {
            int pd = Products.productsArrayList.size();
            Products newProducts = new Products(pID,name,desc,price,latitude,longitude);
            Products.productsArrayList.add(newProducts);
            sqLiteManager.addProductToDatabase(newProducts);
        }
        else
        {
            selectedProducts.setProductName(name);
            selectedProducts.setDescription(desc);
            sqLiteManager.updateProductInDB(selectedProducts);
        }

        finish();
    }

    public void deleteNote(View view)
    {
        selectedProducts.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateProductInDB(selectedProducts);
        finish();
    }

    private void intitWidgets() {
        nameEditText = findViewById(R.id.pName_et);
        idEditText = findViewById(R.id.pId_et);
        descEditText = findViewById(R.id.description_et);
        priceEditText = findViewById(R.id.price_et);
        latitudeEditText = findViewById(R.id.latitude_et);
        longitudeEdittext = findViewById(R.id.longitude_et);
        deleteButton = findViewById(R.id.deleteBtn);
    }

}