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
public class triggercreatedeck extends dialogtrigger
{
    
    String deckname;
    int decksize;


    public triggercreatedeck(menucontrol m,int decksize,String t)
    {
        super(m);
        deckname = t;
        this.decksize = decksize;
    }
    
    public triggercreatedeck(menucontrol m,int decksize,String t,trigger tr)
    {
        super(m);
        deckname = t;
        this.decksize = decksize;
        nexttrigger = tr;
    }
    
    public void trigger(gamepanel target){
        this.target = target;
        cleanup();
    }
        
    public void interupt(){
    }
    
    public void end(){
    }
    
    public void cleanup(){
        gamepanel returnpanel = cont.getgamecontrol().getcurrentdialog().getreturnpanel();
        gamecontrol g = cont.getgamecontrol();

        Vector<card> piclist = g.getplayer().getpiclist();
        Vector<Integer> maxnum = g.getplayer().getmaxnum();
        
        deckmodel deck = new deckmodel(deckname);
        Vector<candidate> candidates = new Vector<candidate>();
        g.getplayer().adddeck(deck);
        collectionmodel collectiontemp = g.getplayer().getcollection();

        for(int i = 0; i < piclist.size();i++){
            System.out.println("candidate "+piclist.elementAt(i).getname()+" "+maxnum.elementAt(i).intValue());
            if((collectiontemp.containscard(piclist.elementAt(i))!=-1)
              &&(maxnum.elementAt(i).intValue() > 0)){
                System.out.println("added");
                candidates.add(new candidate(piclist.elementAt(i),maxnum.elementAt(i).intValue()));
            }
        }
        
        for(int i = 0; i < decksize; i++){    
            if(candidates.size()==0){
            }else if(candidates.size()==1){
                card temp = candidates.elementAt(0).removecard();
                int collectionindex = collectiontemp.containscard(temp);
                card collectionref = g.getplayer().getcollection().getcollection(collectionindex).checkcard();
                deck.addcard(collectionref);
                if((candidates.elementAt(0).getnum() == 0)||
                  (candidates.elementAt(0).getnumused() == 
                  g.getplayer().getcollection().getcollection(collectionindex).numcard())){
                    candidates.remove(0);
                }
            }else{
                Random rand = new Random();
                int index = Math.abs(rand.nextInt()%candidates.size());
                card temp = candidates.elementAt(index).removecard();
                deck.addcard(temp);
                int collectionindex = collectiontemp.containscard(temp);
                if((candidates.elementAt(index).getnum() == 0)||
                  (candidates.elementAt(index).getnumused() == 
                  g.getplayer().getcollection().getcollection(collectionindex).numcard())){
                    candidates.remove(index);
                }
            }
        }
        
        
        
        
        if(nexttrigger != null){
            nexttrigger.trigger(target);
        }else{
            //return to currentpanel
            dialogmodel diag = cont.getgamecontrol().getcurrentdialog();
            cont.getgamecontrol().setcurrentdialog(null);
            cont.switchpanel(diag.getreturnpanel().getname());
        }
    }
    
    
    public class candidate{
        card c;
        int num;
        int numused;
    
        public candidate(card c,int n){
            this.c = c;
            num = n;
            numused = 0;
        }
        
        public card removecard(){
            num --;
            numused ++;
            return c;
        }
        
        public int getnum(){
            return num;
        }
        
        public int getnumused(){
            return numused;
        }
        
        public boolean isempty(){
            if(num > 0){
                return false;
            }
            return true;
        }
    
    }
}
