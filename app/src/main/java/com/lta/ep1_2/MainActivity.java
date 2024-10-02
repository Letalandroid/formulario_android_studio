package com.lta.ep1_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre;
    EditText txtEdad;
    Spinner spnCargo;
    RadioButton rbMasc;
    RadioButton rbFem;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = findViewById(R.id.txtNombre);
        txtEdad = findViewById(R.id.txtEdad);
        spnCargo = findViewById(R.id.spnCargo);
        rbMasc = findViewById(R.id.rbMasc);
        rbFem = findViewById(R.id.rbFem);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {
            next();
        });

    }

    private void next() {
        JSONObject data = new JSONObject();
        try {
            data.put("nombre", txtNombre.getText().toString());
            data.put("edad", txtEdad.getText().toString());
            data.put("cargo", spnCargo.getSelectedItem().toString());
            data.put("genero", getGen());
        } catch (JSONException e) {
            Log.i("ep_1", e.toString());
        }

        Intent it = new Intent(this, Conocimientos.class);
        it.putExtra("data", data.toString());
        startActivity(it);
    }

    private String getGen() {
        if (rbMasc.isChecked()) {
            return "Masculino";
        } else if (rbFem.isChecked()) {
            return "Femenino";
        }

        return "No seleccionado";
    }
}