package com.example.intentodos;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class AdminSQLiteOpenHelpder extends SQLiteOpenHelper {


    public AdminSQLiteOpenHelpder(@Nullable Context context) {
        super(context, "vales", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase vales) {

        vales.execSQL("create table clientes(idCliente int primary key UNIQUE ,nombre text,apellidoP text,apellidoM text,telefono int, direccion text )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase vales, int oldVersion, int newVersion) {
        vales.execSQL("DROP TABLE IF EXISTS clientes");
        onCreate(vales);
    }
}
