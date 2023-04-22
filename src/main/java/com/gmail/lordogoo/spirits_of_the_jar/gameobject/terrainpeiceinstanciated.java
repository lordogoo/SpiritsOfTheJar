package com.gmail.lordogoo.spirits_of_the_jar.gameobject;

import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import info.clearthought.layout.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
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
public class terrainpeiceinstanciated
{

   terrainpeicemodel terrain;
   Polygon shape;
   
    public terrainpeiceinstanciated(terrainpeicemodel t,Polygon s)
    {
        terrain = t;
        shape = s;
    }
    
    public terrainpeicemodel getmodel(){
        return terrain;
    }
    
    public Polygon getpolygon(){
        return shape;
    }

    public terrainpeiceinstanciated(BufferedReader in,texturelibrary lib,gameinfocontrol gcont) throws Exception{
        String inline = in.readLine();
        String[] inlist = inline.split("[|]");
        String name = inlist[0];
        int npoints = Integer.parseInt(inlist[1]);
        terrain = gcont.getterrain(name);
        shape = new Polygon();
        for(int i = 0; i < npoints;i++){
            String inline2 = in.readLine();
            String[] inlist2 = inline2.split("[|]");
            shape.addPoint(Integer.parseInt(inlist2[0]),Integer.parseInt(inlist2[1]));
        }
    }
    
    public void saveinformation(PrintWriter out){
        out.println(terrain.getname()+"|"+shape.npoints);
        for(int i = 0; i <shape.npoints ;i++){
            out.println(shape.xpoints[i]+"|"+shape.ypoints[i]);
        }
    }
    
    
    
 }
