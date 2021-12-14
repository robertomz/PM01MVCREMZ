package com.example.pm01mvcremz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pm01mvcremz.Config.SQLiteConn;
import com.example.pm01mvcremz.Config.Transacciones;
import com.example.pm01mvcremz.Model.Personas;

import java.util.ArrayList;

public class ActivityEdit extends AppCompatActivity {

    SQLiteConn conn;
    private Personas item;
    EditText txtNombreA, txtApellidoA, txtDireccionA, txtEdadA, txtPuestoA;
    Button btnEditar, btnEliminar;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        conn = new SQLiteConn(this, Transacciones.NameDataBase, null, 1);

        item = (Personas) getIntent().getSerializableExtra("objeto");

        txtNombreA = findViewById(R.id.txtNombreA);
        txtApellidoA = findViewById(R.id.txtApellidoA);
        txtDireccionA = findViewById(R.id.txtDireccionA);
        txtEdadA = findViewById(R.id.txtEdadA);
        txtPuestoA = findViewById(R.id.txtPuestoA);

        id = item.getId();
        txtNombreA.setText(item.getNombre());
        txtApellidoA.setText(item.getApellido());
        txtEdadA.setText(item.getEdad());
        txtDireccionA.setText(item.getDireccion());
        txtPuestoA.setText(item.getPuesto());

        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);

        btnEditar.setOnClickListener(view -> {
            Actualizar();
        });

        btnEliminar.setOnClickListener(view -> {
            Eliminar();
        });
    }

    private void Actualizar() {
        SQLiteDatabase db = conn.getWritableDatabase();

        String [] params = {
                id.toString()
        };

        ContentValues valores = new ContentValues();

        valores.put(Transacciones.nombre, txtNombreA.getText().toString());
        valores.put(Transacciones.apellido, txtApellidoA.getText().toString());
        valores.put(Transacciones.edad, txtEdadA.getText().toString());
        valores.put(Transacciones.direccion, txtDireccionA.getText().toString());
        valores.put(Transacciones.puesto, txtPuestoA.getText().toString());

        db.update(Transacciones.tablePersonas, valores, Transacciones.id + "=?", params);

        Toast.makeText(getApplicationContext(), "Editado", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void Eliminar() {
        SQLiteDatabase db = conn.getWritableDatabase();

        String [] params = {
                id.toString()
        };

        String wherecond = Transacciones.id + "=?";

        db.delete(Transacciones.tablePersonas, wherecond, params);

        Toast.makeText(getApplicationContext(), "Eliminado", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}