package com.lta.ep1_2;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Resumen extends AppCompatActivity {

    JSONObject data;
    EditText txtResultados;
    String nombre;
    int edad;
    String cargo;
    int t_puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        txtResultados = findViewById(R.id.txtResultados);

        try {
            data = new JSONObject(getIntent().getStringExtra("data"));
            nombre = data.getString("nombre");
            edad = data.getInt("edad");
            cargo = data.getString("cargo");
            t_puntos = data.getInt("conocimientos") + data.getInt("experiencia") + data.getInt("idiomas") ;
        } catch (JSONException e) {
            Log.i("ep_1", e.toString());
        }

        String text = String.format("El postulante %s\n" +
                                    "De edad %s años\n" +
                                    "Postula a la plaza de %s\n" +
                                    "Ha onbtenido un puntaje total de %s\n" +
                                    "Lo cuál está %s a dicha plaza",
        nombre, edad, cargo, t_puntos, apto() ? "APTO" : "NO APTO");

        txtResultados.setText(text);
    }

    private boolean apto() {
        return (Objects.equals(cargo, "ASISTENTE") && t_puntos >= 14) || (Objects.equals(cargo, "COORDINADOR") && t_puntos >= 16);
    }
}