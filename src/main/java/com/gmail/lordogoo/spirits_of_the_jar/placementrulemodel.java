package com.gmail.lordogoo.spirits_of_the_jar; 
import java.util.*;
import com.gmail.lordogoo.spirits_of_the_jar.destinationrulegame.*;
/**
 * Write a description of class placementrulemodel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class placementrulemodel extends rulemodel
{

    Vector<ruledestinationmodel> constraint;

    public placementrulemodel()
    {
        
        constraint = new Vector<ruledestinationmodel>();
    }
    
    public void addconstraint(ruledestinationmodel dest){
        constraint.add(dest);
    }
    
    public int numconstraint(){
        return constraint.size(); 
    }
    
    public ruledestinationmodel getconstraint(int i){
        return constraint.elementAt(i);
    }
    
    public String getplacementstring(){
        String st = "";
        for(int i = 0; i < constraint.size(); i++){
            st += constraint.elementAt(i).getplacementstring();
            if(i != constraint.size() - 1){
                st+= " or ";
            }
        }
        return st;
    }
    
}
