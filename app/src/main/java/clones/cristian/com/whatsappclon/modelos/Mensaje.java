package clones.cristian.com.whatsappclon.modelos;

public class Mensaje {
    private int tipo;
    private String cuerpo;
    private String hora;

    public Mensaje(int tipo, String cuerpo, String hora) {
        this.tipo = tipo;
        this.cuerpo = cuerpo;
        this.hora = hora;
    }

    public static class Tipos {
        public static final int ENVIADO = 1;
        public static final int RECIBIDO = 2;
        public static final int INFO = 3;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
