package com.gmail.lordogoo.spirits_of_the_jar.controlers;

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
 
public class menucontrol
{

    int xresolution;
    int yresolution;

    volatile boolean running;

    //file controlers
    initfile init;
    
    //main controlers
    gameinfocontrol gameinfo;
    //gamecontrol game;
    texturelibrary lib;
    soundlibrary sound;
    
    //sub controlers
    playermodel currentprofile;
    eventmodel event;
    gamepanel currentmenu;
    
    //app controlers
    JPanel main;
    CardLayout panellist;
   
    //panel data
    Vector<containerplate> list;
    intropanel intropanel;
    mainmenupanel mainpanel;
    deckselectmenupanel newdeckpanel;
    deckmenupanel deckpanel;
    nexusmenupanel nexuspanel;
    worldmenupanel worldpanel;
    bodymenupanel bodypanel;
    playmenupanel playpanel;
    dialogmenupanel diagpanel;
    matchmenupanel matchpanel;
    showcardsmenupanel cardshowpanel;
    prepairmatchmenupanel prepairpanel;
    structuremenupanel structpanel;
    mapmenupanel mappanel;
    profilemenupanel profilepanel;
    shoppanel shoppanel;
    profilepanel propanel;
    burncardspanel burnpanel;
    bountymenupanel bountypanel;
    journalpanel journpanel;
    
    
    public menucontrol(JFrame f,JPanel m,texturelibrary l,soundlibrary s,int x,int y)
    {
        xresolution = x;
        yresolution = y;
        
        running = true;
        lib = l;
        sound = s;
        try{
            gameinfo = new gameinfocontrol(lib,this);
        }catch(Exception e){
            e.printStackTrace();
        }
        main = m;
        
        currentprofile = null;
        
        panellist = new CardLayout();
        main.setLayout(panellist);
        f.setLayout(panellist);
        
        list = new Vector<containerplate>();
        
        int bindex = 3;
        //panels
        intropanel = new intropanel("intropanel",this,lib,sound,bindex);
        addpanel(intropanel);
        mainpanel = new mainmenupanel("menupanel",this,lib,sound,bindex);
        addpanel(mainpanel);
        profilepanel = new profilemenupanel("profpanel",this,lib,sound,bindex);
        addpanel(profilepanel);
        
        deckpanel = new deckmenupanel("deckpanel",this,lib,sound,bindex);
        addpanel(deckpanel);
        nexuspanel = new nexusmenupanel("nexuspanel",this,lib,sound,bindex);
        addpanel(nexuspanel);
        //worldpanel = new worldmenupanel("worldpanel",this,lib,sound,bindex);
        //addpanel(worldpanel);
        //bodypanel = new bodymenupanel("bodypanel",this,lib,sound,bindex);
        //addpanel(bodypanel);
        playpanel = new playmenupanel("playpanel",this,lib,sound,bindex);
        addpanel(playpanel);
        diagpanel = new dialogmenupanel("diagpanel",this,lib,sound,bindex);
        addpanel(diagpanel);
        matchpanel = new matchmenupanel("matchpanel",this,lib,sound,bindex);
        addpanel(matchpanel);
        newdeckpanel = new deckselectmenupanel("newdeckpanel",this,lib,sound,bindex);
        addpanel(newdeckpanel);
        cardshowpanel = new showcardsmenupanel("cardshowpanel",this,lib,sound,bindex);
        addpanel(cardshowpanel);
        prepairpanel = new prepairmatchmenupanel("prepairpanel",this,lib,sound,bindex);
        addpanel(prepairpanel);
        structpanel = new structuremenupanel("structpanel",this,lib,sound,bindex);
        addpanel(structpanel);
        mappanel = new mapmenupanel ("mappanel",this,lib,sound,bindex);
        addpanel(mappanel);
        shoppanel = new shoppanel("shoppanel",this,lib,sound,bindex);
        addpanel(shoppanel);
        
        propanel = new profilepanel("propanel",this,lib,sound,bindex);
        addpanel(propanel);
        burnpanel = new burncardspanel("burnpanel",this,lib,sound,bindex);
        addpanel(burnpanel);
        journpanel = new journalpanel("journpanel",this,lib,sound,bindex);
        addpanel(journpanel);
        bountypanel = new bountymenupanel("bountpanel",this,lib,sound,bindex);
        addpanel(bountypanel);
        
        //panellist.show(main,"menupanel");
        currentmenu = intropanel;
        
        init = new initfile(this);
        mainpanel.initdisplay();
    }
    
    public void resize(int x, int y){
        xresolution = x;
        yresolution = y;
        currentmenu.getcontainerplate().resize();
        
    }
    
    public int getxresolution(){
        return xresolution;
    }
    
    public int getyresolution(){
        return yresolution;
    }
    
    public void addpanel(gamepanel p){
        containerplate containertemp = new containerplate(this,p);
        p.setcontainerplate(containertemp);
        list.add(containertemp);
        main.add(containertemp,p.getname());
    }
    
    
    public void switchpanel(String name){
       System.out.println("switching panel to "+name);
       specialboarderpanel paneltemp = currentmenu.getspecialpanel();
       gamepanel oldcurrentmenu = currentmenu;
       gamepanel newcurrentmenu = null;
       
       //find new panel
       for(int i = 0; i < list.size();i++){
           newcurrentmenu = list.elementAt(i).getgamepanel();
           if(newcurrentmenu.getname().equals(name)){
               break;
           }
       }
       System.out.println("break point 1");
       currentmenu =  newcurrentmenu;
       System.out.println("break point 2");
       //switch panel
       panellist.show(main,name);
       
       System.out.println("break point 3");
       currentmenu.setpervpanel(paneltemp);
       
       //set up look of panel
       currentmenu.switchto(this);
       
       oldcurrentmenu.switchaway(this);
       System.out.println("successful switching to "+name);
    }
    
    public boolean isrunning(){
        return running;
    }
    
    public void setrunning(boolean r){
        running = r;
    }
    
    public synchronized gamepanel getcurrentpanel(){
        return currentmenu;
    }
    
    public gamepanel getpanel(String name){
       for(int i = 0; i < list.size();i++){
           gamepanel temp = list.elementAt(i).getgamepanel();
           if(temp.getname().equals(name)){
               return temp;
           }
       }
       return null;
    }
    
    
    
    public void setcurrentprofile(playermodel p){
        currentprofile = p;
    }
    
    public playermodel getcurrentprofile(){
        return currentprofile;
    }
    
    public void setreturnpanel(gamepanel p){
        newdeckpanel.setreturnpanel(p);
    }
    
    
    public void newgame(){
        
        currentprofile.addgame( new gamecontrol(new tutorialcontrol(this,lib),gameinfo,this.getcurrentprofile()));
    }
    
    public gamecontrol getgamecontrol(){
        return currentprofile.getgame();
    }
    
    public void addevent(eventmodel e){
        event = e;
    }
    
    public eventmodel getevent(){
        return event;
    }
    
    
    public gameinfocontrol getgameinfo(){
        return gameinfo;
    }
    
    public texturelibrary getlib(){
        return lib;
    }
    
    
    
    //init file methods
    public initfile getinitfile(){
        return init;
    }
    
    //profile save/load
    public void saveprofile(playermodel currentprofile,String name){
         File dir =  new File("profiles/"+name+".prof");
         try{
              FileOutputStream fileOut = new FileOutputStream(dir);
              PrintWriter out = new PrintWriter(fileOut);
              out.println("version 0.1");
              currentprofile.saveinformation(out);
              out.close();
         }catch(Exception e){
              //e.printStackTrace();
         }
    }
    
    public playermodel loadprofile(String name){
            File f = new File("profiles/"+name+".prof");
            playermodel playertemp = null;
            try{
                System.out.println("import profile "+name);
                FileReader fis = new FileReader(f);
                BufferedReader in = new BufferedReader(fis);
                //ObjectInputStream in = new ObjectInputStream(fis);
                String version = in.readLine();
                System.out.println("read in file "+version);
                if(version.equals("version 0.1")){
                    playertemp = new playermodel(in,this,lib,gameinfo);
                }
                in.close();
                System.out.println("load successful");
            }catch(Exception e){
                //e.printStackTrace();
                System.out.println("load failed");
                e.printStackTrace();
                playertemp = null;
            }
            return playertemp;
    }
}
