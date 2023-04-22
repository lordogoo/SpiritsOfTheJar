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
public class doodadinstansiated extends gameobjectinstanciated
{
    int primarykey;
    doodadmodel doodad;
    
    public doodadinstansiated(int p,doodadmodel d,int x,int y)
    {
        super(p,x,y);
        primarykey = p;
        doodad = d;
    }

    public doodadmodel getmodel(){
        return doodad;
    }
    
    //save load functions
    public doodadinstansiated(BufferedReader in,texturelibrary lib,gameinfocontrol gcont) throws Exception{
        super(0);
        String inline = in.readLine();
        String[] inlist = inline.split("[|]");
        primarykey = Integer.parseInt(inlist[0]);
        doodad = gcont.getdoodad(inlist[1]);
        locx = Integer.parseInt(inlist[2]);
        locy = Integer.parseInt(inlist[3]);
        alive = Boolean.parseBoolean(inlist[4]);
        killedbyplayer = Boolean.parseBoolean(inlist[5]);    
    }
    
    public void saveinformation(PrintWriter out){
        out.println(primarykey+"|"+doodad.getname()+"|"+locx+"|"+locy+"|"+alive+"|"+killedbyplayer);
    }
}
