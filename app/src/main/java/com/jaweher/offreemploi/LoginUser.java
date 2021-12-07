package com.jaweher.offreemploi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.jaweher.offreemploi.MainActivity.redirectActivity;

public class LoginUser extends AppCompatActivity {
private TextView newCompteUser;
private EditText loginUser;
private EditText passworUser;
    private Button btnLogin;
    private FirebaseAuth mAuth;
    private String uemail, upassword;
    private static final String TAG = "MainActivity";

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        newCompteUser=findViewById(R.id.txtnew);
        loginUser=(EditText)findViewById(R.id.tnameuser);
        passworUser=(EditText)findViewById(R.id.passworduser);
        btnLogin=(Button)findViewById(R.id.btnLogUser);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            updateUI(mAuth.getCurrentUser());
        }
     btnLogin.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             uemail = loginUser.getText().toString();
             upassword = passworUser.getText().toString();
             mAuth.signInWithEmailAndPassword(uemail, upassword)
                     .addOnCompleteListener(LoginUser.this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (task.isSuccessful()) {

                                 // Sign in success, update UI with the signed-in user's information
                                 Log.d(TAG, "signInWithEmail:success");
                                 FirebaseUser user = mAuth.getCurrentUser();
                                 Toast.makeText(getApplicationContext(), "Authentication Réusite.",
                                         Toast.LENGTH_SHORT).show();


                                 updateUI(user);
                             }

                             //test champs
                             else if(loginUser.getText().toString().isEmpty() && passworUser.getText().toString().isEmpty())
                             {
                                 loginUser.setError("entre votre login");
                                 passworUser.setError("entre votre login");
                                 Toast.makeText(getApplicationContext(), "remplir champ login et password.",
                                         Toast.LENGTH_SHORT).show();
                             }
                             else if(loginUser.getText().toString().isEmpty())
                             {
                                 loginUser.setError("entre votre login");
                                 Toast.makeText(getApplicationContext(), "remplir champ login.",
                                         Toast.LENGTH_SHORT).show();

                             }
                             else if(passworUser.getText().toString().isEmpty())
                             {
                                 loginUser.setError("entre votre password");
                                 Toast.makeText(getApplicationContext(), "remplir champ password.",
                                         Toast.LENGTH_SHORT).show();

                             }


                             else {
                                 //loginUser.setError("incorrect Email");
                                 //passworUser.setError("incorrect password");
                                 // If sign in fails, display a message to the user.
                                 Log.w(TAG, "signInWithEmail:failure", task.getException());
                                 Toast.makeText(getApplicationContext(), "Authentication failed.",
                                         Toast.LENGTH_SHORT).show();
                             }

                             // ...
                         }
                     });

         }
     });




    }



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }
    }



    public void updateUI(FirebaseUser currentUser) {
        Intent profileIntent = new Intent(getApplicationContext(), profilUser.class);
        profileIntent.putExtra("email", currentUser.getEmail());
        Log.v("DATA", currentUser.getUid());
        startActivity(profileIntent);
    }



















    public  void newCompteUser(View view)
    {

        redirectActivity(this,InscriptionUser.class);
    }






}