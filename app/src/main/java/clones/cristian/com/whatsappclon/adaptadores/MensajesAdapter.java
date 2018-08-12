package clones.cristian.com.whatsappclon.adaptadores;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import clones.cristian.com.whatsappclon.R;
import clones.cristian.com.whatsappclon.modelos.Mensaje;

public class MensajesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Mensaje> mensajes;

    public MensajesAdapter(ArrayList<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public class MensajeEnviadoViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCuerpo, txtHora;
        private ImageView imgIcono;

        public MensajeEnviadoViewHolder(View itemView) {
            super(itemView);

            txtCuerpo = itemView.findViewById(R.id.item_txt_cuerpo_msj);
            txtHora = itemView.findViewById(R.id.item_txt_hora_msj);
            imgIcono = itemView.findViewById(R.id.item_img_icono_msj);
        }
    }

    public class MensajeRecibidoViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCuerpo, txtHora;

        public MensajeRecibidoViewHolder(View itemView) {
            super(itemView);

            txtCuerpo = itemView.findViewById(R.id.item_txt_cuerpo_msj);
            txtHora = itemView.findViewById(R.id.item_txt_hora_msj);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mensajes.get(position).getTipo();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item;

        switch (viewType){
            case Mensaje.Tipos.ENVIADO:
                item = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_mensaje_enviado, parent, false);

                return new MensajeEnviadoViewHolder(item);

            case Mensaje.Tipos.RECIBIDO:
                item = LayoutInflater.from( parent.getContext() )
                        .inflate(R.layout.item_mensaje_recibido, parent, false);

                return new MensajeRecibidoViewHolder(item);
        }

        // Por defecto ponemos el mensaje enviado
        item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mensaje_enviado, parent, false);

        return new MensajeEnviadoViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Mensaje mensaje = mensajes.get(position);

        switch ( holder.getItemViewType() ){
            case Mensaje.Tipos.RECIBIDO:
                MensajeRecibidoViewHolder holderRec = (MensajeRecibidoViewHolder) holder;

                holderRec.txtCuerpo.setText( mensaje.getCuerpo() );
                holderRec.txtHora.setText( mensaje.getHora() );

                break;
            case Mensaje.Tipos.ENVIADO:

                MensajeEnviadoViewHolder holderEnv = (MensajeEnviadoViewHolder) holder;

                holderEnv.txtCuerpo.setText( mensaje.getCuerpo() );
                holderEnv.txtHora.setText( mensaje.getHora() );

                break;
        }
    }

    @Override
    public int getItemCount() {
        return mensajes.size();
    }


}
