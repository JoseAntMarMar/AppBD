package com.example.aplicacionfinal;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.UsuarioViewHolder> {


    ArrayList<Usuario> listaUser;

    public ListaAdapter(ArrayList<Usuario> listaUser) {
        this.listaUser = listaUser;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_user,null,false);
        return  new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        holder.viewNombre.setText(listaUser.get(position).getNombre());
        holder.viewEdad.setText(String.valueOf(listaUser.get(position).getEdad()));

    }

    @Override
    public int getItemCount() {
        return listaUser.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {

       TextView viewNombre,viewPais,viewEmpleo,viewEdad;
        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre= itemView.findViewById(R.id.viewNombre);
            viewEdad= itemView.findViewById(R.id.viewEdad);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context,VerActivity.class);
                    intent.putExtra("nombre",listaUser.get(getAdapterPosition()).getNombre());

                    context.startActivity(intent);

                }
            });
        }
    }
}