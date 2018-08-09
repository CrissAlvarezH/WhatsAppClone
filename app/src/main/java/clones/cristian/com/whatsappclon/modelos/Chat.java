package clones.cristian.com.whatsappclon.modelos;

public class Chat {
    private int idContacto;
    private String nombreContacto;
    private String urlImagen;
    private String ultimoMensaje;
    private String horaMensaje;
    private int cantidadMsjNoLeidos;

    public Chat(int idContacto, String nombreContacto, String urlImagen, String ultimoMensaje, String horaMensaje, int cantidadMsjNoLeidos) {
        this.idContacto = idContacto;
        this.nombreContacto = nombreContacto;
        this.urlImagen = urlImagen;
        this.ultimoMensaje = ultimoMensaje;
        this.horaMensaje = horaMensaje;
        this.cantidadMsjNoLeidos = cantidadMsjNoLeidos;
    }

    public Chat() {
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
