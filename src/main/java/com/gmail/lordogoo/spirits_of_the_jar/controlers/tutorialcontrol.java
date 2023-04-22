package com.gmail.lordogoo.spirits_of_the_jar.controlers;
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
/**
 * Write a description of class tutorialcontrol here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tutorialcontrol
{
    menucontrol cont;
    boolean active;
    Vector<tutorialpanelcontrol> panelcontrol;
    
            
        //nexus menu
        //courage
        String npc1name = "courage";
        boolean npc1vh = false;
        boolean npc1side = true;
        int npc1location = 200;
        
        //temperance
        String npc2name = "temperance";
        boolean npc2vh = false;
        boolean npc2side = false;
        int npc2location = 200;
        
        //prudence
        String npc3name = "prudence";
        boolean npc3vh = true;
        boolean npc3side = true;
        int npc3location = 200;
        
        //justice
        String npc4name = "justice";
        boolean npc4vh = true;
        boolean npc4side = true;
        int npc4location = 600;
    
    
    
    
    

    public tutorialcontrol(menucontrol m,texturelibrary lib)
    {
        cont = m;
        active = true;
        
        panelcontrol = new Vector<tutorialpanelcontrol>();
        
        //tutorial components
        Random r = new Random();
        boolean vh = r.nextBoolean();
        boolean opp = r.nextBoolean();
        int loc;
        if(vh == true){
            loc = Math.abs(r.nextInt()%m.getyresolution());
        }else{
            loc = Math.abs(r.nextInt()%m.getxresolution());
        }
        
        
        tutorialpanelcontrol nexus = new tutorialpanelcontrol("nexuspanel");
        panelcontrol.add(nexus);
        tutorialpanelcontrol play = new tutorialpanelcontrol("playpanel");
        panelcontrol.add(play);
        tutorialpanelcontrol deck = new tutorialpanelcontrol("newdeckpanel");    
        panelcontrol.add(deck);
        tutorialpanelcontrol card = new tutorialpanelcontrol("deckpanel");
        panelcontrol.add(card);
        createinfo(lib);
    }
    
    public void createinfo(texturelibrary lib){
        
        //tutorial phase 1: 
        //-information about the nexsus 
        //-introduce the meaning behind the bodies and possesion
        //-get the player to select the tutorial world and select the manikin body and enter the tutorial world.
        String nexusline1 = "Hello. Welcome to the nexus.";
        String nexusline2 = "we are the nexus automated tutorial system";
        String nexusline3 = "Each of us non sentient avitars programed to help those who come here.";
        //String nexusline4 = "Are you the new keeper of the jar?";
        String nexusline5 = "well the jar has been broken and all the evil spirits it contained are released.";
        String nexusline6 = "They flead to the moral worlds. looks like they are causing some trouble.";
        String nexusline7 = "Its all your fault for dropping the jar.";
        String nexusline8 = "that means its your job to get them back if you dont want to get stuck in the jar yourself";
        String nexusline9 = "dont worry we are incapable of informing the spirit councel of your mistake so you have some time.";
        String nexusline10 = "your going to need to go to each mortal world and get the evil spirits back by defeating them";
        String nexusline11 = "the mortal worlds are very strange when compaired to the spirit realm.";
        String nexusline12 = "theres a training simulation that will teach you what to expect contained here in the nexus";
        String nexusline13 = "The first step to enter the simulation is to select a world. Please click on the tutorial world.";
        String nexusline14 = "For a spirit to enter a world it needs the body of a desperate mortal."; 
        String nexusline15 = "Peticlarly a mortal that is praying for a mirical and willing to give up anything."; 
        String nexusline16 = "Any mortal like that will appear on the right."; 
        String nexusline17 = "The simulation world is capable of creating manikins that simulate desperate mortals for the perpouses of training new comers to the nexsus."; 
        String nexusline18 = "one of these manikins has apeared on the left and if you click on it you can use it to enter the tutorial world. please click on it now.";
        String nexusline19 = "now the enter world button at the bottom is active. click it to enter the world.";
        String nexusline20 = "see you on the other side.";
        
        
        tutorialpanelcontrol nexus = getcontrol("nexuspanel");
        gamepanel nexuspanel = cont.getpanel("nexuspanel");
        spawngroup(this,lib,nexus,"nexuspanel");
        /*
        createfourwayspeech(this,nexuspanel,lib,nexus,npc1name,nexusline1,1);
        createfourwayspeech(this,nexuspanel,lib,nexus,npc4name,nexusline2,4);
        createfourwayspeech(this,nexuspanel,lib,nexus,npc3name,nexusline3,3);
        createfourwayspeech(this,nexuspanel,lib,nexus,npc2name,nexusline5,2);
        createfourwayspeech(this,nexuspanel,lib,nexus,npc3name,nexusline6,3);
        createfourwayspeech(this,nexuspanel,lib,nexus,npc1name,nexusline7,1);
        createfourwayspeech(this,nexuspanel,lib,nexus,npc4name,nexusline8,4);
        createfourwayspeech(this,nexuspanel,lib,nexus,npc2name,nexusline9,2);
        createfourwayspeech(this,nexuspanel,lib,nexus,npc3name,nexusline10,3);
        createfourwayspeech(this,nexuspanel,lib,nexus,npc1name,nexusline11,1);
        createfourwayspeech(this,nexuspanel,lib,nexus,npc3name,nexusline12,3);
        */
        createfourwaylistspeech(this,nexuspanel,lib,nexus,npc3name,nexusline13,3,0,0);
        /*
        createfourwayspeech(this,nexuspanel,lib,nexus,npc3name,nexusline14,3);
        createfourwayspeech(this,nexuspanel,lib,nexus,npc3name,nexusline15,3);
        createfourwayspeech(this,nexuspanel,lib,nexus,npc3name,nexusline16,3);
        createfourwayspeech(this,nexuspanel,lib,nexus,npc3name,nexusline17,3);
        */
        createfourwaylistspeech(this,nexuspanel,lib,nexus,npc3name,nexusline18,3,1,0);
        createfourwayspeech(this,nexuspanel,lib,nexus,npc3name,nexusline19,3);
        createfourwaybuttonspeech(this,nexuspanel,lib,nexus,npc3name,nexusline13,3,(JButton)nexuspanel.getcomponent(2));
        createfourwayspeech(this,cont.getpanel("nexuspanel"),lib,nexus,npc3name,nexusline20,3);
        //TODO ask user to press button. make sure that the animations are in order afterword
        endgroup(this,lib,nexus,"nexuspanel");
        
        triggerscreentransitionbegining nexsustrans = new triggerscreentransitionbegining(cont.getpanel("nexuspanel").getcomponentlist(),"playpanel");
        tutorialtrigger nexsustranscontainer = new tutorialtrigger(nexsustrans,this,cont.getpanel("nexuspanel"),nexus.size());
        nexsustrans.addtutorialtrigger(nexsustranscontainer);
        nexus.addtrigger(nexsustranscontainer);  
        
        //tutorial phase 2:
        //-introduce game in a general sence. explain that this is a card game.
        //-give the player their initial cards
        //-get them to click on the deck button.
        String playline1 = "You made it.";
        String playline2 = "Not all spirits survive their first possesion.";
        String playline3 = "Most of them get absorbed by their host body.";
        String playline4 = "Moving on. This is the tutorial world and in this place an interface has been provided so you can succede to find the evil spirits you released";
        String playline5 = "Its important to tell you now that to capture these spirits you are going to have to learn to battle in the real of memories.";
        String playline6 = "You will have to collect the memories of mortals to use as a weapon to bind anything threatening that comes your way.";
        String playline7 = "These battles will appear as a card game. We will walk you through the process of getting cards and defeating your enemys.";
        String playline8 = "First of all we have some memory cards that will start you out on your journy.";
        String playline9 = "Here they are";
        String playline10 = "To continue on we are going to have to build a deck from these cards.";
        String playline11 = "Please click the deck button on the upper right hand corner to continue.";
        
        tutorialpanelcontrol play =  getcontrol("playpanel");
        gamepanel playpanel = cont.getpanel("playpanel");
        
        nontutorialtrigger(this,play,playpanel,new tutorialtriggeraddplayertoworld(cont));
        spawngroup(this,lib,play,"playpanel");
        createfourwayspeech(this,playpanel,lib,play,npc1name,playline1,1);
        createfourwayspeech(this,playpanel,lib,play,npc1name,playline2,1);
        createfourwayspeech(this,playpanel,lib,play,npc1name,playline3,1);
        createfourwayspeech(this,playpanel,lib,play,npc1name,playline4,1);
        createfourwayspeech(this,playpanel,lib,play,npc1name,playline5,1);
        createfourwayspeech(this,playpanel,lib,play,npc1name,playline6,1);
        createfourwayspeech(this,playpanel,lib,play,npc1name,playline7,1);
        createfourwayspeech(this,playpanel,lib,play,npc1name,playline8,1);
        createfourwayspeech(this,playpanel,lib,play,npc1name,playline9,1);
        //give cards animation
        
        tutorialanimationgivecards playcards = new tutorialanimationgivecards(playpanel,lib.gettexture(6).getimage(),3,30,cont);
        tutorialtrigger playcardscontainer = new tutorialtrigger(playcards,this,playpanel,play.size());
        playcards.addtutorialtrigger(playcardscontainer);
        play.addtrigger(playcardscontainer);  
        
        createfourwayspeech(this,playpanel,lib,play,npc1name,playline10,1);
        createfourwaybuttonspeech(this,playpanel,lib,play,npc3name,playline11,3,(JButton)playpanel.getcomponent(3));
        endgroup(this,lib,play,"playpanel");
        
        triggerscreentransitionbegining playtrans = new triggerscreentransitionbegining(playpanel.getcomponentlist(),"newdeckpanel");
        tutorialtrigger playtranscontainer = new tutorialtrigger(playtrans,this,playpanel,play.size());
        playtrans.addtutorialtrigger(playtranscontainer);
        play.addtrigger(playtranscontainer);  
        
        //TODO introduce what the game is. a card game
        //TODO give the player initial cards and money
        //TODO introduce the play panel
        //TODO get the player to click the deck button.
        
        //deck manager menu
        String deckline1 = "this is the deck manager.";
        String deckline2 = "click the new deck button to create a new deck.";
        //String 
        /*
        //todo need to create a tutorial for making new decks
        tutorialpanelcontrol deck = getcontrol("newdeckpanel");    
        gamepanel newdeckpanel = cont.getpanel("newdeckpanel");
        spawngroup(this,lib,deck,"newdeckpanel");
        createfourwayspeech(this,newdeckpanel,lib,deck,npc1name,deckline1,1);
        createfourwaybuttonspeech(this,newdeckpanel,lib,deck,npc3name,deckline2,2,(JButton)newdeckpanel.getcomponent(0));
        createfourwaybuttonspeech(this,newdeckpanel,lib,deck,npc3name,deckline2,2,(JButton)newdeckpanel.getpopup(0).getcomponent(2));
        endgroup(this,lib,deck,"newdeckpanel");
        
        triggerscreentransitionbegining newdecktrans = new triggerscreentransitionbegining(newdeckpanel.getcomponentlist(),"deckpanel");
        tutorialtrigger newdecktranscontainer = new tutorialtrigger(newdecktrans,this,newdeckpanel,deck.size());
        newdecktrans.addtutorialtrigger(newdecktranscontainer);
        deck.addtrigger(newdecktranscontainer);  
        
        
        
        //deck creater menu
        tutorialpanelcontrol card = getcontrol("deckpanel");
        
        spawngroup(this,lib,card,"deckpanel");
        createfourwayspeech(this,cont.getpanel("deckpanel"),lib,card,npc1name,deckline1,1);
        createfourwaylistspeech(this,cont.getpanel("deckpanel"),lib,card,npc3name,nexusline18,3,1,0);
        endgroup(this,lib,card,"deckpanel");
        

        //back to deck menu
        spawngroup(this,lib,deck,"newdeckpanel");
        createfourwaybuttonspeech(this,newdeckpanel,lib,deck,npc3name,deckline2,2,(JButton)newdeckpanel.getcomponent(5));
        endgroup(this,lib,deck,"newdeckpanel");
        */
        
        //back to play pannel
        
        
        
        //pre combat manager menu
        //TODO introduce this panel
        //TODO get the player to create a new deck
        

        
        //deck 
        
        //play panel 2
        //TODO introduce movement
        //TODO get the player to move to the nearest enemey
        //TODO get the player to initiate combat
        
        
        //TODO get cthe player to go to the closest town
        //TODO get the player to head to the shop
        //TODO get the player to buy a card
        
        //shop
        
        
        
        
    }
    
    
    
    public tutorialpanelcontrol getcontrol(String name){
        for(int i = 0; i < panelcontrol.size();i++){
            if(panelcontrol.elementAt(i).ispanel(name)){
                return panelcontrol.elementAt(i);
            }
        }
        return null;
    }
    
    
    public void nontutorialtrigger(tutorialcontrol tc,tutorialpanelcontrol tpc,gamepanel gp,trigger t){
        tutorialtrigger triggercontainer = new tutorialtrigger(t,tc,gp,tpc.size());
        t.addtutorialtrigger(triggercontainer);
        tpc.addtrigger(triggercontainer);  
    }
    
    
    public void spawngroup(tutorialcontrol tc,texturelibrary lib,tutorialpanelcontrol tpc,String panel){
        tutorialtriggerspawngroup group = new tutorialtriggerspawngroup(cont.getpanel(panel));
        group.addentity(lib.getnpc(0).getimage(),npc1vh,npc1side,npc1location);
        group.addentity(lib.getnpc(1).getimage(),npc2vh,npc2side,npc2location);
        group.addentity(lib.getnpc(2).getimage(),npc3vh,npc3side,npc3location);
        group.addentity(lib.getnpc(3).getimage(),npc4vh,npc4side,npc4location);
        tutorialtrigger groupcontainer = new tutorialtrigger(group,tc,cont.getpanel(panel),0);
        tpc.addtrigger(groupcontainer);
    }
    
    public void endgroup(tutorialcontrol tc,texturelibrary lib,tutorialpanelcontrol tpc,String panel){
        tutorialtriggerendgroup group = new tutorialtriggerendgroup(cont.getpanel(panel));
        group.addentity(lib.getnpc(0).getimage(),npc1vh,npc1side,npc1location);
        group.addentity(lib.getnpc(1).getimage(),npc2vh,npc2side,npc2location);
        group.addentity(lib.getnpc(2).getimage(),npc3vh,npc3side,npc3location);
        group.addentity(lib.getnpc(3).getimage(),npc4vh,npc4side,npc4location);
        tutorialtrigger groupcontainer = new tutorialtrigger(group,tc,cont.getpanel(panel),0);
        tpc.addtrigger(groupcontainer);
    }
    
    
    
    
    public void createfourwayspeech(tutorialcontrol tc,gamepanel gp,texturelibrary lib,tutorialpanelcontrol tpc,String speeker,String message,int imp){
        
        tutorialtriggergroupspeech speech = new tutorialtriggergroupspeech(gp,speeker,message,lib.gettexture(7).getimage());
        speech.addentity(lib.getnpc(0).getimage(),npc1vh,npc1side,npc1location);
        speech.addentity(lib.getnpc(1).getimage(),npc2vh,npc2side,npc2location);
        speech.addentity(lib.getnpc(2).getimage(),npc3vh,npc3side,npc3location);
        speech.addentity(lib.getnpc(3).getimage(),npc4vh,npc4side,npc4location);
        speech.setimportantentity(imp-1);
        tutorialtrigger speechcontainer = new tutorialtrigger(speech,tc,gp,tpc.size());
        speech.addtutorialtrigger(speechcontainer);
        tpc.addtrigger(speechcontainer);     
        
        tutorialtriggergroupspeechend speechend = new tutorialtriggergroupspeechend(gp,speeker,message,lib.gettexture(7).getimage());
        speechend.addentity(lib.getnpc(0).getimage(),npc1vh,npc1side,npc1location);
        speechend.addentity(lib.getnpc(1).getimage(),npc2vh,npc2side,npc2location);
        speechend.addentity(lib.getnpc(2).getimage(),npc3vh,npc3side,npc3location);
        speechend.addentity(lib.getnpc(3).getimage(),npc4vh,npc4side,npc4location);
        speechend.setimportantentity(imp-1);
        tutorialtrigger speechendcontainer = new tutorialtrigger(speechend,tc,gp,tpc.size());
        speechend.addtutorialtrigger(speechendcontainer);
        tpc.addtrigger(speechendcontainer);     
    }
    
    
    
    public void createfourwaylistspeech(tutorialcontrol tc,gamepanel gp,texturelibrary lib,tutorialpanelcontrol tpc,String speeker,String message,int imp,int comp,int num){
               
        //courage
        String npc1name = "courage";
        boolean npc1vh = false;
        boolean npc1side = true;
        int npc1location = 200;
        
        //temperance
        String npc2name = "temperance";
        boolean npc2vh = false;
        boolean npc2side = false;
        int npc2location = 200;
        
        //prudence
        String npc3name = "prudence";
        boolean npc3vh = true;
        boolean npc3side = true;
        int npc3location = 200;
        
        //justice
        String npc4name = "justice";
        boolean npc4vh = true;
        boolean npc4side = true;
        int npc4location = 600;
        
        JScrollPane list = (JScrollPane)gp.getcomponent(comp); 
        
        tutorialtriggerlistspeech speech = new tutorialtriggerlistspeech(gp,speeker,message,lib.gettexture(7).getimage(),list,num);
        speech.addentity(lib.getnpc(0).getimage(),npc1vh,npc1side,npc1location);
        speech.addentity(lib.getnpc(1).getimage(),npc2vh,npc2side,npc2location);
        speech.addentity(lib.getnpc(2).getimage(),npc3vh,npc3side,npc3location);
        speech.addentity(lib.getnpc(3).getimage(),npc4vh,npc4side,npc4location);
        speech.setimportantentity(imp-1);
        tutorialtrigger speechcontainer = new tutorialtrigger(speech,tc,gp,tpc.size());
        speech.addtutorialtrigger(speechcontainer);
        tpc.addtrigger(speechcontainer);     
        
        tutorialtriggerendlistspeach speechend = new tutorialtriggerendlistspeach(gp,speeker,message,lib.gettexture(7).getimage(),list,num);
        speechend.addentity(lib.getnpc(0).getimage(),npc1vh,npc1side,npc1location);
        speechend.addentity(lib.getnpc(1).getimage(),npc2vh,npc2side,npc2location);
        speechend.addentity(lib.getnpc(2).getimage(),npc3vh,npc3side,npc3location);
        speechend.addentity(lib.getnpc(3).getimage(),npc4vh,npc4side,npc4location);
        speechend.setimportantentity(imp-1);
        tutorialtrigger speechendcontainer = new tutorialtrigger(speechend,tc,gp,tpc.size());
        speechend.addtutorialtrigger(speechendcontainer);
        tpc.addtrigger(speechendcontainer);     
        
        
    }

    public void createfourwaybuttonspeech(tutorialcontrol tc,gamepanel gp,texturelibrary lib,tutorialpanelcontrol tpc,String speeker,String message,int imp,JButton button){
        tutorialtriggerbuttonspeech speech = new tutorialtriggerbuttonspeech(gp,speeker,message,lib.gettexture(7).getimage(),button);
        speech.addentity(lib.getnpc(0).getimage(),npc1vh,npc1side,npc1location);
        speech.addentity(lib.getnpc(1).getimage(),npc2vh,npc2side,npc2location);
        speech.addentity(lib.getnpc(2).getimage(),npc3vh,npc3side,npc3location);
        speech.addentity(lib.getnpc(3).getimage(),npc4vh,npc4side,npc4location);
        speech.setimportantentity(imp-1);
        tutorialtrigger speechcontainer = new tutorialtrigger(speech,tc,gp,tpc.size());
        tpc.addtrigger(speechcontainer);  
        
        tutorialtriggerendbuttonspeech endspeech = new tutorialtriggerendbuttonspeech(gp,speeker,message,lib.gettexture(7).getimage(),button);
        endspeech.addentity(lib.getnpc(0).getimage(),npc1vh,npc1side,npc1location);
        endspeech.addentity(lib.getnpc(1).getimage(),npc2vh,npc2side,npc2location);
        endspeech.addentity(lib.getnpc(2).getimage(),npc3vh,npc3side,npc3location);
        endspeech.addentity(lib.getnpc(3).getimage(),npc4vh,npc4side,npc4location);
        endspeech.setimportantentity(imp-1);
        tutorialtrigger endspeechcontainer = new tutorialtrigger(endspeech,tc,gp,tpc.size());
        tpc.addtrigger(endspeechcontainer);  
    }
    
    
    
    
    public tutorialtrigger gettrigger(gamepanel gp){
        String panelname = gp.getname();
        
        for(int i = 0; i < panelcontrol.size(); i++){
            if(panelcontrol.elementAt(i).ispanel(panelname)){
                return panelcontrol.elementAt(i).gettrigger();
            }
        }
        return null;
    }
    
    public menucontrol getcontrol(){
        return cont;
    }
    
    public tutorialcontrol(BufferedReader in,menucontrol m,texturelibrary lib) throws Exception{
        cont = m;
        active = true;

        if(in.readLine().equals("*tutorial")){
            int num = Integer.parseInt(in.readLine());
            panelcontrol = new Vector<tutorialpanelcontrol>();
            for(int i = 0; i < num;i++){
                tutorialpanelcontrol temp = new tutorialpanelcontrol(in);
                panelcontrol.add(temp);
            }
        }
        
        createinfo(lib);
    }
    
    
    public void saveinformation(PrintWriter out){
        out.println("*tutorial");
        out.println(panelcontrol.size());
        for(int i = 0; i < panelcontrol.size(); i++){
            panelcontrol.elementAt(i).saveinformation(out);
        }
    }
    
    
}
