package clones.cristian.com.whatsappclon.modelos;

import java.io.Serializable;

public class Contacto implements Serializable {
    private int id;
    private String nombre;
    private String estado;
    private String urlImg;

    public Contacto(int id, String nombre, String estado, String urlImg) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.urlImg = urlImg;
    }

    public Contacto() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
