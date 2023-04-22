package com.gmail.lordogoo.spirits_of_the_jar.gameinterface;
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
/**
 * Write a description of class intropanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class intropanel extends gamepanel
{

    gamepanel thisref;
    menucontrol menuref;

    JScrollPane euliscroll;
    JButton next;
    
    public intropanel(String name,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(name,c,i,s,bi);
        thisref = this;
        menuref = c;
        
        double size[][] = 
        {{TableLayout.FILL,20,.8,20,TableLayout.FILL},
        {TableLayout.FILL,20,.8,30,20,TableLayout.FILL,1}};
        
        this.setdimensions(4,5);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"1,1,3,4");
        
        
        
        JLabel euli = new JLabel("<html><center>This is a very early build of the spirit in the jar game.</center><br>"+
                                 "version 1.0<br>"+
                                 "This is a message from the creator mark nicoll.<br><br>"+
                                 "Hello this is the test version of my game.<br>"+
                                 "This is also my first game and my first alpha test for this game<br>"+
                                 "I know its buggy and I know its bad but ive had fun writing this game<br>"+
                                 "and I hope this alpha test will lead to a better game. My first goal for this<br>"+
                                 "alpha test is to nail down all the weaknesses in the interface and to try and make<br>"+
                                 "it more usable and to get an indication of what I should put in the tutorial<br>"+
                                 "Being that this is a test version it has a huge number of holes and problems.<br>"+
                                 "I invite you to help me to improve my game before its release.<br>"+
                                 "There are two things that you could help me with right off the bat.<br>"+
                                 "The first thing you could do is to stop reading right here and try the game.<br>"+
                                 "If you do that and then record your confusion then<br>"+
                                 "I can use that information to make a better tutorial.<br>"+
                                 "Otherwise I will state the rules below so that they might be tested<br>"+
                                 "and then after that I will indicate all the bugs I already have found.<br>"+
                                 "I would ask if you arent going to skip on by could you please skim the below list.<br>"+
                                 "You dont need to read it all but if you find something wrong with the game try and indicate<br>"+
                                 "what it is and maybe in terms of the design of the rules I will outline.<br>"+
                                 "Also please dont redistribute the game without my permision.<br>"+
                                 "my email is mnicoll@ualberta.ca if you need to send me info on bugs or praise or hate.<br>"+
                                 "thanks :)<br><br>"+
                                 
                                 "Game Idea:<br>"+
                                 "  A spirit in the spirit world is charged with protecting a prison for evil spirits<br>"+
                                 "The prison consists of a jar that contains the evil spirits. Due to serconstances beond<br>"+
                                 "the control of our protagonist the prison jar is broken and the evil spirits contained<br>"+
                                 "within escape and flee to a set of mortal worlds where they cause a bunch of ruckas. Time <br>"+
                                 "in the spirit world is diferent so the damage the evil spirits cause happens over eons in the<br>"+
                                 "mortal realms even if it only appears like seconds to the protagonist. The protagonist then must<br>"+
                                 "head to each of the mortal realms and capture these evil spirits. The hard part is the protagonsit<br>"+
                                 "cannot exist in these worlds by itself and must inhabit a mortal body to locomote. To aquire such a<br>"+
                                 "body the protagonist waits at the edge of the spirit world for the prayers of a mortal to cross into<br>"+
                                 "the spirit world. The protagonist then uses the prayer to inhabit the mortal and walk the world<br>"+
                                 "looking for the evil spirits.<br><br>"+
                                 
                                 
                                 "World ideas:<br>"+
                                 "I think im going to have a total of three worlds with different themes<br>"+
                                 "  World 1: this is a mehdevial fantasy world almost entierlt conqured and ruled by a sigle human<br>"+
                                 "kingdom. The kingdom was peaceful and harmonious until the king was possesed by an evil spirit.<br>"+
                                 "That spirit caused the king to become very cruel. There was an uprising. That uprising is still<br>"+
                                 "happening durring the arrival of the protagonist. The point of this world is that there are two<br>"+
                                 "factions. The loalists and the revolutionarys and the protagonist has to navigate the world hopping<br>"+
                                 "between factions to find all the evil spirits. This is the world in this demo game version.<br>"+
                                 "  World 2: this is a druidic world with intelegent animals and their human druid keepers. This world<br>"+
                                 "was goverened by the balance of the species but evil spirits came to the world and upset the chain<br>"+
                                 "of nature. The spirits possesed animals and caused them to eat their own kind and kill animals above<br>"+
                                 "them on the food chian. because of this the world is slowly dieing and the druids arent powerful enough<br>"+
                                 "to stop them. The idea of this world is the protagonist will have to adopt a body of an animal and deal<br>"+
                                 "with the fact that they occupy a place in the food chain of the world.<br>"+
                                 "  World 3: this is a world promethian world of elemental fire. The idea of the world is its theme is<br>"+
                                 "the elements and how they can be harnest and missused. Im still thinking of how to conduct this world at<bt>"+
                                 "this point<br><br>"+
                                 
                                 //TODO
                                 "RULES:<br>"+
                                 "Intro:<br>"+
                                 "First you need to create a profile. That profile is your identity in the game<br>"+
                                 "this game is also a card game and your collection is associated with your profile<br>"+
                                 "after that you can start a new game.<br>"+
                                 "After this theres suppost to be an intro but I havent gotten around to that yet<br>"+
                                 "so what you need to do is select a world by pressing \"the go to world\" button<br>"+
                                 "I think only the one world is playable at the moment so clicking any of the other<br>"+
                                 "options wont work. please try if you want to test it. Then you have to select a body<br>"+
                                 "doing so is the same as selecting a world. just click the \"use this body\" button<br>"+
                                 "After this the game should take you to the game world you picked in the body you picked<br>"+
                                 "at this point you are playing the game.<br>"+
                                 "Rules for the main map:<br>"+
                                 "the map consists of a terrain that the player can traverse. The map is inhabited with<br>"+
                                 "setlments and npc's that the players can interact with. the player is always at the<br>"+
                                 "center of the map and is distinguished by a red square at the characters picture.<br>"+
                                 "the player can then walk around the map using the arrow keys or the wsad keys.<br>"+
                                 "setlements around the map have blue rings around them. When a player approches<br>"+
                                 "a setlment the ring turns yellow and the player can press \"e\" to enter them.<br>"+
                                 "in the setlements you can buy cards, buy roumors and sell cards. When the player<br>"+
                                 "buys enough roumors then the evil spirits should become visible looming over the<br>"+
                                 "npc they inhabit. this doesnt work yet so they are just visible all the time.<br>"+
                                 "arround the map there are nps's going about their buisness. ones with a green<br>"+
                                 "ring are friendly while the ones with red rings are enemies. You can fight the<br>"+
                                 "ones guarding settlements by approching them. those ones are called guards<br>"+
                                 "The ones running around the map are called merchants and cannot be faught yet.<br>"+
                                 "If you get two npc's of different factions to touch each other they will fight<br>"+
                                 "and one will die at random.<br>"+
                                 "Rules for combat:<br>"+
                                 "The player arrives at the pre match screen where they select a deck and see the victory conditions<br>"+
                                 " of the match. the current standard victory conditions are for the player to receive 20 victory<br>"+
                                 "points before the opponent. I will be adding alternative victory conditions later.first the player<br>"+
                                 " needs to choose a deck. Only decks that are valid for the current body<br>"+
                                 "can be chosen. Decks can also be edited. There is a retreat option but its not implemented<br>"+
                                 "yet. After the deck is chosen the match starts. First there is a coin toss to indicate who goes<br>"+
                                 "first. Then the hands of both players are drawn. hands are five cards. The opponents hand is visible for testing perpouses<br>"+
                                 "that wont be the case in the final verson. Then after that each player begins their turn and the match<br>"+
                                 "happens in alternating turns. a turn consists of the player first drawing a card if they have less than<br>"+
                                 "five. Then if there are cards on the board all their update effects go off and their animations play. Then<br>"+
                                 "the player has a chance to play their cards. Something the player might want to do at this point is if they<br>"+
                                 "right click on a card in their hand(and not the opponents) the card will expand and the card text will be<br>"+
                                 "readable. They then can click again to have the card go back to the way it was. What the player then wants to<br>"+
                                 "do is to play cards into the middle tiles such that the cards effects will generate enought points to win. points<br>"+
                                 "can be generated over multiple turns but the player can only win if they achive more than the 20 points limit after<br>"+
                                 "their opponents turn. This way the opponent has one turn to stop them. Cards can only be played on tiles wich meet the<br>"+
                                 "requirements of the card. If this is the case the boarder of the tile will change yellow when the card is picked up<br>"+
                                 "from the hand.<br>"+
                                 "Rules for deck design:<br>"+
                                 "the player needs to make a deck to participate in combat. That deck has no mimimum or<br>"+
                                 "maximum size. Making a deck too small is suppost to be bad in that if a player draws out<br>"+
                                 "of cards they lose the combat while if the deck is too big then the player cant get the<br>"+
                                 "cards they want. The amount of cards in a deck are limited to the restrictions presented<br>"+
                                 "by the characters current body. In the deck creation menu the restrictions of the current<br>"+
                                 "body are indicated.<br>"+
                                 "Rules for the endgame:<br>"+
                                 "When all the badguys are beeten in combat then the player wins. A player gets to keep their<br>"+
                                 "collection and bodies that they had while play so they can take them into the next game.<br>"+
                                 
                                 "<br>"+
                                 "OUTSTANDING FEATURES:<br>"+
                                 "-a better manual(this one sux).<br>"+
                                 "-some way to acess new worlds should be added.<br>"+
                                 "-some way to get new bodys should be added.<br>"+
                                 "-The game needs a tutorial really badly. Im gonna work on that next.<br>"+
                                 "-the journel needs to be finished. it currently doesnt display any info.<br>"+
                                 "-the map should display the name of locations when you mouse over them.<br>"+
                                 "-there should be some way to distinguish between friendly and enemy buildings.<br>"+
                                 "-there should be more action options to do in towns other than buying cards.<br>"+
                                 "-setlements should produce new people for the player to fight and interact with.<br>"+
                                 "-the game needs to be way more story oriented.<br>"+
                                 "-more map npc's need to be added.<br>"+
                                 "-a large over world ai with goals and direction needs to be added.<br>"+
                                 "-more worlds.<br>"+
                                 "-the player should be able to burn cards.<br>"+
                                 "1)the player should be able to burn cards for effects on the map.<br>"+
                                 "2)the player should be able to burn cards to ease the restriction<br>"+
                                 "on their body for deck design.<br>"+
                                 "3)burn cards in conversations for interesting story reasons<br>"+
                                 "-need a better combat ai so there is a range of dificulty in the game.<br>"+
                                 "-need to add more cards with more effects.<br>"+
                                 "-need to create effects for cards that dont have any.<br>"+
                                 "<br>"+
                                 
                                 "KNOWN BUGGS:<br>"+
                                 "-sometimes durring animations there is a flash of something that shouldnt be<br>"+
                                 "animating in the top right corner<br>"+
                                 "-when loading the game the merchants start in the same position as the player even<br>"+
                                 "if they werent there when the game saved<br>"+
                                 "-combat animations sometimes dont play or play at different speeds<br>"+
                                 "-update phase of combat doesnt occure for opponent<br>"+
                                 "<br>"+
                                 
                                 "CHANGE LOG:<br>"+
                                 "1.0<br>"+
                                 "nothing to report because this is the first release of the game.<br>"+
                                 "<br>"+
                                 "1.1<br>"+
                                 "-added a discard card button to combat so the player can discard cards.<br>"+
                                 "Allows combat to keep flowing even if the player is stuck with nothing to play.<br>"+
                                 "-can right click on cards in a tile to get it to display its name and rules text<br>"+
                                 "-when mousing over a game tile that contains cards the cards symbol will change color to<br>"+
                                 "indicate which card will display it information when right clicked<br>"+
                                 "-changed the look of the popups.<br>"+
                                 "-changed the popups to be internal to the aplication. This should stop problems with<br>"+
                                 "the popup being in odd locations and the textbox being unselectable.<br>"+
                                 "-corrected a bug that caused cards to dissapear when you right clicked a card with a card<br>"+
                                 "already picked up.<br>"+
                                 "-turn counter should count up the number of turns gone by now.<br>"+
                                 "-fixed a possible infinte loop that occurs when the computer ai makes a move. There still<br>"+
                                 "might be another loop there.<br>"+
                                 "-fixed a bug that would cause combat to hang if the opponent runs out of cards<br>"+ 
                                 "");
        euli.setOpaque(false);
        euli.setBackground(Color.lightGray);
        euliscroll = new specialscrollpane(euli);
        euliscroll.setOpaque(false);
        this.add(euliscroll,"2,2,2,2");
        this.setComponentZOrder(euliscroll,0);
        new specialscrollpanecontainer(componentlist,containerlist,(specialscrollpane)euliscroll);
        //componentlist.add(euliscroll);
        
        next = new specialboarderbutton("next",i.gettexture(4).getimage(),this,5,null);
        next.setOpaque(false);
        next.addActionListener(new selectclick());
        this.add(next,"2,3");
        this.setComponentZOrder(next,0);
        new specialbuttoncontainer(componentlist,containerlist,(specialboarderbutton)next);
        
    }

    private class selectclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            //addpopupdisplay();
            soundref.playsoundeffect(0);
            setupswitchpanel("menupanel");
        }    
    }
    
}
