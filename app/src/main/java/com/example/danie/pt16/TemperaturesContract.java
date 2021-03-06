package com.example.danie.pt16;


public class TemperaturesContract {

    private TemperaturesContract() {

    }

    public static final String TABLE_NAME = "openWeather";
    public static final String NOMBRE_COLUMNA_ACCESO = "LastModified";
    public static final String NOMBRE_COLUMNA_NOMCIUTAT = "Nom";
    public static final String NOMBRE_COLUMNA_HORES = "Hores";
    public static final String NOMBRE_COLUMNA_TEMPS = "Temps";
    public static final String NOMBRE_COLUMNA_HUMIDITY = "Humedad";
    public static final String NOMBRE_COLUMNA_PRESION = "Presion";


    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TemperaturesContract.TABLE_NAME + " (" +
            TemperaturesContract.NOMBRE_COLUMNA_ACCESO + " TEXT," +
            TemperaturesContract.NOMBRE_COLUMNA_NOMCIUTAT + " TEXT," +
            TemperaturesContract.NOMBRE_COLUMNA_HORES + " TEXT,"+
            TemperaturesContract.NOMBRE_COLUMNA_TEMPS + " TEXT,"+
            TemperaturesContract.NOMBRE_COLUMNA_HUMIDITY + " TEXT,"+
            TemperaturesContract.NOMBRE_COLUMNA_PRESION + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TemperaturesContract.TABLE_NAME;

}
