package com.gmail.lordogoo.spirits_of_the_jar.gametrigger.combattriggers;
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
public class triggerdrawinitalcardsanimation extends triggeranimations
{

    drawdisplay animationcomponent;

    menucontrol cont;
    matchmodel match;
    matchmenupanel matchpanel;
    
    JComponent pdeck;
    JComponent phand;
    JComponent odeck;
    JComponent ohand;
    
    Vector<instansiatedcard> playercards;
    Vector<handcarddisplay> playerdisplay;
    Vector<BufferedImage> playerimages;
    
    Vector<instansiatedcard> opponentcards;
    Vector<handcarddisplay> opponentdisplay;
    Vector<BufferedImage> opponentimages;
    
    int durration = 35;

    public triggerdrawinitalcardsanimation(menucontrol c,matchmodel m,matchmenupanel m2)
    {
        cont = c;
        match = m;
        matchpanel = m2;
        this.pdeck = matchpanel.getplayerdeck();
        this.phand = matchpanel.getplayerhand();
        this.odeck = matchpanel.getopponentdeck();
        this.ohand = matchpanel.getopponenthand();
    }
    
    public void startanimation(gamepanel target){
        System.out.println("initial cards animation start");
        playercards = match.drawcards(5,match.getplayerdeck());
        opponentcards = match.drawcards(5,match.getopponentdeck());
        
        playerdisplay = new Vector<handcarddisplay>();
        for(int i = 0; i < playercards.size();i++){
            handcarddisplay ptemp =  new handcarddisplay(playercards.elementAt(i),false,cont.getlib().gettexture(5).getimage());
            ptemp.setSize(64,phand.getHeight());
            playerdisplay.add(ptemp);
        }
        opponentdisplay = new Vector<handcarddisplay>();
        for(int i = 0; i < opponentcards.size();i++){
            //TODO
            //please make opponent cards hidden again when done bug testing
            handcarddisplay otemp = new handcarddisplay(opponentcards.elementAt(i),false,cont.getlib().gettexture(5).getimage());
            otemp.setSize(64,ohand.getHeight());
            opponentdisplay.add(otemp);
        }
        
        //target.addanimation(new drawinitialcardanimation(target,this,35,new Color(0,0,0,150),playerdisplay,opponentdisplay,
        //                   pdeck,phand,odeck,ohand));

        animationcomponent = new drawdisplay(target,this,durration,new Color(0,0,0,150),
                                             playerdisplay,opponentdisplay,
                                             pdeck,phand,odeck,ohand);
        animationcomponent.setOpaque(false);
        animationcomponent.setLocation(0,0);
        animationcomponent.setSize(target.getcontrol().getxresolution(),target.getcontrol().getyresolution());
        target.addanimationcomponent(animationcomponent);
        
        target.addanimation(new  basicanimation(target,this,durration,null,animationcomponent));
    }
    
    public void endanimation(gamepanel target){
        match.givehandcards(match.getplayerhand(),playercards);
        match.givehandcards(match.getopponenthand(),opponentcards);
        ((matchmenupanel)target).createplayerhand(playerdisplay);
        ((matchmenupanel)target).createopponenthand(opponentdisplay);
        
        target.removeanimationcomponent(animationcomponent);

        
        if(match.whoseturn() == true){
           target.addtrigger(new triggernewplayerturnanimation(cont,match));
        }else{
           target.addtrigger(new triggernewopponentturnanimation(cont,match));
        }
        super.endanimation(target);
        System.out.println("initial cards animation end");
    }
    
    
    public class drawdisplay extends animationscreenpanel
    {
       
          Vector<handcarddisplay> playercards;
          Vector<handcarddisplay> opponentcards;
    
          JComponent pdeck;
          JComponent phand;
          JComponent odeck;
          JComponent ohand;
    
          int pframewidth;
          int oframewidth;
    
          int[] playertimers;
          int[] opponenttimers;
          
        

       public drawdisplay(gamepanel g,triggeranimations t,int durration,Color b,
                                    Vector<handcarddisplay> pc,Vector<handcarddisplay> oc,
                                    JComponent pdeck,JComponent phand,
                                    JComponent odeck,JComponent ohand)
       {
        super(g,durration);
        
        this.pdeck = pdeck;
        this.phand = phand;
        this.odeck = odeck;
        this.ohand = ohand;
        
        playercards = pc;
        opponentcards = oc;
        
        pframewidth = framesdurration/playercards.size();
        oframewidth = framesdurration/opponentcards.size();
    
        playertimers = new int[playercards.size()];
        opponenttimers = new int[opponentcards.size()];
       }   
       
       public void setcurrentframe(int i){
           currentframe = i;
           int pindex = currentframe/pframewidth;
           if(pindex > playertimers.length){pindex = playertimers.length-1;}
           playertimers[pindex]++;
           int oindex = currentframe/oframewidth;
           if(oindex > playertimers.length){oindex = playertimers.length-1;}
           opponenttimers[oindex]++;
       }
     
       public void paintComponent(Graphics g){
        for(int i = 0; i < playercards.size();i++){
            int xloc = pdeck.getX()+((((phand.getX()-pdeck.getX())+64*i)*playertimers[i])/pframewidth);
            int yloc = pdeck.getY();
            //g.drawRect(xloc,yloc,30,30);
            
            //playercards.elementAt(i).setLocation(xloc,yloc);
            playercards.elementAt(i).paintComponent(g,xloc,yloc);
            
        }
        for(int j = 0; j < opponentcards.size();j++){
            int xloc = odeck.getX()+(((ohand.getX()-odeck.getX())+64*j)*opponenttimers[j]/oframewidth);
            int yloc = odeck.getY();
            //g.drawRect(xloc,yloc,30,30);
            //opponentcards.elementAt(j).setLocation(xloc,yloc);
            opponentcards.elementAt(j).paintComponent(g,xloc,yloc);          
        }
       }
    }
}
