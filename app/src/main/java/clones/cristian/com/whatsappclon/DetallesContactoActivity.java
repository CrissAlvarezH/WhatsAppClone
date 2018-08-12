package clones.cristian.com.whatsappclon;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

import clones.cristian.com.whatsappclon.adaptadores.ImagenesAdapter;
import clones.cristian.com.whatsappclon.glide.GlideApp;
import clones.cristian.com.whatsappclon.modelos.Contacto;
import clones.cristian.com.whatsappclon.utilidades.Constantes;

public class DetallesContactoActivity extends AppCompatActivity implements ImagenesAdapter.ImgListener {

    private ImageView img;
    private TextView txtEstado;
    private RecyclerView recyclerImgs;
    private ImagenesAdapter imgsAdapter;
    private LinearLayout layoutImgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_contacto);

        Contacto contacto = (Contacto) getIntent().getExtras().getSerializable( Constantes.Args.CONTACTO );

        setToolbar(contacto.getUrlImg(), contacto.getNombre());

        txtEstado = findViewById(R.id.txt_estado_det_contacto);
        txtEstado.setText( contacto.getEstado() );

        layoutImgs = findViewById(R.id.layout_imagenes);
        recyclerImgs = findViewById(R.id.recycler_img_det_contacto);

        RecyclerView.LayoutManager lmImgs = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerImgs.setLayoutManager(lmImgs);

        ArrayList<String> rutasImgs = new ArrayList<>();
        imgsAdapter = new ImagenesAdapter(this, this, rutasImgs);

        recyclerImgs.setAdapter(imgsAdapter);

        if(imgsAdapter.getItemCount() == 0){
            layoutImgs.setVisibility(View.GONE);
        }
    }

    private void setToolbar(String rutaImg, String titulo){
        Toolbar toolbar = findViewById(R.id.toolbar_det_contacto);
        setSupportActionBar(toolbar);
        if( getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapser = findViewById(R.id.collapser);
        collapser.setTitle(titulo);

        img = findViewById(R.id.img_det_contacto);

        GlideApp.with(this)
                .load(rutaImg)
                .placeholder(R.drawable.imagen_perfil_vacia)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void imgClick(String ruta, int posicion) {

    }
}
