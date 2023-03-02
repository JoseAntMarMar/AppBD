package com.example.aplicacionfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacionfinal.DB.DBHelper;
import com.example.aplicacionfinal.DB.DBUser;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    RecyclerView lista_usuarios;
    ArrayList<Usuario> listaArrayUser;
    Button boton_editar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista_usuarios = findViewById(R.id.listaUsuarios);
        boton_editar = findViewById(R.id.buttonCambios);

        lista_usuarios.setLayoutManager(new LinearLayoutManager(this));
        DBUser dbUser  = new DBUser(MainActivity.this);

        listaArrayUser = new ArrayList<Usuario>();

        ListaAdapter adapter = new ListaAdapter(dbUser.mostrarUsuarios());
        lista_usuarios.setAdapter(adapter);




        /*boton_bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(db!=null)
                {
                    Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "ERROR AL CREAR BASE DE DATOS", Toast.LENGTH_LONG).show();

                }

            }
        });*/

        boton_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CambiosBD.class);
                startActivity(intent);
            }
        });

    }
}
