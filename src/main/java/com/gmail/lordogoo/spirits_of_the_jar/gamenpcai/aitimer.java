package com.gmail.lordogoo.spirits_of_the_jar.gamenpcai;
/**
 * Write a description of class aitimer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class aitimer
{

    int time;
    int durration;

    npcaiinstanciated ai;
    
    public aitimer(int d,npcaiinstanciated a)
    {
        time = 0;
        durration = d;
        ai = a;
    }

    public void update(){
        if(time < durration){
            time++;
        }else{
        }
    }
}
