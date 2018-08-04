package clones.cristian.com.whatsappclon.modelos;

public class ContactoChat {
    private int idContacto;
    private String nombreContacto;
    private String urlImagen;
    private String ultimoMensaje;
    private String horaMensaje;
    private int cantidadMsjNoLeidos;

    public ContactoChat() {
    }

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getUltimoMensaje() {
        return ultimoMensaje;
    }

    public void setUltimoMensaje(String ultimoMensaje) {
        this.ultimoMensaje = ultimoMensaje;
    }

    public String getHoraMensaje() {
        return horaMensaje;
    }

    public void setHoraMensaje(String horaMensaje) {
        this.horaMensaje = horaMensaje;
    }

    public int getCantidadMsjNoLeidos() {
        return cantidadMsjNoLeidos;
    }

    public void setCantidadMsjNoLeidos(int cantidadMsjNoLeidos) {
        this.cantidadMsjNoLeidos = cantidadMsjNoLeidos;
    }
}
