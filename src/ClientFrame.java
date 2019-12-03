
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClientFrame extends JFrame implements KeyListener {
    private JLabel ipLabel =new JLabel("IP:");
    private JTextField configField =new JTextField();
    private JLabel portLabel =new JLabel("port:");
    private JTextField portField =new JTextField();
    private JButton setting =new JButton("設定");
    private JButton start =new JButton("start");

    private JPanel jpl1 =new JPanel(new GridLayout(1,6,0,0));
    private JTextArea area =new JTextArea();
    private JScrollPane jsc =new JScrollPane(area);

    private JPanel jplCenter =new JPanel(new BorderLayout());
    private JPanel jplGame =new JPanel();

    private JPanel jpl2 =new JPanel(new GridLayout(1,2,2,2));
    private JTextField chat =new JTextField();
    private JButton send =new JButton("send");

    private Container cp;
    private ClientNet cn;

    private JLabel char1=new JLabel();
    private JLabel char2=new JLabel();
    private ImageIcon img =new ImageIcon("huu.png");
    private ImageIcon img2 =new ImageIcon("spider.png");


    public ClientFrame(){
        init();
    }
    private void init(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        this.setTitle("Client");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(10,610,700,600);

        ipLabel.setHorizontalAlignment(JLabel.RIGHT);
        portLabel.setHorizontalAlignment(JLabel.RIGHT);

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

        cp.add(jplCenter,BorderLayout.CENTER);
        jplGame.setBackground(new Color(100,210,30));
        jplCenter.add(jplGame,BorderLayout.CENTER);

        jsc.setPreferredSize(new Dimension(150,500));
        jplCenter.add(jsc,BorderLayout.EAST);


        Image reImg=img.getImage();
        Image reImg2=reImg.getScaledInstance(80,100,Image.SCALE_SMOOTH);
        img.setImage(reImg2);

        char1.setIcon(img);
        char1.setBounds(50,60,80,100);

        reImg=img2.getImage();
        reImg2=reImg.getScaledInstance(180,200,Image.SCALE_SMOOTH);
        img2.setImage(reImg2);

        char2.setIcon(img2);
        char2.setBounds(180,60,180,200);

        jplGame.add(char1);
        jplGame.add(char2);

        cp.add(jpl2,BorderLayout.SOUTH);
        jpl2.add(chat);
        jpl2.add(send);

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cn.sendMsg(chat.getText());
                ClientFrame.this.appendMessage("client:"+chat.getText());
                chat.setText("");
            }
        });

        start.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cn.start();
            }
        });
    }

    public void keyPressed(KeyEvent ke){
        System.out.println(ke.getKeyCode());
        switch (ke.getKeyCode()){
            case 39://右
                char1.setLocation(char1.getX()+5,char1.getY());
                break;
            case 38://上
                char1.setLocation(char1.getX(),char1.getY()-5);
                break;
            case 37://左
                char1.setLocation(char1.getX()-5,char1.getY());
                break;
            case 40://下
                char1.setLocation(char1.getX(),char1.getY()+5);
                break;
        } if (ke.getKeyCode()>=37 && ke.getKeyCode()<=40 &&cn!=null){
            cn.sendMsg("#R1,"+Integer.toString(char1.getX())+","+Integer.toString(char1.getY()));
        }

    }
    public void keyTyped(KeyEvent ke) {

    }
    public void keyReleased(KeyEvent e) {

    }

    public int getport(){

        return Integer.parseInt(portField.getText());

    }
    public String getip(){

        return configField.getText();

    }
    public void appendMessage(String str){
        if (str.contains("#R2")){
            String data[] =str.split(",");
            int r1x =Integer.parseInt(data[1]);
            int r1y =Integer.parseInt(data[2]);
            char2.setLocation(r1x,r1y);
        }
        else {
            area.append(str+"\n");
        }
    }



}
