import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientNet extends Thread {
    private String hostIp,hostName;
    private InetAddress inetAddr;
    private int port =5300;
    private ClientFrame cf;
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;

    public ClientNet(ClientFrame clientFrame){
        cf=clientFrame;
        this.setDaemon(true);
    }
    public void run(){
        byte data[] =new byte[1024];
        int n =0;
        try {
            cf.appendMessage("Server start,client connect");
            cf.repaint();
            socket=new Socket(cf.getip(),cf.getport());
            cf.appendMessage("Server accept");
            outputStream=socket.getOutputStream();
            while (true){
                inputStream=socket.getInputStream();
                n=inputStream.read(data);
                if ((new String(data,0,n)).equals("#disconnect#")){
                    cf.appendMessage("client disconnected");
                    close();
                    break;
                }
                else {
                    cf.appendMessage("Server:"+new String(data,0,n));
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
    public void sendMsg(String msg){
        if (socket!=null){
            try {
                outputStream.write(msg.getBytes());
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(cf,e.toString(),"error",1);
            }
        }
    }
    }

