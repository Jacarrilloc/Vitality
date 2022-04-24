package com.netteam.vitality;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    Button login, registrar;
    EditText correo, contrasena;
    FirebaseAuth usuarioLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.iniciarsesion);
        registrar = findViewById(R.id.registrar);
        correo = findViewById(R.id.email);
        contrasena = findViewById(R.id.Contrasena);

        usuarioLog = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion();
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
            }
        });
    }

    private void iniciarSesion(){

        String usuario = correo.getText().toString();
        String contra = contrasena.getText().toString();

        if(validar(usuario,contra)){
            usuarioLog.signInWithEmailAndPassword(usuario,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser usuarioActual = usuarioLog.getCurrentUser();
                        updateUI(usuarioActual);
                    }else{
                        String error = task.getException().getMessage();
                        Log.i("FirebaseInfo",error);
                        updateUI(null);
                    }
                }
            });
        }
    }

    private boolean validar(String email,String contra){
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(contra)){
            if(TextUtils.isEmpty(email) && TextUtils.isEmpty(contra)) {
                Toast.makeText(this,"No se ha Ingresado el Email y la Contraseña",Toast.LENGTH_LONG).show();
            }else{
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(this,"No se ha Ingresado el Email",Toast.LENGTH_LONG).show();
                }
                if(TextUtils.isEmpty(contra)){
                    Toast.makeText(this,"No se ha Ingresado la Contraseña",Toast.LENGTH_LONG).show();
                }
            }
            return false;
        }else{
            if(validarCorreo(email) && validarContra(contra)){
                return true;
            }else{
                if(!validarCorreo(email) && !validarContra(contra))
                    Toast.makeText(this,"Correo y Contraseña no Validos",Toast.LENGTH_LONG).show();
                else{
                    if(!validarCorreo(email))
                        Toast.makeText(this,"Correo Ingresado no Valido",Toast.LENGTH_LONG).show();
                    if(!validarContra(contra))
                        Toast.makeText(this,"Contraseña Ingresada no Valida",Toast.LENGTH_LONG).show();
                }
                return false;
            }
        }
    }

    private boolean validarCorreo(String email){
        if(!email.contains("@") || !email.contains(".") || email.length() < 5)
            return false;
        return true;
    }

    private boolean validarContra(String contra){
        if(contra.length() < 4)
            return false;
        return true;
    }

    private void updateUI(FirebaseUser usuarioActual){
        if(usuarioActual != null){
            Intent actividadInicio = new Intent(this,ActividadPrincipal.class);
            String dato = usuarioActual.getEmail();
            actividadInicio.putExtra("Usuario",dato);
            startActivity(actividadInicio);
        }else{
            correo.setText("");
            contrasena.setText("");
        }
    }

    private void registrar(){
        startActivity(new Intent(this,RegistroActivity.class));
    }
}