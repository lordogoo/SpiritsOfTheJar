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

public class terraintriggerrandombadguys extends terraintriggerbadguy
{

    public terraintriggerrandombadguys()
    {
    }
    
    public void trigger(menucontrol m,terraininstanciated t,terrainmodel tm,Random r)
    {
        Vector<npcinstanciated> candidates = new Vector<npcinstanciated>();
        if(t.numnpc()!=0){
            for(int i = 0; i < t.numnpc();i++){
                candidates.add(t.npc.elementAt(i));
            }
        
        
            for(int i = 0; i < t.worldref.numbadguy();i++){
                int npcindex;
                if(candidates.size() > 1){
                    npcindex = Math.abs(r.nextInt()%candidates.size());
                }else{
                    npcindex = 0;
                }
                candidates.elementAt(npcindex).addbadguy(t.worldref.getbadguy(i));
                //create bad guy rumors
                roumorlistmodel temproumor = createrumors(t.worldref.getbadguy(i),candidates.elementAt(npcindex));
                t.worldref.getbadguy(i).addroumor(temproumor);
            
                //add badguy roumors to the shops and game
                for(int j = 0; j < temproumor.size();j++){
                    int structureindex = Math.abs(r.nextInt()%t.struct.size());
                    roumorperchaseinstantiated rpi = new roumorperchaseinstantiated(
                        temproumor.getroumor(j),
                        new perchacemodel("roumor",0,50,50,null,
                            new shoprumortrigger(m,temproumor.getroumor(j))
                        )
                    );
                    t.struct.elementAt(structureindex).getshop().addperchace(rpi);
                }
            
                candidates.remove(npcindex);
            }
        }
    }
    
    public roumorlistmodel createrumors(badguyinstansiated badguy, npcinstanciated npc){
        roumorlistmodel roumors = new roumorlistmodel();
        for(int i = 0; i < 3; i++){
            roumors.addroumor(new roumormodel(i,"testtext",badguy));
        }
        return roumors;
    }
}
