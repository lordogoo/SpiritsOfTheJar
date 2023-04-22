package com.gmail.lordogoo.spirits_of_the_jar.gameinterface;
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

public class CustomCellRenderer implements ListCellRenderer {
        public Component getListCellRendererComponent
                         (JList list, Object value, int index, 
                         boolean isSelected,boolean cellHasFocus) {
                             
            Component component = (Component)value;
            //((JPanel)component).setBorder(BorderFactory.createLineBorder(Color.gray));
            return component;
     }
}
