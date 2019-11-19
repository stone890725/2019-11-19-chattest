import javax.swing.*;
import java.awt.*;

public class ClientFrame extends JFrame {
    private JLabel ipLabel =new JLabel("                     IP:");

    private JTextField configLabel =new JTextField();
    private JLabel portLabel =new JLabel("                   port:");
    private JTextField portField =new JTextField();
    private JButton setting =new JButton("設定");
    private JButton start =new JButton("start");
    private JPanel jpl1 =new JPanel(new GridLayout(1,6,0,0));
    private JTextArea area =new JTextArea();
    private JPanel jpl2 =new JPanel(new GridLayout(1,2,2,2));
    private JTextField chat =new JTextField();
    private JButton send =new JButton("send");
    private Container cp;


    public ClientFrame(){
        init();
    }
    private void init(){
        this.setTitle("Client");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBounds(10,10,500,500);

        cp=this.getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(jpl1,BorderLayout.NORTH);
        jpl1.add(ipLabel);
        jpl1.add(configLabel);
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
    }
}
