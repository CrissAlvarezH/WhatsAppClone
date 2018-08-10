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
import clones.cristian.com.whatsappclon.glide.GlideApp;
import clones.cristian.com.whatsappclon.modelos.Chat;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    public interface ChatListener {
        void onChatClick(Chat chat, int posicion);
        void onChatLongClick(Chat chat, int posicion);
        void onChatImgClick(Chat chat, int posicion);
    }

    private Context contexto;
    private ArrayList<Chat> chats;
    private ChatListener chatListener;

    public ChatAdapter(Context context, ArrayList<Chat> chats, ChatListener chatListener) {
        this.chats = chats;
        this.contexto = context;
        this.chatListener = chatListener;
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder
                                    implements View.OnClickListener, View.OnLongClickListener{

        private ImageView img;
        private TextView txtNombreContacto, txtUltimoMensaje, txtHora, txtCantidadMsj;

        public ChatViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.item_img);
            txtNombreContacto = itemView.findViewById(R.id.item_nombre_contacto);
            txtUltimoMensaje = itemView.findViewById(R.id.item_ultimo_mensaje);
            txtHora = itemView.findViewById(R.id.item_hora_ultimo_mensaje);
            txtCantidadMsj = itemView.findViewById(R.id.item_cant_msj_no_leidos);

            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
            img.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if( chatListener != null ) {
                switch (v.getId()) {
                    case R.id.layout_item_chat:
                       chatListener.onChatClick( chats.get(getAdapterPosition()), getAdapterPosition() );

                        break;
                    case R.id.item_img:
                        chatListener.onChatImgClick( chats.get(getAdapterPosition()), getAdapterPosition() );
                        break;
                }
            }

        }

        @Override
        public boolean onLongClick(View v) {

            if( chatListener != null ){
                chatListener.onChatLongClick( chats.get(getAdapterPosition()), getAdapterPosition() );
                return true;
            }

            return false;
        }
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from( parent.getContext() ).inflate(R.layout.item_chat, parent, false);

        return new ChatViewHolder( item );
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Chat contacto = chats.get( position );

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

        GlideApp.with(contexto)
                .load( contacto.getUrlImagen() )
                .placeholder(R.drawable.imagen_perfil_vacia)
                .into( holder.img );
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

}
