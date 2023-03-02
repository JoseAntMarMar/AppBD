package com.example.aplicacionfinal;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacionfinal.DB.DBUser;

import java.util.Calendar;

public class CambiosBD extends AppCompatActivity {

    EditText txNombre,txPais,txEmpleo;
    TextView txEdad;
    Button boton_guardar;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_bd);

        txNombre= findViewById(R.id.editTextNombre);
        txPais= findViewById(R.id.editTextPais);
        txEmpleo= findViewById(R.id.editTextEmpleo);
        txEdad= findViewById(R.id.tvDate);
        boton_guardar= findViewById(R.id.buttonGuardarDatos);
        mDisplayDate = (TextView) findViewById(R.id.tvDate);


        boton_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBUser dbUser= new DBUser(CambiosBD.this);
               long id= dbUser.insertarUsuario(txNombre.getText().toString(),txPais.getText().toString(),txEmpleo.getText().toString(), Integer.parseInt(txEdad.getText().toString()));

               if (id>0)
               {
                   Toast.makeText(CambiosBD.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                   limpiar();
               }
               else
               {
                   Toast.makeText(CambiosBD.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();

               }

            }
        });



        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(CambiosBD.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year,month,day);
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



    }
    private void limpiar(){
        txNombre.setText("");
        txEmpleo.setText("");
        txEdad.setText("");
        txPais.setText("");
    }
}