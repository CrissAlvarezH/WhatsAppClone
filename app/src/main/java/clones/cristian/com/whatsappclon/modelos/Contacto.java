package clones.cristian.com.whatsappclon.modelos;

import android.content.ContentValues;

import java.io.Serializable;

import static clones.cristian.com.whatsappclon.basedatos.DBHelper.ESTADO;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.ID;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.NOMBRE;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.TABLA_CONTACTOS;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.URL_IMG;

public class Contacto implements Serializable, BaseModelo {
    private int id;
    private String nombre;
    private String estado;
    private String urlImg;
    private String pass;

    public Contacto(int id, String nombre, String estado, String urlImg) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.urlImg = urlImg;
    }

    public Contacto(int id, String pass, String nombre, String estado, String urlImg) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.urlImg = urlImg;
        this.pass = pass;
    }

    public Contacto() {
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(ID, id);
        values.put(NOMBRE, nombre);
        values.put(ESTADO, estado);
        values.put(URL_IMG, urlImg);

        return values;
    }

    @Override
    public String getNombreTabla() {
        return TABLA_CONTACTOS;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
