package com.example.lan.mylogin;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lan.mylogin.data.LoginDatabaseAdapter;

public class RegistroCuenta extends AppCompatActivity {

    private EditText cue;
    private EditText link;
    private EditText cc;
    private LoginDatabaseAdapter db;
    private Button btnCuenta;
    private String idu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuenta);
        db = new LoginDatabaseAdapter(this);
        Bundle b=getIntent().getExtras();
        idu=b.getString("id");
        //sc = new StringEncrypt();
        btnCuenta = (Button) findViewById(R.id.btnCuenta);
        cue = (EditText) findViewById(R.id.cuenta);
        link = (EditText) findViewById(R.id.link);
        cc = (EditText) findViewById(R.id.password);
        btnCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us = cue.getText().toString();
                String pas = link.getText().toString();
                String con = cc.getText().toString();
                if (us.equals("") || pas.equals("")||con.equals("")) {
                    Toast.makeText(getApplicationContext(), "uno o mas de los campos se encuentran vacios", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor cursor = db.obtenerCuenta(pas);
                    if (cursor.getCount() > 0) {
                        Toast.makeText(getApplicationContext(), "la cuenta ya existe", Toast.LENGTH_SHORT).show();
                    } else {
                        long valido =db.adicionarCuenta(us,con,pas,idu);
                        Toast.makeText(getApplicationContext(), "cuenta creada", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        db.abrir();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.cerrar();
    }
}
