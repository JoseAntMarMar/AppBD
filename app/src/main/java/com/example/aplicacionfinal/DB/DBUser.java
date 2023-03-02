package com.example.aplicacionfinal.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.aplicacionfinal.Usuario;

import java.util.ArrayList;

public class DBUser extends  DBHelper{

    Context context;


    public DBUser(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarUsuario(String nombre,String pais, String empleo,int edad)
    {
        long id=0;
        try {
        DBHelper dbHelper= new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre",nombre);
        values.put("pais",pais);
        values.put("empleo",empleo);
        values.put("edad",edad);

         id=db.insert(TABLE_USER,null,values);
        }catch (Exception ex){

        }
        return id;

    }

    public ArrayList<Usuario> mostrarUsuarios()
    {
        DBHelper dbHelper= new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        Usuario user= null;
        Cursor cursorUsuarios= null;

        cursorUsuarios= db.rawQuery("SELECT * FROM " + TABLE_USER,null );

        if(cursorUsuarios.moveToFirst()){
            do{
                user= new Usuario();
                user.setNombre(cursorUsuarios.getString(0));
                user.setPais(cursorUsuarios.getString(1));
                user.setEmpleo(cursorUsuarios.getString(2));
                user.setEdad(cursorUsuarios.getInt(3));
                listaUsuarios.add(user);
            }while (cursorUsuarios.moveToNext());
        }

        cursorUsuarios.close();

        return  listaUsuarios;

    }

    public  Usuario verUsuarios(String nombre)
    {
        DBHelper dbHelper= new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

         Usuario user= null;
        Cursor cursorUsuarios= null;
        char quotes ='"';
        nombre=quotes+nombre+quotes;

        cursorUsuarios= db.rawQuery(" SELECT * FROM " + TABLE_USER+ " WHERE nombre= " +nombre ,null );

        if(cursorUsuarios.moveToFirst()){

                user= new Usuario();
                user.setNombre(cursorUsuarios.getString(0));
                user.setPais(cursorUsuarios.getString(1));
                user.setEmpleo(cursorUsuarios.getString(2));
                user.setEdad(cursorUsuarios.getInt(3));


        }

        cursorUsuarios.close();

        return  user;

    }

    public boolean editarUsuario(String nombre,String País,String Empleo,int edadS)
    {
        boolean correcto=false;

            DBHelper dbHelper= new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();



        try {
            db.execSQL("UPDATE "+TABLE_USER+" SET pais='"+País+"',empleo='"+Empleo+"',edad="+ edadS +" WHERE nombre='"+nombre+"' ");
            correcto=true;
        }catch (Exception ex) {
            correcto=false;

        }finally {
            db.close();

        }
        return correcto;

    }

    public boolean eliminarUsuario(String nombre)
    {
        boolean correcto=false;

        DBHelper dbHelper= new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM "+TABLE_USER+" WHERE nombre='"+nombre+"'  ");
            correcto=true;
        }catch (Exception ex) {
            correcto=false;

        }finally {
            db.close();

        }
        return correcto;

    }

}
