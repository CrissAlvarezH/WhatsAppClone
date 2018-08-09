package clones.cristian.com.whatsappclon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import clones.cristian.com.whatsappclon.adaptadores.ContactosAdapter;
import clones.cristian.com.whatsappclon.modelos.Contacto;

public class ContactosActivity extends AppCompatActivity implements ContactosAdapter.ContactosListener {

    private RecyclerView recyclerContactos;
    private ContactosAdapter adapterContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        setToolbar();

        recyclerContactos = findViewById(R.id.recycler_contactos);

        RecyclerView.LayoutManager lmContactos = new LinearLayoutManager(this);
        recyclerContactos.setLayoutManager(lmContactos);

        ArrayList<Contacto> contactos = new ArrayList<>();

        contactos.add( new Contacto(1, "Contacto 1", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGsVX7FxBWMuWzfjqGq6uctbI7135afcD9gnME1Xcf7DjsjEn_") );
        contactos.add( new Contacto(1, "Contacto 2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTCqWPEHiLHJfQEiWCDs5qBSVOB9pC2cN6cWdgyVYftHU6T7gtl") );
        contactos.add( new Contacto(1, "Contacto 3", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTOQ6rh2RPa1CHIiLRCiRlW0CbFJQmrNvrtJrcvVrbcX1TyqCeS") );

        adapterContactos = new ContactosAdapter(contactos, this, this);

        recyclerContactos.setAdapter(adapterContactos);
    }

    private void setToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar_contactos);

        if( toolbar != null ){
            setSupportActionBar(toolbar);

            if( getSupportActionBar() != null ){
                getSupportActionBar().setTitle("Contactos");
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

    @Override
    public void contactoClick(Contacto contacto, int posicion) {
        Toast.makeText(this, "Abrir chat con "+contacto.getNombre(), Toast.LENGTH_SHORT).show();
    }
}
