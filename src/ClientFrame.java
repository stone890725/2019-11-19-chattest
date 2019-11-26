
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ClientFrame extends JFrame {
    private JLabel ipLabel =new JLabel("                    IP:");
    private JTextField configField =new JTextField();
    private JLabel portLabel =new JLabel("                 port:");
    private JTextField portField =new JTextField();
    private JButton setting =new JButton("設定");
    private JButton start =new JButton("start");
    private JPanel jpl1 =new JPanel(new GridLayout(1,6,0,0));
    private JTextArea area =new JTextArea();
    private JPanel jpl2 =new JPanel(new GridLayout(1,2,2,2));
    private JTextField chat =new JTextField();
    private JButton send =new JButton("send");
    private Container cp;
    private ClientNet cn;


    public ClientFrame(){
        init();
    }
    private void init(){
        this.setTitle("Client");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBounds(10,10,500,500);

        cn=new ClientNet(ClientFrame.this);

        cp=this.getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(jpl1,BorderLayout.NORTH);
        jpl1.add(ipLabel);
        jpl1.add(configField);
        jpl1.add(portLabel);
        jpl1.add(portField);
        jpl1.add(setting);
        jpl1.add(start);
        area.setBackground(new Color(189, 255, 198));
        area.setEditable(false);

        cp.add(area,BorderLayout.CENTER);

        cp.add(jpl2,BorderLayout.SOUTH);

        jpl2.add(chat);
        jpl2.add(send);

        start.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cn.start();
            }
        });
    }
    public int getport(){
        return Integer.parseInt(portField.getText()) ;
    }
    public String getip(){
        return configField.getText();
    }
    public void appendMessage(String str){
        area.append(str);
    }

}
