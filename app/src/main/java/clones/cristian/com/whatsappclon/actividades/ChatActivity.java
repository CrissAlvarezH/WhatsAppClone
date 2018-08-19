package clones.cristian.com.whatsappclon.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import clones.cristian.com.whatsappclon.R;
import clones.cristian.com.whatsappclon.adaptadores.MensajesAdapter;
import clones.cristian.com.whatsappclon.glide.GlideApp;
import clones.cristian.com.whatsappclon.modelos.Contacto;
import clones.cristian.com.whatsappclon.modelos.Mensaje;
import clones.cristian.com.whatsappclon.utilidades.Constantes;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imgContacto;
    private TextView txtTituloToolbar;
    private RecyclerView recyclerMensajes;
    private EditText edtMensaje;
    private ImageButton btnEnviar;
    private LinearLayout layoutAtras;

    private MensajesAdapter mensajesAdapter;
    private Contacto contacto;


    private Socket socket;
    {
        try {
            socket = IO.socket("http://192.168.137.1:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        socket.on("recibirMensaje", onRecibirMensaje);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(onRecibirMensaje != null)
            socket.off("recibirMensaje", onRecibirMensaje);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Bundle extras = getIntent().getExtras();
        contacto = (Contacto) extras.getSerializable(Constantes.Args.CONTACTO);

        setToolbar(contacto.getUrlImg(), contacto.getNombre());

        recyclerMensajes = findViewById(R.id.recyclerview_mensajes);
        LinearLayoutManager lmMensaje = new LinearLayoutManager(this);
        lmMensaje.setStackFromEnd(true);// te ubica en el ultimo item
        recyclerMensajes.setLayoutManager(lmMensaje);

        layoutAtras = findViewById(R.id.layout_atras);
        edtMensaje = findViewById(R.id.edt_mensaje);
        btnEnviar = findViewById(R.id.imgbtn_enviar);

        layoutAtras.setOnClickListener(this);
        btnEnviar.setOnClickListener(this);

        Mensaje mensaje1  = new Mensaje(1, Mensaje.Tipos.ENVIADO, "Hola", "","10:20 PM", Mensaje.Estados.ENVIADO, 1);
        Mensaje mensaje2  = new Mensaje(1, Mensaje.Tipos.ENVIADO, "¿Como estás?", "","10:22 PM", Mensaje.Estados.ENVIADO, 1);
        Mensaje mensaje3  = new Mensaje(1, Mensaje.Tipos.RECIBIDO, "Bien gracias", "","10:25 PM", Mensaje.Estados.ENVIADO, 1);
        Mensaje mensaje4  = new Mensaje(1, Mensaje.Tipos.ENVIADO, "Me alegra", "","9:20 AM", Mensaje.Estados.ENVIADO, 1);
        Mensaje mensaje5  = new Mensaje(1, Mensaje.Tipos.RECIBIDO, "Gracias", "","17:23 PM", Mensaje.Estados.ENVIADO, 1);
        Mensaje mensaje6  = new Mensaje(1, Mensaje.Tipos.ENVIADO, "De nada", "","2:20 AM", Mensaje.Estados.ENVIADO, 1);

        ArrayList<Mensaje> mensajes = new ArrayList<>();
        mensajes.add(mensaje1);
        mensajes.add(mensaje2);
        mensajes.add(mensaje3);
        mensajes.add(mensaje4);
        mensajes.add(mensaje5);
        mensajes.add(mensaje6);

        mensajesAdapter = new MensajesAdapter(mensajes);

        recyclerMensajes.setAdapter(mensajesAdapter);
    }

    private void setToolbar(String urlImg, String nombreContacto){
        Toolbar toolbar = findViewById(R.id.toolbar_chat);
        imgContacto = findViewById(R.id.img_contacto_toolbar);
        txtTituloToolbar = findViewById(R.id.txt_title_toolbar);

        txtTituloToolbar.setOnClickListener(this);

        setSupportActionBar(toolbar);

        GlideApp.with(this)
                .load(urlImg)
                .placeholder(R.drawable.imagen_perfil_vacia)
                .into(imgContacto);

        txtTituloToolbar.setText( nombreContacto );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgbtn_enviar:

                if( !edtMensaje.getText().toString().trim().isEmpty() ){
                    String fecha = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                            .format( new Date() );
                    String hora = new SimpleDateFormat("HH:mm", Locale.getDefault())
                                            .format( new Date() );

                    Mensaje mensaje = new Mensaje(
                            1,
                            Mensaje.Tipos.ENVIADO,
                            edtMensaje.getText().toString().trim(),
                            fecha,
                            hora,
                            Mensaje.Estados.SIN_ENVIAR,
                            1
                    );

                    mensajesAdapter.agregarMensaje(mensaje);

                    edtMensaje.setText("");;

                    // Movemos el scroll hasta el ultimo mensaje
                    recyclerMensajes.smoothScrollToPosition( mensajesAdapter.getItemCount() - 1 );
                }

                break;
            case R.id.txt_title_toolbar:
                Intent intent = new Intent(this, DetallesContactoActivity.class);
                intent.putExtra( Constantes.Args.CONTACTO, contacto );
                startActivity(intent);
                break;
            case R.id.layout_atras:
                finish();
                break;

        }
    }

    // ============================================================================================
    // [INICIO] EVENTOS DE SOCKET IO
    // --------------------------------------------------------------------------------------------

    private Emitter.Listener onRecibirMensaje = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject json = (JSONObject) args[0];

            try {

                int idEmisor = json.getInt("idEmisor");

                if( idEmisor == contacto.getId() ) {
                    JSONObject mensaje = json.getJSONObject("mensaje");

                    String cuerpoMsj = mensaje.getString("cuerpo");
                    String horaMsj = mensaje.getString("hora");
                    String fechaMsj = mensaje.getString("fecha");

                    mensajesAdapter.agregarMensaje(new Mensaje(
                            1,
                            Mensaje.Tipos.RECIBIDO,
                            cuerpoMsj,
                            fechaMsj,
                            horaMsj,
                            Mensaje.Estados.ENVIADO,
                            idEmisor
                    ));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    // --------------------------------------------------------------------------------------------
    // [FIN] EVENTOS DE SOCKET IO
    // ============================================================================================
}
