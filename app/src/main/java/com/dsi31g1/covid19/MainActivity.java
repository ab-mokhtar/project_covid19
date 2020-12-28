package com.dsi31g1.covid19;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edittxtEmail, edittxtPwd;
    private Button signIn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();
        TextView register = findViewById(R.id.registrer);
        register.setOnClickListener(this);
        signIn = findViewById(R.id.Connexion);
        signIn.setOnClickListener(this);
        edittxtEmail = findViewById(R.id.EmailInp);
        edittxtPwd = findViewById(R.id.PasswordInp);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.registrer) {
            startActivity(new Intent(this, RegisterUsers.class));
        } else if (id == R.id.Connexion) {
            userLogin();
        }
    }

    private void userLogin() {
        String email = edittxtEmail.getText().toString().trim();
        String password = edittxtPwd.getText().toString().trim();

        if (email.isEmpty()) {
            edittxtEmail.setError("ecrivez votre email");
            edittxtEmail.requestFocus();
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edittxtEmail.setError("email invalide");
            edittxtEmail.requestFocus();
        }
        if (password.isEmpty()) {
            edittxtPwd.setError("ecrivez votre mot de passe");
        }
        if (password.length() < 6) {
            edittxtPwd.setError("la longueur minimale du mot de passe doit être de 6 caractères");
            edittxtPwd.requestFocus();
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                    startActivity(new Intent(MainActivity.this, ActivityFragment.class));

                } else {
                    Toast.makeText(MainActivity.this, "échec de la connexion, veuillez vérifier vos informations d'identification et réessayer", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}

