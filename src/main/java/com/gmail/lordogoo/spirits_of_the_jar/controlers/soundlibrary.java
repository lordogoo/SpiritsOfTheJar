package com.gmail.lordogoo.spirits_of_the_jar.controlers;

import java.util.*;
import javax.sound.sampled.*;
import javax.sound.midi.*;


public class soundlibrary
{
    
    Vector<Clip> soundeffect;
    Vector<Clip> music;
    
    

    public soundlibrary()
    {
        soundeffect = new Vector<Clip>();
        
        try{
            Clip input = AudioSystem.getClip();
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource("../../../../../sounds/25847__freqman__concrete-blocks-moving3.wav"));
            input.open(audioIn);
            soundeffect.add(input);
            
            
            
            
            
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Clip getsoundeffect(int i){
        return soundeffect.elementAt(i);
    }

    public void playsoundeffect(int i){
        if(soundeffect.elementAt(i).isRunning()){
           soundeffect.elementAt(i).stop(); 
        }
        soundeffect.elementAt(i).setFramePosition(0);
        soundeffect.elementAt(i).start();
    }
    
}
