package clones.cristian.com.whatsappclon.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import clones.cristian.com.whatsappclon.R;
import clones.cristian.com.whatsappclon.adaptadores.ContactosAdapter;
import clones.cristian.com.whatsappclon.modelos.Contacto;
import clones.cristian.com.whatsappclon.utilidades.Constantes;

public class ContactosActivity extends AppCompatActivity implements ContactosAdapter.ContactosListener {

    private static final String TAG = "ActividadContactos";

    private RecyclerView recyclerContactos;
    private ContactosAdapter adapterContactos;
    private ArrayList<Contacto> contactos;

    private Socket socket;

    private Gson gson;

    {
        try {
            socket = IO.socket("http://10.10.10.104:3000");

            Log.v(TAG, "Creamos instancia del socket");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        socket.on("listarConectados", listarConectados);
        socket.on("identificarse", identificarse);

        socket.connect();

        Log.v(TAG, "Conectamos el socket y lo ponemos a la escucha");
    }

    @Override
    protected void onPause() {
        super.onPause();

        socket.disconnect();

        socket.off("listarConectados", listarConectados);
        socket.off("identificarse", identificarse);

        Log.v(TAG, "DESCONECTAMOS el socket y le quitamos las escuchas");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        setToolbar();
        gson = new Gson();

        recyclerContactos = findViewById(R.id.recycler_contactos);

        RecyclerView.LayoutManager lmContactos = new LinearLayoutManager(this);
        recyclerContactos.setLayoutManager(lmContactos);

        contactos = new ArrayList<>();

        contactos.add( new Contacto(1, "Contacto 1", "Estado 1", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGsVX7FxBWMuWzfjqGq6uctbI7135afcD9gnME1Xcf7DjsjEn_") );
        contactos.add( new Contacto(1, "Contacto 2", "","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTCqWPEHiLHJfQEiWCDs5qBSVOB9pC2cN6cWdgyVYftHU6T7gtl") );
        contactos.add( new Contacto(1, "Contacto 3", "Estado 3","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTOQ6rh2RPa1CHIiLRCiRlW0CbFJQmrNvrtJrcvVrbcX1TyqCeS") );

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contactos, menu);

        MenuItem itemSearch = menu.findItem(R.id.item_buscar_contactos);
        SearchView searchView = (SearchView) itemSearch.getActionView();
        searchView.setQueryHint("Buscar ...");
        searchView.setOnQueryTextListener(new Buscador());

        return super.onCreateOptionsMenu(menu);
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
        Intent intentChat = new Intent(this, ChatActivity.class);
        intentChat.putExtra(Constantes.Args.CONTACTO, contacto);
        startActivity(intentChat);
    }

    private class Buscador implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {

            if( !newText.trim().isEmpty() ){
                ArrayList<Contacto> aux = new ArrayList<>();

                for(Contacto contacto : contactos){

                    if( contacto.getNombre().toLowerCase().contains( newText.toLowerCase() ) ){
                        aux.add(contacto);
                    }
                }

                // aux contiene los contactos cuyo nombre coincide en alguna parte con newText
                adapterContactos.setContactos(aux);

            }else{// Si el buscar está vacio
                adapterContactos.setContactos(contactos);
            }

            return true;
        }
    }


    // ============================================================================================
    // [INICIO] EVENTOS DE SOCKET IO
    // --------------------------------------------------------------------------------------------

    private Emitter.Listener listarConectados = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONArray jsonUsuarios = (JSONArray) args[0];

            Log.v(TAG, "listarContactos = "+gson.toJson(jsonUsuarios));

            for(int i=0; i<jsonUsuarios.length(); i++){
                try {
                    JSONObject usuarioJson = jsonUsuarios.getJSONObject(i);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private Emitter.Listener identificarse = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.v(TAG, "identificarse = "+gson.toJson(args[0]));


            String miIdSocket = String.valueOf( args[0] );

            JSONObject jsonUsuario = new JSONObject();
            try {
                jsonUsuario.put("idSocket", miIdSocket);
                jsonUsuario.put("nombre", "Cristian");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            socket.emit("entrarChat", jsonUsuario);
        }
    };

    // --------------------------------------------------------------------------------------------
    // [FIN] EVENTOS DE SOCKET IO
    // ============================================================================================
}
