package clones.cristian.com.whatsappclon.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import clones.cristian.com.whatsappclon.R;
import clones.cristian.com.whatsappclon.glide.GlideApp;
import clones.cristian.com.whatsappclon.modelos.Contacto;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.ContactosViewHolder>{

    public interface ContactosListener {
        void contactoClick(Contacto contacto, int posicion);
    }

    private ArrayList<Contacto> contactos;
    private ContactosListener contactosListener;
    private Context contexto;

    public ContactosAdapter(ArrayList<Contacto> contactos, ContactosListener contactosListener, Context contexto) {
        this.contactos = contactos;
        this.contactosListener = contactosListener;
        this.contexto = contexto;
    }

    public class ContactosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CircleImageView img;
        private TextView txtNombre, txtEstado;

        public ContactosViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.item_contacto_img);
            txtNombre = itemView.findViewById(R.id.item_contacto_nombre);
            txtEstado = itemView.findViewById(R.id.item_contacto_estado);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(contactosListener != null){
                contactosListener.contactoClick( contactos.get( getAdapterPosition() ), getAdapterPosition() );
            }
        }
    }

    @NonNull
    @Override
    public ContactosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_contacto, parent, false);

        return new ContactosViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactosViewHolder holder, int position) {
        Contacto contacto = contactos.get(position);

        holder.txtNombre.setText( contacto.getNombre() );

        if( contacto.getEstado().trim().isEmpty() ){
            holder.txtEstado.setVisibility(View.GONE);
        }else{
            holder.txtEstado.setText( contacto.getEstado() );
            holder.txtEstado.setVisibility(View.VISIBLE);
        }

        GlideApp.with(contexto)
                .load( contacto.getUrlImg() )
                .placeholder(R.drawable.imagen_perfil_vacia)
                .into( holder.img );
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public void setContactos(ArrayList<Contacto> contactos){
        this.contactos = contactos;
        notifyDataSetChanged();
    }
}
