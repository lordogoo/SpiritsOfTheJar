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
 * Write a description of class triggergivecards here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class triggergivecards extends dialogtrigger
{

    int numcards;    
    Vector<card> piclist;
    Vector<Integer> maxnum;
    int cash;
    String message;
    
    public triggergivecards(menucontrol m,int n,Vector<card> p,Vector<Integer> max,int c,String mes)
    {
        super(m);
        numcards = n;
        piclist = p;
        maxnum = max;   
        cash = c;
        message = mes;
        
    }

    public triggergivecards(menucontrol m,int n,Vector<card> p,Vector<Integer> max,trigger tr)
    {
        super(m);
        numcards = n;
        piclist = p;
        maxnum = max; 
        nexttrigger = tr;
    }
    
     public void trigger(gamepanel target){
        this.target = target;
        cleanup();
    }
    
    public void cleanup(){
        gamepanel returnpanel = cont.getgamecontrol().getcurrentdialog().getreturnpanel();

        Vector<candidate> candidates = new Vector<candidate>();
        for(int i = 0; i < piclist.size();i++){
            
            if(maxnum.elementAt(i).intValue() > 0){
                
                candidates.add(new candidate(piclist.elementAt(i),maxnum.elementAt(i).intValue()));
            }
        }
        
        gameinfocontrol ginfo = cont.getgameinfo();
        playermodel player = cont.getgamecontrol().getplayer();
        for(int i = 0; i < numcards;i++){
            if(candidates.size()==0){
            }else if(candidates.size()==1){
                player.givecard(candidates.elementAt(0).removecard());
                if(candidates.elementAt(0).getnum() == 0){
                    candidates.remove(0);
                }
            }else{
                Random rand = new Random();
                int index = Math.abs(rand.nextInt()%candidates.size());
                player.givecard(candidates.elementAt(index).removecard());
                if(candidates.elementAt(index).getnum() == 0){
                    candidates.remove(index);
                }
            }
            
        } 
        //TODO
        //cont.getgamecontrol().setcurrentevent(this);
        cont.getcurrentprofile().addmoney(cash);
        showcardsmenupanel panel = (showcardsmenupanel)cont.getpanel("cardshowpanel");
        panel.setmessage(message);
        panel.setcash(cash);
        panel.setnexttrigger(nexttrigger);
        cont.switchpanel("cardshowpanel");
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
}
