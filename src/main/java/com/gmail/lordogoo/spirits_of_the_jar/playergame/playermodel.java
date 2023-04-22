package com.gmail.lordogoo.spirits_of_the_jar.playergame; 

import javax.swing.event.*;
import info.clearthought.layout.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import info.clearthought.layout.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
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
public class playermodel
{

    String name;
    int money;
    boolean firstplay;
    
    collectionmodel collection;
    Vector<deckmodel> decks;
    Vector<bodyinstanciated> bodylist;

    
    gamecontrol game;
    bodyinstanciated body;
    
    Vector<card> newcards;
    
    public playermodel(String n)
    {
        name = n;
        money = 0; 
        firstplay = true;
        newcards = new Vector<card>();
        
        decks = new Vector<deckmodel>();
        collection = new collectionmodel();
        bodylist = new Vector<bodyinstanciated>();
    }
    
    public void addgame(gamecontrol g){
        game = g;
    }
    
    public gamecontrol getgame(){
        return game;
    }
    
    public Vector<card> getnewcards(){
        return newcards;
    }
    
    public String getname(){
        return name;
    }
    
    //deck functions
    public int numdeck(){
        return decks.size();
    }
    
    public deckmodel getdeck(int i){
        return decks.elementAt(i);
    }
    
    public void adddeck(deckmodel deck){
        decks.add(deck);
    
    }
    
    public void removedeck(int i){
        decks.removeElementAt(i);
    }
    
    public void renamedeck(int i, String name){
        decks.elementAt(i).setname(name);
    }
    
    //card set/get
    public void givecard(card c){
        newcards.add(c);
        collection.addcard(c);
    }
    
    public void clearnewcards(){
        newcards.clear();
    }
    
    public collectionmodel getcollection(){
        return collection;
    }

    //money
    public void addmoney(int m){
        money += m;
    }
    
    public int getmoney(){
        return money;
    }
    
    //body
    public void setcurrentbody(bodyinstanciated b){
        body = b;
    }
    
    public bodyinstanciated getcurrentbody(){
        return body;
    }
    
    public void addbody(bodyinstanciated b){
        bodylist.add(b);
    }
    
    public bodyinstanciated getbody(int i ){
        return bodylist.elementAt(i);
    }
    
    public int numbody(){
        return bodylist.size();
    }
    
    //deck create functions
    public Vector<card> getpiclist(){
        Vector<card> piclist = new Vector<card>();
        for(int i = 0; i < collection.getnum(); i++){
            piclist.add(collection.checkcard(i));
        }
        return piclist;
    }
    
    public Vector<Integer> getmaxnum(){    
        Vector<Integer> maxnum = new Vector<Integer>();
        for(int i = 0; i < collection.getnum(); i++){
            maxnum.add(Integer.valueOf(collection.getcollection(i).numcard()));
        }
        return maxnum;
    }
    
    public void setfirstplay(boolean b){
        firstplay = b;
    }
    
    public boolean getfirstplay(){
        return firstplay;
    }
    
    //save load functions
    public playermodel(BufferedReader in,menucontrol m,texturelibrary lib,gameinfocontrol gcont) throws Exception{
        name = in.readLine();
        System.out.println("load info "+name);
        money = Integer.parseInt(in.readLine());
        firstplay = Boolean.parseBoolean(in.readLine());
        newcards = new Vector<card>();
        
        //read collection
        collection = new collectionmodel();
        if(in.readLine().equals("*collection")){
            int numcards = Integer.parseInt(in.readLine());
            for(int i = 0; i < numcards; i++){
                String line = in.readLine();
                card cardt = gcont.getcard(line.substring(0,line.indexOf("|")));
                int numcard = Integer.parseInt(line.substring(line.indexOf("|")+1,line.length()));
                for(int j = 0; j < numcard; j++){
                    collection.addcard(cardt);
                }
            }
        }
        
        //read decks
        System.out.println("load decks");
        decks = new Vector<deckmodel>(); 
        if(in.readLine().equals("*decks")){
            int numdecks = Integer.parseInt(in.readLine());
            System.out.println("num decks "+numdecks);
            for(int i = 0; i < numdecks;i++){
                String line = in.readLine();
                String[] list = line.split("[|]");
                String deckname = list[0];
                int numcards = Integer.parseInt(list[1]);
                deckmodel deck = new deckmodel(deckname);
                System.out.println("deck "+deckname+" "+numcards);
                for(int j = 0; j < numcards; j++){
                    String deckline = in.readLine();
                    String[] decklist = deckline.split("[|]");
                    card cardtemp =  gcont.getcard(decklist[0]);
                    int cardtempnum = Integer.parseInt(decklist[1]);
                    System.out.println("card "+decklist[0]+" "+cardtempnum);
                    for(int k = 0; k < cardtempnum;k++){
                        deck.addcard(cardtemp);
                    }
                }
                decks.add(deck);
            }
        }
        System.out.println("decks loaded");
        
        //read bodies
        System.out.println("load bodies");
        bodylist = new Vector<bodyinstanciated>();
        if(in.readLine().equals("*bodies")){
            int numbodies = Integer.parseInt(in.readLine());
            System.out.println("bodies "+numbodies);
            for(int i = 0; i < numbodies;i++){
                bodylist.add(new bodyinstanciated(in,lib,gcont));
            }
        }
        System.out.println("bodies load");
        
        //read game
        if(in.readLine().equals("*game")){
            game = new gamecontrol(in,m,lib,gcont,this);
        }

    }
    
    
    
    public void saveinformation(PrintWriter out)
    {
        out.println(name);
        out.println(money);
        out.println(firstplay);
        //parse out collection
        out.println("*collection");
        out.println(collection.getnum());
        for(int i = 0; i < collection.getnum();i++){
             out.println(collection.checkcard(i).getname()+"|"+collection.checknum(i));
        }
        
        //parse out decks
        out.println("*decks");
        out.println(decks.size());
        for(int i = 0; i < decks.size();i++){
            out.println(decks.elementAt(i).getname()+"|"+decks.elementAt(i).getcollection().getnum());
            for(int j = 0; j < decks.elementAt(i).getcollection().getnum() ;j++){
                out.println(decks.elementAt(i).getcollection().checkcard(j).getname()+"|"+decks.elementAt(i).getcollection().checknum(j));
            }
        }
        
        //parse out bodies
        out.println("*bodies");
        out.println(bodylist.size());
        for(int i = 0; i < bodylist.size();i++){
            bodylist.elementAt(i).saveinformation(out);
        }
        
        //parse out game
        if(game != null){
            out.println("*game");
            game.saveinformation(out);
        }else{
            out.println("null");
        }
    }
}
