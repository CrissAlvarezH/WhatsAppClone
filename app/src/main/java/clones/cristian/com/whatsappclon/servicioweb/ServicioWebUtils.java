package clones.cristian.com.whatsappclon.servicioweb;

import clones.cristian.com.whatsappclon.utilidades.Constantes;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioWebUtils {

    public static ServicioWeb getServicioWeb(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( Constantes.URLs.BASE )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        return retrofit.create( ServicioWeb.class );
    }

}
