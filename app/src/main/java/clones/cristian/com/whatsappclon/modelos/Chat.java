package clones.cristian.com.whatsappclon.modelos;

public class Chat {
    private Contacto contacto;
    private Mensaje ultimoMensaje;
    private int cantidadMsjNoLeidos;

    public Chat(Contacto contacto, Mensaje ultimoMensaje, int cantidadMsjNoLeidos) {
        this.contacto = contacto;
        this.ultimoMensaje = ultimoMensaje;
        this.cantidadMsjNoLeidos = cantidadMsjNoLeidos;
    }

    public Chat() {
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public Mensaje getUltimoMensaje() {
        return ultimoMensaje;
    }

    public void setUltimoMensaje(Mensaje ultimoMensaje) {
        this.ultimoMensaje = ultimoMensaje;
    }

    public int getCantidadMsjNoLeidos() {
        return cantidadMsjNoLeidos;
    }

    public void setCantidadMsjNoLeidos(int cantidadMsjNoLeidos) {
        this.cantidadMsjNoLeidos = cantidadMsjNoLeidos;
    }
}
