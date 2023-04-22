package com.gmail.lordogoo.spirits_of_the_jar.badguygame;
import java.util.*;

public class roumorlistmodel
{


    Vector<roumormodel> list;
    
    public roumorlistmodel()
    {
        list = new Vector<roumormodel>();   
    }
    
    
    public void addroumor(roumormodel r,int i){
        list.add(i,r);
    }
    
    public void addroumor(roumormodel r){
        list.add(r.getslotnum(),r);
    }
    
    public roumormodel getroumor(int i){
        return list.elementAt(i);
    }
    
    public int size(){
        return list.size();
    }
    
    
}
