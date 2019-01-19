package com.example.danie.pt16;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TemperaturesHelper2 extends SQLiteOpenHelper {

    //Definimos la version de la base de datos, en este caso es la 1
    public static final int DATABASE_VERSION = 1;
    //Definimos el nombre de la base de datos
    public static final String DATABASE_NAME = "OpenWeather.db";

    //Definimos el constructor de la clase
    public TemperaturesHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TemperaturesContract.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(TemperaturesContract.SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public boolean estaCiutatDescarregada(String nomCiutat) {
        SQLiteDatabase db = this.getReadableDatabase();


        //db.query...

        //if (cursor.moveToFirst() és fals, o altres maneres...
        return false;

    }

    //FUNCION QUE DEVUELVE FECHAACTUAL
    public String fechaActual() {

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fecha = dateFormat.format(new Date());

            return fecha;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    //AQUI RELLENA LOS VALORES EN LA TABLA
    public void guarda(String nomCiutat, List<Temp> blocs) throws ParseException {

        /*Date fechanow = new Date();
        SimpleDateFormat actual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String fechaActual = actual.format(new Date());
            fechanow = actual.parse(fechaActual);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        SQLiteDatabase db = this.getWritableDatabase();
        //blocs = new ModificaDadesGuardarBDD(blocs).formatearParaBDD();

        ContentValues values = null;
        for (int i = 0; i < blocs.size(); i++) {
            values = new ContentValues();
            values.put(TemperaturesContract.NOMBRE_COLUMNA_ACCESO, String.valueOf(fechaActual()));
            values.put(TemperaturesContract.NOMBRE_COLUMNA_NOMCIUTAT, nomCiutat);
            values.put(TemperaturesContract.NOMBRE_COLUMNA_HORES, blocs.get(i).getData());
            values.put(TemperaturesContract.NOMBRE_COLUMNA_TEMPS, blocs.get(i).getTempe().replace(",","."));
            values.put(TemperaturesContract.NOMBRE_COLUMNA_HUMIDITY, blocs.get(i).getHumidity());
            values.put(TemperaturesContract.NOMBRE_COLUMNA_PRESION, blocs.get(i).getPress());

            db.insert(TemperaturesContract.TABLE_NAME, null, values);
        }
    }



    public List<Temp> llegeix(String nomCiutat) throws ParseException {

        List<Temp> mostrar = new ArrayList<Temp>();
        Temp ciutat;

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                TemperaturesContract.NOMBRE_COLUMNA_ACCESO,
                TemperaturesContract.NOMBRE_COLUMNA_NOMCIUTAT,
                TemperaturesContract.NOMBRE_COLUMNA_HORES,
                TemperaturesContract.NOMBRE_COLUMNA_TEMPS,
                TemperaturesContract.NOMBRE_COLUMNA_HUMIDITY,
                TemperaturesContract.NOMBRE_COLUMNA_PRESION

        };

        String selection = TemperaturesContract.NOMBRE_COLUMNA_NOMCIUTAT + " = ?";
        String[] selectionArgs = {nomCiutat};

        Cursor cursor = db.query(TemperaturesContract.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        while (cursor.moveToNext()) {
            String hora=cursor.getString(1);
            String temperatura=cursor.getString(2);
            String calorFred;
            Double tempInt=Double.parseDouble(temperatura);
            if (tempInt>20) {
                calorFred="hot";
            } else {
                calorFred="cold";
            }
            ciutat=new Temp(hora,temperatura,calorFred,"", "");
            mostrar.add(ciutat);
        }
        cursor.close();

        return mostrar;

    }


    public boolean estaActualitzada(String nomCiutat) throws ParseException {

        SQLiteDatabase db = this.getReadableDatabase();
        String hora="";
        Date fechaBBDD= new Date();
        Date fechanow = new Date();


        SimpleDateFormat actual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String fechaActual = actual.format(new Date());
            fechanow = actual.parse(fechaActual);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat ultimaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            //String query = "SELECT * FROM LastModified;";
            //db.execSQL(query);
            Cursor c = db.rawQuery(" SELECT * FROM LastModified ", null);

            if (c != null) {
                do {
                    String date = c.getString(c.getColumnIndex("LastModified"));
                    if (date.equals(fechanow)) db.delete("LastModified", null, null);
                }while (c.moveToNext());
            }
            //db.execSQL("SELECT * from LastModified );
            fechaBBDD = ultimaHora.parse(hora);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(fechanow.compareTo(fechaBBDD)>0){
            return false;
        }else{
            return  true;
        }


    }





    /*public void eliminaDades(String nomCiutat) {

        SQLiteDatabase db = this.getWritableDatabase();


        String table = "Nom";
        String whereClause = "Nom = '"+nomCiutat+"'";
        //String[] whereArgs = new String[] { String.valueOf(row) };
        //db.delete(table, whereClause, whereArgs);




        //db.delete...
    }*/
    public void eliminaDades(String nomCiutat) {

        SQLiteDatabase db = this.getWritableDatabase();


        String selection = TemperaturesContract.NOMBRE_COLUMNA_NOMCIUTAT + " = ?";

        String[] selectionArgs = {nomCiutat};

        db.delete(TemperaturesContract.TABLE_NAME, selection, selectionArgs);
    }
}
