package com.example.lan.mylogin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.lan.mylogin.data.Cuenta;
import com.example.lan.mylogin.data.ListaAdapter;
import com.example.lan.mylogin.data.LoginDatabaseAdapter;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {
    private LoginDatabaseAdapter db;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new LoginDatabaseAdapter(this);
        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("usuario");
        id = bundle.getString("id");
        cargar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        cargar();
    }

    public void cargar(){
        ArrayList<Cuenta> cuent = new ArrayList<Cuenta>();
        db.abrir();
        Cursor cursor = db.obtenerTodasCuentasUs(id);
        if (cursor.moveToFirst()) {
            do {
                String cu = cursor.getString(1);
                String pas = cursor.getString(2);
                String link = cursor.getString(3);
                cuent.add(new Cuenta(cu, pas, link));
            } while (cursor.moveToNext());
        }
        db.cerrar();
        ListaAdapter la = new ListaAdapter(getApplicationContext(), cuent);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(la);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegistroCuenta.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
