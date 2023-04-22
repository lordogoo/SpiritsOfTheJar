package com.gmail.lordogoo.spirits_of_the_jar.controlers;

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
/**
 * Write a description of class gameinfo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class gameinfocontrol
{
    menucontrol cont;
    texturelibrary lib;
    Vector<card> cardtypes;
    Vector<worldmodel> worldlist;
    Vector<terrainpeicemodel> terrain;
    
    Vector<npcmodel> npc;
    Vector<doodadmodel> doodad;
    Vector<structuremodel> structures;
    
    Vector<perchacemodel> perchase;
    
    Vector<bodymodel> startbodies;
    
    Vector<npcai> ai;
    Vector<deckmodel> decks;
    
    Vector<badguymodel> badguy;
    
    public gameinfocontrol(texturelibrary l,menucontrol cont)
    {
        lib = l;
        this.cont = cont;
        /************************************
         * cards
         ************************************/
        cardtypes = new Vector<card>();
        perchase = new Vector<perchacemodel>();
        
        card voidspace = new card("void",7,lib.getsymbol(1));
        
        
        //cardtypes.add(voidspace);
        //light
        card light = new card("light",6,lib.getsymbol(2));
        light.addparent(voidspace);
        //cardtypes.add(light);
        //dark
        card dark = new card("dark",6,lib.getsymbol(0));
        dark.addparent(voidspace);
        //cardtypes.add(dark);
        
        //energy
        card energy = new card("energy",5,lib.getsymbol(0));
        energy.addparent(light);
        //cardtypes.add(energy);
        //time
        card time = new card("time",5,lib.getsymbol(0));
        //cardtypes.add(time);
        //matter
        card matter = new card("matter",5,lib.getsymbol(0));
        //cardtypes.add(matter);
        //force
        card force = new card("force",5,lib.getsymbol(0));
        //cardtypes.add(force);
        
        card madness = new card("madness",4,lib.getsymbol(0));
        //cardtypes.add(madness);
        card justice = new card("justice",4,lib.getsymbol(0));
        //cardtypes.add(justice);
        card freedom = new card("freedom",4,lib.getsymbol(0));
        //cardtypes.add(freedom);
            //greed
        card greed = new card("greed",4,lib.getsymbol(0));
        greed.addconstraint(new anywheredestmodel());
        cardtypes.add(greed);
        perchacemodel greedperchace = new perchacemodel(greed.getname(),perchase.size(),greed.getcost(),10,greed.getsymbol(),new shopgivesingletrigger(cont,greed));
        perchase.add(greedperchace);
            //war
        card war = new card("war",4,lib.getsymbol(0));
        //on place destroys all the humanity cards in the stack and 
        //gives points for each card destroyed
        cardtypes.add(war);
        //think tank
        card truth;
        //card 

        card soul;
        
        
        
        //card  = new card("",lib.getsymbol(0));

        card death = new card("death",3,lib.getsymbol(0));
        //cardtypes.add(death);
        card plant = new card("plant",3,lib.getsymbol(0));
        //cardtypes.add(plant);

        card water = new card("water",3,lib.getsymbol(0));
        //cardtypes.add(water);
        card air = new card("air",3,lib.getsymbol(0));
        //cardtypes.add(air);
        card fire = new card("fire",3,lib.getsymbol(0));
        //cardtypes.add(fire);
        card metal = new card("metal",3,lib.getsymbol(0));
        //cardtypes.add(metal);
        card bone = new card("bone",3,lib.getsymbol(0));
        //cardtypes.add(bone);
        card blood = new card("blood",3,lib.getsymbol(0));//(might get rid of)
        //cardtypes.add(blood);
        card mind = new card("mind",3,lib.getsymbol(0));
        //cardtypes.add(mind);

        

        card sun = new card("sun",2,lib.getsymbol(0));
        //cardtypes.add(sun);
        card moon = new card("moon",2,lib.getsymbol(0));
        //cardtypes.add(moon);
        
        card earth = new card("earth",3,lib.getsymbol(0));
        //cardtypes.add(earth);
        
        card terra = new card("terra",2,lib.getsymbol(0));
        //cardtypes.add(terra);
        
        card volcano = new card("volcano",1,lib.getsymbol(0));
        volcano.addparent(terra);
        volcano.addparent(fire);
        //cardtypes.add(volcano);
        card mountain = new card("mountain",1,lib.getsymbol(0));
        mountain.addparent(terra);
        mountain.addparent(earth);
        //cardtypes.add(mountain);
        card desert = new card("desert",1,lib.getsymbol(0));
        desert.addparent(terra);
        desert.addparent(time);
        //cardtypes.add(desert);
                
        card forest = new card("forest",1,lib.getsymbol(0));
        forest.addparent(terra);
        forest.addparent(plant);
        //cardtypes.add(forest);
        card reef = new card("reef",1,lib.getsymbol(0));
        reef.addparent(terra);
        //cardtypes.add(reef);
        card grass = new card("grass",1,lib.getsymbol(0));
        grass.addparent(terra);
        //cardtypes.add(grass);
        card river = new card("river",1,lib.getsymbol(0));
        forest.addparent(terra);
        //cardtypes.add(river);
        card ocean = new card("ocean",1,lib.getsymbol(0));
        ocean.addparent(water);
        //cardtypes.add(ocean);
        card sky = new card("sky",1,lib.getsymbol(0));
        sky.addparent(air);
        //cardtypes.add(sky);
        
        card life = new card("life",3,lib.getsymbol(0));
        //cardtypes.add(life);
        
        card insect = new card("insect",1,lib.getsymbol(0));
        //cardtypes.add(insect);
        card animal = new card("animal",1,lib.getsymbol(0));
        //cardtypes.add(animal);
        card fish = new card("fish",1,lib.getsymbol(0));
        //cardtypes.add(fish);
        card bird = new card("bird",1,lib.getsymbol(0));
        //cardtypes.add(bird);
        
        card humanity = new card("humanity",3,lib.getsymbol(0));
        cardtypes.add(humanity);
        perchacemodel humanityperchace = new perchacemodel(humanity.getname(),perchase.size(),humanity.getcost(),10,humanity.getsymbol(),new shopgivesingletrigger(cont,humanity));
        perchase.add(humanityperchace);
        
        
        //TODO make the caracter cards similar to the characters in game 
            //merchant
            //preist
            //
        
            //poor
            //detremental initilizer card
        card poor = new card("poor",1,lib.getsymbol(0));
        poor.addparent(humanity);
        poor.addconstraint(new emptytiledestmodel());
        poor.addonupdaterule(new givepointsmodel(-1));
        cardtypes.add(poor);
        perchacemodel poorperchace = new perchacemodel(poor.getname(),perchase.size(),poor.getcost(),10,poor.getsymbol(),new shopgivesingletrigger(cont,poor));
        perchase.add(poorperchace);
            //theif(outlaw)
        card theif = new card("theif",1,lib.getsymbol(0));
        theif.addparent(humanity);
        theif.addconstraint(new emptytiledestmodel());
        theif.addonupdaterule(new givepointsmodel(-1));
        //if placed on your gold get 5 points
        //if placed on opponents gold opponent loses 5 points
        cardtypes.add(theif);
        perchacemodel theifperchace = new perchacemodel(theif.getname(),perchase.size(),theif.getcost(),10,theif.getsymbol(),new shopgivesingletrigger(cont,theif));
        perchase.add(theifperchace);
            //guard(guard)
        card guard = new card("guard",1,lib.getsymbol(0));
        guard.addparent(humanity);
        cardtypes.add(guard);
        perchacemodel guardperchace = new perchacemodel(guard.getname(),perchase.size(),guard.getcost(),10,guard.getsymbol(),new shopgivesingletrigger(cont,guard));
        perchase.add(guardperchace);
            //lord
        card lord = new card("lord",1,lib.getsymbol(0));
        lord.addparent(humanity);
        lord.addconstraint(new emptytiledestmodel());
        cardtypes.add(lord);
        perchacemodel lordperchace = new perchacemodel(lord.getname(),perchase.size(),lord.getcost(),10,lord.getsymbol(),new shopgivesingletrigger(cont,lord));
        perchase.add(lordperchace);
            //scolar
        card scolar = new card("scolar",1,lib.getsymbol(0));
        scolar.addconstraint(new emptytiledestmodel());
        scolar.addonplacerule(new givepointsmodel(-5));
        scolar.addonupdaterule(new givepointsmodel(2));
        cardtypes.add(scolar);
        perchacemodel scolarperchace = new perchacemodel(scolar.getname(),perchase.size(),scolar.getcost(),10,scolar.getsymbol(),new shopgivesingletrigger(cont,scolar));
        perchase.add(scolarperchace);
        
        
        //TODO make the building cards similar to the structures in game 
            //civilization
        card civilization = new card("civilization",3,lib.getsymbol(0));
        cardtypes.add(civilization);
        perchacemodel civilizationperchace = new perchacemodel(civilization.getname(),perchase.size(),civilization.getcost(),10,civilization.getsymbol(),new shopgivesingletrigger(cont,civilization));
        perchase.add(civilizationperchace);
        
        //reveiw the material cards some of them dont fit into the design
            //gold
        card gold = new card("gold",2,lib.getsymbol(3));
        gold.addparent(metal);
        gold.addconstraint(new inheritortoptiledestmodel(humanity));
        gold.addonplacerule(new ifoncarddomodel(poor,new givepointsmodel(10)));
        cardtypes.add(gold);
        perchacemodel goldperchace = new perchacemodel(gold.getname(),perchase.size(),gold.getcost(),10,gold.getsymbol(),new shopgivesingletrigger(cont,gold));
        perchase.add(goldperchace);
        
            //iron
        card iron = new card("iron",2,lib.getsymbol(6));
        iron.addparent(metal);
        iron.addconstraint(new cardtoptiledestmodel(civilization));
        cardtypes.add(iron);
        perchacemodel ironperchace = new perchacemodel(iron.getname(),perchase.size(),iron.getcost(),10,iron.getsymbol(),new shopgivesingletrigger(cont,iron));
        perchase.add(ironperchace);
        
        
        //TODO make the building cards similar to the structures in game 
            //stronghold
            //ruin
            //ghost town
            //camp
            //fort
        
            //farm -> town
        card farm = new card("farm",1,lib.getsymbol(0));
        farm.addparent(civilization);
        farm.addparent(humanity);
        farm.addparent(plant);
        farm.addconstraint(new emptytiledestmodel());
        farm.addonupdaterule(new givepointsmodel(1));
        farm.addondestroyrule(new givepointsmodel(-10));
        cardtypes.add(farm);
        perchacemodel farmperchace = new perchacemodel(farm.getname(),perchase.size(),farm.getcost(),10,farm.getsymbol(),new shopgivesingletrigger(cont,farm));
        perchase.add(farmperchace);
        
            //road(thinking) -> ruind camp -> crossroads
            //road should be an simple card that is used to initilize a strategy
            //road really doesnt fit with the games consept might want to remove
        card road = new card("road",1,lib.getsymbol(4));//
        road.addparent(civilization);
        road.addparent(terra);
        road.addconstraint(new anywheredestmodel());
        road.addonplacerule(new givepointsmodel(1));
        cardtypes.add(road);
        perchacemodel roadperchace = new perchacemodel(road.getname(),perchase.size(),road.getcost(),10,road.getsymbol(),new shopgivesingletrigger(cont,road));
        perchase.add(roadperchace);
        
            //city(thinking)(designed)
            //worlds:fantasy
        card city = new card("city",1,lib.getsymbol(0));
        city.addparent(humanity);
        city.addparent(civilization);
        city.addconstraint(new cardtoptiledestmodel(road));
        city.addonplacerule(new givepointsmodel(5));
        city.addonupdaterule(new givepointsmodel(1));
        cardtypes.add(city);
        perchacemodel cityperchace = new perchacemodel(city.getname(),perchase.size(),city.getcost(),10,city.getsymbol(),new shopgivesingletrigger(cont,city));
        perchase.add(cityperchace);
        
            //castle
            //locks the tile its in after a few turns
        card castle = new card("castle",1,lib.getsymbol(0));
        castle.addparent(civilization);
        castle.addconstraint(new emptytiledestmodel());
        cardtypes.add(castle);
        perchacemodel castleperchace = new perchacemodel(castle.getname(),perchase.size(),castle.getcost(),10,castle.getsymbol(),new shopgivesingletrigger(cont,castle));
        perchase.add(castleperchace);
        
        /*fantasy world cards
        humanity
        civilization
        poor
        theif
        lord
        scolar
        gold
        farm
        road
        city
        castle
        animal
        grass
        forest
        mountain
        war
        river
        iron
         */
        
        
        
        /**************************
         * pre made decks
         **************************/
        decks = new Vector<deckmodel>();
        decks.add(createrandomdeck());
        decks.add(createrandomdeck());
        decks.add(createrandomdeck());        
        /**************************
         * 
         */

        /*******************
         * worlds
         *******************/
        worldlist = new Vector<worldmodel>();
        //tutorial world
        terrainmodel tutorialterrain = new terrainmodel(-250,-750,500,1000,0,0,this,cont);
        tutorialterrain.addpolygonscript(new terraintriggersinglepolygon());
        tutorialterrain.adddoodadscript(new terraintriggerrandomdoodads());
        tutorialterrain.addstructurescript(new terraintriggerpresetstructure(0,100,0));
        tutorialterrain.addstructurescript(new terraintriggerpresetstructure(0,-500,1));
        tutorialterrain.addnpcscript(new terraintriggerpresetnpcs(0,-500,1,0));
        worldmodel tutorialworld = new worldmodel(cont,"tutorial world",tutorialterrain,lib.getplanet(0));
        worldlist.add(tutorialworld);
        tutorialworld.addmultycardperchace(goldperchace,gold);
        
        
        
        //fantasy world
        terrainmodel fantasyterrain = new terrainmodel(-500,-500,1000,1000,0,0,this,cont);
        fantasyterrain.addpolygonscript(new terraintriggerrandompolygons());
        fantasyterrain.adddoodadscript(new terraintriggerrandomdoodads());
        fantasyterrain.addstructurescript(new terraintriggerrandomstructures());
        fantasyterrain.addnpcscript(new terraintriggerrandomnpcs());
        //fantasyterrain.addbadguyscript(new terraintriggerrandombadguys());
        
        worldmodel fantasyworld = new worldmodel(cont,"fantasy world",fantasyterrain,lib.getplanet(1));
        worldlist.add(fantasyworld);
        fantasyworld.addmultycardperchace(goldperchace,gold);
        fantasyworld.addmultycardperchace(farmperchace,farm);
        fantasyworld.addmultycardperchace(roadperchace,road);
        fantasyworld.addmultycardperchace(cityperchace,city);
        fantasyworld.addmultycardperchace(castleperchace,castle);

        
        //fantasyworld.addsinglecardperchase(voidspace,10);
        /******************************************
         * terrain peices
         ******************************************/                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
         terrain = new Vector<terrainpeicemodel>();
         terrainpeicemodel metalterrainpeice = new terrainpeicemodel("metal",lib.gettexture(6).getimage());
         terrain.add(metalterrainpeice);
         
         terrainpeicemodel grassterrainpeice = new terrainpeicemodel("grass",lib.gettexture(0).getimage());
         terrain.add(grassterrainpeice);
         terrainpeicemodel waterterrainpeice = new terrainpeicemodel("water",lib.gettexture(8).getimage());
         terrain.add(waterterrainpeice);
         terrainpeicemodel beachterrainpeice = new terrainpeicemodel("beach",lib.gettexture(10).getimage());
         terrain.add(beachterrainpeice);
         terrainpeicemodel rockterrainpeice = new terrainpeicemodel("rock",lib.gettexture(9).getimage());
         terrain.add(rockterrainpeice);
         
         terrainpeicemodel forestterrainpeice = new terrainpeicemodel("forest",lib.gettexture(11).getimage());
         terrain.add(forestterrainpeice);
         terrainpeicemodel dirtterrainpeice = new terrainpeicemodel("dirt",lib.gettexture(12).getimage());
         terrain.add(dirtterrainpeice);
         //possibilities
         //dirt
         //sand
         //ash
         //lava
         //water
         
         tutorialworld.addterrainpeice(metalterrainpeice);
         
         fantasyworld.addterrainpeice(grassterrainpeice);
         fantasyworld.addterrainpeice(waterterrainpeice);
         fantasyworld.addterrainpeice(beachterrainpeice);
         fantasyworld.addterrainpeice(rockterrainpeice);
         
         
        /******************************************
         * bodies
         *****************************************/ 
        npc = new Vector<npcmodel>();
        startbodies = new Vector<bodymodel>(); 
        //triggers
        npctrigger clearrps = new clearrpstrigger(cont);
        killtrigger kill = new killtrigger(cont);
        ifrpstrigger ifrps = new ifrpstrigger(cont,kill);
        rpstrigger rpc = new rpstrigger(cont,ifrps);
        ifhateddotrigger hated = new ifhateddotrigger(cont,rpc);
         
        //****manikins****
        Vector<imagewithindex> manikinlist = new Vector<imagewithindex>();
        manikinlist.add(lib.getnpc(540));
        manikinlist.add(lib.getnpc(541));
        bodymodel tutorialmanikinbody = new bodymodel("manikin",3,100,1000,tutorialworld,manikinlist);
        tutorialmanikinbody.addrestriction(road,5);
        tutorialmanikinbody.addrestriction(gold,5);
        npcmodel tutorialmanikin = new npcmodel("manikin",tutorialmanikinbody,3,10);
        //manikin triggers
        tutorialmanikin.addonnpctouchtrigger(hated);
        //program manikin on player touch info
        battletrigger manikinbattle = new battletrigger(cont,decks,new basiccardai(),new victory20pts());
        dialogcratetrigger manikindialogsecondln = new dialogcratetrigger(cont,"initial conversation",manikinbattle);
        dialogdisplaytexttrigger manikindialogfirstln = new dialogdisplaytexttrigger(cont,"this outlaw wants to fight",manikindialogsecondln);
        ifhateddotrigger manikinplayerhated = new ifhateddotrigger(cont,manikindialogfirstln);
        tutorialmanikin.addonplayertouchtrigger(manikinplayerhated);
        //program manikin on idle trigger info
        tutorialmanikin.addonidletrigger(clearrps);
        tutorialmanikin.addai(new guardianai(100));
        tutorialworld.addnpc(tutorialmanikin);
        npc.add(tutorialmanikin);
        
        Vector<imagewithindex> battlemanikinlist = new Vector<imagewithindex>();
        battlemanikinlist.add(lib.getnpc(538));
        battlemanikinlist.add(lib.getnpc(539));
        bodymodel tutorialbattlemanikinbody = new bodymodel("battlemanikin",3,100,1000,tutorialworld,battlemanikinlist);
        tutorialbattlemanikinbody.addrestriction(road,5);
        tutorialbattlemanikinbody.addrestriction(gold,5);
        npcmodel tutorialbattlemanikin = new npcmodel("battlemanikin",tutorialbattlemanikinbody,3,10);
        //manikin triggers
        tutorialbattlemanikin.addonnpctouchtrigger(hated);
        //program manikin on player touch info
        battletrigger manikinbattlebattle = new battletrigger(cont,decks,new basiccardai(),new victory20pts());
        dialogcratetrigger manikinbattledialogsecondln = new dialogcratetrigger(cont,"initial conversation",manikinbattlebattle);
        dialogdisplaytexttrigger manikinbattledialogfirstln = new dialogdisplaytexttrigger(cont,"this outlaw wants to fight",manikinbattledialogsecondln);
        ifhateddotrigger manikinbattleplayerhated = new ifhateddotrigger(cont,manikinbattledialogfirstln);
        tutorialbattlemanikin.addonplayertouchtrigger(manikinbattleplayerhated);
        //program manikin on idle trigger info
        tutorialbattlemanikin.addonidletrigger(clearrps);
        tutorialbattlemanikin.addai(new guardianai(100));
        tutorialworld.addnpc(tutorialbattlemanikin);
        npc.add(tutorialbattlemanikin);
        
        //****rebels****
        //outlaw body
        Vector<imagewithindex> outlawlist = new Vector<imagewithindex>();
        outlawlist.add(lib.getnpc(454));
        outlawlist.add(lib.getnpc(455));
        outlawlist.add(lib.getnpc(456));
        bodymodel fantasyoutlawbody = new bodymodel("outlaw",3,100,1000,fantasyworld,outlawlist);
        fantasyworld.addbody(fantasyoutlawbody);
        fantasyoutlawbody.addrestriction(road,5);
        fantasyoutlawbody.addrestriction(poor,5);
        fantasyoutlawbody.addrestriction(gold,5);
        fantasyoutlawbody.addrestriction(farm,5);
        fantasyoutlawbody.addrestriction(iron,5);
        fantasyoutlawbody.addrestriction(lord,5);
        npcmodel fantasyoutlaw = new npcmodel("outlaw",fantasyoutlawbody,3,10);
        fantasyoutlaw.addonnpctouchtrigger(hated);
        //program outlaw on player touch info
        battletrigger outlawbattle = new battletrigger(cont,decks,new basiccardai(),new victory20pts());
        dialogcratetrigger outlawdialogsecondln = new dialogcratetrigger(cont,"initial conversation",outlawbattle);
        dialogdisplaytexttrigger outlawdialogfirstln = new dialogdisplaytexttrigger(cont,"this outlaw wants to fight",outlawdialogsecondln);
        ifhateddotrigger outlawplayerhated = new ifhateddotrigger(cont,outlawdialogfirstln);
        fantasyoutlaw.addonplayertouchtrigger(outlawplayerhated);
        //program outlaw on idle trigger info
        fantasyoutlaw.addonidletrigger(clearrps);
        fantasyoutlaw.addai(new merchantai(50));
        fantasyworld.addnpc(fantasyoutlaw);
        npc.add(fantasyoutlaw);
        
        //thug body
        Vector<imagewithindex> thuglist = new Vector<imagewithindex>();
        thuglist.add(lib.getnpc(308));
        thuglist.add(lib.getnpc(309));
        thuglist.add(lib.getnpc(310));
        bodymodel fantasythugbody = new bodymodel("thug",3,200,1000,fantasyworld,thuglist);
        fantasyworld.addbody(fantasythugbody);
        fantasythugbody.addrestriction(road,5);//
        fantasythugbody.addrestriction(poor,5);
        fantasythugbody.addrestriction(theif,5);
        fantasythugbody.addrestriction(gold,5);//
        fantasythugbody.addrestriction(lord,5);
        fantasythugbody.addrestriction(grass,5);
        fantasythugbody.addrestriction(forest,5);
        fantasythugbody.addrestriction(iron,5);//
        fantasythugbody.addrestriction(farm,5);
        fantasythugbody.addrandomname("jhon");
        fantasythugbody.addrandomname("fred");
        fantasythugbody.addrandomname("tim");
        npcmodel fantasythug = new npcmodel("thug",fantasythugbody,3,10);
        autosavetrigger thugsave = new autosavetrigger(cont);
        triggergiverewards thugrewards = new triggergiverewards(cont,3,fantasythug,"your reward for beating the thug");
        thugrewards.addnexttrigger(thugsave);
        triggerplayerkillnpc killthug = new triggerplayerkillnpc(cont);
        killthug.addnexttrigger(thugrewards);
        battletrigger thugbattle = new battletrigger(cont,decks,new basiccardai(),new victory20pts());
        thugbattle.setvictorytrigger(killthug);
        //thugbattle.setsetdefeattrigger();
        dialogdisplaytexttrigger thugdialogsecondln = new dialogdisplaytexttrigger(cont,"he wants to fight",thugbattle);
        dialogcratetrigger thugdialogfirstln = new dialogcratetrigger(cont,"you have met a thug",thugdialogsecondln);
        ifhateddotrigger thugplayerhated = new ifhateddotrigger(cont,thugdialogfirstln);
        fantasythug.addonplayertouchtrigger(thugplayerhated);
        fantasythug.addonnpctouchtrigger(hated);
        fantasythug.addonidletrigger(clearrps);
        fantasythug.addai(new guardianai(100));
        npc.add(fantasythug);
        
        //bandit body
        Vector<imagewithindex> banditlist = new Vector<imagewithindex>();
        banditlist.add(lib.getnpc(410));
        banditlist.add(lib.getnpc(411));
        banditlist.add(lib.getnpc(412));
        bodymodel fantasybanditbody = new bodymodel("bandit",3,300,3000,fantasyworld,banditlist);
        fantasyworld.addbody(fantasybanditbody);

        //lord body
        Vector<imagewithindex> lordlist = new Vector<imagewithindex>();
        lordlist.add(lib.getnpc(407));
        lordlist.add(lib.getnpc(408));
        lordlist.add(lib.getnpc(409));
        bodymodel fantasylordbody = new bodymodel("lord",3,500,8000,fantasyworld,lordlist);
        fantasyworld.addbody(fantasylordbody);
        
        //priest body
        Vector<imagewithindex> priestlist = new Vector<imagewithindex>();
        priestlist.add(lib.getnpc(439));
        priestlist.add(lib.getnpc(440));
        priestlist.add(lib.getnpc(442));
        bodymodel fantasypriestbody = new bodymodel("preist",3,500,8000,fantasyworld,priestlist);
        fantasyworld.addbody(fantasypriestbody);
        
        //****loayalists****
        //merchant body
        Vector<imagewithindex> merchantlist = new Vector<imagewithindex>();
        merchantlist.add(lib.getnpc(303));
        merchantlist.add(lib.getnpc(306));
        merchantlist.add(lib.getnpc(307));
        bodymodel fantasymerchantbody = new bodymodel("merchant",3,200,1000,fantasyworld,merchantlist);
        fantasyworld.addbody(fantasymerchantbody);
        fantasymerchantbody.addrestriction(gold,10); 
        fantasymerchantbody.addrestriction(poor,5);
        fantasymerchantbody.addrestriction(road,5); 
        fantasymerchantbody.addrestriction(lord,5);
        fantasymerchantbody.addrestriction(farm,5);
        fantasymerchantbody.addrestriction(city,5);
        fantasymerchantbody.addrestriction(greed,5);
        npcmodel fantasymerchant = new npcmodel("merchant",fantasymerchantbody,3,10);
        
        fantasymerchant.addonnpctouchtrigger(hated);
        fantasymerchant.addonidletrigger(clearrps);
        fantasymerchant.addai(new merchantai(50));
        fantasyworld.addnpc(fantasymerchant);
        npc.add(fantasymerchant);
        
        //guard body
        Vector<imagewithindex> guardlist = new Vector<imagewithindex>();
        guardlist.add(lib.getnpc(252));
        guardlist.add(lib.getnpc(253));
        guardlist.add(lib.getnpc(254));
        bodymodel fantasyguardbody = new bodymodel("guard",3,100,1000,fantasyworld,guardlist);
        fantasyworld.addbody(fantasyguardbody);
        npcmodel fantasyguard = new npcmodel("guard",fantasyguardbody,3,10);
        autosavetrigger guardsave = new autosavetrigger(cont);
        triggergiverewards guardrewards = new triggergiverewards(cont,3,fantasyguard,"your reward for beating the guard");
        guardrewards.addnexttrigger(guardsave);
        triggerplayerkillnpc killguard = new triggerplayerkillnpc(cont);
        killguard.addnexttrigger(guardrewards);
        battletrigger guardbattle = new battletrigger(cont,decks,new basiccardai(),new victory20pts());
        guardbattle.setvictorytrigger(killguard);
        //guardbattle.setsetdefeattrigger();
        dialogdisplaytexttrigger guarddialogsecondln = new dialogdisplaytexttrigger(cont,"he wants to fight",guardbattle);
        dialogcratetrigger guarddialogfirstln = new dialogcratetrigger(cont,"you have met a guard",guarddialogsecondln);
        ifhateddotrigger guardplayerhated = new ifhateddotrigger(cont,guarddialogfirstln);
        fantasyguard.addonplayertouchtrigger(guardplayerhated);
        fantasyguard.addonnpctouchtrigger(hated);
        fantasyguard.addonidletrigger(clearrps);
        fantasyguard.addai(new guardianai(100));
        npc.add(fantasyguard);
        
        //knight body
        Vector<imagewithindex> knightlist = new Vector<imagewithindex>();
        knightlist.add(lib.getnpc(393));
        knightlist.add(lib.getnpc(396));
        knightlist.add(lib.getnpc(365));
        bodymodel fantasyknightbody = new bodymodel("knight",3,300,5000,fantasyworld,knightlist);
        fantasyworld.addbody(fantasyknightbody);
        
        //nobel body
        Vector<imagewithindex> nobellist = new Vector<imagewithindex>();
        nobellist.add(lib.getnpc(684));
        nobellist.add(lib.getnpc(685));
        nobellist.add(lib.getnpc(686));
        bodymodel fantasynobelbody = new bodymodel("nobel",3,500,8000,fantasyworld,nobellist);
        fantasyworld.addbody(fantasynobelbody);
        
        //wizard body
        Vector<imagewithindex> wizardlist = new Vector<imagewithindex>();
        wizardlist.add(lib.getnpc(414));
        wizardlist.add(lib.getnpc(415));
        wizardlist.add(lib.getnpc(416));
        bodymodel fantasywizardbody = new bodymodel("wizard",3,500,8000,fantasyworld,wizardlist);
        fantasyworld.addbody(fantasywizardbody);
        
        //****monsters****
        //goblin body
        Vector<imagewithindex> goblinlist = new Vector<imagewithindex>();
        goblinlist.add(lib.getnpc(471));
        goblinlist.add(lib.getnpc(472));
        goblinlist.add(lib.getnpc(473));
        goblinlist.add(lib.getnpc(474));
        goblinlist.add(lib.getnpc(475));
        bodymodel fantasygoblinbody = new bodymodel("goblin",3,100,1000,fantasyworld,goblinlist);
        fantasyworld.addbody(fantasygoblinbody);
         
        //skeleton body
        Vector<imagewithindex> skeletonlist = new Vector<imagewithindex>();
        skeletonlist.add(lib.getnpc(720));
        skeletonlist.add(lib.getnpc(729));
        skeletonlist.add(lib.getnpc(730));
        bodymodel fantasyskeletonbody = new bodymodel("skeleton",3,100,2000,fantasyworld,skeletonlist);
        fantasyworld.addbody(fantasyskeletonbody);
        
        
        
        //TODO
        //TODO
        //TODO
        //initial events
        iffirstplaytrigger fantasyinit = new iffirstplaytrigger(cont);
        dialogcratetrigger fantasyinitialize = new dialogcratetrigger(cont,"initial conversation");
        dialogdisplaytexttrigger fantasyinitilize2 = new dialogdisplaytexttrigger(cont,"get some cards");
        String initialcardmessage = "Here are some cards to start your collection";
        triggergivecards fantasyinitilize3 = new triggergivecards(cont,30,fantasymerchantbody.getrestrictedcards(),fantasymerchantbody.getrestrictednum(),100,initialcardmessage);
        triggercreatedeck fantasyinitilize4 = new triggercreatedeck(cont,30,"tutorial deck");
        autosavetrigger fantasyinitilize5 = new autosavetrigger(cont);
        fantasyinit.addnexttrigger(fantasyinitialize);
        fantasyinitialize.addnexttrigger(fantasyinitilize2);
        fantasyinitilize2.addnexttrigger(fantasyinitilize3);
        fantasyinitilize3.addnexttrigger(fantasyinitilize4);
        fantasyinitilize4.addnexttrigger(fantasyinitilize5);
        
        fantasyworld.setenterworldtrigger(fantasyinit);
        
        
        /*
        eventmodel fantasyev1 = new conversationmodel("initial conversation");
        //fantasyworld.setenterworldtrigger(fantasyev1);
        eventmodel fantasyev2 = new conversationmodel("get some cards");
        fantasyev1.addnextevent(fantasyev2);
        eventmodel fantasyev3 = new givecardseventmodel(30,fantasythugbody.getrestrictedcards(),fantasythugbody.getrestrictednum());
        fantasyev2.addnextevent(fantasyev3);
        eventmodel fantasyev4 = new createdeckeventmodel(30,fantasythugbody.getrestrictedcards(),fantasythugbody.getrestrictednum());
        fantasyev3.addnextevent(fantasyev4);
        */

        /************************************
         * structures
         ************************************/
        structures = new Vector<structuremodel>();
        
        //tutorial world
        structuremodel tutorialarray = new structuremodel("array",lib.getstructure(5).getimage().getWidth()/2,lib.getstructure(605));
        structures.add(tutorialarray);
        tutorialworld.addstructure(tutorialarray);
        structuremodel tutorialruinedarray = new structuremodel("ruined array",lib.getstructure(5).getimage().getWidth()/2,lib.getstructure(605));
        structures.add(tutorialruinedarray);
        tutorialworld.addstructure(tutorialruinedarray);
        
        townupdatetrigger update = new townupdatetrigger(cont);
        
        //fantasy world
        structuremodel fantasycastle = new structuremodel("Castle",lib.getstructure(5).getimage().getWidth()/2,lib.getstructure(605));
        fantasycastle.setupdatetrigger(update);
        structures.add(fantasycastle);
        fantasyworld.addstructure(fantasycastle);
        structuremodel fantasycastleruin = new structuremodel("Ruined Castle",lib.getstructure(4).getimage().getWidth()/2,lib.getstructure(586));
        fantasycastleruin.setupdatetrigger(update);
        structures.add(fantasycastleruin);
        structuremodel fantasystronghold = new structuremodel("Stronghold",lib.getstructure(4).getimage().getWidth()/2,lib.getstructure(606));
        fantasystronghold.setupdatetrigger(update);
        structures.add(fantasystronghold);
        fantasyworld.addstructure(fantasystronghold);
        
        structuremodel fantasycity = new structuremodel("City",lib.getstructure(4).getimage().getWidth()/2,lib.getstructure(598));
        fantasycity.setupdatetrigger(update);
        structures.add(fantasycity);
        fantasyworld.addstructure(fantasycity);
        structuremodel fantasycityruin = new structuremodel("Ruined City",lib.getstructure(4).getimage().getWidth()/2,lib.getstructure(585));
        fantasycityruin.setupdatetrigger(update);
        structures.add(fantasycityruin);
        structuremodel fantasytownship = new structuremodel("township",lib.getstructure(4).getimage().getWidth()/2,lib.getstructure(592));
        fantasytownship.setupdatetrigger(update);
        structures.add(fantasytownship);
        fantasyworld.addstructure(fantasytownship);
        
        structuremodel fantasycamp = new structuremodel("Camp",lib.getstructure(4).getimage().getWidth()/2,lib.getstructure(628));
        fantasycamp.setupdatetrigger(update);
        structures.add(fantasycamp);
        fantasyworld.addstructure(fantasycamp);
        structuremodel fantasycampruin = new structuremodel("Ruined Camp",lib.getstructure(4).getimage().getWidth()/2,lib.getstructure(621));
        fantasycampruin.setupdatetrigger(update);
        structures.add(fantasycampruin);
        structuremodel fantasyrampart = new structuremodel("Rampart",lib.getstructure(4).getimage().getWidth()/2,lib.getstructure(627));
        fantasyrampart.setupdatetrigger(update);
        structures.add(fantasyrampart);
        fantasyworld.addstructure(fantasyrampart);
        
        
        /*
         structures i actualy want
         fantasy:
         
         forest:
         -den
         -grove
         -village
         
         
         promethean
         -volcano
         -palous
         -college
         
         */
        
        /*****************************************
         * faction
         *****************************************/
        factionmodel tutorialmanikins = new factionmodel();
        tutorialmanikins.getrewardslist().addreward(gold,2);
        tutorialmanikins.getrewardslist().addreward(city,2);
        tutorialmanikins.addnpc(tutorialmanikin,50);
        tutorialmanikins.addstructure(tutorialarray);
         
        factionmodel tutorialrougemanikins = new factionmodel();
        tutorialrougemanikins.getrewardslist().addreward(gold,2);
        tutorialrougemanikins.getrewardslist().addreward(city,2);
        tutorialrougemanikins.addnpc(tutorialbattlemanikin,50); 
        tutorialrougemanikins.addstructure(tutorialruinedarray);
         
        //fantasy rebels
        factionmodel fantasyrebels = new factionmodel();
        fantasyrebels.getrewardslist().addreward(gold,2);
        fantasyrebels.getrewardslist().addreward(poor,2);
        fantasyrebels.getrewardslist().addreward(road,2);
        fantasyrebels.getrewardslist().addreward(lord,2);
        fantasyrebels.getrewardslist().addreward(farm,2);
        fantasyrebels.getrewardslist().addreward(city,2);
        fantasyrebels.getrewardslist().addreward(greed,2);
        fantasyrebels.addnpc(fantasyoutlaw,10);
        fantasyrebels.addnpc(fantasythug,20);
        fantasyrebels.addstartnpc(fantasythug);
        //fantasyrebels.addbody(fantasybanditbody);
        //fantasyrebels.addbody(fantasylordbody);
        //fantasyrebels.addbody(fantasypriestbody);
        fantasyrebels.addstructure(fantasystronghold);
        fantasyrebels.addstructure(fantasytownship);
        fantasyrebels.addstructure(fantasyrampart);
        
        //loyalists
        factionmodel fantasyloyalists = new factionmodel();
        fantasyloyalists.getrewardslist().addreward(gold,2);
        fantasyloyalists.getrewardslist().addreward(poor,2);
        fantasyloyalists.getrewardslist().addreward(road,2);
        fantasyloyalists.getrewardslist().addreward(lord,2);
        fantasyloyalists.getrewardslist().addreward(farm,2);
        fantasyloyalists.getrewardslist().addreward(city,2);
        fantasyloyalists.getrewardslist().addreward(greed,2);
        fantasyloyalists.addnpc(fantasymerchant,10);
        fantasyloyalists.addnpc(fantasyguard,20);
        fantasyloyalists.addstartnpc(fantasyguard);
        //fantasyloyalists.addbody(fantasyknightbody);
        //fantasyloyalists.addbody(fantasynobelbody);
        //fantasyloyalists.addbody(fantasywizardbody);
        fantasyloyalists.addstructure(fantasycastle);
        fantasyloyalists.addstructure(fantasycity);
        fantasyloyalists.addstructure(fantasycamp);
        
        
        //monsters
        factionmodel fantasymonster = new factionmodel();
        //fantasymonster.addbody(fantasygoblinbody);
        //fantasymonster.addbody(fantasyskeletonbody);
        fantasymonster.addstructure(fantasycastleruin);
        fantasymonster.addstructure(fantasycityruin);
        fantasymonster.addstructure(fantasycampruin);
        
        //allegences
        tutorialmanikins.addhated(tutorialrougemanikins);
        tutorialrougemanikins.addhated(tutorialmanikins);
        fantasyrebels.addhated(fantasyloyalists);
        fantasyrebels.addhated(fantasymonster);
        fantasyloyalists.addhated(fantasyrebels);
        fantasyloyalists.addhated(fantasymonster);
        fantasymonster.addhated(fantasyrebels);
        fantasymonster.addhated(fantasyloyalists);
        /*********************************
         * create npc's
         *********************************/
        
        /*
        worldmodel druidworld = new worldmodel("druid world",lib.getplanet(1));
        worldlist.add(druidworld);
        bodymodel druidbody1 = new bodymodel("stag",3,druidworld);
        druidworld.addbody(druidbody1);
        bodymodel druidbody2 = new bodymodel("wolf",3,druidworld);
        druidworld.addbody(druidbody2);
        bodymodel druidbody3 = new bodymodel("druid",3,druidworld);
        druidworld.addbody(druidbody3);
        bodymodel druidbody4 = new bodymodel("sprigon",3,druidworld);
        druidworld.addbody(druidbody4);
        bodymodel druidbody5 = new bodymodel("fairy",3,druidworld);
        druidworld.addbody(druidbody5);
        bodymodel druidbody6 = new bodymodel("spirit",3,druidworld);
        druidworld.addbody(druidbody6);
        */
        /*
        worldmodel fireworld = new worldmodel("promethian(fire) world",lib.getplanet(2));
        worldlist.add(fireworld);
        bodymodel firebody1 = new bodymodel("",3,fireworld);
        fireworld.addbody(firebody1);
        bodymodel firebody2 = new bodymodel("promethian",3,fireworld);
        fireworld.addbody(firebody2);
        bodymodel firebody3 = new bodymodel("",3,fireworld);
        fireworld.addbody(firebody3);
        bodymodel firebody4 = new bodymodel("elemental",3,fireworld);
        fireworld.addbody(firebody4);
        bodymodel firebody5 = new bodymodel("effriti",3,fireworld);
        fireworld.addbody(firebody5);
        bodymodel firebody6 = new bodymodel("dragon",3,fireworld);
        fireworld.addbody(firebody6);
        */
        /*
        worldmodel necroworld = new worldmodel("necromatic world",lib.getplanet(3));
        //worldlist.add();
        bodymodel necrobody1 = new bodymodel("walking corpse",3,necroworld);
        bodymodel necrobody2 = new bodymodel("necromancer",3,necroworld);
        bodymodel necrobody3 = new bodymodel("specter",3,necroworld);
        bodymodel necrobody4 = new bodymodel("vampire",3,necroworld);
        bodymodel necrobody5 = new bodymodel("lich",3,necroworld);
        bodymodel necrobody6 = new bodymodel("",3,necroworld);
        */
        /*
        worldmodel transworld = new worldmodel("transendent world",null);
        //worldlist.add();
        bodymodel transbody1 = new bodymodel("crystal being",3,transworld);
        bodymodel transbody2 = new bodymodel("",3,transworld);
        bodymodel transbody3 = new bodymodel("",3,transworld);
        bodymodel transbody4 = new bodymodel("",3,transworld);
        bodymodel transbody5 = new bodymodel("",3,transworld);
        bodymodel transbody6 = new bodymodel("",3,transworld);
        */
        
        /*********************************************
         * doodads
         *********************************************/
        doodad = new Vector<doodadmodel>();
        
        doodadmodel fantasytree1 = new doodadmodel("tree1",lib.getdoodad(0));
        doodad.add(fantasytree1);
        doodadmodel fantasytree2 = new doodadmodel("tree2",lib.getdoodad(1));
        doodad.add(fantasytree2);
        doodadmodel fantasyrock1 = new doodadmodel("rock1",lib.getdoodad(10));
        doodad.add(fantasyrock1);
        doodadmodel fantasyrock2 = new doodadmodel("rock2",lib.getdoodad(11));
        doodad.add(fantasyrock2);
        doodadmodel fantasycube1 = new doodadmodel("cube1",lib.getdoodad(12));
        doodad.add(fantasycube1);
        doodadmodel fantasycube2 = new doodadmodel("cube2",lib.getdoodad(13));
        doodad.add(fantasycube2);
        doodadmodel fantasycylinder1 = new doodadmodel("cylinder1",lib.getdoodad(14));
        doodad.add(fantasycylinder1);
        
        tutorialworld.adddoodad(fantasycube1);
        tutorialworld.adddoodad(fantasycube2);
        tutorialworld.adddoodad(fantasycylinder1);
        
        fantasyworld.adddoodad(fantasytree1);
        fantasyworld.adddoodad(fantasytree2);
        fantasyworld.adddoodad(fantasyrock1);
        fantasyworld.adddoodad(fantasyrock2);

        /*****************
         * badguy initilization
         *****************/
         badguy = new Vector<badguymodel>();
         
         //on npc death
         triggerwarptotown ondeath = new triggerwarptotown(cont);
         resurecttrigger resurect = new resurecttrigger(cont,ondeath);
         
         //on player death
         //kill the current badguy and remove all the roumors form the shops then check to see if all badguys are dead
         //dialogdisplaytexttrigger thugdialogsecondln = new dialogdisplaytexttrigger(cont,"he wants to fight",thugbattle);
         triggerendgame endgametrigger = new triggerendgame(cont);
         dialogcratetrigger endgamedialog = new dialogcratetrigger(cont,"you have captured all the spirits",endgametrigger);
         triggerkillbadguy killbadguytrigger = new triggerkillbadguy(cont,endgamedialog);
         
         badguymodel fantacybadguy0 = new badguymodel("the Meek","",lib.getnpc(282));
         fantacybadguy0.addondeathtrigger(resurect);
         fantacybadguy0.addondeathbyplayertrigger(killbadguytrigger);
         badguy.add(fantacybadguy0);
         fantasyworld.addbadguy(fantacybadguy0);
         
         badguymodel fantacybadguy1 = new badguymodel("Knollek the Shadow","",lib.getnpc(283));
         fantacybadguy1.addondeathtrigger(resurect);
         fantacybadguy1.addondeathbyplayertrigger(killbadguytrigger);   
         badguy.add(fantacybadguy1);
         fantasyworld.addbadguy(fantacybadguy1);
         
         badguymodel fantacybadguy2 = new badguymodel("the Horder","",lib.getnpc(284));
         fantacybadguy2.addondeathtrigger(resurect);
         fantacybadguy2.addondeathbyplayertrigger(killbadguytrigger);  
         badguy.add(fantacybadguy2);
         fantasyworld.addbadguy(fantacybadguy2);
         
         badguymodel fantacybadguy3 = new badguymodel("the Warmonger","",lib.getnpc(285));
         badguy.add(fantacybadguy3);
         badguymodel fantacybadguy4 = new badguymodel("the Manipulator","",lib.getnpc(286));
         badguy.add(fantacybadguy4);
         badguymodel fantacybadguy5 = new badguymodel("the Glutton","",lib.getnpc(287));
         badguy.add(fantacybadguy5);
         badguymodel fantacybadguy6 = new badguymodel("the Snitch","",lib.getnpc(288));
         badguy.add(fantacybadguy6);
         badguymodel fantacybadguy7 = new badguymodel("the Tyrant","",lib.getnpc(289));
         badguy.add(fantacybadguy7);
         badguymodel fantacybadguy8 = new badguymodel("the Betrayer","",lib.getnpc(290));
         badguy.add(fantacybadguy8);
         badguymodel fantacybadguy9 = new badguymodel("the Deciver","",lib.getnpc(291));
         badguy.add(fantacybadguy9);
         badguymodel fantacybadguy10 = new badguymodel("the Sloth","",lib.getnpc(292));
         badguy.add(fantacybadguy10);
         //badguy ideas
         //the talon
         //the defiler
         //the wrecker
         //the hunter
         //the tired
         //the theif
         //the murderer
        
    }
    
    public menucontrol getmenucontrol()
    {
        return cont;
    }
    
    public deckmodel createrandomdeck(){
        Random r = new Random();
        deckmodel random = new deckmodel("rand"+decks.size());
        for(int i = 0; i < 30; i++){
            int index = Math.abs(r.nextInt()%cardtypes.size());
            random.addcard(cardtypes.elementAt(index));
        }
        return random; 
    }
    
    public deckmodel createdeckfromlist(Vector<card> list){
        Random r = new Random();
        deckmodel random = new deckmodel("rand"+decks.size());
        for(int i = 0; i < 30; i++){
            int index = Math.abs(r.nextInt()%cardtypes.size());
            random.addcard(cardtypes.elementAt(index));
        }
        return random;
    }
    
    public deckmodel createdeckfromlist(Vector<card> piclist,Vector<Integer> maxnum){
        int decksize = 30;
        
        Random r = new Random();
        deckmodel random = new deckmodel("rand"+decks.size());
        Vector<candidate> candidates = new Vector<candidate>();
        
        for(int i = 0; i < piclist.size();i++){
                candidates.add(new candidate(piclist.elementAt(i),maxnum.elementAt(i).intValue()));
        }
        
        for(int i = 0; i < 30; i ++){
            if(candidates.size()==0){
            }else if(candidates.size() == 1){
                int index = 0;
                candidate ctemp = candidates.elementAt(index);
                random.addcard(ctemp.removecard());
                if(ctemp.getnum() == 0){
                    candidates.remove(index);
                }
            }else{
                int index = Math.abs(r.nextInt()%candidates.size());
                candidate ctemp = candidates.elementAt(index);
                random.addcard(ctemp.removecard());
                if(ctemp.getnum() == 0){
                    candidates.remove(index);
                }
            }
        }
        
        return random;
    }
    
    public card getcard(int i){
        return cardtypes.elementAt(i);
    }
    
    public card getcard(String n){
        for(int i = 0; i < cardtypes.size(); i++){
            if(cardtypes.elementAt(i).getname().equals(n)){
                return cardtypes.elementAt(i);
            }
        }
        return null;
    }
    
    //world stuff
    public worldmodel getworld(int i){
        return worldlist.elementAt(i);
    }
    
    public worldmodel getworld(String n){
        for(int i = 0; i < worldlist.size(); i++){
            if(worldlist.elementAt(i).getname().equals(n)){
                return worldlist.elementAt(i);
            }
        }
        return null;
    }
    
    public int numworld(){
        return worldlist.size();
    }
    
    public terrainpeicemodel getterrain(String n){
        for(int i = 0; i < terrain.size();i++){
            if(terrain.elementAt(i).getname().equals(n)){
                return terrain.elementAt(i);
            }
        }
        return null;
    }
    
    //doodad stuff
    public doodadmodel getdoodad(int i){
        return doodad.elementAt(i).copy();
    }
    
    public doodadmodel getdoodad(String n){
        for(int i = 0; i < doodad.size(); i++){
            if(doodad.elementAt(i).getname().equals(n)){
                return doodad.elementAt(i);
            }
        }
        return null;
    }
    
    public int numdoodad(){
        return doodad.size();
    }
    
    //structure stuff
    public structuremodel getstructure(int i){
        return structures.elementAt(i).copy();
    }
    
    public structuremodel getstructure(String s){
        for(int i = 0; i < structures.size();i++){
            if(structures.elementAt(i).getname().equals(s)){
                return structures.elementAt(i);
            }
        }
        return null;
    }
    
    public int numstructure(){
        return structures.size();
    }
    
    //npc stuff
    public npcmodel getnpc(String b){

        for(int i = 0; i < npc.size();i++){
            if(npc.elementAt(i).getname().equals(b)){
                return npc.elementAt(i);
            }
        }
        return null;
    }
    
    
    public bodymodel getbody(String b){
        System.out.println("#!$%#$%!#@%@#%!#$%#$!%#$!npc check");
        System.out.println(b);
        for(int i = 0; i < npc.size();i++){
            System.out.println("+ "+npc.elementAt(i).getname());
            if(npc.elementAt(i).getbody().getname().equals(b)){
                System.out.println("*+");
                return npc.elementAt(i).getbody();
            }
        }
        System.out.println("*-");
        return null;
    }
    
    public badguymodel getbadguy(String b){
        for(int i = 0; i < badguy.size();i++){
            if(badguy.elementAt(i).getname().equals(b)){
                return badguy.elementAt(i);
            }
        }
        return null;
    }
    
    //perchace stuff
    public perchacemodel getperchase(int i){
        return perchase.elementAt(i);
    }
    
    public perchacemodel getperchase(String s){
        for(int i = 0; i < perchase.size();i++){
            if(perchase.elementAt(i).gettext().equals(s)){
                return perchase.elementAt(i);
            }
        }
        return null;
    }
    
    /**********************
     * candidate
     */
    public class candidate{
        card c;
        int num;
        int numused;
    
        public candidate(card c,int n){
            this.c = c;
            num = n;
            numused = 0;
        }
        
        public card removecard(){
            num --;
            numused ++;
            return c;
        }
        
        public int getnum(){
            return num;
        }
        
        public int getnumused(){
            return numused;
        }
        
        public boolean isempty(){
            if(num > 0){
                return false;
            }
            return true;
        }
    
    }
    
    
}
