package com.netteam.vitality;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Button Boton;
    FirebaseAuth usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Boton = findViewById(R.id.buttoncito);

        usuario = FirebaseAuth.getInstance();

        Boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarTodo();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(usuario.getCurrentUser() != null){
            FirebaseUser usuarioActual = usuario.getCurrentUser();
            updateUI(usuarioActual);
        }
    }

    private void iniciarTodo(){
        startActivity(new Intent(this,LoginActivity.class));
    }

    private void updateUI(FirebaseUser usuarioActual){
        if(usuarioActual != null){
            Intent actividadInicio = new Intent(this,ActividadPrincipal.class);
            String dato = usuarioActual.getEmail();
            actividadInicio.putExtra("Usuario",dato);
            startActivity(actividadInicio);
        }
    }
}