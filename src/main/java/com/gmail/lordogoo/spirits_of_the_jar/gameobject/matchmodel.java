package com.gmail.lordogoo.spirits_of_the_jar.gameobject;

import java.util.*;
import com.gmail.lordogoo.spirits_of_the_jar.*;
import com.gmail.lordogoo.spirits_of_the_jar.controlers.*;
import com.gmail.lordogoo.spirits_of_the_jar.cardgame.*;
import com.gmail.lordogoo.spirits_of_the_jar.gametrigger.*;
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
/**
 * Write a description of class matchmodel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class matchmodel
{

    gameboardmodel gameboard;
    Random rand;
    
    trigger battleevent;
    Vector<deckmodel> decks;
    
    int boardx;
    int boardy;
    
    //player info
    boolean player;
    int playerpoints;
    int playerturnnum;
    deckmodel playerdeck;
    Vector<instansiatedcard> instanciatedplayerdeck;
    Vector<instansiatedcard> playerhand;
    Vector<instansiatedcard> playerdiscard;
    
    //opponent info
    opponentmodel opponent;
    int opponentpoints;
    int opponentturnnum;
    deckmodel opponentdeck;
    Vector<instansiatedcard> instanciatedopponentdeck;
    Vector<instansiatedcard> oponenthand;
    Vector<instansiatedcard> oponentdiscard;
    
    //match info
    rulevictorycondition victory;
    boolean playersturn;
    
    //end triggers
    trigger victorytrigger;
    trigger defeattrigger;
    
    public matchmodel(int x,int y,trigger event,Vector<deckmodel> d,cardai ai,rulevictorycondition v)
    {
        rand = new Random();
        decks = d;
        battleevent = event;
        boardx = x;
        boardy = y;
        playerpoints = 0;
        playerturnnum = 1;
        opponentpoints = 0;
        opponentturnnum = 1;
        opponent = new opponentmodel(ai);
        victory = v;
    }
    
    
    //match flags
    public void setwhoseturn(boolean turn){
        playersturn = turn;
    }
    
    public boolean whoseturn(){
        return playersturn;
    }
    
    //point functions
    public void giveplayerpoints(int points){
        playerpoints+=points;
    }
    
    public void giveopponentpoints(int points){
        opponentpoints+=points;
    }
    
    public int getopponentpoints(){
        return opponentpoints;
    }
    
    public int getplayerpoints(){
        return playerpoints;
    }
    
    
    public int getplayerturnnum(){
        return playerturnnum;
    }
    
    public void incplayerturnnum(){
        playerturnnum++;
    }
    
    public int getopponentturnnum(){
        return opponentturnnum;
    }
    
    public void incopponentturnnum(){
        opponentturnnum++;
    }
    
    //game board functions
    public int getgameboardy(){
        return boardx;
    }
    
    public int getgameboardx(){
        return boardy;
    }
    
    public gameboardmodel getgameboard(){
        return gameboard;
    }
    
    //get player info functions
    public Vector<instansiatedcard> getplayerhand(){
        return playerhand; 
    }
    
    public Vector<instansiatedcard> getplayerdeck(){
        return instanciatedplayerdeck;
    }
    
    public void addplayerhand(instansiatedcard c){
        playerhand.add(c);
    }
    
    public void removeplayerhand(int i){
        playerhand.remove(i);
    }
    
    public int numplayercardsdeck(){
        return  instanciatedplayerdeck.size();
    }
    
    //get oppomemt info fucntions
    public Vector<instansiatedcard> getopponenthand(){
        return oponenthand;
    }
    
    public Vector<instansiatedcard> getopponentdeck(){
        return instanciatedopponentdeck;
    }
    
    
    public int numopponentcardsdeck(){
        return  instanciatedopponentdeck.size();
    }
    
    public opponentmodel getopponent(){
        return opponent;
    }
    
    //victory functions
    public rulevictorycondition getvictory(){
        return victory;
    }
    
    //trigger functions
    public void setvictorytrigger(trigger v){
        victorytrigger = v;
    }
    
    public trigger getvictorytrigger(){
        return victorytrigger;
    }
    
    public void setdefeattrigger(trigger d){
        defeattrigger = d;
    }
    
    public trigger getdefeattrigger(){
        return defeattrigger;
    }
    /************************
     * start match
     ************************/
    public void startmatch(deckmodel d){
        playerdeck = d;
        
        instanciatedplayerdeck = new Vector<instansiatedcard>();
        instanciatedopponentdeck = new Vector<instansiatedcard>();
        playerdiscard = new Vector<instansiatedcard>();
        oponentdiscard = new Vector<instansiatedcard>();
        
        //select deck
        Random r = new Random();
        if(decks.size() == 1){
           opponentdeck = decks.elementAt(0);
        }else{
            int index = Math.abs(r.nextInt()%decks.size());
            opponentdeck = decks.elementAt(index);
        }
        
        //create match info
        playerhand = new Vector<instansiatedcard>();
        oponenthand = new Vector<instansiatedcard>();
        instansiatedeck(true,playerdeck,instanciatedplayerdeck);
        instansiatedeck(false,opponentdeck,instanciatedopponentdeck);  
        
        gameboard = new gameboardmodel(this,boardx,boardy);
    }
    

    
    
    /*********************************
     * 
     ********************************/
    public void playermakemove(){
    }
    
    public void opponentmakemove(){
    }
    
    public void instansiatedeck(boolean p,deckmodel deck,Vector<instansiatedcard> uninstansiateddeck){
        for(int i = 0; i < deck.getcollection().getnum();i++){
            for(int j = 0; j < deck.getcollection().getcollection(i).numcard();j++){
                if(uninstansiateddeck.size()==0){
                    uninstansiateddeck.add(new instansiatedcard(deck.getcollection().getcollection(i).checkcard(),p));
                }else{
                    int index = Math.abs(rand.nextInt()%(uninstansiateddeck.size()+1));
                    instansiatedcard cardtemp = new instansiatedcard(deck.getcollection().getcollection(i).checkcard(),p);
                    uninstansiateddeck.insertElementAt(cardtemp,index);        
                }
            }
        }
    }    
    
    public Vector<instansiatedcard> drawcards(int num,Vector<instansiatedcard> deck){
        Vector<instansiatedcard> cardlist = new Vector<instansiatedcard>();
        for(int i = 0; i < num;i++){
            System.out.println("drawcard "+deck.size());
            instansiatedcard cardtemp = deck.remove(0);
            cardlist.add(cardtemp);
        }
        return cardlist;
    }
    
    public void givehandcards(Vector<instansiatedcard> hand,Vector<instansiatedcard> cards){
        for(int i = 0; i < cards.size();i++){
            hand.add(cards.elementAt(i));
        }
    }
    
    public void givehandcards(Vector<instansiatedcard> hand,instansiatedcard card){
            hand.add(card);
    }
    
    public deckmodel getrandomdeck(Vector<deckmodel> decklist){
        Random r = new Random();
        if(decklist.size() == 0){
            return null;
        }else if(decklist.size() == 1){
            return decklist.elementAt(0);
        }else{
            int index = Math.abs(r.nextInt()%decklist.size());
            return decklist.elementAt(index);
        }
    }
    
}
