package clones.cristian.com.whatsappclon.modelos;

import android.content.ContentValues;

public interface BaseModelo {
    ContentValues toContentValues();
    String getNombreTabla();
}
