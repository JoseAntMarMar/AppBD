package com.example.aplicacionfinal;

import java.time.LocalDate;
import java.time.Period;

public class Herramientas {

    public int conseguirEdad(int year, int month, int day)
    {
        return Period.between(
                LocalDate.of(year, month, day),
                LocalDate.now()
        ).getYears();
    }
}
