package clones.cristian.com.whatsappclon.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import clones.cristian.com.whatsappclon.ChatAplicacion;
import clones.cristian.com.whatsappclon.R;
import static clones.cristian.com.whatsappclon.utilidades.Constantes.ListenersIO.ENTRAT_CHAT;

public class RegistroActivity extends AppCompatActivity {

    private ImageView img;
    private EditText edtNombre, edtEstado;
    private ProgressBar progress;
    private Button btnEntrar;
    private TextView txtDescripcionProgreso;

    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtDescripcionProgreso = findViewById(R.id.txt_descripcion_progreso);
        img = findViewById(R.id.img_registro);
        edtNombre = findViewById(R.id.edt_nombre_registro);
        edtEstado = findViewById(R.id.edt_estado_registro);
        progress = findViewById(R.id.progress_entrar);
        btnEntrar = findViewById(R.id.btn_entrar_chat);

        ChatAplicacion app = (ChatAplicacion) getApplication();
        socket = app.getSocket();

        socket.on(ENTRAT_CHAT, entradaAlChat);
    }

    public void clickEntrar(View btn){
        if( validarCampos() ){

        }
    }

    private boolean validarCampos(){
        if( edtNombre.getText().toString().trim().isEmpty() ){
            edtNombre.setError("Ingrese su nombre");

            return false;
        }

        return true;
    }

    private Emitter.Listener entradaAlChat = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            String idSocket = (String) args[0];


        }
    };
}
