package com.jaweher.offreemploi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InscriptionSociete extends AppCompatActivity {
    private Button btnInscrit;
    private EditText nom;
    private EditText spassword;
    private EditText semail;
    private EditText num;
    private EditText description;
    private EditText localisation;

    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private static final String USERS = "societe";
    private String TAG = "RegisterActivity";
    private String  fname, email, profession, phone, workplace;
    private String password;
    private societe user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inscription_societe);
        nom = findViewById(R.id.NomSociete);
        spassword = findViewById(R.id.passwordSoc);
        semail = findViewById(R.id.emailSoc);
        num = findViewById(R.id.numSoc);
        description = findViewById(R.id.descriptionSoc);
        localisation = findViewById(R.id.localisationSoc);
        btnInscrit = findViewById(R.id.ajouterOffre);

        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USERS);
        mAuth = FirebaseAuth.getInstance();

btnInscrit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //insert data into firebase database
        if(nom.getText().toString() != null && spassword.getText().toString() != null) {
            fname = nom.getText().toString();
            email = semail.getText().toString();
            phone = num.getText().toString();
            profession = description.getText().toString();
            workplace = localisation.getText().toString();
            password = spassword.getText().toString();
            user = new societe(fname, password, email, phone, profession,workplace);
            registerUser();
        }
    }
});
    }
    public void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(InscriptionSociete.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void updateUI(FirebaseUser currentUser) {
        //String keyid = mDatabase.push().getKey();
       // mDatabase.child(keyid).setValue(user); //adding user info to database
        mDatabase.child(mAuth.getCurrentUser().getUid()).setValue(user);
        Intent i = new Intent(this, ProfilSociete.class);

        i.putExtra("nom",fname);
        i.putExtra("num",phone);
        i.putExtra("email",email);
        i.putExtra("adress",workplace);
        i.putExtra("description",profession);

        startActivity(i);
    }
}
