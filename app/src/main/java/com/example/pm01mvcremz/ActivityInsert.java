package com.example.pm01mvcremz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pm01mvcremz.Config.SQLiteConn;
import com.example.pm01mvcremz.Config.Transacciones;

public class ActivityInsert extends AppCompatActivity {

    EditText txtNombre, txtApellido, txtDireccion, txtEdad, txtPuesto;
    Button btnGuardar, btnSalvados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtEdad = findViewById(R.id.txtEdad);
        txtPuesto = findViewById(R.id.txtPuesto);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnSalvados = findViewById(R.id.btnSalvados);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guardar();
            }
        });

        btnSalvados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Guardar() {
        SQLiteConn conn = new SQLiteConn(this, Transacciones.NameDataBase, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(Transacciones.nombre, txtNombre.getText().toString());
        valores.put(Transacciones.apellido, txtApellido.getText().toString());
        valores.put(Transacciones.edad, txtEdad.getText().toString());
        valores.put(Transacciones.direccion, txtDireccion.getText().toString());
        valores.put(Transacciones.puesto, txtPuesto.getText().toString());

        Long result = db.insert(Transacciones.tablePersonas, Transacciones.id, valores);

        Toast.makeText(getApplicationContext(), "Guardado: " + result.toString(), Toast.LENGTH_LONG).show();

        db.close();

        Clean();
    }

    private void Clean() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtDireccion.setText("");
        txtPuesto.setText("");
    }
}