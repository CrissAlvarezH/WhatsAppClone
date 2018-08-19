package clones.cristian.com.whatsappclon.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String NOMBRE_DB = "chat.db";
    private static final int VERSION_DB = 1;

    public DBHelper(Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB);
    }

    public static final String TABLA_DATOS_LOGIN = "datos_login";
    public static final String TABLA_CONTACTOS = "contactos";
    public static final String TABLA_MENSAJES = "mensajes";
    public static final String TABLA_CHATS = "chats";

    public static final String ID = "id";
    public static final String ID_EMISOR = "id_emisor";
    public static final String ID_ULTIMO_MENSAJE = "id_ultimo_mensaje";
    public static final String NOMBRE = "nombre";
    public static final String ESTADO = "estado";
    public static final String CUERPO = "cuerpo";
    public static final String FECHA = "fecha";
    public static final String HORA = "hora";
    public static final String TIPO = "tipo";
    public static final String URL_IMG = "url_img";
    public static final String CANTIDAD_MSJ_NO_LEIDOS = "cant_msj_no_leidos";


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREAR_TABLA_DATOS_LOGIN = "CREATE TABLE "+ TABLA_DATOS_LOGIN + " (" +
                ID + " INTEGER, " +
                NOMBRE + " TEXT, " +
                ESTADO + " TEXT, " +
                URL_IMG + " TEXT " +
                "); ";

        String CREAR_TABLA_CONTACTOS = "CREATE TABLE "+ TABLA_CONTACTOS + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                NOMBRE + " TEXT, " +
                ESTADO + " TEXT, " +
                URL_IMG + " TEXT " +
                "); ";

        String CREAR_TABLA_MENSAJES = "CREATE TABLE "+ TABLA_MENSAJES + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CUERPO + " TEXT, " +
                FECHA + " TEXT, " +
                HORA + " TEXT, " +
                ESTADO + " TEXT, " +
                TIPO + " INTEGER, " +
                ID_EMISOR + " INTEGER, " +
                "FOREIGN KEY ("+ ID_EMISOR +") REFERENCES "+ TABLA_CONTACTOS +"("+ ID +") " +
                "); ";

        String CREAR_TABLA_CHAT = "CREATE TABLE "+ TABLA_CHATS + " (" +
                ID_EMISOR + " INTEGER, " +
                ID_ULTIMO_MENSAJE + " INTEGER, " +
                CANTIDAD_MSJ_NO_LEIDOS + " INTEGER, " +
                "FOREIGN KEY ("+ ID_EMISOR +") REFERENCES "+ TABLA_CONTACTOS +"("+ ID +"), " +
                "FOREIGN KEY ("+ ID_ULTIMO_MENSAJE +") REFERENCES "+ TABLA_MENSAJES +"("+ ID +") " +
                "); ";


        db.execSQL(CREAR_TABLA_DATOS_LOGIN);
        db.execSQL(CREAR_TABLA_CONTACTOS);
        db.execSQL(CREAR_TABLA_MENSAJES);
        db.execSQL(CREAR_TABLA_CHAT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
