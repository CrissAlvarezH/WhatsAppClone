package clones.cristian.com.whatsappclon.modelos;

import android.content.ContentValues;

import static clones.cristian.com.whatsappclon.basedatos.DBHelper.CUERPO;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.ESTADO;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.FECHA;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.HORA;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.ID_EMISOR;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.TABLA_MENSAJES;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.TIPO;

public class Mensaje implements BaseModelo {
    private int id;
    private int tipo;
    private String cuerpo;
    private String hora;
    private String fecha;
    private String estado;
    private int idEmisor;

    public Mensaje(int id, int tipo, String cuerpo, String fecha, String hora, String estado, int idEmisor) {
        this.id = id;
        this.tipo = tipo;
        this.cuerpo = cuerpo;
        this.hora = hora;
        this.fecha = fecha;
        this.estado = estado;
        this.idEmisor = idEmisor;
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(CUERPO, cuerpo);
        values.put(FECHA, fecha);
        values.put(HORA, hora);
        values.put(ESTADO, estado);
        values.put(TIPO, tipo);
        values.put(ID_EMISOR, idEmisor);

        return values;
    }

    @Override
    public String getNombreTabla() {
        return TABLA_MENSAJES;
    }

    public static class Tipos {
        public static final int ENVIADO = 1;
        public static final int RECIBIDO = 2;
    }

    public static class Estados {
        public static final String ENVIADO = "Enviado";
        public static final String SIN_ENVIAR = "Sin enviar";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(int idEmisor) {
        this.idEmisor = idEmisor;
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
