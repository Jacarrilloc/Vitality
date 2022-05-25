package com.netteam.vitality;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ActividadPrincipal extends AppCompatActivity {

    TextView usuarioNombre;
    Button LogOut;
    ImageButton calendario,corazon, llamada,premium,instagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);

        String url = "https://instagram.com/vitality.serviciospsicologicos?igshid=YmMyMTA2M2Y=";

        usuarioNombre = findViewById(R.id.UsuarioLoggeado);
        LogOut = findViewById(R.id.LogOutBoton);

        calendario = findViewById(R.id.calendario);
        corazon = findViewById(R.id.corazon);
        llamada = findViewById(R.id.llamada);
        premium = findViewById(R.id.premium);
        instagram = findViewById(R.id.instagram);



        String correo = getIntent().getStringExtra("Usuario");

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesion();
            }
        });

        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActividadPrincipal.this,CalendarioAct.class));
            }
        });

        corazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActividadPrincipal.this,CorazonAct.class));
            }
        });

        llamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActividadPrincipal.this,LlamadaAct.class));
            }
        });

        premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActividadPrincipal.this,PremiumAct.class));
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri _link = Uri.parse(url);
                Intent link = new Intent(Intent.ACTION_VIEW,_link);
                startActivity(link);
            }
        });
    }

    private void cerrarSesion(){
        Intent intent = new Intent(ActividadPrincipal.this,LoginActivity.class);
        startActivity(intent);
    }
}