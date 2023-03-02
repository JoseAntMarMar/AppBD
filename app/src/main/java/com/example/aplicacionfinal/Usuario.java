package com.example.aplicacionfinal;

import android.os.Build;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class Usuario implements Serializable {

    private String nombre,pais,empleo;
    private int edad;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEmpleo() {
        return empleo;
    }

    public void setEmpleo(String empleo) {
        this.empleo = empleo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}


