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
public class tutorialtriggerspawnspeech extends triggeranimations
{   
    String speeker;
    String message;
    
    BufferedImage character;
    BufferedImage background;
    
    int xloc;
    int yloc;
    
    int poix;
    int poiy;
    
    int xstart;
    int ystart;
    int startwidth;
    int startheight;

    int xend;
    int yend;
    int endwidth;
    int endheight;
    
    
    
    
    public tutorialtriggerspawnspeech(tutorialcontrol tcont,gamepanel namepanel,int sq,
                           String sp,String mess,
                           BufferedImage ch,BufferedImage back,
                           boolean vh,boolean s,int l)
    {
        
        //super(tcont,namepanel,sq);
        super();
        character = ch;
        
        if(vh){
            if(s){
                //north
                xloc = l;
                yloc = 0;
                
                poix = l+(character.getWidth()/2);
                poiy = character.getHeight();
                
                xstart = poix;
                ystart = poiy;
                startwidth = 0;
                startheight = 0;
                
                
                xend = 100;
                yend = character.getHeight()+20;
                endwidth = tcont.getcontrol().getxresolution()-200;
                endheight = 200;
                
                //yend = 0;
            }else{
                //south
                xloc = l;
                yloc = tcont.getcontrol().getyresolution()-character.getHeight();
                
                poix = l+(character.getWidth()/2);
                poiy = tcont.getcontrol().getyresolution()-character.getHeight();
                
                xstart = poix;
                ystart = poiy;
                startwidth = 0;
                startheight = 0;
                
                xend = 100;
                yend = tcont.getcontrol().getyresolution()-character.getHeight()-200-20;
                endwidth = tcont.getcontrol().getxresolution()-200;
                endheight = 200;
                
                
                
            }
        }else{
             if(s){
                //east
                xloc = tcont.getcontrol().getxresolution()-character.getWidth();
                yloc = l;
                
                poix =  tcont.getcontrol().getxresolution()-character.getWidth();
                poiy = l + (character.getHeight()/2);
                
                
                xstart = poix;
                ystart = poiy;
                startwidth = 0;
                startheight = 0;
                
                xend = 100;
                yend = 100;
                endwidth = tcont.getcontrol().getxresolution()-200;
                endheight = 200;
                
            }else{
                //west
                xloc = 0;
                yloc = l;
                
                poix = character.getWidth();
                poiy = l + (character.getHeight()/2);
                
                xstart = poix;
                ystart = poiy;
                startwidth = 0;
                startheight = 0;
                
                
                xend = 100;
                yend = 100;
                endwidth = tcont.getcontrol().getxresolution()-200;
                endheight = 200;
            }
        }
        
        
        
        speeker = sp;
        message = mess;
        
        character = ch;
        background = back;
        
        active = true;
    }

    public void prewait(){ 
        
        
        
        
        
        //target.addanimation(new tutorialanimation(target,this,30,null,
        //                                          speeker,message,
        //                                          character,background,
        //                                          xstart,ystart,startwidth,startheight,
        //                                          xend,yend,endwidth,endheight,
        //                                          xloc,yloc,poix,poiy));
        
        target.addanimationdisplay();   
    }
    
    public void postwait(){
        target.removeanimationdisplay();
        //loadnextanimation();
    }
    
}
