package com.netteam.vitality;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegistroActivity extends AppCompatActivity {

    EditText correo, contrasena;
    Button registrar;
    FirebaseAuth crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        crear = FirebaseAuth.getInstance();

        correo = findViewById(R.id.editTextTextPersonName);
        contrasena = findViewById(R.id.editTextTextPassword);

        registrar = findViewById(R.id.registrarBoton);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistroActivity.this,ActividadPrincipal.class));
            }
        });
    }

}