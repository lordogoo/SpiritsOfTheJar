package com.gmail.lordogoo.spirits_of_the_jar.events;
import java.awt.image.*;
import com.gmail.lordogoo.spirits_of_the_jar.controlers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gameobject.*;
import com.gmail.lordogoo.spirits_of_the_jar.gameinterface.*;
public class eventmodel
{
    
    menucontrol cont;
    //initial
    eventmodel next;
    //runtime info
    gamepanel returnpanel;
    gameobjectinstanciated target;
    boolean moveaway;
    
    public eventmodel(menucontrol m)
    {
        cont = m;
         moveaway = false;
    }
    
    public void addnextevent(eventmodel e){
        next = e;
    }
    
    public eventmodel getnextevent(){
        return next;
    }
    
    public gamepanel getreturn(){
        return returnpanel;
    }
    
    public gameobjectinstanciated gettarget(){
        return target;
    }
    

    
    public String gettext(){
        return "";
    }
    
    public BufferedImage getimage(){
        return null;
    }
    
    public int getcost(){
        return 0;
    }
    
    public void returntopanel(){
            //cont.switchpanel(returnpanel);
            cont.addevent(null);
    }
    
    public void setmoveaway(boolean t){
        moveaway = t;
    }
    
    public boolean getmoveaway(){
        return moveaway;
    }
    

}
