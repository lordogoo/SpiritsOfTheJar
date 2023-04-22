package com.gmail.lordogoo.spirits_of_the_jar.terraintrigger;

import javax.swing.event.*;
import info.clearthought.layout.*;
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
public class terraintriggerrandomstructures extends terraintriggerstructure
{

    public terraintriggerrandomstructures()
    {

    }

    public void trigger(menucontrol m,terraininstanciated t,terrainmodel tm,Random r)
    {
        if(tm.getworld().numstructure()!=0){
            Vector<pointint> pointlist2 = tm.randomizepoints(200,30,0,0,t.getx(),t.gety(),t.getwidth(),t.getheight());
            for(int i = 0; i < pointlist2.size();i++){
                int doodadindex = Math.abs(r.nextInt()%tm.getworld().numstructure());
                int xpoint = pointlist2.elementAt(i).getx();
                int ypoint = pointlist2.elementAt(i).gety();
                structureinstansiated structtemp = new structureinstansiated(t.getstructureindex(),tm.getworld().getstructure(doodadindex),xpoint,ypoint);
                //fill the shops with items
                int numofitems = tm.getPoisson(4);
                for(int j = 0; j < numofitems; j++){
                    if(tm.getworld().getmaxweightmulty()+tm.getworld().getmaxweightsingle()!=0){
                    
                        int num = Math.abs(r.nextInt()%(tm.getworld().getmaxweightmulty()+tm.getworld().getmaxweightsingle()));
                        System.out.println("randomizer "+tm.getworld().getmaxweightmulty()+" "+tm.getworld().getmaxweightsingle()+" "+num+" "+(num - tm.getworld().getmaxweightsingle()));
                        if((num - tm.getworld().getmaxweightsingle()) >= 0){
                            int index = 0;
                            int currentweight = num - tm.getworld().getmaxweightsingle();
                            while(currentweight > tm.getworld().getmulty(index).getweight()){
                                currentweight -= tm.getworld().getmulty(index).getweight();
                                index++;
                            }
                        
                            structtemp.getshop().addperchace(new cardperchaseinstantiated(tm.getworld().getmulty(index),tm.getworld().getmultycard(index)));
                        
                        }else{
                            int index = 0;
                            int currentweight = num;
                            while(currentweight >  tm.getworld().checksingle(index).getweight()){
                                currentweight -= tm.getworld().checksingle(index).getweight();
                                index++;
                            }
                            structtemp.getshop().addperchace(new cardperchaseinstantiated(tm.getworld().getsingle(index),tm.getworld().getsinglecard(index)));
                        }
                    }
                }
                t.addstructure(structtemp);
            }
        }
    }
}
