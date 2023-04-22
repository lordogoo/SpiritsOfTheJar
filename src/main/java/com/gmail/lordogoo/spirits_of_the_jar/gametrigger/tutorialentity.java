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

public class tutorialentity{
    BufferedImage character;
    //gamepanel gamep;
    boolean verticalhorzontal;
    boolean side;
    int location;
    
    int xstartmove;
    int ystartmove;
    int xendmove;
    int yendmove;
    
    int xlocspeech;
    int ylocspeech;
    int xpointofinterestspeech;
    int ypointofinterestspeech;
    int xstartspeech;
    int ystartspeech;
    int startwidthspeech;
    int startheightspeech;
    int xendspeech;
    int yendspeech;
    int endwidthspeech;
    int endheightspeech;
            
    public tutorialentity(gamepanel gp,BufferedImage ch,boolean vh,boolean s,int loc){
        character = ch;
        verticalhorzontal = vh;
        side = s;
        location = loc;
        
        if(vh){
            if(side){
                //north
                xstartmove = location;
                ystartmove = -character.getHeight();
                xendmove = location;
                yendmove = 0;
                
                xlocspeech = location;
                ylocspeech = 0;
                
                xpointofinterestspeech = location+(character.getWidth()/2);
                ypointofinterestspeech = character.getHeight();
                
                xstartspeech = xpointofinterestspeech;
                ystartspeech = ypointofinterestspeech;
                startwidthspeech = 0;
                startheightspeech = 0;
                
                
                xendspeech = character.getWidth()+20;
                yendspeech = character.getHeight()+20;
                endwidthspeech = gp.getWidth()-2*xendspeech;
                endheightspeech = gp.getHeight()-2*yendspeech;
            }else{
                //south
                xstartmove = location;
                ystartmove = gp.getHeight();
                xendmove = location;
                yendmove = gp.getHeight()-character.getHeight();
                
                xlocspeech = location;
                ylocspeech = gp.getHeight()-character.getHeight();
                
                xpointofinterestspeech = location+(character.getWidth()/2);
                ypointofinterestspeech = gp.getHeight()-character.getHeight();
                
                xstartspeech = xpointofinterestspeech;
                ystartspeech = ypointofinterestspeech;
                startwidthspeech = 0;
                startheightspeech = 0;
                
                xendspeech = character.getWidth()+20;
                yendspeech = character.getHeight()+20;
                endwidthspeech = gp.getWidth()-2*xendspeech;
                endheightspeech = gp.getHeight()-2*yendspeech;
            }
        }else{
             if(side){
                //east
                xstartmove = gp.getWidth();
                ystartmove = location;
                xendmove = gp.getWidth()-character.getWidth();
                yendmove = location;
                
                xlocspeech = gp.getWidth()-character.getWidth();
                ylocspeech = location;
                
                xpointofinterestspeech  =  gp.getWidth()-character.getWidth();
                ypointofinterestspeech = location + (character.getHeight()/2);
                
                
                xstartspeech = xpointofinterestspeech ;
                ystartspeech = ypointofinterestspeech;
                startwidthspeech = 0;
                startheightspeech = 0;
                
                xendspeech = character.getWidth()+20;
                yendspeech = character.getHeight()+20;
                endwidthspeech = gp.getWidth()-2*xendspeech;
                endheightspeech = gp.getHeight()-2*yendspeech;
                
                
            }else{
                //west
                xstartmove = -character.getHeight();
                ystartmove = location;
                xendmove = 0;
                yendmove = location;
                
                xlocspeech = 0;
                xlocspeech = location;
                
                xpointofinterestspeech = character.getWidth();
                ypointofinterestspeech = location + (character.getHeight()/2);
                
                xstartspeech = xpointofinterestspeech;
                ystartspeech = ypointofinterestspeech;
                startwidthspeech = 0;
                startheightspeech = 0;
                
                
                xendspeech = character.getWidth()+20;
                yendspeech = character.getHeight()+20;
                endwidthspeech = gp.getWidth()-2*xendspeech;
                endheightspeech = gp.getHeight()-2*yendspeech;
            }
        }   
    }
           
    public boolean getvh(){
        return verticalhorzontal;
    }
    
    public boolean getside(){
        return side;
    }
    
    public int getlocation(){
        return location;
    }
    
    public BufferedImage getpicture(){
        return character;
    }
    
    //entity animation
    public int getstartx(){
        return xstartmove;
    }
    
    public int getstarty(){
        return ystartmove;
    }
    
    public int getendx(){
        return xendmove;
    }
    
    public int getendy(){
        return yendmove;
    }
            
    //speech animation
    public int getlocationspeechx(){
        return xlocspeech;
    }
    
    public int getlocationspeechy(){
        return ylocspeech;
    }
    
    public int getpointofinterestx(){
        return xpointofinterestspeech;
    }
    
    public int getpointofinteresty(){
        return ypointofinterestspeech;
    }
    
    public int getstartspeechx(){
        return xstartspeech;
    }
    
    public int getstartspeechy(){
        return ystartspeech;
    }
    
    public int getstartspeechwidth(){
        return startwidthspeech;
    }
    
    public int getstartspeechheight(){
        return startheightspeech;
    }
    
    public int getendspeechx(){
        return xendspeech;
    }
    
    public int getendspeechy(){
        return yendspeech;
    }
    
    public int getendspeechwidth(){
        return endwidthspeech;
    }
    
    public int getendspeechheight(){
        return endheightspeech;
    }
    
}
