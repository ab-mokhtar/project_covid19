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

public class RegisterUsers extends AppCompatActivity implements View.OnClickListener {
    private EditText Nom, Age, EmailInpSignUP, PasswordInpSignUP;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        mAuth = FirebaseAuth.getInstance();

        TextView banner = findViewById(R.id.banner);
        banner.setOnClickListener(this);

        TextView registerButton = (Button) findViewById(R.id.RegisterButton);
        registerButton.setOnClickListener(this);
        Nom = findViewById(R.id.Nom);
        Age = findViewById(R.id.Age);
        EmailInpSignUP = findViewById(R.id.EmailInpSignUP);
        PasswordInpSignUP = findViewById(R.id.PasswordInpSignUP);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.banner) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (id == R.id.RegisterButton) {
            RegisterUser();
        }
    }

    private void RegisterUser() {
        final String email = EmailInpSignUP.getText().toString().trim();
        String password = PasswordInpSignUP.getText().toString().trim();
        final String nom = Nom.getText().toString().trim();
        final String age = Age.getText().toString().trim();
        if (nom.isEmpty()) {
            Nom.setError("Enter FirstName");
            Nom.requestFocus();
            return;
        }
        if (age.isEmpty()) {
            Age.setError("Entez votre age");
            Age.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            EmailInpSignUP.setError("Entez votre email");
            EmailInpSignUP.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            EmailInpSignUP.setError("email invalide");
            EmailInpSignUP.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            PasswordInpSignUP.setError("entrez password");
            PasswordInpSignUP.requestFocus();
            return;
        }
        if (password.length() < 6) {
            PasswordInpSignUP.setError("la longueur minimale du mot de passe doit être de 6 caractères");
            PasswordInpSignUP.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(RegisterUsers.this, MainActivity.class));
                    } else {
                        Toast.makeText(RegisterUsers.this,
                                "creation a échoué! veuillez réessayer", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RegisterUsers.this,
                            "creation a échoué", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}