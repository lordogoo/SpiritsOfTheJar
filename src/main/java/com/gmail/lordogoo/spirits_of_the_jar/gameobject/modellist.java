package com.gmail.lordogoo.spirits_of_the_jar.gameobject;
/**
 * Write a description of class modellist here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class modellist
{

    int listsize;
    
    public modellist()
    {
        listsize = 0;
    }
    
    public modellist(int i){
        listsize = i;
    }

    public int checknum(){
        return listsize;
    }
    
    public int getnum(){
        listsize++;
        return (listsize - 1);
    }
  
}
