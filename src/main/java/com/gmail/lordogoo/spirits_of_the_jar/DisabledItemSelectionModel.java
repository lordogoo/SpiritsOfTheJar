package com.gmail.lordogoo.spirits_of_the_jar;
import java.awt.*;
import javax.swing.*;
import java.util.*;    

public class DisabledItemSelectionModel extends DefaultListSelectionModel {

     private Vector<Integer> enabledFlags;
     private static final long serialVersionUID = 1L;

     public DisabledItemSelectionModel(){
         super();
         enabledFlags = new Vector<Integer>();
     }
     
     @Override
     public void setSelectionInterval(int index0, int index1) {
         if (isElementDisabled(index0)) {
             super.setSelectionInterval(index0, index0);
         } else {
                /*
                 * The previously selected index is before this one,
                 * so walk forward to find the next selectable item.
                 */
             if (getAnchorSelectionIndex() < index0) {
                 for (int i = index0; i < enabledFlags.size(); i++) {
                     if (isElementDisabled(i)) {
                         super.setSelectionInterval(i, i);
                         return;
                     }
                 }
             } /*
              * Otherwise, walk backward to find the next selectable item.
              */ else {
                 for (int i = index0; i >= 0; i--) {
                     if (isElementDisabled(i)) {
                         super.setSelectionInterval(i, i);
                         return;
                     }
                 }
             }
         }
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
