import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.ServerSocket;

public class ServerFrame extends JFrame {
    private JLabel ipLabel =new JLabel("                    IP:");
    private JLabel configLabel =new JLabel();
    private JLabel portLabel =new JLabel("                 port:");
    private JLabel portField =new JLabel("5300");
    private JButton setting =new JButton("設定");
    private JButton start =new JButton("start");
    private JPanel jpl1 =new JPanel(new GridLayout(1,6,0,0));
    private JTextArea area =new JTextArea();
    private JPanel jpl2 =new JPanel(new GridLayout(1,2,2,2));
    private JTextField chat =new JTextField();
    private JButton send =new JButton("send");
    private Container cp;
    private ServerNet sn;

    public ServerFrame(){
        init();
    }
    private void init(){
        this.setTitle("Server");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBounds(10,10,500,500);

        sn=new ServerNet(ServerFrame.this);
        configLabel.setText(sn.getHostIp());

        cp=this.getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(jpl1,BorderLayout.NORTH);
        jpl1.add(ipLabel);
        jpl1.add(configLabel);
        jpl1.add(portLabel);
        jpl1.add(portField);
        jpl1.add(setting);
        jpl1.add(start);

        cp.add(area,BorderLayout.CENTER);
        area.setBackground(new Color(0, 212, 255));
        area.setEditable(false);

        cp.add(jpl2,BorderLayout.SOUTH);

        jpl2.add(chat);
        jpl2.add(send);

        start.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sn.start();
            }
        });
        send.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sn.sendMsg(chat.getText());
            }
        });
    }

    public void appendMessage(String str){
        area.append(str);
    }


}


