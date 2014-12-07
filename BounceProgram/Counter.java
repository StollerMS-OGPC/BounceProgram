import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
/**
 * Write a description of class Counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Counter extends Actor
{
    private int value = 0;
    private int target = 0;
    private String text;
    private int stringLength;
    public Counter()
    {
        this("");
    }
    public Counter(String prefix)
    {
        text=prefix;
        stringLength = (text.length()+2)*16;
        setImage(new GreenfootImage(stringLength,24));

        GreenfootImage image=getImage();
        Font font=image.getFont();
        image.setFont(font.deriveFont(24.0F));//use larger font
        updateImage(0);
    }
    public void updateImage(int v)
    {
        GreenfootImage image = getImage(); //get an image object
        image.clear(); //clear the image, like wiping it clean
        image.drawString(text+v,1,18); //then draw the string
    }
}