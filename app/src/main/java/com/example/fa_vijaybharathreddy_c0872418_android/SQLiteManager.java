package com.example.fa_vijaybharathreddy_c0872418_android;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SQLiteManager extends SQLiteOpenHelper
{
    private static SQLiteManager sqLiteManager;

    private static final String DATABASE_NAME = "ProductsDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Products";
    private static final String COUNTER = "Counter";

     String ID_FIELD = "id";
    String PRICE_FIELD = ("price");
     String LATITUDE_FIELD = ("latitude");
    String LONGITUDE_FIELD = ("longitude");
   String NAME_FIELD = "productsName";
     String DESC_FIELD = "desc";
    String DELETED_FIELD = "deleted";

    @SuppressLint("SimpleDateFormat")
    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    public SQLiteManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManager instanceOfDatabase(Context context)
    {
        if(sqLiteManager == null)
            sqLiteManager = new SQLiteManager(context);

        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD)
                .append(" INT, ")
                .append(NAME_FIELD)
                .append(" TEXT, ")
                .append(PRICE_FIELD)
                .append(" INT, ")
                .append(DELETED_FIELD)
                .append(" TEXT)");

        sqLiteDatabase.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
//
    }

    public void addProductToDatabase(Products products)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, products.getId());
        contentValues.put(NAME_FIELD, products.getProductPrice());
        contentValues.put(DESC_FIELD, products.getDescription());
        contentValues.put(String.valueOf(PRICE_FIELD), products.getProductPrice());
        contentValues.put(String.valueOf(LATITUDE_FIELD), products.getLatitude());
        contentValues.put(String.valueOf(LONGITUDE_FIELD), products.getLongitude());
        contentValues.put(DELETED_FIELD, getStringFromDate(products.getDeleted()));

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void populateProductListArray()
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null))
        {
            if(result.getCount() != 0)
            {
                while (result.moveToNext())

                {
                    int id = result.getInt(1);

                    String productName = result.getString(2);
                    int price = result.getInt(3);
                    Double latitude= result.getDouble(4);
                    Double longitude = result.getDouble(5);
                    String desc = result.getString(6);
                    String stringDeleted = result.getString(7);
                    Date deleted = getDateFromString(stringDeleted);
                    Products products = new Products(id,productName,price,latitude,longitude,desc,deleted);
                    Products.productsArrayList.add(products);
                }
            }
        }
    }

    public void updateProductInDB(Products products)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, products.getId());
        contentValues.put(NAME_FIELD, products.getProductName());
        contentValues.put(String.valueOf(PRICE_FIELD), products.getProductPrice());
        contentValues.put(String.valueOf(LONGITUDE_FIELD), products.getLongitude());
        contentValues.put(String.valueOf(LATITUDE_FIELD), products.getLatitude());
        contentValues.put(DESC_FIELD, products.getDescription());
        contentValues.put(DELETED_FIELD, getStringFromDate(products.getDeleted()));

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + " =? ", new String[]{String.valueOf(products.getId())});
    }

    private String getStringFromDate(Date date)
    {
        if(date == null)
            return null;
        return dateFormat.format(date);
    }

    private Date getDateFromString(String string)
    {
        try
        {
            return dateFormat.parse(string);
        }
        catch (ParseException | NullPointerException e)
        {
           return null;
        }
    }
}

















