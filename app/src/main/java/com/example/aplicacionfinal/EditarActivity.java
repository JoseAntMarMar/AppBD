package com.example.aplicacionfinal;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacionfinal.DB.DBUser;

import java.util.Calendar;

public class EditarActivity extends AppCompatActivity {

    EditText txNombre,txPais,txEmpleo;
    TextView txEdad;
    Button boton_guardar;
    Usuario user;
    String nombre="";
    boolean correcto=false;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);


        txNombre= findViewById(R.id.editTextNombre);
        txPais= findViewById(R.id.editTextPais);
        txEmpleo= findViewById(R.id.editTextEmpleo);
        txEdad = findViewById(R.id.tvDate);
        mDisplayDate = (TextView) findViewById(R.id.tvDate);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(EditarActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);



                Herramientas herramientas= new Herramientas();
                int date = herramientas.conseguirEdad(year,month,day);
                System.out.println(date);
                mDisplayDate.setText(String.valueOf(date));
            }
        };

        boton_guardar= findViewById(R.id.buttonGuardarDatos);
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

        DBUser dbUser = new DBUser(EditarActivity.this);
        user=dbUser.verUsuarios(nombre);

        if(user!=null)
        {
            txNombre.setText(user.getNombre());
            txPais.setText(user.getPais());
            txEmpleo.setText(user.getEmpleo());
            txEdad.setText(String.valueOf(user.getEdad()));

        }

        boton_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txNombre.getText().toString().equals("")&&!txPais.getText().toString().equals(""))
                {
                    correcto= dbUser.editarUsuario(nombre,txPais.getText().toString(),txEmpleo.getText().toString(),Integer.valueOf(txEdad.getText().toString()));

                    if(correcto)
                    {
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistros();
                    }
                    else
                    {
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();

                    }
                }
                else
                {
                    Toast.makeText(EditarActivity.this, "RELLENE LOS CAMPOS", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void verRegistros()
    {
        Intent intent= new Intent(this,VerActivity.class);
        intent.putExtra("nombre",nombre);
        startActivity(intent);
    }
}