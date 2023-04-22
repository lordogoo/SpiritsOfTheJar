package com.gmail.lordogoo.spirits_of_the_jar.gameobject;
import java.util.*;
/**
 * Write a description of class rpsitem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class rpsitem
{

    Random r;

    //rps incicates what is picked in a rps(rock paper sizers) match
    //0 = rock
    //1 = paper
    //2 = sizors
    int rps;
    
    
    public rpsitem()
    {
        r = new Random(System.nanoTime());
        rps = Math.abs(r.nextInt()%3);
    }

    public int getvalue(){
        return rps;
    }
    
    public boolean isbeeten(rpsitem r){
        System.out.println("rps data "+rps+" "+r.getvalue());
        if      ((rps == 0)&&(r.getvalue() == 1)){
            return true;
        }else if((rps == 1)&&(r.getvalue() == 2)){
            return true;
        }else if((rps == 2)&&(r.getvalue() == 0)){
            return true;
        }
        return false;
    
    }

}
