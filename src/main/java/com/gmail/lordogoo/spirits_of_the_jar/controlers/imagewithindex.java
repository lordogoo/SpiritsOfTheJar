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

public class imagewithindex
{

    BufferedImage image;
    int index;

    public imagewithindex(BufferedImage im,int in)
    {
        image = im;
        index = in;
    }

    public BufferedImage getimage(){
        return image; 
    }
    
    public int getindex(){
        return index;
    }
}
