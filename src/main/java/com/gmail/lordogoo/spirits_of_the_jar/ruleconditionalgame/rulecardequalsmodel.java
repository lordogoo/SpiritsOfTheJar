package com.gmail.lordogoo.spirits_of_the_jar.ruleconditionalgame;

import com.gmail.lordogoo.spirits_of_the_jar.cardgame.*;
/**
 * Write a description of class ruleiscard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class rulecardequalsmodel extends ruleconditionalmodel
{

    card compare;

    public rulecardequalsmodel(card c)
    {
        compare = c;
    }

    public boolean condition(card c){
        System.out.println(compare.getname()+" "+c.getname());
        if(compare.getname().equals(c.getname())){
            return true;
        }
        return false;
    }
    
    public String gettext(){
        return compare.getname();
    }
}
