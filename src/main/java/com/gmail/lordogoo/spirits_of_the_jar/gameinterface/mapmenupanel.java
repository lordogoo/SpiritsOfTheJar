package com.gmail.lordogoo.spirits_of_the_jar.gameinterface;
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
public class mapmenupanel extends gamepanel
{

    JButton back;
    mapdisplay map;
    
    public mapmenupanel(String n,menucontrol c,texturelibrary i,soundlibrary s,int bi)
    {
        super(n,c,i,s,bi);
        
        double size[][] = 
        {{TableLayout.FILL,20,.8,20,TableLayout.FILL},
        {TableLayout.FILL,20,30,.8,20,TableLayout.FILL,1}};
        
        this.setdimensions(4,5);
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        panel = new specialboarderpanel(i.gettexture(4).getimage(),10,null);
        this.add(panel,"1,1,3,4");
        
        map = new mapdisplay(i.gettexture(6).getimage());
        this.add(map,"2,3");
        this.setComponentZOrder(map,0);
        componentlist.add(map);
        
        back = new specialboarderbutton("back",i.gettexture(4).getimage(),this,5,null);
        back.addActionListener(new backclick());
        this.add(back,"2,2");
        this.setComponentZOrder(back,0);
        componentlist.add(back);
        
    }
    
    public void switchto(menucontrol g){
        map.switchto(g.getcurrentprofile().getgame().getcurrentworld());
        map.repaint();
        super.switchto(g);
    }
    
    private class backclick implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            setupswitchpanel("playpanel");
        }    
    }
    
    private class mapdisplay extends JPanel{

        BufferedImage texture;
        worldinstanciated world;
        terraininstanciated terrain;
        
        public mapdisplay(BufferedImage t){
            texture = t;
        }
        
        public void switchto(worldinstanciated w){
            world = w;
            terrain = w.getterrain();
            
        }
        
        public void paintComponent(Graphics g) {
            try{
            Graphics2D g2d = (Graphics2D) g;
            if(terrain != null){
               int screenwidth = this.getWidth();
               int screenheight = this.getHeight();
               int mapwidth = terrain.getwidth();
               int mapheight = terrain.getheight();
               int offsetx = world.getoffsetx();
               int offsety = world.getoffsety();
               int soffsetx = offsetx-this.getWidth()/2;
               int soffsety = offsety-this.getHeight()/2;
               
               int playerwidth = 4;
               int playerheight = 4;
               int structurewidth = 6;
               int structureheight = 6;
              
               //copy defaults
               Color defaultcolor = g.getColor();
               g.setColor(Color.black);
               g.fillRect(0,0,this.getWidth(),this.getHeight());
               //draw polygons
               
               g.setColor(Color.blue);
               BufferedImage imm = texture;
               Rectangle2D rect = new Rectangle2D.Double(0,0,screenwidth,screenheight);
               g2d.setPaint(new  TexturePaint(imm,rect));
               
               Polygon p = new Polygon();
               
               p.addPoint(0,0);
               p.addPoint(screenwidth,0);
               p.addPoint(screenwidth,screenheight);
               p.addPoint(0,screenheight);

               g2d.fillPolygon(p);
               

               
               int playerxloc = (((offsetx+(mapwidth/2))-playerwidth)*screenwidth)/mapwidth;
               int playeryloc = (((offsety+(mapheight/2))-playerheight)*screenheight)/mapheight;
               
               g.setColor(Color.green);
               g.drawRect(playerxloc,playeryloc,playerwidth,playerheight);
               
               g.setColor(Color.blue);
               for(int i = 0; i < world.getterrain().getstructurenum(); i++){
                   structureinstansiated struct = world.getterrain().getstructure(i);
                   int structurex = (((struct.getx()+(mapwidth/2))-playerwidth)*screenwidth)/mapwidth;
                   int structurey = (((struct.gety()+(mapheight/2))-playerheight)*screenheight)/mapheight;
                   
                   g.drawRect(structurex,structurey,structurewidth,structureheight);
               }
               
            }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    
    } 
    
}
