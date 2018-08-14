package clones.cristian.com.whatsappclon.adaptadores;

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
import clones.cristian.com.whatsappclon.modelos.Mensaje;

public class MensajesAdapter extends RecyclerView.Adapter<MensajesAdapter.MensajeViewHolder> {
    private ArrayList<Mensaje> mensajes;

    public MensajesAdapter(ArrayList<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public class MensajeViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCuerpo, txtHora;
        private ImageView imgIcono;

        public MensajeViewHolder(View itemView) {
            super(itemView);

            txtCuerpo = itemView.findViewById(R.id.item_txt_cuerpo_msj);
            txtHora = itemView.findViewById(R.id.item_txt_hora_msj);
            imgIcono = itemView.findViewById(R.id.item_img_icono_msj);
        }

        public void setCuerpo(String cuerpo){
            if( txtCuerpo != null ) txtCuerpo.setText(cuerpo);
        }

        public void setHora(String hora){
            if( txtHora != null ) txtHora.setText(hora);
        }

        public void setImgIcono(String url){
            if( imgIcono != null ) {
                // TODO setear el logo
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mensajes.get(position).getTipo();
    }

    @NonNull
    @Override
    public MensajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutItem = -1;

        switch (viewType){
            case Mensaje.Tipos.ENVIADO:
                layoutItem = R.layout.item_mensaje_enviado;
                break;
            case Mensaje.Tipos.RECIBIDO:
                layoutItem = R.layout.item_mensaje_recibido;
                break;
        }

        View item = LayoutInflater.from( parent.getContext() )
                .inflate(layoutItem, parent, false);

        return new MensajeViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MensajeViewHolder holder, int position) {
        Mensaje mensaje = mensajes.get(position);

        holder.setCuerpo( mensaje.getCuerpo() );
        holder.setHora( mensaje.getHora() );
    }

    @Override
    public int getItemCount() {
        return mensajes.size();
    }

    public void agregarMensaje(Mensaje mensaje){
        mensajes.add(mensaje);
        notifyItemInserted(mensajes.size()  - 1);
    }
}
