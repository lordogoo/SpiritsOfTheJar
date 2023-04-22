package com.gmail.lordogoo.spirits_of_the_jar.gameobject;

import java.util.*;
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

public class gametilemodel
{
    Vector<instansiatedcard> cardlist;
    gameboardmodel boardref;
    int xloc;
    int yloc;
    boolean locked;
    
    public gametilemodel(gameboardmodel b,int x,int y)
    {
        cardlist = new Vector<instansiatedcard>();
        boardref = b;
        xloc = x;
        yloc = y;
        locked = false;
    }
    
    public void addcard(instansiatedcard c){
        cardlist.add(c);
    }
    
    public void playcard(instansiatedcard c){
        cardlist.add(c);
        card temp = c.getcard();
        c.setlocation(xloc,yloc,cardlist.size()-1);
        temp.onplace(boardref.getmatch(),c,this);
    }
    
    public int numcard(){
        return cardlist.size();
    }
    
    public instansiatedcard getcard(int i){
        return cardlist.elementAt(i);
    }
    
    public gameboardmodel getgameboard(){
        return boardref;
    }
    
    public int getxlocation(){
        return xloc;
    }
    
    public int getylocation(){
        return yloc;
    }

}
