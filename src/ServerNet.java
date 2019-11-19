import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class ServerNet extends Thread {
    private String hostIp,hostName;
    private InetAddress inetAddr;
    private int port =5300;
    private ServerFrame server;
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;


    public ServerNet(ServerFrame fr){
        server=fr;
        this.setDaemon(true);
        try {
            inetAddr=InetAddress.getLocalHost();
            hostIp=inetAddr.getHostAddress();
        }
        catch (UnknownHostException ioe){
            System.out.println("can't find host");
        }
    }
    public String getHostIp(){
        return hostIp;
    }
}
