package clones.cristian.com.whatsappclon.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import clones.cristian.com.whatsappclon.R;

public class ImagenesAdapter extends RecyclerView.Adapter<ImagenesAdapter.ImagenesViewHolder> {
    public interface ImgListener {
        void imgClick(String ruta, int posicion);
    }

    private Context contexto;
    private ImgListener miListener;
    private ArrayList<String> rutasImg;

    public ImagenesAdapter(Context contexto, ImgListener miListener, ArrayList<String> rutasImg) {
        this.miListener = miListener;
        this.rutasImg = rutasImg;
        this.contexto = contexto;
    }

    public class ImagenesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView img;

        public ImagenesViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.item_img);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(miListener != null){
                miListener.imgClick(rutasImg.get( getAdapterPosition()), getAdapterPosition());
            }
        }
    }

    @NonNull
    @Override
    public ImagenesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_imagen, parent, false);

        return new ImagenesViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagenesViewHolder holder, int position) {

        Glide.with(contexto)
                .load( rutasImg.get(position) )
                .into( holder.img );
    }

    @Override
    public int getItemCount() {
        return rutasImg.size();
    }

}
