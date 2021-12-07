package com.jaweher.offreemploi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.jaweher.offreemploi.MainActivity.redirectActivity;

public class AjoutEmploi extends AppCompatActivity {
private Button back,ajouterOffre;
private EditText nom;
private  EditText num;
private EditText adressemail;
private EditText decriptionoffre;
private EditText categorie;
OffreEmploi offreEmploi;
FirebaseDatabase base;
DatabaseReference ref;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_emploi);
        nom=findViewById(R.id.NomSociete);
        num=findViewById(R.id.ednumsoc);
        adressemail=findViewById(R.id.edadresse);
        decriptionoffre=findViewById(R.id.eddescription);
        categorie=findViewById(R.id.eddcategorie);
        ajouterOffre=findViewById(R.id.ajouterOffre);
        ajouterOffre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouter();
                //ajoutParsoc();
                Intent i=new Intent(AjoutEmploi.this,ListEmploiSociete.class);
                startActivity(i);
            }
        });
    }

    private void ajouter() {
 String name=nom.getText().toString().trim();
 String numero=num.getText().toString().trim();
 String adresse=adressemail.getText().toString().trim();
 String description=decriptionoffre.getText().toString().trim();
 String categories=categorie.getText().toString().trim();
offreEmploi=new OffreEmploi(name,numero,adresse,description,categories);


 //ref = FirebaseDatabase.getInstance().getReference().child("societe").child("offre_emploi");
        ref = FirebaseDatabase.getInstance().getReference().child("Les_Offres").push();
        ref.setValue(offreEmploi);




    }


    /*private void ajoutParsoc() {
        String name=nom.getText().toString().trim();
        String numero=num.getText().toString().trim();
        String adresse=adressemail.getText().toString().trim();
        String description=decriptionoffre.getText().toString().trim();
        String categories=categorie.getText().toString().trim();
        offreEmploi=new OffreEmploi(name,numero,adresse,description,categories);


        //ref = FirebaseDatabase.getInstance().getReference().child("societe").child("offre_emploi");
        //ref = FirebaseDatabase.getInstance().getReference().child("societe").getKey().;
        //ref.setValue(offreEmploi);




    }*/


    public void back(View view)
    {
        redirectActivity(this,ProfilSociete.class);


    }

}