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
import javax.imageio.*;
import javax.imageio.stream.*;
import java.util.*;
import java.net.*;
import com.gmail.lordogoo.spirits_of_the_jar.controlers.*;

public class texturelibrary
{
    public BufferedImage gameicon;
    Vector<imagewithindex> library;
    Vector<imagewithindex> symbols;
    Vector<imagewithindex> planet;
    Vector<imagewithindex> doodads;
    Vector<imagewithindex> structures;
    Vector<imagewithindex> npc;
    Vector<Vector<BufferedImage>> animations;
    
    public texturelibrary()
    {
        library = new Vector<imagewithindex>();
        symbols = new Vector<imagewithindex>();
        planet = new Vector<imagewithindex>();
        doodads = new Vector<imagewithindex>();
        structures = new Vector<imagewithindex>();
        npc = new Vector<imagewithindex>();
        animations = new Vector<Vector<BufferedImage>>();
        
        BufferedImage image;
        try { 
          gameicon = ImageIO.read(getClass().getResource("../../../../../icon/SpiritsLogo.png"));
            
            
          //textures images
          image = ImageIO.read(getClass().getResource("../../../../../textures/00 Common Ground.jpg"));
          library.add(new imagewithindex(image,0));
          image = ImageIO.read(getClass().getResource("../../../../../textures/01 Circle Rose Tiles.jpg"));
          library.add(new imagewithindex(image,1));
          image = ImageIO.read(getClass().getResource("../../../../../textures/02 Copper Verdigris.jpg"));
          library.add(new imagewithindex(image,2));
          image = ImageIO.read(getClass().getResource("../../../../../textures/03 Barren Reds.jpg"));
          library.add(new imagewithindex(image,3));
          image = ImageIO.read(getClass().getResource("../../../../../textures/04 Green Marble Blur.jpg"));
          library.add(new imagewithindex(image,4));          
          image = ImageIO.read(getClass().getResource("../../../../../textures/05 Fantasy Stone.jpg"));
          library.add(new imagewithindex(image,5));
          image = ImageIO.read(getClass().getResource("../../../../../textures/06 Dull Galvanization.jpg"));
          library.add(new imagewithindex(image,6));
          image = ImageIO.read(getClass().getResource("../../../../../textures/seamlesspaper4.jpg"));
          library.add(new imagewithindex(image,7));
          //loadfolder("textures",library);
          image = ImageIO.read(getClass().getResource("../../../../../textures/07 Ocean.png"));
          library.add(new imagewithindex(image,8));
          image = ImageIO.read(getClass().getResource("../../../../../textures/08 Rockcracked.png"));
          library.add(new imagewithindex(image,9));
          image = ImageIO.read(getClass().getResource("../../../../../textures/09 Sand.png"));
          library.add(new imagewithindex(image,10));
          image = ImageIO.read(getClass().getResource("../../../../../textures/10 Moss.png"));
          library.add(new imagewithindex(image,11));
          image = ImageIO.read(getClass().getResource("../../../../../textures/11 Dirt.png"));
          library.add(new imagewithindex(image,12));
          //TODO
          //add symbols
          image = ImageIO.read(getClass().getResource("../../../../../symbols/placeholder.png"));
          symbols.add(new imagewithindex(image,0));
          image = ImageIO.read(getClass().getResource("../../../../../symbols/void.png"));
          symbols.add(new imagewithindex(image,1));
          image = ImageIO.read(getClass().getResource("../../../../../symbols/madness.png"));
          symbols.add(new imagewithindex(image,2));
          image = ImageIO.read(getClass().getResource("../../../../../symbols/coins.png"));
          symbols.add(new imagewithindex(image,3));
          image = ImageIO.read(getClass().getResource("../../../../../symbols/road.png"));
          symbols.add(new imagewithindex(image,4));
          image = ImageIO.read(getClass().getResource("../../../../../symbols/mineral.png"));
          symbols.add(new imagewithindex(image,5));
          image = ImageIO.read(getClass().getResource("../../../../../symbols/iron.png"));
          symbols.add(new imagewithindex(image,6));
          
          //add planets
          image = ImageIO.read(getClass().getResource("../../../../../planets/tutorialworld.png"));
          planet.add(new imagewithindex(image,0));
          image = ImageIO.read(getClass().getResource("../../../../../planets/fantasyworld.png"));
          planet.add(new imagewithindex(image,1));
          image = ImageIO.read(getClass().getResource("../../../../../planets/forestworld.png"));
          planet.add(new imagewithindex(image,2));
          image = ImageIO.read(getClass().getResource("../../../../../planets/promethianworld.png"));
          planet.add(new imagewithindex(image,3));
          image = ImageIO.read(getClass().getResource("../../../../../planets/necroworldtp.png"));
          planet.add(new imagewithindex(image,4));
          
          //add doodads
          image = ImageIO.read(getClass().getResource("../../../../../doodads/deadtreesmall1.png"));
          doodads.add(new imagewithindex(image,0));
          image = ImageIO.read(getClass().getResource("../../../../../doodads/deadtreesmall2.png"));
          doodads.add(new imagewithindex(image,0));
          image = ImageIO.read(getClass().getResource("../../../../../doodads/deadtreesmall3.png"));
          doodads.add(new imagewithindex(image,0));
          image = ImageIO.read(getClass().getResource("../../../../../doodads/deadtreesmall4.png"));
          doodads.add(new imagewithindex(image,0));
          image = ImageIO.read(getClass().getResource("../../../../../doodads/deadtreesmall5.png"));
          doodads.add(new imagewithindex(image,0));
          image = ImageIO.read(getClass().getResource("../../../../../doodads/deadtreesmall6.png"));
          doodads.add(new imagewithindex(image,0));
          image = ImageIO.read(getClass().getResource("../../../../../doodads/deadtreesmall7.png"));
          doodads.add(new imagewithindex(image,0));
          image = ImageIO.read(getClass().getResource("../../../../../doodads/deadtreesmall8.png"));
          doodads.add(new imagewithindex(image,0));
          image = ImageIO.read(getClass().getResource("../../../../../doodads/deadtreesmall9.png"));
          doodads.add(new imagewithindex(image,0));
          image = ImageIO.read(getClass().getResource("../../../../../doodads/deadtreesmall10.png"));
          doodads.add(new imagewithindex(image,0));
          
          image = ImageIO.read(getClass().getResource("../../../../../doodads/rottenrock1cropped.png"));
          doodads.add(new imagewithindex(image,1));
          image = ImageIO.read(getClass().getResource("../../../../../doodads/rottenrock2cropped.png"));
          doodads.add(new imagewithindex(image,2));
          
          image = ImageIO.read(getClass().getResource("../../../../../doodads/untitled1mini.png"));
          doodads.add(new imagewithindex(image,0));
          image = ImageIO.read(getClass().getResource("../../../../../doodads/untitled2mini.png"));
          doodads.add(new imagewithindex(image,0));
          image = ImageIO.read(getClass().getResource("../../../../../doodads/untitled6mini.png"));
          doodads.add(new imagewithindex(image,0));
          
          //loadfolder("doodads",doodads);
          //structures = splitImage(image,image.getWidth()/30,image.getHeight()/32);
          
          
          //add structures
          image = ImageIO.read(getClass().getResource("../../../../../structures/terrain1alpha.png"));
          structures = splitImage(image,image.getWidth()/30,image.getHeight()/32);
          
          //add npcs
          image = ImageIO.read(getClass().getResource("../../../../../npcs/monster1alpha.png"));
          npc = splitImage(image,image.getWidth()/30,image.getHeight()/32);
          
          //load coin animation
          image = ImageIO.read(getClass().getResource("../../../../../animations/coin.png"));
          int numframes = 7;
          Vector<BufferedImage> temp = new Vector<BufferedImage>();
          for(int i = 0; i < numframes;i++){
              temp.add(image);
          }
          animations.add(temp);
          
        } catch (IOException ex) {
            // handle exception...
        }
    }
    
    public static Vector<imagewithindex> splitImage(BufferedImage img, int width, int height) {  
         int w = width;  
         int h = height;  
         int rows = img.getHeight()/h;
         int cols = img.getWidth()/w;
         int num = 0;  
         Vector<imagewithindex> imgs = new Vector<imagewithindex>(w*h);  
         for(int y = 0; y < rows; y++) {  
             for(int x = 0; x < cols; x++) {  
                 imgs.addElement(new imagewithindex(new BufferedImage(w, h, img.getType()),x+y*w));  
                 // Tell the graphics to draw only one block of the image  
                 Graphics2D g = imgs.elementAt(num).getimage().createGraphics();  
                 g.drawImage(img, 0, 0, w, h, w*x, h*y, w*x+w, h*y+h, null);  
                 g.dispose();  
                 num++;  
             }  
         }  
         return imgs;  
    } 
    
    public Vector<BufferedImage> getanimation(int i){
        return animations.elementAt(i);
    }
     
    public imagewithindex gettexture(int i){
        return library.elementAt(i);
    }
    
    public imagewithindex getsymbol(int i){
        return symbols.elementAt(i);
    }
    
    public imagewithindex getplanet(int i){
        return planet.elementAt(i);
    }
    
    public imagewithindex getdoodad(int i){
        return doodads.elementAt(i);
    }
    
    public imagewithindex getnpc(int i){
        return npc.elementAt(i);
    }
    
    public imagewithindex getnpcwithindex(int i){
        for(int k = 0; k < npc.size();k++){
            if(npc.elementAt(k).getindex()==i){
                return npc.elementAt(k);
            }
        }
        return null;
    }
    
    public imagewithindex getstructure(int i){
        return structures.elementAt(i);
    }
    
    
}