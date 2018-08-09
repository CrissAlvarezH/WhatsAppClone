package clones.cristian.com.whatsappclon.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import clones.cristian.com.whatsappclon.R;
import clones.cristian.com.whatsappclon.modelos.Chat;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ContactosViewHolder> {

    private Context contexto;
    private ArrayList<Chat> contactos;

    public ChatAdapter(Context context, ArrayList<Chat> contactos) {
        this.contactos = contactos;
        this.contexto = context;
    }

    public class ContactosViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView txtNombreContacto, txtUltimoMensaje, txtHora, txtCantidadMsj;

        public ContactosViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.item_img);
            txtNombreContacto = itemView.findViewById(R.id.item_nombre_contacto);
            txtUltimoMensaje = itemView.findViewById(R.id.item_ultimo_mensaje);
            txtHora = itemView.findViewById(R.id.item_hora_ultimo_mensaje);
            txtCantidadMsj = itemView.findViewById(R.id.item_cant_msj_no_leidos);
        }
    }

    @NonNull
    @Override
    public ContactosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from( parent.getContext() ).inflate(R.layout.item_chat, parent, false);

        return new ContactosViewHolder( item );
    }

    @Override
    public void onBindViewHolder(@NonNull ContactosViewHolder holder, int position) {
        Chat contacto = contactos.get( position );

        holder.txtNombreContacto.setText( contacto.getNombreContacto() );
        holder.txtUltimoMensaje.setText( contacto.getUltimoMensaje() );
        holder.txtHora.setText( contacto.getHoraMensaje() );

        // Si no hay mensaje no leidos escondemos el txtCantidadMsj
        if( contacto.getCantidadMsjNoLeidos() == 0 ){
            holder.txtCantidadMsj.setVisibility(View.INVISIBLE);
        }else {
            holder.txtCantidadMsj.setText( contacto.getCantidadMsjNoLeidos() + "" );
            holder.txtCantidadMsj.setVisibility(View.VISIBLE);
        }

        Glide.with(contexto)
                .load( contacto.getUrlImagen() )
                .into( holder.img );
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

}
