package com.example.intentodos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class Clientes extends AppCompatActivity {

    private EditText txtNombre, txtApellidoP, txtApellidoM, txtNumber, txtDireccion;
    private EditText txtVID;

    Spinner spinU;
    Spinner spinD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);


        spinU = (Spinner) findViewById(R.id.spinU);
        spinD = (Spinner) findViewById(R.id.spinD);


        txtVID = (EditText) findViewById(R.id.txtVID);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellidoP = (EditText) findViewById(R.id.txtApellidoP);
        txtApellidoM = (EditText) findViewById(R.id.txtApellidoM);
        txtNumber = (EditText) findViewById(R.id.txtNumber);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);


        List<IDS> ListaCategorias = LLenadoList();
        ArrayAdapter<IDS> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.support.constraint.R.layout.support_simple_spinner_dropdown_item,ListaCategorias);
        spinU.setAdapter(arrayAdapter);

        List<Names> ListaNombres =  LLenadoNombres();
        ArrayAdapter<Names> arrayAdapterD = new ArrayAdapter<>(getApplicationContext(), android.support.constraint.R.layout.support_simple_spinner_dropdown_item,ListaNombres);
        spinD.setAdapter(arrayAdapterD);


    }
    @SuppressLint("Range")
    private List<IDS> LLenadoList() {
        List<IDS> ListaCat = new ArrayList<>();
        DbIDs dbCate = new DbIDs(Clientes.this);
        Cursor cursor = dbCate.mostrarCategorias();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    IDS cat = new IDS();
                    cat.setId(cursor.getInt(cursor.getColumnIndex("idCliente")));
                    ListaCat.add(cat);
                } while (cursor.moveToNext());

            }
        }
        dbCate.close();
        return ListaCat;
    }

    @SuppressLint("Range")
    private List<Names> LLenadoNombres() {
        List<Names> ListaCat = new ArrayList<>();
        DbIDs dbCate = new DbIDs(Clientes.this);
        Cursor cursor = dbCate.mostrarNombres();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
              Names cat = new Names();
              cat.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                    ListaCat.add(cat);
                } while (cursor.moveToNext());

            }
        }
        dbCate.close();
        return ListaCat;
    }






    public void Buscar(View view) {
        AdminSQLiteOpenHelpder admin = new AdminSQLiteOpenHelpder(this);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String id = txtVID.getText().toString();

        if (!id.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery("select nombre,apellidoP,apellidoM,telefono,direccion from clientes where idCliente = " + id, null);

            if (fila.moveToFirst()) {
                txtNombre.setText(fila.getString(0));
                txtApellidoP.setText(fila.getString(1));
                txtApellidoM.setText(fila.getString(2));
                txtNumber.setText(fila.getString(3));
                txtDireccion.setText(fila.getString(4));

                BaseDeDatos.close();
            } else {
                Toast.makeText(this, "No existe el cliente.", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }


        } else {
            Toast.makeText(this, "Debes llenar todos los campos.", Toast.LENGTH_SHORT).show();

        }


    }

    public void Eliminar(View view) {
        AdminSQLiteOpenHelpder admin = new AdminSQLiteOpenHelpder(this);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String nombre = txtNombre.getText().toString();
        if (!nombre.isEmpty()) {
            int cantidad = BaseDeDatos.delete("clientes", "nombre = '" + nombre+"'", null);
            BaseDeDatos.close();
            txtNombre.setText("");
            txtApellidoP.setText("");
            txtApellidoM.setText("");
            txtNumber.setText("");
            txtDireccion.setText("");

            if (cantidad == 1) {
                Toast.makeText(this, "Se elimino exitosamente.", Toast.LENGTH_SHORT).show();

                List<IDS> ListaCategorias = LLenadoList();
                ArrayAdapter<IDS> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.support.constraint.R.layout.support_simple_spinner_dropdown_item,ListaCategorias);
                spinU.setAdapter(arrayAdapter);


            } else {
                Toast.makeText(this, "No existe el cliente.", Toast.LENGTH_SHORT).show();

            }


        } else {
            Toast.makeText(this, "Debes escribir el nombre del cliente", Toast.LENGTH_SHORT).show();
        }
    }

    public void Configurar(View view) {
        AdminSQLiteOpenHelpder admin = new AdminSQLiteOpenHelpder(this);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombreN = txtNombre.getText().toString();
        String ApellidoP = txtApellidoP.getText().toString();
        String ApellidoM = txtApellidoM.getText().toString();
        String numeroT = txtNumber.getText().toString();
        String direccion = txtDireccion.getText().toString();

        if (!nombreN.isEmpty() && !ApellidoP.isEmpty() && !ApellidoM.isEmpty() &&
                !numeroT.isEmpty() && !direccion.isEmpty()) {
            ContentValues registro = new ContentValues();

            registro.put("nombre", nombreN);
            registro.put("apellidoP", ApellidoP);
            registro.put("apellidoM", ApellidoM);
            registro.put("telefono", numeroT);
            registro.put("direccion", direccion);


            int cantidad = BaseDeDatos.update("clientes", registro, "nombre = '"+nombreN+"'", null);

            BaseDeDatos.close();

            if (cantidad == 1) {
                Toast.makeText(this, "Registro Exitoso Modificado", Toast.LENGTH_SHORT).show();


                txtNombre.setText("");
                txtApellidoP.setText("");
                txtApellidoM.setText("");
                txtNumber.setText("");
                txtDireccion.setText("");

            } else {
                Toast.makeText(this, "No se modifico.", Toast.LENGTH_SHORT).show();

                txtNombre.setText("");
                txtApellidoP.setText("");
                txtApellidoM.setText("");
                txtNumber.setText("");
                txtDireccion.setText("");
            }
        } else {
            Toast.makeText(this, "Debes llenar todos los campos.", Toast.LENGTH_SHORT).show();
        }
    }


    public void irPrincipal(View view) {
        Intent irPrincipal = new Intent(this, MainActivity.class);
        startActivity(irPrincipal);
    }

    //Metodo para registrar Clientes.
    public void Registrar(View view) {
        AdminSQLiteOpenHelpder admin = new AdminSQLiteOpenHelpder(this);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        //String idF = String.valueOf(idGenerador);
        //txtVID.setText(idF);


        String id = txtVID.getText().toString();
        String nombre = txtNombre.getText().toString();
        String ApellidoP = txtApellidoP.getText().toString();
        String ApellidoM = txtApellidoM.getText().toString();
        String numeroT = txtNumber.getText().toString();
        String direccion = txtDireccion.getText().toString();


        if (!id.isEmpty() && !nombre.isEmpty() && !ApellidoP.isEmpty() && !ApellidoM.isEmpty() &&
                !numeroT.isEmpty() && !direccion.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("idCliente",id);
            registro.put("nombre",nombre);
            registro.put("apellidoP",ApellidoP);
            registro.put("ApellidoM",ApellidoM);
            registro.put("telefono",numeroT);
            registro.put("direccion",direccion);

            BaseDeDatos.insert("clientes",null,registro);

            BaseDeDatos.close();
            //idGenerador++;

            //idF = String.valueOf(idGenerador);
            //txtVID.setText(idF);
            txtVID.setText("");
            txtNombre.setText("");
            txtApellidoP.setText("");
            txtApellidoM.setText("");
            txtNumber.setText("");
            txtDireccion.setText("");

            Toast.makeText(this,"Registro Exitoso", Toast.LENGTH_SHORT).show();

            List<IDS> ListaCategorias = LLenadoList();
            ArrayAdapter<IDS> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.support.constraint.R.layout.support_simple_spinner_dropdown_item,ListaCategorias);
            spinU.setAdapter(arrayAdapter);


        }else{
            Toast.makeText(this,"Debes llenar todos los campos.", Toast.LENGTH_SHORT).show();
        }

    }
}
