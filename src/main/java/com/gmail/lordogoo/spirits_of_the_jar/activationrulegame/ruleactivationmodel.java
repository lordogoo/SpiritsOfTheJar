package com.gmail.lordogoo.spirits_of_the_jar.activationrulegame;
import java.awt.*;
import com.gmail.lordogoo.spirits_of_the_jar.*;
import com.gmail.lordogoo.spirits_of_the_jar.controlers.*;
import com.gmail.lordogoo.spirits_of_the_jar.cardgame.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.*;
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
 * Write a description of class activationrulemodel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ruleactivationmodel extends rulemodel
{

    animation anim;

    public ruleactivationmodel()
    {
    }
    
    public void execute(matchmodel m,instansiatedcard c,gametilemodel tile){
        System.out.println("!@#$$%%^*execute*&^%$#@!");
    }
    
    public void addanimation(animation a){
        anim = a;
    }
    
    public animation getanimation(){
        return anim;
    }
    
    public animation createanimation(gamepanel g,trigger t,int durration,Color b,instansiatedcard c,gametilemodel tile){
        return null;
    }
    
    public String gettext(){
        return "";
    }
    
}
