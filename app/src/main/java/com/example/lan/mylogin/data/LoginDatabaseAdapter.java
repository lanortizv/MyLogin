package com.example.lan.mylogin.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lan on 27-05-17.
 */

public class LoginDatabaseAdapter {
    private LoginDatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public LoginDatabaseAdapter(Context context) {
        databaseHelper = new LoginDatabaseHelper(context);
    }

    public void abrir() {
        db = databaseHelper.getWritableDatabase();
    }

    public void cerrar() {
        databaseHelper.close();
    }

    public long adicionarUsuario(String nombre, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("password", password);
        return db.insert("user", null, contentValues);
    }

    public long adicionarCuenta(String nombre, String password,String pagina ,String idusuario) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("password", password);
        contentValues.put("pagina",pagina);
        contentValues.put("idus", idusuario);
        return db.insert("account", null, contentValues);
    }

    public int actualizarUsuario(long id, String nombre, String password) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("nombre", nombre);
        contentValues.put("password", password);
        return db.update("user", contentValues, "_id=?", new String[]{id + ""});
    }

    public int actualizarCuenta(long id, String nombre, String password,String pagin,String idus) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("nombre", nombre);
        contentValues.put("password", password);
        contentValues.put("pagina",pagin);
        contentValues.put("idus", idus);
        return db.update("account", contentValues, "_id=?", new String[]{id + ""});
    }

    public boolean eliminarUsuario(long id) {
        return db.delete("user", "_id=?", null) > 0;
    }

    public boolean eliminarCuenta(long id) {
        return db.delete("account", "_id=?", null) > 0;
    }

    public Cursor obtenerUsuario(String nom) {
        return db.query("user", new String[]{"_id", "nombre", "password"}, "nombre=?", new String[]{nom}, null, null, null);
    }

    public Cursor obtenerCuenta(String cuenta) {
        return db.query("account", new String[]{"_id", "nombre", "password", "idus"}, "nombre=?", new String[]{cuenta}, null, null, null);
    }

    public Cursor obtenerTodasCuentasUs(String id) {

        return db.query("account", new String[]{"_id", "nombre", "password","pagina" ,"idus"}, "idus=?",new String[]{id}, null, null, null);
    }

    private static class LoginDatabaseHelper extends SQLiteOpenHelper {

        public LoginDatabaseHelper(Context context) {
            super(context, "login.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE account (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, " +
                    "password TEXT NOT NULL,pagina TEXT NOT NULL ,idus TEXT NOT NULL)");
            db.execSQL("CREATE TABLE user (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, " +
                    "password TEXT NOT NULL)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS account");
            db.execSQL("DROP TABLE IF EXISTS user");
            onCreate(db);
        }
    }
}
