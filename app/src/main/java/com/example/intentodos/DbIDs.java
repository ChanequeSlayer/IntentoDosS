package com.example.intentodos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

public class DbIDs extends AdminSQLiteOpenHelpder{
    public DbIDs(@Nullable Context context) {
        super(context);
    }

    public Cursor mostrarCategorias(){
        try{
            SQLiteDatabase bd = this.getReadableDatabase();
            Cursor filas = bd.rawQuery("select idCliente from clientes",null);
            if(filas.moveToFirst()){
                return filas;
            }else{
                return null;
            }
        }catch(Exception ex){
            return null;
        }
    }

    public Cursor mostrarNombres(){
        try{
            SQLiteDatabase bd = this.getReadableDatabase();
            Cursor filas = bd.rawQuery("select nombre from clientes",null);
            if(filas.moveToFirst()){
                return filas;
            }else{
                return null;
            }
        }catch(Exception ex){
            return null;
        }
    }



}
