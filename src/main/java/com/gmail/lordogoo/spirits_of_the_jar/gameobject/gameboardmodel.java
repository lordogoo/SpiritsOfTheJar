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

public class gameboardmodel
{
    
    matchmodel match;
    int boardxsize;
    int boardysize;
    gametilemodel[][] gameboard;
    
    
    public gameboardmodel(matchmodel m,int x,int y)
    {
        boardxsize = x;
        boardysize = y;
        match = m;
        gameboard = new gametilemodel[boardxsize][boardysize];
        for(int i = 0; i < boardxsize;i++){
            for(int j = 0; j < boardysize;j++){
                gameboard[i][j] = new gametilemodel(this,i,j);
            }
        }
    }
    
    public int getxsize(){
        return boardxsize;
    }
    
    public int getysize(){
        return boardysize;
    }
    
    public gametilemodel gettile(int x,int y){
        return gameboard[x][y];
    }
    
    public void playcard(int x,int y,card c){
        //gameboard[x][y].add(c);
    }
    
    public matchmodel getmatch(){
        return match;
    }
    
}
