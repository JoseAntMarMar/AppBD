package com.example.aplicacionfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aplicacionfinal.DB.DBUser;

public class VerActivity extends AppCompatActivity {

    EditText txNombre,txPais,txEmpleo;
    TextView txEdad;
    Button boton_guardar;
    Button boton_editar;
    Button boton_borrar;
    Usuario user;
    String nombre="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txNombre= findViewById(R.id.editTextNombre);
        txPais= findViewById(R.id.editTextPais);
        txEmpleo= findViewById(R.id.editTextEmpleo);
        txEdad = findViewById(R.id.tvDate);
        boton_guardar= findViewById(R.id.buttonGuardarDatos);
        boton_editar = findViewById(R.id.buttonEditar);
        boton_borrar= findViewById(R.id.buttonBorrar);


        boton_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Quiere borrar este usuario?").setPositiveButton("SI", new DialogInterface.OnClickListener()

                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBUser dbUser  = new DBUser(VerActivity.this);
                        System.out.println("Hola");
                       if(dbUser.eliminarUsuario(nombre))
                       {
                            lista();
                       }
                    }

                })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

            }
        });

        boton_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerActivity.this,EditarActivity.class);
                intent.putExtra("nombre",nombre);
                startActivity(intent);
            }
        });

        if(savedInstanceState==null)
        {
            Bundle extras= getIntent().getExtras();
            if(extras==null)
            {
                nombre= String.valueOf(null);
            }
            else {
                nombre=extras.getString("nombre");
            }
        }else
        {
            nombre=(String) savedInstanceState.getSerializable("nombre");
        }

        DBUser dbUser = new DBUser(VerActivity.this);
        user=dbUser.verUsuarios(nombre);

        if(user!=null)
        {
            txNombre.setText(user.getNombre());
            txPais.setText(user.getPais());
            txEmpleo.setText(user.getEmpleo());
            txEdad.setText(String.valueOf(user.getEdad()));
            boton_guardar.setVisibility(View.INVISIBLE);
            txNombre.setInputType(InputType.TYPE_NULL);
            txPais.setInputType(InputType.TYPE_NULL);
            txEmpleo.setInputType(InputType.TYPE_NULL);
            txEdad.setInputType(InputType.TYPE_NULL);
        }
    }

    private void lista()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}