package clones.cristian.com.whatsappclon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import clones.cristian.com.whatsappclon.glide.GlideApp;
import clones.cristian.com.whatsappclon.modelos.Contacto;
import clones.cristian.com.whatsappclon.utilidades.Constantes;
import clones.cristian.com.whatsappclon.utilidades.StatusBarUtil;

public class ImagenActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);
        StatusBarUtil.cambiarColor(this, R.color.negro);

        Bundle extras = getIntent().getExtras();

        img = findViewById(R.id.img_grande);

        if( extras != null) {
            Contacto contacto = (Contacto) extras.getSerializable( Constantes.Args.CONTACTO );

            setToolbar( contacto.getNombre() );

            GlideApp.with(this)
                    .load( contacto.getUrlImg() )
                    .placeholder(R.drawable.imagen_perfil_vacia)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(img);

        }else{
            Toast.makeText(this, "Contacto no especificado", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void setToolbar(String titulo){
        Toolbar toolbar = findViewById(R.id.toolbar_imagen);

        if(toolbar != null){
            setSupportActionBar(toolbar);

            if(getSupportActionBar() != null){
                getSupportActionBar().setTitle(titulo);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
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
}
