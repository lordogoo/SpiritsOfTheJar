package com.gmail.lordogoo.spirits_of_the_jar.gameobject;

import java.util.*;

public class shopmodel
{
    
    String name;
    
    Vector<perchaseinstantiated> purchacelist;

    public shopmodel()
    {
        purchacelist = new Vector<perchaseinstantiated>();
    }
    
    public void addperchace(perchaseinstantiated p){
        purchacelist.add(p);
    }
    
    public int numperchace(){   
        return purchacelist.size();
    }
    
    public perchaseinstantiated getperchace(int i){
        return purchacelist.elementAt(i);
    }
    
    public perchaseinstantiated removeperchace(int i){
        return purchacelist.remove(i);
    }

}
