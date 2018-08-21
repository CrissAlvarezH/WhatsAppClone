package clones.cristian.com.whatsappclon.servicioweb;

import clones.cristian.com.whatsappclon.modelos.Contacto;
import clones.cristian.com.whatsappclon.utilidades.Constantes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServicioWeb {

    @POST( Constantes.URLs.LOGIN )
    Call<Respuesta> login(@Body Contacto contacto);

    @POST( Constantes.URLs.REGISTRO )
    Call<Respuesta> registro(@Body Contacto contacto);


}
