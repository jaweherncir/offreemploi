package com.jaweher.offreemploi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawer_layout);
    }
    public void ClickMenu(View view)
    {
openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        //open drawer
        drawerLayout.openDrawer(GravityCompat.START);


    }
    public void cliklogo()
    {
        //close drawer


        closerDrawer(drawerLayout);
    }

    private  static void closerDrawer(DrawerLayout drawerLayout) {

      //close drawer

      //check condition
      if(drawerLayout.isDrawerOpen(GravityCompat.START))
      {
          //when drawer is open
          //close drawer
          drawerLayout.closeDrawer(GravityCompat.START);
      }
    }
    public  void clikacceuil()
    {
        recreate();
    }


    public void clickParam(View view)
    {
        redirectActivity(this,Parameter.class);
    }
    public void clickClient(View view)
    {
        //redirection login user

        redirectActivity(this,LoginUser.class);

    }
public void clickSos(View view)
{
    //Intent i=new Intent(this,Parameter.class);
    //startActivity(i);
    //direction  parametre activity
   redirectActivity(this,LoginSociete.class);
}
public  void  clicklogout(View view)
{
    logout(this);
}

    private static void logout(Activity activity) {
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

    static void redirectActivity(Activity activity, Class aClasse) {
        //start login user
        Intent intent=new Intent(activity,aClasse);
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        //start activity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        closerDrawer(drawerLayout);
    }
}