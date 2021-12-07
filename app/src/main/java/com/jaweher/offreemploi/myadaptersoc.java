
package com.jaweher.offreemploi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadaptersoc extends FirebaseRecyclerAdapter<OffreEmploi,myadaptersoc.myviewholder>
{
    public myadaptersoc(@NonNull FirebaseRecyclerOptions<OffreEmploi> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder,  final int position, @NonNull OffreEmploi model) {
        holder.name.setText(model.getNom());
        holder.course.setText(model.getNum());
        holder.email.setText(model.getAddresse());
        holder.description.setText(model.getDescription());


        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.name.getContext()).setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1200)
                        .create();
                //dialogPlus.show();
                View view=dialogPlus.getHolderView();
                EditText nom=view.findViewById(R.id.txtname);
                EditText postal=view.findViewById(R.id.txtadrPostal);
                EditText cate=view.findViewById(R.id.txtcategorieedit);
                EditText des=view.findViewById(R.id.txtDescriptionedit);
                EditText tel=view.findViewById(R.id.txtNumteledit);

                Button btnupdate=view.findViewById(R.id.btnupdate);
                nom.setText(model.getNom());
                postal.setText(model.getAddresse());
                cate.setText(model.getCategorie());
                des.setText(model.getDescription());
                tel.setText(model.getNum());
                dialogPlus.show();



                btnupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object>map=new HashMap<>();
                        map.put("nom",nom.getText().toString());
                        map.put("addresse",postal.getText().toString());
                        map.put("categorie",cate.getText().toString());
                        map.put("description",des.getText().toString());
                        map.put("num",tel.getText().toString());
                        FirebaseDatabase.getInstance().getReference().child("Les_Offres").child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(holder.name.getContext(),"modification reussite",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.name.getContext(),"modification echouée",Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        });
                    }
                });




            }
        });



holder.btndelet.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder=new AlertDialog.Builder(holder.name.getContext());
        builder.setTitle("VOUS ALLEZ SUPPRIMER CETTE OFFRE????");
        builder.setMessage("supprimer ");
        builder.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            FirebaseDatabase.getInstance().getReference().child("Les_Offres").child(getRef(position).getKey()).removeValue();
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(holder.name.getContext(),"suppression non realisé",Toast.LENGTH_SHORT).show();

            }
        });
        builder.show();
    }
});





    }



    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowsoc,parent,false);
       return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name,course,email,description;
        ImageView btnedit,btndelet;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.nametext);
            description=(TextView)itemView.findViewById(R.id.desctext);
            course=(TextView)itemView.findViewById(R.id.coursetext);
            email=(TextView)itemView.findViewById(R.id.emailtext);
            btnedit=(ImageView)itemView.findViewById(R.id.edit);
            btndelet=(ImageView)itemView.findViewById(R.id.delet);

        }
    }
}
