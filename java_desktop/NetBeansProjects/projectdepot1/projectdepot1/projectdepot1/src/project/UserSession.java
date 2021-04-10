/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author asus
 */
public class UserSession {
    private static String acces;
    private static String name;
    public static String getacces()
    {
        return acces;
    }
    public static void setacces(String acces)
    {
        UserSession.acces = acces;
    }
    public static String getname(){
        return name;
    }
    public static void setname(String name){
        UserSession.name = name;
    }
}
