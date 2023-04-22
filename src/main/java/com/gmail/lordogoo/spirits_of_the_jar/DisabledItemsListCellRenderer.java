package com.gmail.lordogoo.spirits_of_the_jar;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class DisabledItemsListCellRenderer extends DefaultListCellRenderer {

     private Vector<Integer> enabledFlags;
     private static final long serialVersionUID = 1L;
        
     public DisabledItemsListCellRenderer(){
         super();
         enabledFlags = new Vector<Integer>();
     }
     
     
     @Override
     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
         Component comp = super.getListCellRendererComponent(list, value, index, false, false);
         JComponent jc = (JComponent) comp;
         if (isElementDisabled(index)) {
             if (isSelected & cellHasFocus) {
                 comp.setForeground(Color.black);
                 comp.setBackground(Color.red);
             } else {
                 comp.setForeground(Color.blue);
             }
             if (!isSelected) {
                 if ((value.toString()).trim().equals("yellow")) {
                     comp.setForeground(Color.orange);
                     comp.setBackground(Color.magenta);
                 }
             }
             return comp;
         }
         comp.setEnabled(false);
         return comp;
     }
     
     public void addDisabled(int i){
         if(!isElementDisabled(i)){
             enabledFlags.add(new Integer(i));
        }
     }
     
     public void removeDisabled(int i){
         for(int j = 0; j < enabledFlags.size();j++){
             if(enabledFlags.elementAt(j).intValue()==i){
                 enabledFlags.remove(j);
                 return;
             }
         }
     }
     
     public boolean isElementDisabled(int i){
         for(int j = 0; j < enabledFlags.size();j++){
             if(enabledFlags.elementAt(j).intValue()==i){
                 return true;
             }
         }
         return false;
     }
     
}
