package com.gmail.lordogoo.spirits_of_the_jar.gametrigger.combattriggers;
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
public class triggeropponentplaycard extends trigger
{

    menucontrol cont;
    matchmodel match;
    moveinfo move;
    
    public triggeropponentplaycard(menucontrol c,matchmodel m)
    {
        cont = c;
        match = m;
    }

    public void trigger(gamepanel target){
        System.out.println("+++trigger begin");
        this.target = target;
        
        move = match.getopponent().getai().makemove(match.getgameboard(),match.getopponenthand());
        
        if(move != null){
            prewait();
            java.util.Timer animationthread = new java.util.Timer("animator");
            runanimation t = new runanimation(this);
            animationthread.schedule(t, 0, 40);
        }else{
            target.addtrigger(new triggeropponentendturn(cont,match));
            target.runtriggers(target);
        }
    }
    
    //TODO move might be null
    public void prewait(){
        System.out.println("**triggeropponentplaycard start");
        instansiatedcard movecard = move.getcard();
        System.out.println("bp1");
        gametilemodel movetile = move.gettile(); 
        System.out.println("bp2");
        Vector<animation> animlist = movecard.getcard().getonplaceanimation(target,this,20,new Color(0,0,0,150),movecard,movetile);
        System.out.println("bp3");
        //target.addanimation(new opponentplaycardanimation(target,this,20,new Color(0,0,0,150),movetile,movecard,animlist));
        System.out.println("bp4");
        target.addanimationdisplay();
        System.out.println("bp5");
    }
    
    public void postwait(){
        //System.out.println("do move "+move.getindex()+" "+((matchmenupanel)target).getopponenthand().size()+" "+match.getopponenthand().size());
        //TODO
        //((matchmenupanel)target).getopponenthand().remove(move.getindex());
        match.getopponenthand().remove(move.getindex());
        ((matchmenupanel)target).reloadopponenthand();
        move.domove();

        target.removeanimationdisplay();
        target.addtrigger(new triggeropponentendturn(cont,match));
        System.out.println("triggeropponentplaycard cleanup**");
    }
    
}
