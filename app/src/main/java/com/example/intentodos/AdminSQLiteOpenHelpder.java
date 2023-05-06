package com.example.intentodos;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class AdminSQLiteOpenHelpder extends SQLiteOpenHelper {


    public AdminSQLiteOpenHelpder(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase ValesDB) {

        ValesDB.execSQL("create table clientes(idCliente text not null unique primary key,nombre text not null,apellidoP text not null ,apellidoM text not null," +
                "direccion text not null, telefono text not null,nacimiento date not null)");


        //id del cliente
        //nombre(s) cliente
        //apellidoP
        //apellidoM
        //Direccion
        //telefono
        //nacimiento

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
