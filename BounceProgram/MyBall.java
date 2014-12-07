import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class MyBall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyBall extends Actor

{
    GreenfootImage img = new GreenfootImage(MAX_WIDTH, MAX_HEIGHT);
    //need to declare these two variables as part of the FIELDs

    private static final int MAX_WIDTH = 100;
    private static final int MAX_HEIGHT = 200;

    int red = Greenfoot.getRandomNumber(256);
    int green =  Greenfoot.getRandomNumber(256);
    int blue =  Greenfoot.getRandomNumber(256);
    int alpha =  Greenfoot.getRandomNumber(256); //for transparency
   
     private Color randomColor()
    {
       int r = Greenfoot.getRandomNumber(256);
       int g = Greenfoot.getRandomNumber(256);
       int b = Greenfoot.getRandomNumber(256);
       int a = Greenfoot.getRandomNumber(256);
       return new Color(r, g, b, a);
 
    }
    
    public void setMyImage()
    {
        GreenfootImage img = new GreenfootImage(MAX_WIDTH,MAX_HEIGHT);
        img.setColor(randomColor());
        int size = Greenfoot.getRandomNumber(MAX_WIDTH);
        img.fillOval(0,0,size,size);
        setImage(img);
    }
    
    public MyBall(int nRotation, int nImageNum)
    {
        setRotation(nRotation);
        String strImageName = "button-" + nImageNum + ".png";
        
        setImage(strImageName);

        img.setColor(new Color(red, green, blue, alpha));
       
        img.drawOval(0, 0, MAX_WIDTH, MAX_HEIGHT);
        setImage(img);
        img.fillOval(0, 0, MAX_WIDTH, MAX_HEIGHT);
        setImage(img);
    }

    /**
     * Act - do whatever the MyBall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {	
        move(5);
        if( atWorldEdge() ){
            turn(Greenfoot.getRandomNumber(360));  //turn to a random rotation
            changeImageRandom();  //change image, see details later

        } 

    } 
    private int nImageNum;
    public boolean atWorldEdge()
    {
        if( getX() < 10 || getX() > getWorld().getWidth() - 15 )
            return true;

        if ( getY() < 10 || getY() > getWorld().getHeight() - 15 )
            return true;
        else 
            return false;
    }
   
    public void changeImageRandom()
    {
        nImageNum++;
        if (nImageNum > 4)
            nImageNum = 0;

        String strImageName = "button-" + nImageNum + ".png";
        setImage(strImageName);

    }
    
   void changeTransparency()
   {
      GreenfootImage img = getImage();
      
      // get current transpancy value
      int alpha = img.getTransparency();
      
      img.setTransparency(alpha - 1);

      getWorld().removeObject(this);
   }
}
