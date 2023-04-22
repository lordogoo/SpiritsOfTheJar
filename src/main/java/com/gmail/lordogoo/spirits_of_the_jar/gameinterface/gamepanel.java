package com.gmail.lordogoo.spirits_of_the_jar.gameinterface;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import info.clearthought.layout.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.font.*;
import java.applet.*;
import java.io.*;
import java.util.*;
import org.pushingpixels.trident.*;
 import com.gmail.lordogoo.spirits_of_the_jar.*;
import com.gmail.lordogoo.spirits_of_the_jar.controlers.*;
import com.gmail.lordogoo.spirits_of_the_jar.cardgame.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.battletriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.combattriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.dialogtriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.generictriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.npctriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.screentransitiontriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.shoptriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.playergame.*;
import com.gmail.lordogoo.spirits_of_the_jar.gameanimation.*;
import com.gmail.lordogoo.spirits_of_the_jar.badguygame.*;
import com.gmail.lordogoo.spirits_of_the_jar.bodygame.*;
import com.gmail.lordogoo.spirits_of_the_jar.gamefaction.*;
import com.gmail.lordogoo.spirits_of_the_jar.events.*;
import com.gmail.lordogoo.spirits_of_the_jar.gameobject.*;
import com.gmail.lordogoo.spirits_of_the_jar.victorygame.*;
import com.gmail.lordogoo.spirits_of_the_jar.destinationrulegame.*;
import com.gmail.lordogoo.spirits_of_the_jar.terraintrigger.*;
import com.gmail.lordogoo.spirits_of_the_jar.gamecardai.*;
import com.gmail.lordogoo.spirits_of_the_jar.activationrulegame.*;
import com.gmail.lordogoo.spirits_of_the_jar.interfacecomponents.*;
import com.gmail.lordogoo.spirits_of_the_jar.gamepopup.*;
import com.gmail.lordogoo.spirits_of_the_jar.ruleconditionalgame.*;
import com.gmail.lordogoo.spirits_of_the_jar.gamenpcai.*;
import com.gmail.lordogoo.spirits_of_the_jar.gameinterface.*;
public abstract class gamepanel extends JPanel
{


    menucontrol cont;
    gamepanel thisref;
    texturelibrary textureref;
    soundlibrary soundref;
    
    //info
    String name; 
    imagewithindex background;   
    int backgroundindex;
    
    //
    containerplate frontplate;
    Vector<JComponent> componentlist;
    Vector<specialpopup> popuplist;
    Vector<specialcomponentcontainer> containerlist;
    int xdim;
    int ydim;
    specialboarderpanel panel;
    specialboarderpanel prevpanel;
    
    
    //animation stuff
    volatile trigger currenttrigger;
    volatile trigger suspendedtrigger;
    volatile animation anim;
    volatile Vector<trigger> nexttriggerlist;
    volatile animationdisplay animationsurface;
    volatile java.util.Timer triggerthread;
    volatile runtriggers triggertask;
    Vector<animationscreenpanel> animationcomponents;
    boolean tutorialrunning;
    
    
    //popup stuff
    popupbox popup;
    
    
    public gamepanel(String n,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        thisref = this;
        name = n;
        cont = c;
        textureref = i;
        soundref = s;
        background = textureref.gettexture(bi);
        backgroundindex = bi;
        
        nexttriggerlist = new Vector<trigger>();
        componentlist = new Vector<JComponent>();
        popuplist = new Vector<specialpopup>();
        containerlist = new Vector<specialcomponentcontainer>();
        animationcomponents = new Vector<animationscreenpanel>();
        
        triggerthread = new java.util.Timer("animator");
    }
    
    public void switchto(menucontrol menu){
        //this.addtrigger(new triggerscreentransitionmid(prevpanel,panel,textureref.gettexture(4)));
        this.addtrigger(new triggerscreentransitionend());
        if(menu.getcurrentprofile() != null){
            if(menu.getcurrentprofile().getgame()!=null){
                tutorialtrigger tutorialtemp = menu.getcurrentprofile().getgame().gettutorial().gettrigger(this);
                if(tutorialtemp != null){
                    tutorialrunning = true;
                    trigger temp = tutorialtemp.gettrigger();
                    this.addtrigger(temp);
                    this.disableall();
                }else{
                    tutorialrunning = false;
                }
            }
        }
        this.runtriggers(this); 
        
        cont.resize(cont.getxresolution(),cont.getyresolution());
    }
    
    public void switchaway(menucontrol menu){
    }
    
    public boolean istutorialrunning(){
        return tutorialrunning;
    }
    
    public void setcontainerplate(containerplate cp){
        frontplate = cp;
    }
    
    public containerplate getcontainerplate(){
        return frontplate;
    }
    
    public void enableall(){
        for(int i = 0; i < componentlist.size();i++){
            componentlist.elementAt(i).setEnabled(true);
            if(componentlist.elementAt(i) instanceof JScrollPane){
                ((JScrollPane)componentlist.elementAt(i)).getHorizontalScrollBar().setEnabled(true);
                ((JScrollPane)componentlist.elementAt(i)).setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                ((JScrollPane)componentlist.elementAt(i)).getVerticalScrollBar().setEnabled(true);
                ((JScrollPane)componentlist.elementAt(i)).setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                ((JScrollPane)componentlist.elementAt(i)).getViewport().getView().setEnabled(true);
            }
        }
        
        specialconditionsonenable();
    }
    
    public void specialconditionsonenable(){
    }
    
    
    public void disableall(){
        for(int i = 0; i < componentlist.size();i++){
            componentlist.elementAt(i).setEnabled(false);
            if(componentlist.elementAt(i) instanceof JScrollPane){
                ((JScrollPane)componentlist.elementAt(i)).getHorizontalScrollBar().setEnabled(false);
                ((JScrollPane)componentlist.elementAt(i)).setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                ((JScrollPane)componentlist.elementAt(i)).getVerticalScrollBar().setEnabled(false);
                ((JScrollPane)componentlist.elementAt(i)).setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                ((JScrollPane)componentlist.elementAt(i)).getViewport().getView().setEnabled(false);
            }
        }
        
        specialconditionsondiable();
    }
    
    public void specialconditionsondiable(){
    }
    
    public boolean isDoubleBuffered(){
        return true;
    }
    
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }
    
    public void visibleall(){
        for(int i = 0; i < componentlist.size();i++){
            componentlist.elementAt(i).setVisible(true);
            this.setComponentZOrder(componentlist.elementAt(i),0);
        }
    }
    
    public void invisibleall(){
        for(int i = 0; i < componentlist.size();i++){
            componentlist.elementAt(i).setVisible(false);
        }
    }
    
    public void panelinvisible(){
        if(panel != null){
            panel.setVisible(false);
        }
    }
    
    public void panelvisible(){
        if(panel != null){
            panel.setVisible(true);
        }
    }
    
    public menucontrol getcontrol(){
        return cont;
    }

    /*******************************
     * component list stuff
     */
    public int numcomponents(){
        return componentlist.size();
    }
    
    public JComponent getcomponent(int i){
        return componentlist.elementAt(i);
    }
    
    public specialpopup getpopup(int i){
        return popuplist.elementAt(i);
    }
    
    public void addpopup(specialpopup p){
        popuplist.add(p);
    }
    
    public int numcontainers(){
        return containerlist.size();
    }
    
    public specialcomponentcontainer getcomponentcontainer(int i){
        return containerlist.elementAt(i);
    }
    
    public Vector<JComponent> getcomponentlist(){
        return componentlist;
    }
    
    /*******************************
     *animation display
     *******************************/
     /*
    public void addanimationdisplay(int sx,int sy,int ex,int ey){
        System.out.println("add animation display");
        
        animationsurface = new animationdisplay();
        animationsurface.addanimation(anim);
        
        animationsurface.addMouseListener(new interuptclick());
        
        //this.add(animationsurface,sx+","+sy+","+ex+","+ey);
        //this.setComponentZOrder(animationsurface,0);
        //this.revalidate();
        
        animationsurface.setBounds(0,0,cont.getxresolution(),cont.getyresolution());
        frontplate.addanimationdisplay(animationsurface);
        animationsurface.setVisible(true);
    }
    */
    public void addanimationdisplay(){
        System.out.println("add animation display guessing corrordiants "+this.getname());
        
        animationsurface = new animationdisplay();
        animationsurface.addanimation(anim);
        animationsurface.addMouseListener(new interuptclick());
        //this.add(animationsurface,"0,0,"+xdim+","+ydim);
        //this.setComponentZOrder(animationsurface,0);
        //this.revalidate();
        animationsurface.setBounds(0,0,cont.getxresolution(),cont.getyresolution());
        frontplate.addanimationdisplay(animationsurface);
        animationsurface.setVisible(true);
    }
    
    public void removeanimationdisplay(){
        System.out.println("remove animation display guessing corrordiants "+this.getname());
        
        anim = null;
        if(animationsurface != null){
            animationsurface.addanimation(null);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    frontplate.removeanimationdisplay();
                }
            });
        }
        //this.remove(animationsurface);
        //this.revalidate();
        
        this.animationsurface = null;
    }
    
    public void addanimationcomponent(animationscreenpanel c){
        frontplate.addanimationcomponent(c);
        //this.add(c,"0,0,"+xdim+","+ydim);
        //this.revalidate();
        animationcomponents.add(c);
    }
    
    public void removeanimationcomponent(animationscreenpanel c){
        frontplate.removeanimationcomponent(c);
        //this.remove(c);
        //this.revalidate();
    }
    
    public Vector<animationscreenpanel> getanimationcomponentlist(){
        return  animationcomponents;
    }
    
    public int numanimationcomponent(){
        return animationcomponents.size();
    }
    
    //animation functions
    public void addanimation(animation a){
        anim = a;
    }
    
    public animation getanimation(){
        return anim;
    }
    
    public void updateinterface(){
        this.repaint();
        this.getanimationdisplay().repaint();
    }
        
    public JPanel getanimationdisplay(){
        return animationsurface;
    }
    
    public void clickinteruptanimation(MouseEvent e){
        System.out.println("animation interupted");
        if((suspendedtrigger != null)&&(suspendedtrigger.suspentioncondition(e))){
            System.out.println("animation interupted running triggers");
            trigger temp = suspendedtrigger;
            suspendedtrigger = null;
            nexttriggerlist.add(temp);
            this.runtriggers(this);
        } 
    }
    
       
    /***************
    * popup stuff   
    ****************/
    public void addpopupdisplay(JPanel front){
        disableall();
        popup = new popupbox(cont,this,textureref,front);
        popup.setBounds(0,0,cont.getxresolution(),cont.getyresolution());
        //animationsurface.addMouseListener(new interuptclick());
        
        //this.add(popup,"0,0,"+xdim+","+ydim);
        //this.setComponentZOrder(popup,0);
        //this.revalidate();
        frontplate.addpopupdisplay(popup);
        
        popup.setVisible(true);
        frontplate.repaintlayeredpane();
    }
    
    public void removepopupdisplay(){
        enableall();
        //this.remove(popup);
        //this.revalidate();
        frontplate.removepopupdisplay();
        this.popup = null;
        frontplate.repaintlayeredpane();
    }
    
    //trigger functions  
    public void addtrigger(trigger t){
        System.out.println("add trigger "+t.getClass().getName());
        nexttriggerlist.add(t);
        System.out.println("num triggers "+nexttriggerlist.size());
    }
    
    public boolean hasnexttrigger(){
        return nexttriggerlist.size()>0;
    }
    
    /*********************************/
    /*********************************/
    /*********************************
     * Switch animation part 2:run next trigger
     */
    //TODO
    //TODO
    //TODO
    //TODO
    public synchronized void runtriggers(gamepanel target){
        System.out.println("???????begin running triggers");
        triggertask = new runtriggers(this);
        if((nexttriggerlist.size()>0)&&(triggertask.isrunning()!=true)){
            triggerthread.schedule(triggertask, 0, 40);
        }
    }
    
    public synchronized void suspendtrigger(trigger t){
        suspendedtrigger = t;
    }
    
    //minsc functions
    public void setdimensions(int x,int y){
        xdim = x;
        ydim = y;
    }
    
    public menucontrol getmenu(){
        return cont;
    }
    /**********************************/
    /**********************************/
    /*********************************
     * Switch animation part 1:set up switch
     */
    public void setupswitchpanel(String nextpanel){
        gamepanel next = cont.getpanel(nextpanel);
        next.invisibleall();
        this.switchaway(cont);
        this.addtrigger(new triggerscreentransitionbegining(componentlist,nextpanel));
        this.runtriggers(this);
    }
    
    //helper functions
    public void drawoutlinetext(Graphics2D g2d,String font,String s,int x,int y,Color back,Color front){   
            Font f = new Font(font, Font.BOLD, 13);
            g2d.setFont(f);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(back);
            
            FontRenderContext frc = g2d.getFontRenderContext();
            TextLayout tl = new TextLayout(s, f, frc);
            float sw = (float) tl.getBounds().getWidth();
            AffineTransform transform = new AffineTransform();
            transform.setToTranslation(x,y);
            Shape shape = tl.getOutline(transform);
            Rectangle r = shape.getBounds();
            g2d.setColor(back);
            g2d.setStroke(new BasicStroke(3));
            g2d.draw(shape);
            g2d.setColor(front);
            g2d.drawString(s,x,y);
     }
    
    //set/get functions
    public specialboarderpanel getspecialpanel(){
        return panel;
    }
    
    public void setpervpanel(specialboarderpanel pp){
        prevpanel = pp;
    }
    
    public specialboarderpanel getprevpanel(){
        return prevpanel;
    }
    
    public String getname(){
        return name;
    }
    
    //paint function
    public void paintComponent(Graphics g) {
        int w = this.getWidth( );        
        int h = this.getHeight( );
        
        Graphics2D g2d = (Graphics2D)g;
        Polygon back = new Polygon();
        back.addPoint(0,0);
        back.addPoint(w,0);
        back.addPoint(w,h);
        back.addPoint(0,h);
        Rectangle2D rect = new Rectangle2D.Double(this.getLocation().getX(),this.getLocation().getY(),
              background.getimage().getWidth()+this.getLocation().getX(),background.getimage().getHeight()+this.getLocation().getY());
        g2d.setPaint(new  TexturePaint(background.getimage(),rect));
        g2d.fillPolygon(back);

        GradientPaint gp = new GradientPaint(0, 0,new Color(0,0,0,0),0, h,new Color(0,0,0,200));
        g2d.setPaint( gp );
        g2d.fillRect( 0, 0, w, h );
    }    
    
    public class interuptclick implements MouseListener{
           public void mousePressed(MouseEvent e) {
               System.out.println("click interupt detected");
               if(anim != null){
                   anim.interupt(e);
               }
           }

           public void mouseReleased(MouseEvent e) {
           }

           public void mouseEntered(MouseEvent e) {
           }

           public void mouseExited(MouseEvent e) {
           }

           public void mouseClicked(MouseEvent e) {

           }
    }
    
    /********************************
     * trigger thread
     */
    public class triggerthread extends Thread
    {
        volatile gamepanel panel;
        
        public triggerthread(gamepanel g)
        {
            panel = g;
        }
        
        public synchronized void run(){
            disableall();
            while(nexttriggerlist.size()>0){
                currenttrigger = nexttriggerlist.remove(0);
                currenttrigger.trigger(panel);
            }
            enableall();
        }
    }
    
    class runtriggers extends TimerTask {
        
        trigger trig;
        boolean running;
        boolean blocked;
        gamepanel gp;
        
        public runtriggers(gamepanel g){
            super();
            trig = null;
            gp = g;
            blocked = false;
        }
        
        public synchronized boolean isrunning(){
            while(blocked){
            }
            return running;
        }
        
        public void run() {
            
            try{
                if((trig == null)||(trig.isactive()==false )){
                    System.out.println("???????num triggers "+gp.nexttriggerlist.size()+" "+gp.getClass().getName());
                    blocked = true;
                    if(gp.nexttriggerlist.size()==0){
                        running = false;
                        this.cancel();
                        System.out.println("???????End running triggers");
                    }else{
                        trig = gp.nexttriggerlist.remove(0);
                    }
                    blocked = false;
                }else{
                    //System.out.println("???????running "+trig.getClass().getName());
                    trig.trigger(gp);
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    
}
