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


public class InscriptionUser extends AppCompatActivity {


private EditText txtLogin;
private EditText editPassworduser;
private EditText verifPassworduser;
    private EditText num;
    private EditText semail;
private Button isncritUser;

    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private static final String USERS = "users";
    private String TAG = "RegisterActivity";
    private String  fname, mail,phone, workplace;
    private String password;
    private user user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_user);
        txtLogin = (EditText) findViewById(R.id.NomSociete);
        num = (EditText) findViewById(R.id.txtNum);
        semail = (EditText) findViewById(R.id.txtemail);
        editPassworduser = (EditText) findViewById(R.id.passwordSoc);
        verifPassworduser = (EditText) findViewById(R.id.verifPassworduser);
        isncritUser = (Button) findViewById(R.id.btnInscrit);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USERS);
        mAuth = FirebaseAuth.getInstance();
        isncritUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert data into firebase database
                if(txtLogin.getText().toString() != null && editPassworduser.getText().toString() != null) {
                    fname = txtLogin.getText().toString();
                    mail = semail.getText().toString();
                    phone = num.getText().toString();

                    workplace = verifPassworduser.getText().toString();
                    password = editPassworduser.getText().toString();




                    user = new user(fname, password, phone,workplace);
                    registerUser();
                }
            }
        });

    }

    public void registerUser() {
        mAuth.createUserWithEmailAndPassword(mail, password)
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
                            Toast.makeText(InscriptionUser.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void updateUI(FirebaseUser currentUser) {
        //String keyid = mDatabase.push().getKey();
        //mDatabase.child(keyid).setValue(user); //adding user info to database


mDatabase.child(mAuth.getCurrentUser().getUid()).setValue(user);
        Intent i = new Intent(this, profilUser.class);
        i.putExtra("nom",fname);
        i.putExtra("num",phone);
        i.putExtra("email",mail);
        i.putExtra("adress",workplace);
        startActivity(i);
    }













}