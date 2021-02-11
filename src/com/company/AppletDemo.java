package com.company;

import javax.swing.JApplet;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class AppletDemo extends JApplet implements ActionListener {
    private static final long serialVersionUID = 1L;

    int r1=1,r2=1,r3=1; //diametr up to 50 pixels
    int dx1=1,dx2=10,dx3=3; //speed
    JButton susp1,st1;

    class ThrDraw extends Thread {
        int r,dx,k;
        Graphics g;
        public ThrDraw(int diam, int ddx, Graphics gg, int kk){
            r=diam;
            dx=ddx;
            g=gg;
            k=kk;
        }
        public void paintg(){
            g.setColor(Color.white);
            g.fillOval(getWidth()/4*k-r/2,getHeight()/2-r/2,r,r);
            r+=dx;
            if ((r>=100) || (r<=1))
                dx*=-1;
            g.setColor(Color.green);
            g.fillOval(getWidth()/4*k-r/2,getHeight()/2-r/2,r,r);
        }
        public void run(){
            while (true){
                paintg();
                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e) {
                }
            }
        }
    }
    ThrDraw MyThr1;

    public AppletDemo() throws HeadlessException {
    }

    public void start(){
    }

    public void init(){
        getGraphics().setColor(Color.white);
        setSize(new Dimension(600,400));
        susp1 = new JButton ("Остановить");
        st1 = new JButton ("Запустить");
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        c.add(susp1);
        c.add(st1);
        susp1.addActionListener(this);
        st1.addActionListener(this);
        c.setBackground(Color.white);
        c.setForeground(Color.green);
        MyThr1=new ThrDraw(r1,dx1,getGraphics(),1);
        MyThr1.start();
    }


    public void paint(Graphics g){
        super.paint(g);
        st1.updateUI();
        susp1.updateUI();
    }

    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals("Остановить"))
            MyThr1.suspend();
        if (str.equals("Запустить"))
            MyThr1.resume();
    }

}