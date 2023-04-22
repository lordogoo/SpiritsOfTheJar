package com.gmail.lordogoo.spirits_of_the_jar.gameobject;

import javax.swing.event.*;
import info.clearthought.layout.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
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
public class terrainmodel
{
    menucontrol control;
    
    int xloc;
    int yloc;
    int width;    
    int height;
    
    int startx;
    int starty;
    
    worldmodel worldref;
    gameinfocontrol ginfo;
    
    Vector<terraintriggerpolygon> polygonscripts;
    Vector<terraintriggerdoodad> doodadscripts;
    Vector<terraintriggerstructure> structurescripts;
    Vector<terraintriggernpc> npcscripts;
    Vector<terraintriggerbadguy> badguyscripts;
    
    public terrainmodel(int x,int y,int w,int h,int sx,int sy,gameinfocontrol g,menucontrol cont)
    {
        control = cont;
        xloc = x;
        yloc = y;
        width = w;
        height = h;
        
        startx = sx;
        starty = sy;
        
        ginfo = g;
        Random r = new Random();
        
        polygonscripts = new Vector<terraintriggerpolygon>();
        doodadscripts = new Vector<terraintriggerdoodad>();
        structurescripts = new Vector<terraintriggerstructure>();
        npcscripts = new Vector<terraintriggernpc>();
        badguyscripts = new Vector<terraintriggerbadguy>();

    }
    
    public void addworld(worldmodel wr){
        worldref = wr;
    }
    
    public worldmodel getworld(){
        return worldref;
    }
    
    public terraininstanciated instanciateterrain(worldinstanciated wr){
        terraininstanciated t = new terraininstanciated(this,wr);
        return t;
    }
    
    
    /*************************
     * create methods
     */
    //create terrain peices
    public void createpolygons(Random r,terraininstanciated t){
       for(int i = 0; i < polygonscripts.size(); i++){
           polygonscripts.elementAt(i).trigger(control,t,this,r);
       }
    }
    
    //create doodads
    public void createdoodads(Random r,terraininstanciated t){
       for(int i = 0; i < doodadscripts.size(); i++){
           doodadscripts.elementAt(i).trigger(control,t,this,r);
       }
    }
    
    //todo
    //create structures
    public void createstructures(Random r,terraininstanciated t){
       for(int i = 0; i < structurescripts.size(); i++){
           structurescripts.elementAt(i).trigger(control,t,this,r);
       }
    }
    
    //create npc's
    public void createnpc(Random r,terraininstanciated t){
       for(int i = 0; i < npcscripts.size(); i++){
           npcscripts.elementAt(i).trigger(control,t,this,r);
       }
    }
    
    //create badguys 
    public void createbadguy(Random r,terraininstanciated t){
       for(int i = 0; i < badguyscripts.size(); i++){
           badguyscripts.elementAt(i).trigger(control,t,this,r);
       }
    }
    
    
    //create roumors
    public roumorlistmodel createrumors(badguyinstansiated b,npcinstanciated n){
        //might need to add world location info
        roumorlistmodel temp = new roumorlistmodel();
        temp.addroumor(new roumormodel(0,b.getmodel().getname(),b));
        temp.addroumor(new roumormodel(1,b.getmodel().getdiscription(),b));
        temp.addroumor(new roumormodel(2,b.getmodel().getimage(),b));
        temp.addroumor(new roumormodel(3,n.getmodel().getname(),b));
        temp.addroumor(new roumormodel(4,n.getname(),b));
        temp.addroumor(new roumormodel(5,n.getbody().getimage(),b));
        return temp;
    }
    
    /***************************
     *set get methods 
     */
    

    //set/get methods
    public int getx(){
        return xloc;
    }
    
    public int gety(){
        return yloc;
    }
    
    public void setx(int x){
        xloc = x;
    }
    
    public void sety(int y){
        yloc = y;
    }
    
    public int getwidth(){
        return width;
    }
    
    public int getheight(){
        return height;
    }
    
    public int getstartx(){
        return startx;
    }
    
    public int getstarty(){
        return starty;
    }
    
    //randomizers
    
    /*****************************************
     * point placement randomization algorithm
     *****************************************/
    public Vector<pointint> randomizepoints(int dist,int std,int xcenter,int ycenter,
                          int xstart,int ystart,int width,int height){
                              
        System.out.println("point randomization alg begin");                      
        Vector<pointint> points = new Vector<pointint>(); 
        Random rand = new Random();
        
        //generate the distance from center to all four corners
        int topleftdistx = (xcenter - xstart)*(xcenter - xstart);
        int topleftdisty = (ycenter - ystart)*(ycenter - ystart);
        int tld = (int)Math.sqrt(topleftdistx + topleftdisty);
        //System.out.println("top left corner dist "+tld);
        
        int toprightdistx = ((xstart+width)-xcenter)*((xstart+width)-xcenter);
        int toprightdisty = (ycenter - ystart)*(ycenter - ystart);
        int trd = (int)Math.sqrt(toprightdistx + toprightdisty);
        //System.out.println("top right corner dist "+trd);
        
        int botleftdistx = (xcenter - xstart)*(xcenter - xstart);
        int botleftdisty = ((ystart+height)-ycenter)*((ystart+height)-ycenter);
        int bld = (int)Math.sqrt(botleftdistx+botleftdisty);
        //System.out.println("bottom left corner dist "+bld);
        
        int botrightdistx = ((xstart+width)-xcenter)*((xstart+width)-xcenter);
        int botrightdisty = ((ystart+height)-ycenter)*((ystart+height)-ycenter);
        int brd = (int)Math.sqrt(botrightdistx+botrightdisty);
        //System.out.println("bottom right corner dist "+brd);
        
        //generate centerpoint and displacement vector then add it
        int disangle = Math.abs(rand.nextInt()%360);
        int disdist = Math.abs(rand.nextInt()%std);
        int disx = disdist*(int)Math.cos(disangle);
        int disy = disdist*(int)Math.sin(disangle);
        pointint center = new pointint(xcenter+disx,ycenter+disy);
        //points.add(center);
        
        int r = dist;
        //main algorithm
        //creates random circles of points
        while( r <= Math.max(Math.max(tld,trd),Math.max(bld,brd))){
            //System.out.println("current radious "+r);
            //given the side length and radious find the number of
            //sides of the polygon
            double numsides = Math.floor(Math.PI/Math.asin((double)dist/(2*(double)r)));
           
            //generate a random angle to modify the rotation of the circle
            double randangle = Math.toRadians((double)Math.abs(rand.nextInt()%360));
            
            //generate the angle between points of the polygon 
            double angle = Math.toRadians(360/numsides);
            
            //System.out.println("random angle "+randangle);
            //System.out.println("numsides "+numsides);
            //System.out.println("angle "+angle);
            
           //generate the points 
            //System.out.println("add points");
            for(int i = 0; i < numsides;i++){
                
                double dx = r*Math.cos(randangle+i*angle);
                double dy = r*Math.sin(randangle+i*angle);
                
                double ndisangle = Math.toRadians(Math.abs(rand.nextInt()%360));
                double ndisdist = dist/2;//Math.abs(rand.nextInt()%std);
                double ndisx = ndisdist*Math.cos(ndisangle);
                double ndisy = ndisdist*Math.sin(ndisangle);
                
                //TODO
                //need to make sure that the location randomization dosent put things on top of each other
                //pointint p = new pointint((int)(xcenter+dx+ndisx),(int)(ycenter+dy+ndisy));
                pointint p = new pointint((int)(xcenter+dx),(int)(ycenter+dy));
                //make sure not add points outside map
                if   (((p.getx()>xstart)&&(p.getx()<xstart+width))
                   &&((p.gety()>ystart)&&(p.gety()<ystart+height))){
                    //System.out.print("("+p.getx()+","+p.gety()+"("+ndisx+","+ndisy+"))");
                    points.add(p);
                }
            }
            //System.out.println();
            //increase radious
            r = r + dist;
        }
        
        System.out.println("point randomization alg end");
        return points;
    }
    
    public static int getPoisson(double lambda) {
        double L = Math.exp(-lambda);
        double p = 1.0;
        int k = 0;

        do {
            k++;
            p *= Math.random();
        } while (p > L);

        return k - 1;
    }

    
    //helper functions for inner polygon
    public double findtheta(pointint point1,pointint point2){
        int dx = point2.getx()-point1.getx();
        int dy = point2.gety()-point1.gety();
        double l = Math.sqrt(dx*dx+dy*dy);
        double theta = Math.acos(dx/l); 
        return theta;
    }
    
    
    public Polygon innerpolygon(Vector<pointint> pointcloud,pointint point){
        //1) copy pointcloud & make sure point is not in point cloud
        Vector<pointint> pointcopy = new Vector<pointint>();
        for(int i = 0; i < pointcloud.size();i++){
            pointint temp = pointcloud.elementAt(i);
            if((point.getx() == temp.getx())&&(point.gety()== temp.gety())){
            }else{
                //insert in sorted location.
                //sort by angle to "point"
                pointint tempcopy = new pointint(temp.getx(),temp.gety());
                int j = 0;
                while((findtheta(point,tempcopy) < 
                      findtheta(point,pointcloud.elementAt(j)))&&
                      (j<pointcloud.size())){
                    j++;
                }
                pointcopy.insertElementAt(tempcopy,j);
            }
        }
        
        //4) 
        //5)run convex hull
        for(int i = 0; i < 10;i++){
        }
        
        return null;
    }
    
    //add scripts
    public void addpolygonscript(terraintriggerpolygon ttp){
        polygonscripts.add(ttp);
    }
    
    public void adddoodadscript(terraintriggerdoodad ttd){
        doodadscripts.add(ttd);
    }
    
    public void addstructurescript(terraintriggerstructure tts){
        structurescripts.add(tts);
    }
    
    public void addnpcscript(terraintriggernpc ttn){
        npcscripts.add(ttn);
    }

    public void addbadguyscript(terraintriggerbadguy ttb){
        badguyscripts.add(ttb);
    }   

}
