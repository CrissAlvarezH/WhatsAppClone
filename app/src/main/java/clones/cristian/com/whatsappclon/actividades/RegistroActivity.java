package clones.cristian.com.whatsappclon.actividades;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;

import java.io.IOException;
import java.util.ArrayList;

import clones.cristian.com.whatsappclon.R;
import clones.cristian.com.whatsappclon.glide.GlideApp;
import clones.cristian.com.whatsappclon.modelos.Contacto;
import clones.cristian.com.whatsappclon.servicioweb.Respuesta;
import clones.cristian.com.whatsappclon.servicioweb.ServicioWeb;
import clones.cristian.com.whatsappclon.servicioweb.ServicioWebUtils;
import clones.cristian.com.whatsappclon.utilidades.CamaraUtils;
import clones.cristian.com.whatsappclon.utilidades.GaleriaUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistroActivity extends AppCompatActivity {

    private static final int COD_PERMISOS = 324;

    private static final int INTENT_CAMARA = 342;
    private static final int INTENT_GALERIA = 982;

    private ImageView img;
    private EditText edtNombre, edtEstado, edtPass, edtConfPass, edtCel;
    private ProgressBar progress;
    private Button btnEntrar;
    private FloatingActionButton fabEditarImg;
    private TextView txtDescripcionProgreso;

    private ServicioWeb servicioWeb;

    private CamaraUtils camaraUtils;
    private GaleriaUtils galeriaUtils;
    private String rutaImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtDescripcionProgreso = findViewById(R.id.txt_descripcion_progreso);
        img = findViewById(R.id.img_registro);
        fabEditarImg = findViewById(R.id.fab_editar_img);
        edtCel = findViewById(R.id.edt_cel_registro);
        edtNombre = findViewById(R.id.edt_nombre_registro);
        edtEstado = findViewById(R.id.edt_estado_registro);
        edtPass = findViewById(R.id.edt_pass_registro);
        edtConfPass = findViewById(R.id.edt_conf_pass_registro);
        progress = findViewById(R.id.progress_entrar);
        btnEntrar = findViewById(R.id.btn_entrar_chat);

        servicioWeb = ServicioWebUtils.getServicioWeb();

        camaraUtils = new CamaraUtils(this);
        galeriaUtils = new GaleriaUtils(this);
    }

    public void clickEntrar(View btn){
        if( validarCampos() ){
            btnEntrar.setVisibility(View.GONE);
            progress.setVisibility(View.VISIBLE);

            Contacto contacto = new Contacto(
                    0,
                    edtPass.getText().toString().trim(),
                    edtNombre.getText().toString().trim(),
                    edtEstado.getText().toString().trim(),
                    ""
            );

            final Call<Respuesta> respuestaCall = servicioWeb.registro(contacto);

            respuestaCall.enqueue(new Callback<Respuesta>() {
                @Override
                public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                    if( response.isSuccessful() ){
                        Respuesta respuesta = response.body();

                        if( respuesta != null && respuesta.getRespuesta() != null ){

                            if( respuesta.getRespuesta().equals("OKAY")){
                                // Registro exitoso
                                Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    btnEntrar.setVisibility(View.VISIBLE);
                    progress.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<Respuesta> call, Throwable t) {
                    Toast.makeText(RegistroActivity.this, "Ocurrió un inconveniente, vuelva a intentar", Toast.LENGTH_SHORT).show();

                    btnEntrar.setVisibility(View.VISIBLE);
                    progress.setVisibility(View.GONE);
                }
            });
        }
    }

    private boolean validarCampos(){

        if( edtCel.getText().toString().trim().isEmpty() ){
            edtCel.setError("Campo obligatorio");
            edtCel.requestFocus();
            return false;
        }

        if( edtNombre.getText().toString().trim().isEmpty() ){
            edtNombre.setError("Ingrese su nombre");
            edtNombre.requestFocus();
            return false;
        }

        if( edtPass.getText().toString().trim().isEmpty() ){
            edtPass.setError("Ingrese una contraseña");
            edtPass.requestFocus();
            return false;
        }

        if( edtConfPass.getText().toString().trim().isEmpty() ){
            edtConfPass.setError("Confirme su contraseña");
            edtConfPass.requestFocus();
            return false;
        }

        if( !edtPass.getText().toString().equals( edtConfPass.getText().toString() ) ){
            edtConfPass.setError("Las contraseñas no coinciden");
            edtConfPass.requestFocus();
            return false;
        }

        return true;
    }

    public void clickEditarImg(View btn){
        if( pedirPermisosCamara() ){

            AlertDialog.Builder builderDialog = new AlertDialog.Builder(this)
                    .setMessage("¿De donde desea tomar la imagen?")
                    .setPositiveButton("CAMARA", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            try {
                                Intent intentCamara = camaraUtils.crearIntentCamera();

                                startActivityForResult(intentCamara, INTENT_CAMARA);

                            } catch (IOException e) {
                                e.printStackTrace();
                                Toast.makeText(RegistroActivity.this, "Error al tomar imagen", Toast.LENGTH_SHORT).show();
                            }

                        }
                    })
                    .setNegativeButton("GALERIA", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intentGaleria = galeriaUtils.getIntentGaleria();

                            startActivityForResult(intentGaleria, INTENT_GALERIA);

                        }
                    });

            builderDialog.create().show();
        }
    }

    public boolean pedirPermisosCamara(){
        boolean todosConsedidos = true;
        ArrayList<String> permisosFaltantes = new ArrayList<>();

        boolean permisoCamera = ( ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED);

        boolean permisoEscrituraSD = ( ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED);

        boolean permisoLecturaSD = ( ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED);


        if(!permisoCamera){
            todosConsedidos = false;
            permisosFaltantes.add(android.Manifest.permission.CAMERA);
        }

        if(!permisoEscrituraSD){
            todosConsedidos = false;
            permisosFaltantes.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if(!permisoLecturaSD){
            todosConsedidos = false;
            permisosFaltantes.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if(!todosConsedidos) {
            String[] permisos = new String[permisosFaltantes.size()];
            permisos = permisosFaltantes.toArray(permisos);

            ActivityCompat.requestPermissions(this, permisos, COD_PERMISOS);
        }

        return todosConsedidos;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch (requestCode){
                case INTENT_CAMARA:

                    rutaImg = camaraUtils.getRuta();

                    break;
                case INTENT_GALERIA:

                    galeriaUtils.setImgUri( data.getData() );

                    rutaImg = galeriaUtils.getRuta();

                    break;
            }

            if(rutaImg != null){

                GlideApp.with(this)
                        .load(rutaImg)
                        .into(img);

            }
        }

    }
}
