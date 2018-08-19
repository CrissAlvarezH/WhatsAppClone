package clones.cristian.com.whatsappclon;

import android.app.Application;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class ChatAplicacion extends Application {

    private Socket socket;

    {
        try {
            socket = IO.socket("http://10.10.10.104:3000");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket(){
        return socket;
    }
}
