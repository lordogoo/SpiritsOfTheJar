package com.gmail.lordogoo.spirits_of_the_jar;
import javax.swing.*;
import javax.swing.event.*;
import info.clearthought.layout.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.plaf.*;
import com.gmail.lordogoo.spirits_of_the_jar.controlers.*;
 
public class mainapp extends JFrame
{

    
    int xresolution;
    int yresolution;
    menucontrol cont;
    texturelibrary lib;
    soundlibrary sound;
    JFrame thisref;
    
    public mainapp(int x,int y)
    {
        thisref = this;
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.setUndecorated(true);
        this.addComponentListener(new resizelistener());
        this.setPreferredSize(new Dimension(x,y));
        this.pack();
        xresolution = x - (this.getInsets().left + this.getInsets().right);
        yresolution = y - (this.getInsets().bottom + this.getInsets().top);
    }
    
    public void init(){
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    System.out.println("attempting createGUI");
                    createGUI();
                    System.out.println("createGUI successful");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("createGUI didn't complete successfully");
        }
    }
    
    public void createGUI(){
        
        this.addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosing(WindowEvent e) {
                  destroy();
               }
            }
        );
        
        //JLayeredPane lp = this.getLayeredPane();
        JPanel mainpanel = new JPanel();
        System.out.println("system size"+this.getSize());
        
        
        
        mainpanel.setPreferredSize(this.getSize());
        lib = new texturelibrary();
        sound = new soundlibrary();
        cont = new menucontrol(this,mainpanel,lib,sound,xresolution,yresolution);
        setVisible(true);
        this.add(mainpanel);
    }
    
    public void destroy(){
        cont.setrunning(false);
        System.out.println("this applet has been terminated");
        System.exit(0);
    }
    
    
    private class resizelistener implements ComponentListener{  
        public void componentHidden(ComponentEvent e) {}
        public void componentMoved(ComponentEvent e){}
        public void componentResized(ComponentEvent e) {
            Component c = (Component)e.getSource();

            Dimension newSize = c.getSize();
            if(cont != null){
                int xwidth = (int)newSize.getWidth()  - (thisref.getInsets().left + thisref.getInsets().right);
                int yheight = (int)newSize.getHeight() - (thisref.getInsets().bottom + thisref.getInsets().top);
                cont.resize(xwidth,yheight);
            }
        }
        public void componentShown(ComponentEvent e){}
    }

}
