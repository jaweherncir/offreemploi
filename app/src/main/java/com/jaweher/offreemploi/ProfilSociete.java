package com.jaweher.offreemploi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import static com.jaweher.offreemploi.MainActivity.redirectActivity;

public class ProfilSociete extends AppCompatActivity {
private Button param;
    private Button route;
    private Button ajout;
    private Button list;
    private Button quitter;
    private Button logout;
    private TextView nom,email,localisation,num;
    private FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profil_societe);
        param=findViewById(R.id.btnparametter);
        route=findViewById(R.id.btnrout);
        ajout=findViewById(R.id.btnAddEmplo);
        list=findViewById(R.id.btnListe);
       quitter =findViewById(R.id.btnQuit);
       logout=findViewById(R.id.btnLogout);
        nom=findViewById(R.id.nsoc);
        email=findViewById(R.id.desc);
        localisation=findViewById(R.id.nsoc);
        route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(ProfilSociete.this);
                dialog.setContentView(R.layout.dialogue);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialog.getWindow().setWindowAnimations(R.style.AnimationForDialog);
                dialog.setTitle("Votre Localisation");

                ImageView close;
               close=dialog.findViewById(R.id.fermer);
               close.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       dialog.cancel();
                   }
               });
                dialog.show();


            }
        });


        num=findViewById(R.id.nuSos);
       //getAlldata
        showUserData();


       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               logout();
           }
       });
    }



    public void parametter(View view)
    {
        redirectActivity(this,Parameter.class);
    }
    public void router(Activity activity)
    {
        //redirectActivity(this,AjoutEmploi.class);
        //initial alert
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        //set title
        builder.setTitle("Votre Adresse");
        builder.setIcon(R.drawable.maps);
        //set msg
        builder.setMessage("recuiperation du l'adresse ");
        //positif button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Ffinich
                dialog.dismiss();

            }
        });
        
        //show dialog
        builder.show();
    }
    public void ajouter(View view)
    {
        redirectActivity(this,AjoutEmploi.class);
    }
    public void ListEmploisSoc(View view)
    {
        redirectActivity(this,ListEmploiSociete.class);
    }
    public void exit(Activity activity)
    {
        //redirectActivity(this,AjoutEmploi.class);
        //initial alert
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        //set title
        builder.setTitle("Quitter");
        //set msg
        builder.setMessage("voulez vous quitter l'application");
        //positif button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Ffinich
                activity.finish();
                System.exit(0);
            }
        });
        //negatif  button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //rest en application
                dialog.dismiss();
            }
        });
        //show dialog
        builder.show();
    }
    public void logout()
    {
        mFirebaseAuth.getInstance().signOut();
        Intent i =new Intent(ProfilSociete.this,LoginSociete.class);
        startActivity(i);
    }

    private void showUserData() {

        Intent i=getIntent();
        String soc_name=i.getStringExtra("nom");
        String soc_num=i.getStringExtra("num");
        String soc_mail=i.getStringExtra("email");
        String soc_local=i.getStringExtra("adress");
        nom.setText(soc_name);
        localisation.setText(soc_local);
        email.setText(soc_mail);
        num.setText(soc_num);
    }


}