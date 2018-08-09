package clones.cristian.com.whatsappclon.modelos;

public class Contacto {
    private int id;
    private String nombre;
    private String urlImg;

    public Contacto(int id, String nombre, String urlImg) {
        this.id = id;
        this.nombre = nombre;
        this.urlImg = urlImg;
    }

    public Contacto() {
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
