package clones.cristian.com.whatsappclon;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

import clones.cristian.com.whatsappclon.adaptadores.ChatAdapter;
import clones.cristian.com.whatsappclon.glide.GlideApp;
import clones.cristian.com.whatsappclon.modelos.Chat;
import clones.cristian.com.whatsappclon.modelos.Contacto;
import clones.cristian.com.whatsappclon.modelos.Mensaje;
import clones.cristian.com.whatsappclon.utilidades.Constantes;

public class ChatsActivity extends AppCompatActivity implements ChatAdapter.ChatListener{

    private RecyclerView recyclerChats;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        setToobar();

        recyclerChats = findViewById(R.id.recycler_chats);

        RecyclerView.LayoutManager lmContactosChat = new LinearLayoutManager(this);
        recyclerChats.setLayoutManager(lmContactosChat);

        ArrayList<Chat> chats = new ArrayList<>();

        // Llenamos con datos de prueba
        Contacto contato1 = new Contacto(1, "Cristian", "Disponible", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnuj-RSh6Sqf_J5BKjXAaMjoC9zeeImmxncIg2QQIHmfMadNW5");
        Contacto contato2 = new Contacto(1, "Jose", "Ocupado", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmWrzMfHDpcOcSPxiXERbtr_qbzhOAhJVvBQI3xnP8dsskoLP-");
        Contacto contato3 = new Contacto(1, "Maria", "En la Universidad", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_hmURl3BzZBAZ-TWMj8PMEWhuOQEqPHbJToXvDAhwFEtypkiq");
        Contacto contato4 = new Contacto(1, "Juan", "En mi casa", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpJd4_WJ9OpAkHOPjiHJ1uysrxMq45Kcry7tbCPUMjdJ9s_fQ0");

        Mensaje mensaje1 = new Mensaje(Mensaje.Tipos.RECIBIDO, "Hola ¿como estás?", "12:34 PM");
        Mensaje mensaje2 = new Mensaje(Mensaje.Tipos.RECIBIDO, "¿Como te ha ido?", "10:34 PM");
        Mensaje mensaje3 = new Mensaje(Mensaje.Tipos.RECIBIDO, "¿Qué haces?", "9:14 AM");
        Mensaje mensaje4 = new Mensaje(Mensaje.Tipos.RECIBIDO, "Yo estoy bien", "2:08 AM");

        chats.add( new Chat(contato1, mensaje1, 2) );
        chats.add( new Chat(contato2, mensaje2, 10) );
        chats.add( new Chat(contato3, mensaje3, 0) );
        chats.add( new Chat(contato4, mensaje4, 6) );

        chatAdapter = new ChatAdapter(this, chats, this);
        recyclerChats.setAdapter(chatAdapter);
    }

    private void setToobar(){
        Toolbar toolbar = findViewById(R.id.toolbar_chats);

        if(toolbar != null){
            setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onChatClick(Chat chat, int posicion) {
        Intent intent = new Intent(ChatsActivity.this, ChatActivity.class);
        intent.putExtra( Constantes.Args.CONTACTO, chat.getContacto() );
        startActivity(intent);
    }

    @Override
    public void onChatLongClick(Chat chat, int posicion) {
        Toast.makeText(this, "Long click a "+chat.getContacto().getNombre(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChatImgClick(final Chat chat, int posicion) {
        Dialog dialogoImg = new Dialog(this);

        dialogoImg.setContentView(R.layout.dialogo_img_perfil);

        ImageView imgPerfil = dialogoImg.findViewById(R.id.dialogo_img);
        TextView txtNombre = dialogoImg.findViewById(R.id.dialogo_txt_nombre);
        ImageView imgChat = dialogoImg.findViewById(R.id.dialogo_img_chat);
        ImageView imgInfo = dialogoImg.findViewById(R.id.dialogo_img_info);

        GlideApp.with(this)
                .load( chat.getContacto().getUrlImg() )
                .placeholder(R.drawable.imagen_perfil_vacia)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgPerfil);

        txtNombre.setText( chat.getContacto().getNombre() );

        imgPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatsActivity.this, ImagenActivity.class);
                intent.putExtra( Constantes.Args.CONTACTO, chat.getContacto() );
                startActivity(intent);
            }
        });

        imgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatsActivity.this, ChatActivity.class);
                intent.putExtra( Constantes.Args.CONTACTO, chat.getContacto() );
                startActivity(intent);
            }
        });

        imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatsActivity.this, DetallesContactoActivity.class);
                intent.putExtra( Constantes.Args.CONTACTO, chat.getContacto() );
                startActivity(intent);
            }
        });

        dialogoImg.show();
    }

    public void clickAddChat(View btn){
        Intent intent = new Intent(this, ContactosActivity.class);
        startActivity(intent);
    }
}
