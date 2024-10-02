package com.lta.ep1_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Experiencia_Idiomas extends AppCompatActivity {

    JSONObject data;
    RadioGroup rgExperiencia;
    ArrayList<CheckBox> cbIdiomas;
    Button btnFinish;
    String aExperiencia = "No seleccionado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiencia_idiomas);

        rgExperiencia = findViewById(R.id.rgExperiencia);
        cbIdiomas = new ArrayList<>();
        cbIdiomas.add(findViewById(R.id.cbIng));
        cbIdiomas.add(findViewById(R.id.cbPort));
        cbIdiomas.add(findViewById(R.id.cbFran));
        cbIdiomas.add(findViewById(R.id.cbItal));
        btnFinish = findViewById(R.id.btnFinish);

        rgExperiencia.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb = findViewById(checkedId);
            aExperiencia = rb.getText().toString();
        });

        btnFinish.setOnClickListener(v -> {
            next();
        });

        try {
            data = new JSONObject(getIntent().getStringExtra("data"));
        } catch (JSONException e) {
            Log.i("ep_1", e.toString());
        }
    }

    private void next() {

        Intent it = new Intent(this, Resumen.class);

        try {
            data.put("experiencia", cExperiencia());
            data.put("idiomas", cIdiomas());
        } catch (JSONException e) {
            Log.i("ep_1", e.toString());
        }

        it.putExtra("data", data.toString());
        startActivity(it);
    }

    private int cExperiencia() {
        switch (aExperiencia) {
            case "Menos de 1 año":
                return 2;
            case "De 1 a 3 años":
                return 3;
            case "De 4 a más":
                return 6;
            default:
                return 0;
        }
    }

    private int cIdiomas() {

        int count = 0;

        for (CheckBox c : cbIdiomas) {
            if (c.isChecked()) {
                count += 2;
            }
        }

        return count;
    }
}