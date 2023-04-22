package com.gmail.lordogoo.spirits_of_the_jar.gamepopup;
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
public class textpopup extends specialpopup
{
    
    JLabel text;
    JTextField input;
    JButton accept;
    JButton cancel;

    public textpopup(gamepanel g,texturelibrary i,String message,ActionListener acceptbutton, ActionListener rejectbutton)
    {
        super(g,i);
        
        double size[][] = 
        {{TableLayout.FILL,TableLayout.FILL},
        {TableLayout.FILL,30,30}};
        
        this.setLayout(new TableLayout(size));
        this.setVisible(true);
        
        text = new JLabel(message);
        text.setHorizontalAlignment( SwingConstants.CENTER );
        componentlist.add(text);
        this.add(text,"0,0,1,0");
        
        input = new JTextField();
        input.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == '|') {
                    e.consume();  // ignore event
                }
                if(!input.getText().equals("")){
                    accept.setEnabled(true);
                }else{
                    accept.setEnabled(false);
                }
                
            }
        });
        componentlist.add(input);
        this.add(input,"0,1,1,1");
        
        accept = new  specialboarderbutton("accept",i.gettexture(4).getimage(),null,5,null);
        accept.setEnabled(false);
        if(acceptbutton!=null){
            accept.addActionListener(acceptbutton);
        }
        componentlist.add(accept);
        this.add(accept,"0,2");
        
        cancel = new specialboarderbutton("cancel",i.gettexture(4).getimage(),null,5,null);
        /*
        if(gp.istutorialrunning()){
            cancel.setEnabled(false);
        }
        */
        if(rejectbutton!=null){
            cancel.addActionListener(rejectbutton);
        }
        componentlist.add(cancel);
        this.add(cancel,"1,2");
    }
    
    public JTextField gettextfield(){
        return input;
    }

}
