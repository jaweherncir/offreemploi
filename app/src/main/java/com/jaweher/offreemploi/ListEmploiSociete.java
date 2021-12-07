package com.jaweher.offreemploi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ListEmploiSociete extends AppCompatActivity {
    RecyclerView recview;
String id;


    myadaptersoc adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_emploi_societe);
        recview=(RecyclerView)findViewById(R.id.recview);

        recview.setLayoutManager(new LinearLayoutManager(this));
        recview.setHasFixedSize(true);
        //delete=findViewById(R.id.delet);
        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<OffreEmploi> options =
                new FirebaseRecyclerOptions.Builder<OffreEmploi>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Les_Offres"), OffreEmploi.class)
                        .build();

        adapter=new myadaptersoc(options);
        recview.setAdapter(adapter);
    }




    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}