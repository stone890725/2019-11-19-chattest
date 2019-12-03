import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.ServerSocket;

public class ServerFrame extends JFrame implements KeyListener {
    private JLabel ipLabel =new JLabel("IP:");
    private JLabel configLabel =new JLabel();
    private JLabel portLabel =new JLabel("port:");
    private JLabel portField =new JLabel("5300");
    private JButton setting =new JButton("設定");
    private JButton start =new JButton("start");
    private JPanel jpl1 =new JPanel(new GridLayout(1,6,0,0));
    private JTextArea area =new JTextArea();
    private JScrollPane jsc =new JScrollPane(area);
    private JPanel jpl2 =new JPanel(new GridLayout(1,2,2,2));
    private JTextField chat =new JTextField();
    private JButton send =new JButton("send");
    private JPanel jplCenter =new JPanel(new BorderLayout());
    private JPanel jplGame =new JPanel();
    private Container cp;
    private ServerNet sn;

    private JLabel char1=new JLabel();
    private JLabel char2=new JLabel();
    private ImageIcon img =new ImageIcon("huu.png");
    private ImageIcon img2 =new ImageIcon("spider.png");

    public ServerFrame(){
        init();
    }
    public void init(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        this.setTitle("Server");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(10,10,700,600);
        cp=this.getContentPane();
        cp.setLayout(new BorderLayout());

        ipLabel.setHorizontalAlignment(JLabel.RIGHT);
        portLabel.setHorizontalAlignment(JLabel.RIGHT);

        sn=new ServerNet(ServerFrame.this);
        configLabel.setText(sn.getHostIp());

        cp.add(jpl1,BorderLayout.NORTH);
        jpl1.add(ipLabel);
        jpl1.add(configLabel);
        jpl1.add(portLabel);
        jpl1.add(portField);
        jpl1.add(setting);
        jpl1.add(start);

        cp.add(jsc,BorderLayout.CENTER);
        area.setBackground(new Color(0, 212, 255));
        area.setEditable(false);

        cp.add(jpl2,BorderLayout.SOUTH);

        cp.add(jplCenter,BorderLayout.CENTER);
        jplGame.setBackground(new Color(0,255,127));
        jplCenter.add(jplGame,BorderLayout.CENTER);
        jplCenter.add(jsc,BorderLayout.EAST);
        jsc.setPreferredSize(new Dimension(150,500));

        jpl2.add(chat);
        jpl2.add(send);

        Image reImg=img.getImage();
        Image reImg2=reImg.getScaledInstance(80,100,Image.SCALE_SMOOTH);
        img.setImage(reImg2);
        char1.setIcon(img);
        char1.setBounds(50,60,80,100);

        reImg=img2.getImage();
        reImg2=reImg.getScaledInstance(180,200,Image.SCALE_SMOOTH);
        img2.setImage(reImg2);

        char2.setIcon(img2);
        char2.setBounds(180,60,200,100);

        jplGame.add(char1);
        jplGame.add(char2);

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
                ServerFrame.this.appendMessage("server:"+chat.getText());
                chat.setText("");
            }
        });
    }


    public void appendMessage(String str){
        if (str.contains("#R1")){
            String data[] =str.split(",");
            int r1x =Integer.parseInt(data[1]);
            int r1y =Integer.parseInt(data[2]);
            char1.setLocation(r1x,r1y);
        }
        else {
        area.append(str+"\n");
        }
    }
    public void keyPressed(KeyEvent ke){
        System.out.println(ke.getKeyCode());
        switch (ke.getKeyCode()){
            case 39://右
                char2.setLocation(char2.getX()+5,char2.getY());
                break;
            case 38://上
                char2.setLocation(char2.getX(),char2.getY()-5);
                break;
            case 37://左
                char2.setLocation(char2.getX()-5,char2.getY());
                break;
            case 40://下
                char2.setLocation(char2.getX(),char2.getY()+5);
                break;
        }
        if (ke.getKeyCode()>=37 && ke.getKeyCode()<=40 &&sn!=null){
            sn.sendMsg("#R2,"+Integer.toString(char2.getX())+","+Integer.toString(char2.getY()));
        }

    }
    public void keyTyped(KeyEvent ke) {

    }
    public void keyReleased(KeyEvent e) {

    }


}


