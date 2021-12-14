package com.example.pm01mvcremz.Config;

public class Transacciones {

    //Nombre de la base de datos
    public static final String NameDataBase = "PM01MVCREMZ";

    //Tablas de la base de datos
    public static final String tablePersonas = "Personas";

    //Campos de la tabla
    public static final String id = "id";
    public static final String nombre = "nombre";
    public static final String apellido = "apellido";
    public static final String edad = "edad";
    public static final String direccion = "direccion";
    public static final String puesto = "puesto";

    //Transacciones DDL (Data Definition Language) tabla personas
    public static final String CreateTablePersonas = "CREATE TABLE Personas (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEXT, apellido TEXT, edad TEXT, direccion TEXT, puesto TEXT)";

    public static final String DROPTablePersonas = "DROP TABLE IF EXISTS Personas";
}
