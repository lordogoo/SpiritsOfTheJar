package com.gmail.lordogoo.spirits_of_the_jar.gameobject;
/**
 * Write a description of class gameobjectinstanciation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class gameobjectinstanciated extends modelobject
{
    
    int locx;
    int locy;
    
    boolean alive;
    boolean killedbyplayer;
    
    public gameobjectinstanciated(int p){
        super(p);
        alive = true;
        killedbyplayer = false; 
    }

    public gameobjectinstanciated(int p,int x,int y)
    {
        super(p);
        locx = x;
        locy = y;
        alive = true;
        killedbyplayer = false;
    }

    
    public void setlocation(int x,int y){
        locx = x;
        locy = y;
    }
    
    public int getx(){
        return locx;
    }
    
    public int gety(){
        return locy;
    }
    
    public void setalive(boolean a){
        alive = a;
    }
    
    public boolean getalive(){
        return alive;
    }
    
    public void setkilledbyplayer(boolean a){
         killedbyplayer = a;
    }
    
    public boolean getkilledbyplayer(){
        return killedbyplayer;
    }
    
    public boolean isplayer(){
        return false;
    }
    
}
