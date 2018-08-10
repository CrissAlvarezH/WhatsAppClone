package clones.cristian.com.whatsappclon.utilidades;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.Window;

public class StatusBarUtil {

    public static void cambiarColor(Activity contexto, int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = contexto.getWindow();
            window.setStatusBarColor(ContextCompat.getColor(contexto, color));
        }
    }
}
