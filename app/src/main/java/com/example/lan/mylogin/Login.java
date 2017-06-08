package com.example.lan.mylogin;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lan.mylogin.data.LoginDatabaseAdapter;

public class Login extends AppCompatActivity {

    private Button btnregistro;
    private Button btningresar;
    private EditText user;
    private EditText pass;
    private LoginDatabaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnregistro = (Button) findViewById(R.id.btnRegistro);
        btningresar = (Button) findViewById(R.id.btnIngresar);
        user = (EditText) findViewById(R.id.etUser);
        pass = (EditText) findViewById(R.id.etPassword);
        db = new LoginDatabaseAdapter(this);
        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        getApplicationContext(),
                        RegistroUser.class);
                startActivity(i);
                vaciar();

            }
        });
        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us = user.getText().toString();
                String pas = pass.getText().toString();
                if (us.equals("") || pas.equals("")) {
                    Toast.makeText(getApplicationContext(), "usuario o contraseña vacios", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor cursor = db.obtenerUsuario(us);
                    if (cursor.moveToFirst()) {
                        String contra = cursor.getString(2);
                        String ide=cursor.getInt(0)+"";
                        if (contra.equals(pas)) {
                            Intent intent = new Intent(getApplicationContext(), Lista.class);
                            intent.putExtra("usuario", us);
                            intent.putExtra("id",ide);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "usuario o contraseña no validos", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "no existe el usuario", Toast.LENGTH_SHORT).show();
                    }
                }
                vaciar();
            }
        });

    }
    public void vaciar() {
        user.setText("");
        pass.setText("");
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
