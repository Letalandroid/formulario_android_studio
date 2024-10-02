package com.lta.ep1_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Conocimientos extends AppCompatActivity {

    Button btnNext;
    JSONObject data;
    ArrayList<CheckBox> cbs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conocimientos);

        btnNext = findViewById(R.id.btnNext2);
        cbs.add(findViewById(R.id.cbEst));
        cbs.add(findViewById(R.id.cbMEcono));
        cbs.add(findViewById(R.id.cbMicEco));
        cbs.add(findViewById(R.id.cbAudi));
        cbs.add(findViewById(R.id.cbMetoInfo));
        cbs.add(findViewById(R.id.cbAEcono));

        try {
            data = new JSONObject(getIntent().getStringExtra("data"));
        } catch (JSONException e) {
            Log.i("ep_1", e.toString());
        }

        btnNext.setOnClickListener(v -> {
            next();
        });
    }

    private void next() {
        Intent it = new Intent(this, Experiencia_Idiomas.class);
        try {
            data.put("conocimientos", countConocimientos());
        } catch (JSONException e) {
            Log.i("ep_1", e.toString());
        }
        it.putExtra("data", data.toString());
        startActivity(it);
    }

    private int countConocimientos() {

        int count = 0;

        for (CheckBox c : cbs) {
            if (c.isChecked()) {
                count++;
            }
        }

        return count;
    }

}