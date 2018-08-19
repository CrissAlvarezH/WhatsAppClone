package clones.cristian.com.whatsappclon.basedatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import clones.cristian.com.whatsappclon.modelos.BaseModelo;
import clones.cristian.com.whatsappclon.modelos.Chat;
import clones.cristian.com.whatsappclon.modelos.Contacto;
import clones.cristian.com.whatsappclon.modelos.Mensaje;

import static clones.cristian.com.whatsappclon.basedatos.DBHelper.CUERPO;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.ESTADO;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.FECHA;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.HORA;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.ID_EMISOR;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.ID;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.ID_ULTIMO_MENSAJE;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.NOMBRE;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.TABLA_CHATS;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.TABLA_CONTACTOS;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.TABLA_MENSAJES;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.TIPO;
import static clones.cristian.com.whatsappclon.basedatos.DBHelper.URL_IMG;

public class DBControlador {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DBControlador(Context context){
        this.dbHelper = new DBHelper(context);
    }

    public long insertarModelo(BaseModelo modelo){
        db = dbHelper.getWritableDatabase();

        return db.insert(modelo.getNombreTabla(), null, modelo.toContentValues());
    }

    public long actualizarModelo(BaseModelo modelo, String where, String[] args){
        db = dbHelper.getWritableDatabase();

        return db.update(modelo.getNombreTabla(), modelo.toContentValues(), where, args);
    }

    public ArrayList<Contacto> getContactos(String where, String[] args){
        db = dbHelper.getReadableDatabase();

        ArrayList<Contacto> contactos = new ArrayList<>();

        Cursor cur = db.query(TABLA_CONTACTOS, null, where, args, null, null, null);

        if( cur.moveToFirst() ){

            Contacto contacto = new Contacto(
                cur.getInt( cur.getColumnIndex(ID) ),
                cur.getString( cur.getColumnIndex(NOMBRE) ),
                cur.getString( cur.getColumnIndex(ESTADO) ),
                cur.getString( cur.getColumnIndex(URL_IMG) )
            );

            contactos.add(contacto);
        }

        cur.close();

        return contactos;
    }

    public ArrayList<Mensaje> getMensajes(String where, String[] args){
        db = dbHelper.getReadableDatabase();

        ArrayList<Mensaje> mensajes = new ArrayList<>();

        Cursor cur = db.query(TABLA_MENSAJES, null, where, args, null, null, null);

        if( cur.moveToFirst() ){
            Mensaje mensaje = new Mensaje(
                    cur.getInt( cur.getColumnIndex(ID) ),
                    cur.getInt( cur.getColumnIndex(TIPO) ),
                    cur.getString( cur.getColumnIndex(CUERPO) ),
                    cur.getString( cur.getColumnIndex(FECHA) ),
                    cur.getString( cur.getColumnIndex(HORA) ),
                    cur.getString( cur.getColumnIndex(ESTADO) ),
                    cur.getInt( cur.getColumnIndex(ID_EMISOR) )
            );

            mensajes.add(mensaje);
        }

        cur.close();

        return mensajes;
    }

    public ArrayList<Chat> getChats(){
        db = dbHelper.getReadableDatabase();

        ArrayList<Chat> chats = new ArrayList<>();

        Cursor cur = db.rawQuery(
                "SELECT " +TABLA_CONTACTOS+"."+ID+" AS id_contacto,"+TABLA_MENSAJES+"."+ID+" AS id_msj, * " +
                        " FROM "+TABLA_CHATS+", "+TABLA_CONTACTOS+", "+TABLA_MENSAJES +
                        " WHERE "+TABLA_CHATS+"."+ID_EMISOR+" = "+TABLA_CONTACTOS+"."+ID
                            +" AND "+TABLA_CHATS+"."+ID_ULTIMO_MENSAJE+" = "+TABLA_MENSAJES+"."+ID,
                null
        );

        if( cur.moveToFirst() ){

            Cursor curCantMjs = db.rawQuery(
                    "SELECT COUNT(*) FROM "+TABLA_MENSAJES+
                            " WHERE " + ID_EMISOR+ " = ?" +
                            " AND "+ESTADO+" = ?",
                    new String[]{cur.getInt( cur.getColumnIndex("id_contacto") )+"",
                            Mensaje.Estados.SIN_ENVIAR}
            );

            int cantMsjNoLeidos = curCantMjs.getInt(0);

            Contacto contacto = new Contacto(
                    cur.getInt( cur.getColumnIndex("id_contacto") ),
                    cur.getString( cur.getColumnIndex(NOMBRE) ),
                    cur.getString( cur.getColumnIndex(ESTADO) ),
                    cur.getString( cur.getColumnIndex(URL_IMG) )
            );

            Mensaje ultMensaje = new Mensaje(
                    cur.getInt( cur.getColumnIndex("id_msj") ),
                    cur.getInt( cur.getColumnIndex(TIPO) ),
                    cur.getString( cur.getColumnIndex(CUERPO) ),
                    cur.getString( cur.getColumnIndex(FECHA) ),
                    cur.getString( cur.getColumnIndex(HORA) ),
                    cur.getString( cur.getColumnIndex(ESTADO) ),
                    cur.getInt( cur.getColumnIndex(ID_EMISOR) )
            );

            Chat chat = new Chat(
                    contacto,
                    ultMensaje,
                    cantMsjNoLeidos
            );

            chats.add( chat );

            curCantMjs.close();
        }

        cur.close();

        return chats;
    }
}
