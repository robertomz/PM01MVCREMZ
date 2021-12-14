package com.example.pm01mvcremz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.pm01mvcremz.Config.SQLiteConn;
import com.example.pm01mvcremz.Config.Transacciones;
import com.example.pm01mvcremz.Model.Personas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteConn conn;
    ListView listaPersonas;
    ArrayList<Personas> lista;
    ArrayList<String> ArregloPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNuevo = findViewById(R.id.btnNuevo);
        ArregloPersonas = new ArrayList<String>();

        /* LISTAR CONTACTOS */
        conn = new SQLiteConn(this, Transacciones.NameDataBase, null, 1);
        listaPersonas = (ListView) findViewById(R.id.listaPersonas);

        ObtenerContactos();

        ArrayAdapter adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ArregloPersonas);
        listaPersonas.setAdapter(adp);

        listaPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ActivityEdit.class);
                intent.putExtra("objeto", lista.get(position));
                startActivity(intent);
            }
        });

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityInsert.class);
                startActivity(intent);
            }
        });
    }

    private void ObtenerContactos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Personas list_personas = null;
        lista = new ArrayList<Personas>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablePersonas, null);

        while(cursor.moveToNext()) {
            list_personas = new Personas();
            list_personas.setId(cursor.getInt(0));
            list_personas.setNombre(cursor.getString(1));
            list_personas.setApellido(cursor.getString(2));
            list_personas.setEdad(cursor.getString(3));
            list_personas.setDireccion(cursor.getString(4));
            list_personas.setPuesto(cursor.getString(5));

            lista.add(list_personas);
        }

        cursor.close();

        filllist();
    }

    private void filllist() {
        ArregloPersonas = new ArrayList<String>();

        for (int i = 0; i < lista.size(); i++) {
            ArregloPersonas.add(lista.get(i).getNombre() + " "
                    + lista.get(i).getApellido() + " | "
                    + lista.get(i).getPuesto());
        }
    }

}