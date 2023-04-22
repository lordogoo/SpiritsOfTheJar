package com.gmail.lordogoo.spirits_of_the_jar.gametrigger.dialogtriggers;
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
 * Write a description of class triggergiverewards here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class triggergiverewards extends dialogtrigger
{
    // instance variables - replace the example below with your own
    int numcards;    
    npcmodel faction;
    String message;
    
    public triggergiverewards(menucontrol m,int n,npcmodel f,String mes)
    {
        super(m);
        numcards = n;
        faction = f;  
        message = mes;
    }

     public void trigger(gamepanel target){
        this.target = target;
        cleanup();
    }
    
    public void cleanup(){
        gamepanel returnpanel = cont.getgamecontrol().getcurrentdialog().getreturnpanel();

        playermodel player = cont.getgamecontrol().getplayer();
        rewardslist rewards = faction.getbody().getfaction().getrewardslist();
        //gameinfocontrol ginfo = cont.getgameinfo();
        
        Vector<card> cardlist = rewards.getcards(numcards);
        
        for(int i = 0; i < cardlist.size();i++){
            player.givecard(cardlist.elementAt(i));
        }

        cont.getcurrentprofile().addmoney(faction.getbody().getbounty());
        showcardsmenupanel panel = (showcardsmenupanel)cont.getpanel("cardshowpanel");
        panel.setmessage(message);
        panel.setcash(faction.getbody().getbounty());
        panel.setnexttrigger(nexttrigger);
        cont.switchpanel("cardshowpanel");
    }
}
