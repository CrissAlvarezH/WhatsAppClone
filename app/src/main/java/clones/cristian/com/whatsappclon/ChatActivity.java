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

import java.util.ArrayList;

import clones.cristian.com.whatsappclon.adaptadores.ChatAdapter;
import clones.cristian.com.whatsappclon.glide.GlideApp;
import clones.cristian.com.whatsappclon.modelos.Chat;

public class ChatActivity extends AppCompatActivity implements ChatAdapter.ChatListener{

    private RecyclerView recyclerChats;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setToobar();

        recyclerChats = findViewById(R.id.recycler_chats);

        RecyclerView.LayoutManager lmContactosChat = new LinearLayoutManager(this);
        recyclerChats.setLayoutManager(lmContactosChat);

        ArrayList<Chat> chats = new ArrayList<>();

        // Llenamos con datos de prueba
        chats.add( new Chat(0, "Nombre Contacto 1", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnuj-RSh6Sqf_J5BKjXAaMjoC9zeeImmxncIg2QQIHmfMadNW5", "ultimoMensaje", "12:19 PM", 3) );
        chats.add( new Chat(0, "Nombre Contacto 2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxKNEVSl0JXI1jjeAAPEN8ut0v-z6IM6OOAfIC621nOSxNMSZz", "ultimoMensaje", "9:29 PM", 1) );
        chats.add( new Chat(0, "Nombre Contacto 3", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmaLl2wTvViAfXR2xlwxnuhpR74pHzUH7wV-VY8BSi9Ep0MJeK", "ultimoMensaje", "10:33 PM", 23) );
        chats.add( new Chat(0, "Nombre Contacto 4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnuj-RSh6Sqf_J5BKjXAaMjoC9zeeImmxncIg2QQIHmfMadNW5", "ultimoMensaje", "2:30 PM", 2) );
        chats.add( new Chat(0, "Nombre Contacto 5", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSiwf-Ig8De4vyIVgCIvFdLWRF8dnTSMe6ihG1iZchNcX7AVKuO", "ultimoMensaje", "4:27 AM", 0) );
        chats.add( new Chat(0, "Nombre Contacto 6", "https://i.pinimg.com/originals/83/1f/7e/831f7ebda69e66fc88dd07aa013923ea.jpg", "ultimoMensaje", "12:10 PM", 1) );
        chats.add( new Chat(0, "Nombre Contacto 6", "https://i.pinimg.com/originals/83/1f/7e/831f7ebda69e66fc88dd07aa013923ea.jpg", "ultimoMensaje", "12:10 PM", 1) );
        chats.add( new Chat(0, "Nombre Contacto 6", "https://i.pinimg.com/originals/83/1f/7e/831f7ebda69e66fc88dd07aa013923ea.jpg", "ultimoMensaje", "12:10 PM", 1) );
        chats.add( new Chat(0, "Nombre Contacto 6", "https://i.pinimg.com/originals/83/1f/7e/831f7ebda69e66fc88dd07aa013923ea.jpg", "ultimoMensaje", "12:10 PM", 1) );
        chats.add( new Chat(0, "Nombre Contacto 6", "https://i.pinimg.com/originals/83/1f/7e/831f7ebda69e66fc88dd07aa013923ea.jpg", "ultimoMensaje", "12:10 PM", 1) );
        chats.add( new Chat(0, "Nombre Contacto 6", "https://i.pinimg.com/originals/83/1f/7e/831f7ebda69e66fc88dd07aa013923ea.jpg", "ultimoMensaje", "12:10 PM", 1) );

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
        Toast.makeText(this, "Click a "+chat.getNombreContacto(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChatLongClick(Chat chat, int posicion) {
        Toast.makeText(this, "Long click a "+chat.getNombreContacto(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChatImgClick(Chat chat, int posicion) {
        Dialog dialogoImg = new Dialog(this);

        dialogoImg.setContentView(R.layout.dialogo_img_perfil);

        ImageView imgPerfil = dialogoImg.findViewById(R.id.dialogo_img);
        TextView txtNombre = dialogoImg.findViewById(R.id.dialogo_txt_nombre);
        ImageView imgChat = dialogoImg.findViewById(R.id.dialogo_img_chat);
        ImageView imgInfo = dialogoImg.findViewById(R.id.dialogo_img_info);

        GlideApp.with(this)
                .load( chat.getUrlImagen() )
                .placeholder(R.drawable.imagen_perfil_vacia)
                .into(imgPerfil);

        txtNombre.setText( chat.getNombreContacto() );

        imgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dialogoImg.show();
    }

    public void clickAddChat(View btn){
        Intent intent = new Intent(this, ContactosActivity.class);
        startActivity(intent);
    }
}
