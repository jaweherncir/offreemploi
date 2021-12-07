package com.jaweher.offreemploi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class profilUser extends AppCompatActivity {
private FirebaseAuth mFirebaseAuth;
private ImageView deconect,parametre,listemploi,quiter,back,favorise;
    private TextView nom,email,localisation,num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_user);
        deconect=findViewById(R.id.logout);
        parametre=findViewById(R.id.parametre);
        listemploi=findViewById(R.id.listemploi);
        quiter=findViewById(R.id.quiter);
        back=findViewById(R.id.bacprofiluser);
        favorise=findViewById(R.id.fav);
        nom=findViewById(R.id.nameusers);
        localisation=findViewById(R.id.local);
        email=findViewById(R.id.email);
        num=findViewById(R.id.number);
        showUserData();
        favorise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(profilUser.this,ListFavoris.class);
                startActivity(i);
            }
        });
       back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i =new Intent(profilUser.this,MainActivity.class);
        startActivity(i);
    }
});

        listemploi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(profilUser.this,ConsultationEmploi.class);
                startActivity(i);
            }
        });
        deconect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        parametre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting();
            }
        });

        quiter.setOnClickListener(new View.OnClickListener() {
         @Override
        public void onClick(View v) {
        quite();
    }
           });

    }
    private void quite() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.fermer);
builder.setMessage("Quitter L'application");
builder.setTitle("Quitter");
builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        finish();

    }
});
builder.setNegativeButton("NON", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
    }
});
AlertDialog alertDialog=builder.create();
alertDialog.show();
    }

    private void setting() {
        Intent i =new Intent(profilUser.this,Parameter.class);
        startActivity(i);
    }

    public void logout()
    {
        mFirebaseAuth.getInstance().signOut();
        Intent i =new Intent(profilUser.this,LoginUser.class);
        startActivity(i);
    }


    private void showUserData() {

        Intent i=getIntent();
        String user_name=i.getStringExtra("nom");
        String user_num=i.getStringExtra("num");
        String user_mail=i.getStringExtra("email");
        String user_local=i.getStringExtra("adress");
        nom.setText(user_name);
        localisation.setText(user_local);
        email.setText(user_mail);
        num.setText(user_num);
    }




}