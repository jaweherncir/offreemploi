package com.jaweher.offreemploi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ConsultationEmploi extends AppCompatActivity {
    RecyclerView recview;
    myadapter adapter;
    ImageView fav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation_emploi);
        fav=findViewById(R.id.favemp);
        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<OffreEmploi> options =
                new FirebaseRecyclerOptions.Builder<OffreEmploi>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Les_Offres"), OffreEmploi.class)
                        .build();

        adapter=new myadapter(options);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recherche,menu);
        MenuItem item=menu.findItem(R.id.recherche);
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtsearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtsearch(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void txtsearch(String str)
    {
        FirebaseRecyclerOptions<OffreEmploi> options =
                new FirebaseRecyclerOptions.Builder<OffreEmploi>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Les_Offres").orderByChild("categorie").startAt(str).endAt(str+"~"), OffreEmploi.class)
                        .build();
        adapter=new myadapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }
}