package com.gmail.lordogoo.spirits_of_the_jar.cardgame;
import java.util.*;
/**
 * Write a description of class instansiatedcard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class instansiatedcard
{

    card c;
    boolean owner;
    
    
    //action memory data
    int turnsinplay;
    int pointsgenerated;
    int pointsgeneratedthisturn;
    
    
    //location data
    int xlocation;
    int ylocation;
    int depth;
    
    public instansiatedcard(card c,boolean o)
    {
        owner = o;
        this.c = c;
        
        turnsinplay = 0;
        pointsgenerated = 0;
        pointsgeneratedthisturn = 0;
        xlocation = -1;
        ylocation = -1;
        depth = -1;
    }
    
    public card getcard(){
        return c;
    }
    
    public card getmodel(){
        return c;
    }
    
    public boolean getowner(){
        return owner;
    }
    
    public void setlocation(int x, int y,int z){
        xlocation = x;
        ylocation = y;
        depth = z;
    }
    
    public int getdepth(){
        return depth;
    }
    
    public int getxlocation(){
        return xlocation;
    }

    public int getylocation(){
        return ylocation;
    }
}
