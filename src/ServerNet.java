import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class ServerNet extends Thread {
    private String hostIp,hostName;
    private InetAddress inetAddr;
    private int port =5300;
    private ServerFrame server;
    private Socket  socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private ServerSocket serverSocket;


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
    public void run(){
        byte data[] =new byte[1024];
        int n =0;
        try {
             serverSocket =new ServerSocket(port);
             server.appendMessage("Server start,client connect");
             server.repaint();
             socket=serverSocket.accept();
             server.appendMessage("Cline accept");
             outputStream=socket.getOutputStream();
             while (true){
                 inputStream=socket.getInputStream();
                 n=inputStream.read(data);
                 if ((new String(data,0,n)).equals("#disconnect#")){
                     server.appendMessage("client disconnected");
                     close();
                     break;
                 }
                 else {
                     server.appendMessage("Cline:"+new String(data,0,n));
                 }
             }
        }
        catch (UnknownHostException e){
            System.out.println(e.toString());
        }
        catch (IOException ex){
            System.out.println(ex.toString());
        }
    }

    public void sendMsg(String msg){
        if (socket!=null){
            try {
                outputStream.write(msg.getBytes());
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(server,e.toString(),"error",1);
            }
        }
    }

    public String getHostIp(){
        hostIp=inetAddr.getHostAddress();
        return hostIp;
    }
    public void close(){
       try {
           inputStream.close();
           outputStream.close();
           socket.close();
       }
       catch (Exception e){
           System.out.println(e.toString());
       }
    }


}
