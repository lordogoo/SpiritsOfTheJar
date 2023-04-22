package com.gmail.lordogoo.spirits_of_the_jar.controlers;

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
import com.gmail.lordogoo.spirits_of_the_jar.controlers.*;

public class initfile
{

    menucontrol cont;

    public initfile(menucontrol cont)
    {
        this.cont = cont;
        try{
             String profilename;
             File dir =  new File("init");
             if(!dir.createNewFile()){
                 			FileReader fileReader = new FileReader(dir);
                 			BufferedReader bufferedReader = new BufferedReader(fileReader);
                 			profilename = bufferedReader.readLine();
                 			fileReader.close();
                 			cont.setcurrentprofile(cont.loadprofile(profilename));
             }
        }catch(Exception e){
                   
        }
    }

    public void saveinformation(){
    
         try{
             File dir =  new File("init");
             if(!dir.createNewFile()){
                 PrintWriter out = new PrintWriter(dir);
                 out.println(cont.getcurrentprofile().getname());
                 out.close();
             }
         }catch(Exception e){
             //e.printStackTrace();
         }
    
    }
    
}