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
import java.applet.*;
import java.io.*;
import java.util.*;
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
public class playmenupanel extends gamepanel
{

    menucontrol cont;
    gamecontrol game;
    
    playermodel player;
    worldinstanciated world;
    terraininstanciated terrain;
    bodymodel body;
    
    JButton backtogamemenu;
    JButton Journalbutton;
    JButton mapbutton;
    JButton deckbutton;
    
    mapdisplay map;
    
    playmenuthread thread;
    

    
    //flags
    volatile boolean active;
    
    boolean northup;
    boolean southdown;
    boolean eastright;
    boolean westleft;
    boolean northw;
    boolean souths;
    boolean eastd;
    boolean westa;
    
    boolean space;
    
    boolean enter;
    boolean leave;
    
    public playmenupanel(String name,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(name,c,i,s,bi);
        cont = c;
        double size[][] = 
        {{TableLayout.FILL,.225,.225,.225,.225,TableLayout.FILL},
        {TableLayout.FILL,30,.9,TableLayout.FILL,1}};
        
        this.setdimensions(4,3);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"0,0,5,3");
        
        backtogamemenu = new specialboarderbutton("leave this world",i.gettexture(4).getimage(),this,5,null);
        backtogamemenu.addActionListener(new gameclick());
        backtogamemenu.addMouseListener(new clickdilimeter());
        this.add(backtogamemenu,"1,1");
        this.setComponentZOrder(backtogamemenu,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)backtogamemenu);
        //componentlist.add(backtogamemenu);
        
        Journalbutton = new specialboarderbutton("journal",i.gettexture(4).getimage(),this,5,null);
        Journalbutton.addActionListener(new journelclick());
        Journalbutton.addMouseListener(new clickdilimeter());
        this.add(Journalbutton,"2,1");
        this.setComponentZOrder(Journalbutton,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)Journalbutton);
        //componentlist.add(Journalbutton);
        
        mapbutton = new specialboarderbutton("map",i.gettexture(4).getimage(),this,5,null);
        mapbutton.addActionListener(new mapclick());
        mapbutton.addMouseListener(new clickdilimeter());
        this.add(mapbutton,"3,1");
        this.setComponentZOrder(mapbutton,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)mapbutton);
        //componentlist.add(mapbutton);
        
        deckbutton = new specialboarderbutton("deck",i.gettexture(4).getimage(),this,5,null);
        deckbutton.addActionListener(new deckclick());
        deckbutton.addMouseListener(new clickdilimeter());
        this.add(deckbutton,"4,1");
        this.setComponentZOrder(deckbutton,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)deckbutton);
        //componentlist.add(deckbutton);
        
        map = new mapdisplay();
        map.setBackground(Color.white);
        map.setFocusable(true);
        map.addKeyListener(new keytyped());
        map.setOpaque(false);
        this.add(map,"1,2,4,2");
        this.setComponentZOrder(map,0);
        new specialpanelcontainer(componentlist,containerlist,map);
        //componentlist.add(map);
        
        //initilaze flags
        northup = false;
        northw = false;
        southdown = false;
        souths = false;
        eastright = false;
        eastd = false;
        westleft = false;
        westa = false;
        
        space = false;
        enter = false;
        leave = false;
    }
    
    public void disableall(){
        backtogamemenu.setEnabled(false);
        Journalbutton.setEnabled(false);
        mapbutton.setEnabled(false);
        deckbutton.setEnabled(false);
    }
    
    public void enableall(){
        backtogamemenu.setEnabled(true);
        Journalbutton.setEnabled(true);
        mapbutton.setEnabled(true);
        deckbutton.setEnabled(true);
    }
    
    /***********************************
     * switch to
     **********************************/
    public void switchto(menucontrol g){
        //get info
        game = g.getcurrentprofile().getgame();
        world = game.getcurrentworld();
        
        //clean up model
        if(world.getterrain()==null){
            world.instansiateterrain(cont.getgameinfo(),cont);
        }
        terrain = world.getterrain();
        
        super.switchto(g);
        map.switchto(game.getcurrentworld());
        if(!tutorialrunning){
            this.addtrigger(new triggeraddplayertoworld(cont));
            this.addtrigger(new triggerworldstart());
            enableall();
        }
        /*
        trigger e = world.getmodel().getenterworldtrigger();
        if(e != null){
            //deal with on enter world event
            System.out.println("play event");
            cont.addevent(new eventmodel(cont));
            e.trigger(this);
        }
       
        if((cont.getevent()==null)||(!cont.getevent().getmoveaway())){                              
            System.out.println("map start");
            map.switchto(game.getcurrentworld());
            
            triggeranimation part = new triggeranimation();
            
            //TODO
            //set up animation
            Shape circle = new Ellipse2D.Float(100.0f, 100.0f, 100.0f, 100.0f);
            particle p = new particle(10,10,Color.red,0.5f,10,10,circle);
            particleemitter emitter = new particleemitter(this.getWidth()/2,this.getHeight()/2,p,100);
            //animation animpart = new particleanimation(this,part,100,null);
            super.switchto(g);
            //part.addanimation(animpart);
            //this.addtrigger(part);
            this.addtrigger(new triggeraddplayertoworld(cont));
            this.addtrigger(new triggerworldstart());
        }
        */
    }
    
    
    public void switchaway(menucontrol menu){
        //must set flags to false or game will have bad behavior when we switch back
        northup = false;
        northw = false;
        southdown = false;
        souths = false;
        eastright = false;
        eastd = false;
        westleft = false;
        westa = false;
        
        space = false;
        enter = false;
        leave = false;
        //stop thread: prevents infinite loop
        System.out.println("@#%$#$%!$%!#$%#@!%!#@switch away "+active);
        if(active == true){
            stopthread();
        }
    }
    
    
    
    
    /*****************************
     * thread 
     */
    public void startthread(){
            System.out.println("???playthread start");
            thread = new playmenuthread(this,cont);
            thread.start();
            active = true;
            map.requestFocusInWindow();
            this.setComponentZOrder(map,0);
    }
    
    public void stopthread(){
        System.out.println("play thread stop???");
        thread.stopthread();
        active = false;
    }
    
    /******************************
     * update
     ******************************/
    public void update(){
        map.requestFocusInWindow();
        //update player position
        //System.out.println("update player position");
        int xupdate = 0;
        int yupdate = 0;
        double speed = (double)game.getplayer().getcurrentbody().getmodel().getspeed();
        if      ((((northup)||(northw))&&((eastright)||(eastd)))&&(!((southdown)||(souths))&&!((westleft)||(westa)))){
            xupdate = (int)(speed*Math.cos(Math.PI/4));
            yupdate = (int)(-speed*Math.sin(Math.PI/4));
            world.addoffset(xupdate,yupdate);
        }else if((((northup)||(northw))&&!((eastright)||(eastd)))&&(!((southdown)||(souths))&&((westleft)||(westa)))){
            xupdate = (int)(-speed*Math.cos(Math.PI/4));
            yupdate = (int)(-speed*Math.sin(Math.PI/4));
            world.addoffset(xupdate,yupdate);
        }else if((!((northup)||(northw))&&!((eastright)||(eastd)))&&(((southdown)||(souths))&&((westleft)||(westa)))){
            xupdate = (int)(-speed*Math.cos(Math.PI/4));
            yupdate = (int)(speed*Math.sin(Math.PI/4));
            world.addoffset(xupdate,yupdate);
        }else if((!((northup)||(northw))&&((eastright)||(eastd)))&&(((southdown)||(souths))&&!((westleft)||(westa)))){
            xupdate = (int)(speed*Math.cos(Math.PI/4));
            yupdate = (int)(speed*Math.sin(Math.PI/4));
            world.addoffset(xupdate,yupdate);
        }else if(((northup)||(northw))&&!((southdown)||(souths))){
            world.addoffset(0,(int)(-speed));
        }else if(!((northup)||(northw))&&((southdown)||(souths))){
            world.addoffset(0,(int)(speed));
        }else if(((eastright)||(eastd))&&!((westleft)||(westa))){
            world.addoffset((int)(speed),0);
        }else if(!((eastright)||(eastd))&&((westleft)||(westa))){
            world.addoffset((int)(-speed),0);
        }
        
        
        //System.out.println("remove dead npcs");
        //remove dead npc's
        for(int i = 0; i < terrain.numnpc();i++){
            if(terrain.getnpc(i).getalive()==false){
                if(terrain.getnpc(i).getbadguy()!=null){
                    if(terrain.getnpc(i).getkilledbyplayer()){
                        if(terrain.getnpc(i).getbadguy().getmodel().getondeathbyplayertrigger()!=null){
                            cont.addevent(new npceventmodel(cont,terrain.getnpc(i),null));
                            terrain.getnpc(i).getbadguy().getmodel().getondeathbyplayertrigger().trigger(this);
                        }
                    }else{
                        if(terrain.getnpc(i).getbadguy().getmodel().getondeathtrigger()!=null){
                            cont.addevent(new npceventmodel(cont,terrain.getnpc(i),null));
                            terrain.getnpc(i).getbadguy().getmodel().getondeathtrigger().trigger(this);
                        }
                    }
                }
                terrain.removenpc(i);
                i--;
            }
        }
        
        
        //System.out.println("update structures");
        //update structures
        for(int i = 0; i < terrain.getstructurenum();i++){
            
            if(terrain.getstructure(i).getmodel().getupdatetrigger() != null){
                cont.addevent(new structureupdateeventmodel(cont,terrain.getstructure(i)));
                terrain.getstructure(i).getmodel().getupdatetrigger().trigger(this);
            }
            
        }
        
        //System.out.println("update npcs");
        //TODO remove try catch when finished debugging
        try{
        //update npc's
        // TODO need to reright this function to account for npc vs npc interactons
        for(int i = 0; i < terrain.numnpc();i++){
            
            if(terrain.getnpc(i).getalive()){
                //update the npc's 
                terrain.getnpc(i).getai().update(game);
                int npcxupdate = terrain.getnpc(i).getai().getxvilosity();
                int npcyupdate = terrain.getnpc(i).getai().getyvilosity();
                terrain.getnpc(i).move(npcxupdate,npcyupdate);
                if(!terrain.getnpc(i).isplayer()){
            
                    int numaicollisions = 0;
                    //check if this npc has made contact with another npc
                    for(int j = 0; j < terrain.numnpc();j++){
                        
                        if(terrain.getnpc(j).getalive()){
                            //if not itself
                            if(terrain.getnpc(i) != terrain.getnpc(j)){
                                int npcdx = terrain.getnpc(j).getx() - terrain.getnpc(i).getx();
                                int npcdy = terrain.getnpc(j).gety() - terrain.getnpc(i).gety();
                                int npcradious = terrain.getnpc(i).getmodel().getradius();
                    
                                if(npcdx*npcdx+npcdy*npcdy <= 2*npcradious*npcradious +1){
                                    if(terrain.getnpc(j).isplayer()){
                                        //System.out.println("player touch");
                                        if(terrain.getnpc(i).getonplayertouchtrigger()!=null){
                                            cont.addevent(new npceventmodel(cont,terrain.getnpc(i),terrain.getnpc(j)));
                                            terrain.getnpc(i).getonplayertouchtrigger().trigger(this);
                                            //TODO need to deal with the problem of when the player encounters an npc that doesnt 
                                            //do anything peticularly problimatic
                                            if(cont.getevent().getmoveaway()){
                                                return;
                                            }
                                        }
                                    }else{
                                        //System.out.println("npc touch");
                                        if(terrain.getnpc(i).getonnpctouchtrigger()!=null){
                                            cont.addevent(new npceventmodel(cont,terrain.getnpc(i),terrain.getnpc(j)));
                                            terrain.getnpc(i).getonnpctouchtrigger().trigger(this);
                                            numaicollisions++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                    //TODO
                    //will have to make a section checking between npc and structure collisions
                    
                    //check to see if npc is idle
                    if((numaicollisions == 0)&&(terrain.getnpc(i).getonidletrigger()!=null)){
                         cont.addevent(new npceventmodel(cont,terrain.getnpc(i),null));
                         terrain.getnpc(i).getonidletrigger().trigger(this);
                    }
                }
            }
        }
        }catch(Exception e){
                e.printStackTrace();
        }
        
        this.repaint();
        
        //System.out.println("has enter button been pressed");
        
        if(enter == true){
            System.out.println("enter");
            for(int i = 0; i < terrain.getstructurenum(); i++){
                int offsetx = world.getoffsetx();
                int offsety = world.getoffsety();
                structureinstansiated struct = terrain.getstructure(i);
                int radius = struct.getmodel().getradius(); 
                int dispx = offsetx - struct.getx();
                int dispy = offsety - struct.gety();
                if(dispx*dispx+dispy*dispy <= radius*radius){
                    game.setcurrentstruct(struct);
                    cont.switchpanel("structpanel");
                }
            }
        }
        
    }
    
    private class clickdilimeter implements MouseListener{
    
        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
            clickinteruptanimation(e);
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }
    
    }
   
    private class gameclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
             soundref.playsoundeffect(0);
             autosavetrigger autosave = new autosavetrigger(cont);
             autosave.trigger(thisref); 
             setupswitchpanel("nexuspanel");
        }    
    }
    
    private class journelclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
             soundref.playsoundeffect(0);
             setupswitchpanel("propanel");
        }    
    }
    
    private class mapclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
             soundref.playsoundeffect(0);
             setupswitchpanel("mappanel");
        }    
    }
    
    private class deckclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
             soundref.playsoundeffect(0);
             cont.setreturnpanel(thisref);
             if(!tutorialrunning){
                 setupswitchpanel("newdeckpanel");
             }
        }    
    }
    
    private class mapdisplay extends specialpanel{
        
        worldinstanciated world;
        terraininstanciated terrain;
        
        public mapdisplay(){
        }
        
        public void switchto(worldinstanciated w){
            world = w;
            terrain = w.getterrain();
            
        }
        
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            if(alpha != 1){
               int type = AlphaComposite.SRC_OVER;            
               g2d.setComposite(AlphaComposite.getInstance(type, alpha));
            }
            
            if(terrain != null){
               int screenwidth = this.getWidth();
               int screenheight = this.getHeight();
               int offsetx = world.getoffsetx();
               int offsety = world.getoffsety();
               int soffsetx = offsetx-this.getWidth()/2;
               int soffsety = offsety-this.getHeight()/2;
               
               int playerwidth = 10;
               int playerheight = 10;
              
               //copy defaults
               Color defaultcolor = g.getColor();
               g.setColor(Color.black);
               g.fillRect(0,0,this.getWidth(),this.getHeight());
               //draw polygons

               g.setColor(Color.blue);
               BufferedImage imm = cont.getlib().gettexture(0).getimage();
               Rectangle2D rect = new Rectangle2D.Double(-offsetx,-offsety, imm.getWidth(),imm.getHeight());
               g2d.setPaint(new  TexturePaint(imm,rect));
               /*
               Polygon p = new Polygon();
               
               p.addPoint(terrain.getx()-soffsetx,
                          terrain.gety()-soffsety);
               p.addPoint(terrain.getx()+terrain.getwidth()-soffsetx,
                          terrain.gety()-soffsety);
               p.addPoint(terrain.getx()+terrain.getwidth()-soffsetx,
                          terrain.gety()+terrain.getheight()-soffsety);
               p.addPoint(terrain.getx()-soffsetx,
                          terrain.gety()+terrain.getheight()-soffsety);

               g2d.fillPolygon(p);
                */
               //System.out.println("terrain peices "+terrain.getterrainpeicenum());
               for(int i = 0; i < terrain.getterrainpeicenum(); i++){
                   //System.out.println("tt "+i);
                   terrainpeiceinstanciated terrainpeice = terrain.getterrainpeice(i);
                   //terrainpeice.getpolygon().translate(-soffsetx,-soffsety);
                   Polygon temppoly = new Polygon(terrainpeice.getpolygon().xpoints, terrainpeice.getpolygon().ypoints, terrainpeice.getpolygon().npoints);
                   temppoly.translate(-soffsetx,-soffsety);
                   Rectangle2D rect2 = new Rectangle2D.Double(-offsetx,-offsety, terrainpeice.getmodel().gettexture().getWidth(),terrainpeice.getmodel().gettexture().getHeight());
                   g2d.setPaint(new  TexturePaint(terrainpeice.getmodel().gettexture(),rect2));
                   
                   g2d.fillPolygon(temppoly);
               }
               //System.out.println("terrain peices end");
               
               //display doodads
               for(int i = 0; i < terrain.getdoodadnum(); i++){
                    doodadinstansiated doodad = terrain.getdoodad(i);
                    BufferedImage itemimage = doodad.getmodel().getimage().getimage();
                    int ddxloc = doodad.getx()-(offsetx-this.getWidth()/2);
                    int ddyloc = doodad.gety()-(offsety-this.getHeight()/2);
                    g.drawImage(itemimage,ddxloc,ddyloc,null);
               }
               //display structures
               for(int i = 0; i < terrain.getstructurenum(); i++){
                   structureinstansiated struct = terrain.getstructure(i);
                   int radius = struct.getmodel().getradius(); 
                   int dispx = offsetx - struct.getx();
                   int dispy = offsety - struct.gety();
                   if(dispx*dispx+dispy*dispy >= radius*radius){
                        g.setColor(Color.blue);
                    }else{
                        g.setColor(Color.yellow);
                    }

                    BufferedImage itemimage = struct.getmodel().getimage();
                    int ddxloc = struct.getx()-(offsetx-this.getWidth()/2);
                    int ddyloc = struct.gety()-(offsety-this.getHeight()/2);
                    g.drawOval(ddxloc-radius,ddyloc-radius,radius*2,radius*2);
                    g.drawImage(itemimage,ddxloc-itemimage.getWidth()/2,ddyloc-itemimage.getHeight()/2,null);
               }
               
               //draw npc's
               for(int i = 0; i < terrain.numnpc();i++){
                   npcinstanciated npc = terrain.getnpc(i);
                   int radius = npc.getmodel().getradius();
                   int npcx = npc.getx()-(offsetx-this.getWidth()/2);
                   int npcy = npc.gety()-(offsety-this.getHeight()/2);
                   
                   if(npc.getbadguy()!=null){
                       BufferedImage badguyimage = npc.getbadguy().getmodel().getimage().getimage();
                       g.drawImage(badguyimage,npcx-((badguyimage.getWidth()/2)),npcy-((badguyimage.getHeight()/2))-20,null);
                   }
                   
                   if(cont.getcurrentprofile().getcurrentbody().getmodel().getfaction().ishatednpc(npc.getmodel().getbody())){
                       g.setColor(Color.red);
                   }else{
                       g.setColor(Color.green);
                   } 
                   g.drawOval(npcx-radius,npcy-radius,radius*2,radius*2);
                   g.drawImage(npc.getbody().getimage().getimage(),npcx-((npc.getbody().getimage().getimage().getWidth()/2)),npcy-((npc.getbody().getimage().getimage().getHeight()/2)),null);
               }
               
               
               //draw player
               g.setColor(Color.red);
               g.drawRect((this.getWidth()-playerwidth)/2,(this.getHeight()-playerheight)/2,playerwidth,playerheight);
               
               
               g.setColor(defaultcolor);
               //
               //g.drawString(c.checkcard().getname()+"",5,this.getHeight()-35);
               //g.drawString("num "+c.numcard()+"",5,this.getHeight()-20);
               //g.drawString("used "+numused+"",5,this.getHeight()-5);
            }
        }
    
    } 
    
    private class pointint{

        int x;
        int y;
        
        public pointint(int x,int y){
            this.x = x;
            this.y = y;
        }
    
    }
    
    
    public class keytyped implements KeyListener{
        
        public void keyTyped(KeyEvent e){

        }
    
        public void keyPressed(KeyEvent e){
            switch( e.getKeyCode() ) { 
                case KeyEvent.VK_UP: 
                     northup = true;
                     break;
                case KeyEvent.VK_W: 
                     northw = true;
                     break;
                case KeyEvent.VK_DOWN:
                     southdown = true;
                     break;
                case KeyEvent.VK_S:
                     souths = true;
                     break;
                case KeyEvent.VK_LEFT:
                     westleft = true; 
                     break;
                case KeyEvent.VK_A:
                     westa = true; 
                     break;     
                case KeyEvent.VK_RIGHT:
                     eastright = true;
                     break;
                case KeyEvent.VK_D:
                     eastd = true;
                     break;
                case KeyEvent.VK_SPACE:
                     space = true;
                     break;
                case KeyEvent.VK_E:
                     enter = true;
                     break;
                case KeyEvent.VK_ESCAPE:
                     leave = true;
                     break;
            } 
        }
    
        public void keyReleased(KeyEvent e){
            switch( e.getKeyCode() ) { 
                case KeyEvent.VK_UP: 
                     northup = false;
                     break;
                case KeyEvent.VK_W: 
                     northw = false;
                     break;
                case KeyEvent.VK_DOWN:
                     southdown = false;
                     break;
                case KeyEvent.VK_S:
                     souths = false;
                     break;   
                case KeyEvent.VK_LEFT:
                     westleft = false;
                     break;
                case KeyEvent.VK_A:
                     westa = false;
                     break;
                case KeyEvent.VK_RIGHT:
                     eastright = false;
                     break;
                case KeyEvent.VK_D:
                     eastd = false;
                     break;
                case KeyEvent.VK_SPACE:
                     space = false;
                     break;
                case KeyEvent.VK_E:
                     enter = false;
                     break;
            } 
        }
    }
    

}
