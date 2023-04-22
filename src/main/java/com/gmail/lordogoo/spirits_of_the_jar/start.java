package com.gmail.lordogoo.spirits_of_the_jar;

public class start
{

    public static void main(String[] args){
        start();
    }
    
    public static void start()
    {
       System.out.println("some kind of message");
       //exicute if applet
       //AppletWindow t = new AppletWindow("app",800,600,new mainapp());
       
       
       //exicute if application
       mainapp t = new mainapp(800,600);
       t.setVisible(true);
       
       //t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       t.init();
       t.repaint();
    }
}
