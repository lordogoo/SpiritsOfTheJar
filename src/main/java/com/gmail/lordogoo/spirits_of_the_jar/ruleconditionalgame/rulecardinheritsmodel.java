package com.gmail.lordogoo.spirits_of_the_jar.ruleconditionalgame;

import com.gmail.lordogoo.spirits_of_the_jar.cardgame.*;
/**
 * Write a description of class rulecardinheritsmodel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class rulecardinheritsmodel extends ruleconditionalmodel
{

    card condition;
    
    public rulecardinheritsmodel(card c)
    {
        condition = c;
    }

    public boolean condition(card c){
        card check = condition;
        if (check != null){
            if(check.getname().equals(c.getname())){
                return true;
            }
            //TODO
            //check = check.getinheritor();
        }
        return false;
    }
    
    public String gettext(){
        return "that inherits from "+condition.getname();
    }
}
