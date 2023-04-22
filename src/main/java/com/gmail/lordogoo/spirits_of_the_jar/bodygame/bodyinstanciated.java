package com.gmail.lordogoo.spirits_of_the_jar.bodygame;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import info.clearthought.layout.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.applet.*;
import java.io.*;
import java.util.*;
import com.gmail.lordogoo.spirits_of_the_jar.*;
import com.gmail.lordogoo.spirits_of_the_jar.controlers.*;
import com.gmail.lordogoo.spirits_of_the_jar.cardgame.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.battletriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.combattriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.dialogtriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.generictriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.npctriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.screentransitiontriggers.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.shoptriggers.*;
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
public class bodyinstanciated
{
    bodymodel type;

    String name;
    int speed;
    char sex;
    
    imagewithindex image;
    
    collectionmodel restrictions;
    //Vector<card> restrictions;
    //Vector<Integer> restrictednum;

   
    public bodyinstanciated(bodymodel b)
    {
        type = b;
        speed = type.getspeed();
        
        restrictions = new collectionmodel();
        //restrictions = new Vector<card>();
        //restrictednum = new Vector<Integer>();
        
        image = type.getrandomimage();
        
        //copy over restrictions
        for(int i = 0; i < type.numrestricted();i++){
            for(int j = 0; j < type.getrestrictionnum(i);j++){
                restrictions.addcard(type.getrestriction(i));
            }
        }
        
        //set random name
        Random rand = new Random();
        if(type.numrandomname() == 1 ){
            name = type.getrandomname(0);
        }else{
            if(type.numrandomname() > 1){
                int n = Math.abs(rand.nextInt()%type.numrandomname());
                name = type.getrandomname(n);
            }else{
                name = "";
            }
        }
    }

    //restriction
    public void addrestriction(card c,int num){
        for(int i = 0; i < num; i++){
            restrictions.addcard(c);
        }
    }
    
    public void addrestriction(card c,Integer num){
        for(int i = 0; i < num.intValue(); i++){
            restrictions.addcard(c);
        }
    }
    
    public int isrestricted(card c){
        for(int i = 0; i < restrictions.getnum();i++){
            if(restrictions.checkcard(i).getname().equals(c.getname())){
                return restrictions.checknum(i);
            }
        }
        return 0;
    }
    
    public Vector<card> getrestrictedcards(){
        Vector<card> cardlist = new Vector<card>();
        for(int i = 0; i < restrictions.getnum();i++){
            cardlist.add(restrictions.checkcard(i));
        }
        return cardlist;
    }
    
    public Vector<Integer> getrestrictednum(){
        Vector<Integer> numlist = new Vector<Integer>();
        for(int i = 0; i < restrictions.getnum();i++){
            numlist.add(Integer.valueOf(restrictions.checknum(i)));
        }
        return numlist;
    }
    
    public int contiansrestriction(card c){
        for(int i = 0; i < restrictions.getnum();i++){
            if(restrictions.checkcard(i).getname().equals(c.getname())){
                return i;
            }
        }
        return -1;
    }
    

    
    public String getname(){
        return name;
    }
    
    public imagewithindex getimage(){
        return image;
    }
    
    //
    public bodymodel getmodel(){
        return type;
    }
    
    //save load information
    public bodyinstanciated(BufferedReader in,texturelibrary lib,gameinfocontrol gcont) throws Exception{
        String line = in.readLine();
        System.out.println(line);
        String[] list = line.split("[|]");
        type = gcont.getbody(list[0]);
        name = list[1];
        sex = list[2].charAt(0);
        speed = Integer.parseInt(list[3]);
        image = lib.getnpcwithindex(Integer.parseInt(list[4]));
        System.out.println("'t't't't't "+list[4]+" "+image.getindex());
        
        String line2 = in.readLine();
        System.out.println(line2);
        String[] list2 = line2.split("[|]");
        int numrestrictions = Integer.parseInt(list2[1]);
        restrictions = new collectionmodel();
        for(int i = 0; i < numrestrictions; i++){
            String rline = in.readLine();
            String[] rlist = rline.split("[|]");
            card cardt = gcont.getcard(rlist[0]);
            int numcard = Integer.parseInt(rlist[1]);
            for(int j = 0; j < numcard; j++){
                restrictions.addcard(cardt);
            }
        }
    }
    
    
    public void saveinformation(PrintWriter out){
        System.out.println("{{{{{{{{{{{{}}}}}}}}}}}image index "+image.getindex());
        out.println(type.getname()+"|"+name+"|"+sex+"|"+speed+"|"+image.getindex());
        
        out.println("*restriction|"+restrictions.getnum());
        for(int i = 0; i < restrictions.getnum();i++){
             out.println(restrictions.checkcard(i).getname()+"|"+restrictions.checknum(i));
        }
    }
}
