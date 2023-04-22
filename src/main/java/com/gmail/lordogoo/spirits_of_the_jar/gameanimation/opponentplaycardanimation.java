package com.gmail.lordogoo.spirits_of_the_jar.gameanimation;
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

public class opponentplaycardanimation extends animation
{

    matchmenupanel matchpanel;

    Vector<animation> animationlist;
    instansiatedcard card;
    gametilemodel tile;
    
    int animcurrentframe;
    int animframedurration;
    
    int p1x;
    int p1y;
    int p2x;
    int p2y;
    
                
    int imagesizex;
    int imagesizey;
    
    //TODO
    //animate not just the card animation but also the playing of the card
    
    
    
    
    public opponentplaycardanimation(gamepanel g,triggeranimations t,int durration,Color b,gametilemodel ti,instansiatedcard c,Vector<animation> a)
    {
        super(g,t,durration,b,0);
        
        matchpanel = (matchmenupanel)g;
        
        animationlist = a;
        card = c;
        tile = ti;
        
        animcurrentframe = 0;
        int nullnotnumber = 0;
        animation temp = null;
        for(int i = 0;i<animationlist.size();i++){
            if(animationlist.elementAt(i)!=null){
                temp = animationlist.elementAt(i);
                nullnotnumber++;
            }
        }
        if(nullnotnumber == 0){
            animframedurration = 0;
        }else{
            animframedurration = temp.getdurration();
        }
        
        
        imagesizex = 32;
        imagesizey = 32;
        p1x = matchpanel.getgameboard().getX() + matchpanel.getgametile(tile.getxlocation(),tile.getylocation()).getX();
        p1y = matchpanel.getgameboard().getY() + matchpanel.getgametile(tile.getxlocation(),tile.getylocation()).getY();
        p2x = matchpanel.getopponentdeck().getX();
        p2y = matchpanel.getopponentdeck().getY();
    }
    
    public void update(){
        System.out.println("%^% %^% opponentplaycardanimationopponentplaycardanimation anim update "+currentframe+" "+animcurrentframe);
        if(currentframe != framesdurration){
            currentframe++;
            
        }else if(animcurrentframe != animframedurration){
           for(int i = 0; i < animationlist.size();i++){
               if(animationlist.elementAt(i) != null){
                   animationlist.elementAt(i).update();
               }
           }
          animcurrentframe++;
        }else{
            this.end();
        }
    }
    
    public void draw(Graphics g,JComponent c){
        if(currentframe != framesdurration){
            int imagelocationx = (currentframe*p1x + framesdurration*p2x - currentframe*p2x)/framesdurration;
            int imagelocationy = (currentframe*p1y + framesdurration*p2y - currentframe*p2y)/framesdurration;
            g.drawImage(card.getcard().getsymbol().getimage(),imagelocationx,imagelocationy,imagesizex,imagesizey,null);
            
        }else{
            
            for(int i = 0; i < animationlist.size(); i++){
                //System.out.println("======="+animationlist.elementAt(i));
                if(animationlist.elementAt(i) != null){
                    animationlist.elementAt(i).draw(g,c);
                }
            }
            
        }
        
    }
    
}
