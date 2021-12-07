package com.jaweher.offreemploi;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<OffreEmploi,myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<OffreEmploi> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull OffreEmploi model)
    {
        holder.name.setText(model.getNom());
        holder.course.setText(model.getNum());
        holder.email.setText(model.getAddresse());
        holder.description.setText(model.getDescription());
        //Glide.with(holder.img.getContext()).load(model.getDescription()).into(holder.img);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        //CircleImageView img;
        TextView name,course,email,description;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            //img=(CircleImageView)itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.nametext);
            description=(TextView)itemView.findViewById(R.id.desctext);
            course=(TextView)itemView.findViewById(R.id.coursetext);
            email=(TextView)itemView.findViewById(R.id.emailtext);





        }
    }
}
