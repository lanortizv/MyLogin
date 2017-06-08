package com.example.lan.mylogin;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lan.mylogin.data.LoginDatabaseAdapter;

public class RegistroUser extends AppCompatActivity {

    private EditText use;
    private EditText pa;
    private EditText cc;
    private LoginDatabaseAdapter db;
    private Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_user);
        db = new LoginDatabaseAdapter(this);
        //sc = new StringEncrypt();
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        use = (EditText) findViewById(R.id.etUsuario);
        pa = (EditText) findViewById(R.id.etPass);
        cc = (EditText) findViewById(R.id.etConfirm);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us = use.getText().toString();
                String pas = pa.getText().toString();
                String con = cc.getText().toString();
                //pass.setText(cifrar(user.getText().toString()));
                if (us.equals("") || pas.equals("")) {
                    Toast.makeText(getApplicationContext(), "usuario o contraseña vacios", Toast.LENGTH_SHORT).show();
                } else {
                    if (pas.equals(con)) {
                        Cursor cursor = db.obtenerUsuario(us);
                        if (cursor.getCount() > 0) {
                            Toast.makeText(getApplicationContext(), "el usuario ya existe", Toast.LENGTH_SHORT).show();
                        } else {
                            //pas=cifrar(pas);
                            long valido = db.adicionarUsuario(us, pas);
                            Toast.makeText(getApplicationContext(), "usuario creado", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "No coinciden las contraseñas", Toast.LENGTH_SHORT).show();
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
