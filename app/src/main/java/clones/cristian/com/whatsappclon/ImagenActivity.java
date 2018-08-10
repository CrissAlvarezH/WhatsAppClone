package clones.cristian.com.whatsappclon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import clones.cristian.com.whatsappclon.glide.GlideApp;
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
            String urlImg = extras.getString(Constantes.Args.URL_IMG);
            String nombre = extras.getString(Constantes.Args.NOMBRE_CONTACTO);

            setToolbar(nombre);

            GlideApp.with(this)
                    .load(urlImg)
                    .placeholder(R.drawable.imagen_perfil_vacia)
                    .into(img);

        }else{
            Toast.makeText(this, "Imagen no especificada", Toast.LENGTH_SHORT).show();
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
