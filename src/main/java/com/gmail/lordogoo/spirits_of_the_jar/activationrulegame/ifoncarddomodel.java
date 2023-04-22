package com.gmail.lordogoo.spirits_of_the_jar.activationrulegame;
import java.awt.*;
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
 * Write a description of class ifoncardgivepointsmodel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ifoncarddomodel extends ruleactivationmodel
{

    ruleconditionalmodel iftrue;
    ruleactivationmodel donext;

    public ifoncarddomodel(card c,ruleactivationmodel d)
    {
        iftrue = new rulecardequalsmodel(c);
        donext = d;
    }
    
    public void execute(matchmodel m,instansiatedcard c,gametilemodel tile){
        //System.out.println("%%%%%%%%%%%%%%%%%ifoncarddomodel execute");
        if(c.getdepth() != -1){
            if(c.getdepth()!=0){
                if(iftrue.condition(tile.getcard(c.getdepth()-1).getcard())){
                    donext.execute(m,c,tile);
                }
            }
        }else{
            if(tile.numcard()!=0){
                if(iftrue.condition(tile.getcard(tile.numcard()-1).getcard())){
                    donext.execute(m,c,tile);
                }
            }
        }
    }
    
    public animation createanimation(gamepanel g,trigger t,int durration,Color b,instansiatedcard c,gametilemodel tile){
        if(c.getdepth() != -1){
            if(c.getdepth()!=0){
                if(iftrue.condition(tile.getcard(c.getdepth()-1).getcard())){
                    return donext.createanimation(g,t,durration,b,c,tile);
                }
            }
        }else{
            if(tile.numcard()!=0){
                if(iftrue.condition(tile.getcard(tile.numcard()-1).getcard())){
                    return donext.createanimation(g,t,durration,b,c,tile);
                }
            }
        }
        return null;
    }
    
    public String gettext(){
        return "if placed on the card "+iftrue.gettext()+" then "+donext.gettext();
    }
    
    
    
}
