package com.gmail.lordogoo.spirits_of_the_jar.playergame; 
import java.io.Serializable;
import java.util.*;

import com.gmail.lordogoo.spirits_of_the_jar.cardgame.*;

public class deckmodel implements Serializable
{
    collectionmodel deck;
    String name;
    
    public deckmodel(String n)
    {
        name = n;
        deck = new collectionmodel();
    }
    
    public void addcard(card c){
        deck.addcard(c);
    }
    
    public card removecard(int i){
        return deck.removecard(i);
    }
    
    public collectionmodel getcollection(){
        return deck;
    }
    
    public String getname(){
        return name;
    }
    
    public void setname(String n){
        name = n;
    }
}
