package com.gmail.lordogoo.spirits_of_the_jar.gametrigger;
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
/**
 * Write a description of class tutorialanimationgivecards here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tutorialanimationgivecards extends triggeranimations
{
    
    carddisplay animationcomponent;
    
    gamepanel namepanel;
    menucontrol cont;
    BufferedImage background;
    JButton button;
    int numcards;
    int durration = 5;
    
    Vector<card> piclist;
    Vector<Integer> max;
    Vector<card> cardlist;
    
    public tutorialanimationgivecards(gamepanel gp,BufferedImage b,int butt,int n,menucontrol c)
    {
        super();
        
        background = b;
     
        namepanel = gp;
        
        numcards = n;
        cont = c;
        button = (JButton)gp.getcomponent(butt);
        cardlist = new Vector<card>();
    }

    public void startanimation(gamepanel target){ 
        Vector<candidate> candidates = new Vector<candidate>();
        playermodel player = cont.getgamecontrol().getplayer();
        piclist = player.getcurrentbody().getrestrictedcards();
        max = player.getcurrentbody().getrestrictednum();
        
        for(int i = 0; i < piclist.size();i++){
            if(max.elementAt(i).intValue() > 0){
                candidates.add(new candidate(piclist.elementAt(i),max.elementAt(i).intValue()));
            }
        }
        
        gameinfocontrol ginfo = cont.getgameinfo();

        for(int i = 0; i < numcards;i++){
            if(candidates.size()==0){
            }else if(candidates.size()==1){
                cardlist.add(candidates.elementAt(0).removecard());
                if(candidates.elementAt(0).getnum() == 0){
                    candidates.remove(0);
                }
            }else{
                Random rand = new Random();
                int index = Math.abs(rand.nextInt()%candidates.size());
                cardlist.add(candidates.elementAt(index).removecard());
                if(candidates.elementAt(index).getnum() == 0){
                    candidates.remove(index);
                }
            }
        } 
        
        animationcomponent = new carddisplay(target,durration*cardlist.size(),cardlist,
                                             target.getcontrol().getxresolution()/2,
                                             target.getcontrol().getyresolution()/2,
                                             button.getX()+(button.getWidth()/2),
                                             button.getY()+(button.getHeight()/2));
        animationcomponent.setOpaque(false);
        animationcomponent.setLocation(0,0);
        animationcomponent.setSize(target.getcontrol().getxresolution(),target.getcontrol().getyresolution());
        namepanel.addanimationcomponent(animationcomponent);
        
        target.addanimation(new  basicanimation(target,this,durration*cardlist.size(),null,animationcomponent));
        
    }
        
    public void endanimation(gamepanel target){
        
        namepanel.removeanimationcomponent(animationcomponent);
        
        playermodel player = cont.getgamecontrol().getplayer();
        for(int i = 0; i < cardlist.size(); i++){
             player.givecard(cardlist.elementAt(i));
        }
        
        triggercontainer.loadnextanimation();
        super.endanimation(target);
    }
   
    public class candidate{
        card c;
        int num;
    
        public candidate(card c,int n){
            this.c = c;
            num = n;
        }
        
        public card removecard(){
            num --;
            return c;
        }
        
        public int getnum(){
            return num;
        }
        
        public boolean isempty(){
            if(num > 0){
                return false;
            }
            return true;
        }
    }
    
    public class carddisplay extends animationscreenpanel
    {
    
       Vector<card> cards;
       int durr;
       int startx;
       int starty;
       int endx;
       int endy;
       
       public carddisplay(gamepanel gp,int durration,Vector<card> c,int sx,int sy,int ex,int ey)
       {
          super(gp,durration);
          cards = c;
       
          durr = durration/c.size();
          startx = sx;
          starty = sy;
          endx = ex;
          endy = ey;
       }
     
       public void paintComponent(Graphics g){
         System.out.println("carddisplay "+currentframe+" "+framesdurration);
         Graphics2D g2d = (Graphics2D) g;
         
         int cardnum = currentframe/durr;
         int cardtotal = framesdurration/durr;
         
         for(int i = 0; i < cards.size();i++){
             if((cardtotal - cardnum) == i ){
                 g.drawImage(cards.elementAt(i).getsymbol().getimage(),
                             startx+(((endx-startx)*(currentframe%durr)/(framesdurration/durr))),
                             starty+(((endy-starty)*(currentframe%durr)/(framesdurration/durr))),
                             64,64,null);
             }else if((cardtotal - cardnum) > i){
                 g.drawImage(cards.elementAt(i).getsymbol().getimage(),startx,starty,64,64,null);
             }else if((cardtotal - cardnum) < i){
             }
         }
       }
    
    }
    
    
    
    
    
    
    
    
}
