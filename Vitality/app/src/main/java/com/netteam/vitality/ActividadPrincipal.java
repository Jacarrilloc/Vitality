package com.netteam.vitality;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ActividadPrincipal extends AppCompatActivity {

    TextView usuarioNombre;
    Button LogOut;
    FirebaseAuth usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);

        usuario = FirebaseAuth.getInstance();
        usuarioNombre = findViewById(R.id.UsuarioLoggeado);
        LogOut = findViewById(R.id.LogOutBoton);

        String correo = getIntent().getStringExtra("Usuario");

        llenarUsuario(correo);

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesion();
            }
        });
    }

    private void cerrarSesion(){
        usuario.signOut();
        Intent intent = new Intent(ActividadPrincipal.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void llenarUsuario(String correo){
        if(TextUtils.isEmpty(correo)){
            Toast.makeText(this,"ERROR",Toast.LENGTH_LONG).show();
        }else{
            String impresion = "Hola! \n" + correo;
            usuarioNombre.setText(impresion);
        }
    }
}