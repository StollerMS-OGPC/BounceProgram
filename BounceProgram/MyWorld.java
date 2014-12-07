import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.awt.Color;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    MyBall objMyBallOrig;
    GreenfootImage imageBackground = getBackground();
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        Counter counter = new Counter("Score: ");
        //int nWidth = 600;
        //int nHeight = 400;
        //int nPixel = 1;
        prepare();
        setPaintOrder(ScoreBoard.class, MyBall.class, Counter.class);
        ctr = new Counter("Score:");
        addObject(ctr,65,15);
        GreenfootImage imageBackground =getBackground();
        imageBackground.setColor(Color.GREEN);
        imageBackground.fill();


    }
    java.util.List listBallsHit = null; //list holding the balls get hit
    java.util.List listBallsAll = null; //list holding all the balls
    
    Counter ctr;
    List<MyBall> listBallsExtra;
    
    
    int nTotalScore = 0;
    int nHighScore = 20;
    int nLowScore = 10;
    int nDeductScore = 5;

    MouseInfo objMouseInfo;
    public void act()
    {
        if (Greenfoot.mouseClicked(null))
        {
            objMouseInfo = Greenfoot.getMouseInfo();
            objMouseInfo.getX();
            objMouseInfo.getY();

            getObjectsAt(objMouseInfo.getX(),objMouseInfo.getY(),MyBall.class);

            listBallsHit = getObjectsAt(objMouseInfo.getX(), objMouseInfo.getY(), MyBall.class);
            if ( listBallsHit != null && (listBallsHit.isEmpty()) == false )
            {
                if ( listBallsHit.get(0) == objMyBallOrig )
                {   
                    //get all objects of class type MyBall
                    listBallsAll = getObjects(MyBall.class);

                    //find out how many elements in the list/array
                    int nTotalBallsInList = listBallsAll.size();
                    removeObjects(getObjects(MyBall.class)); ///and remove all balls on the screen 
                    
                    //hit the original ball
                    nTotalScore=nTotalScore + nHighScore;
                    ctr.updateImage(nTotalScore);
                    if ( nTotalBallsInList > 1 )
                    {
                        //there are more than 1 balls alive, including the current one
                        Greenfoot.playSound("buzz.wav");
                        gameOver();
                    } 
                    else
                    {
                        // yes, this oBallOrig is the only ball alive 
                        Greenfoot.playSound("buzz.wav");
                        removeObject((MyBall)listBallsHit.get(0));
                        gameOver();
                        //wonderful, we are all done gracefully
                    }
                }
                else if (listBallsExtra!= null && (listBallsExtra.isEmpty() || !( listBallsExtra.contains(listBallsHit.get(0)))))
                {
                    nTotalScore=nTotalScore+nHighScore;                 
                } 
                else
                {
                    Greenfoot.playSound("explosion.wav");
                    removeObject((MyBall)listBallsHit.get(0));
                    nTotalScore=nTotalScore+nHighScore;
                    ctr.updateImage(nTotalScore);
                }
            }
            else            {
                List<MyBall> listBallsExtra = new ArrayList<MyBall>();
                int nRotation, nImageNum;
                nRotation=Greenfoot.getRandomNumber(360);
                nImageNum=Greenfoot.getRandomNumber(1000)%5;
                
                MyBall objMyBall = new MyBall(nRotation, nImageNum);
                
                addObject(objMyBall,objMouseInfo.getX(),objMouseInfo.getY());
                Greenfoot.playSound("pop.wav");
                nTotalScore = nTotalScore - nDeductScore;
                listBallsExtra.add(objMyBall);
                ctr.updateImage(nTotalScore);
            }
        }
    }

    public void prepare()
    {
        int nRotation = Greenfoot.getRandomNumber(360);
        int nImageNum = 0; 
        int x = Greenfoot.getRandomNumber(getWidth());
        int y = Greenfoot.getRandomNumber(getHeight());
        int nMaxBall = 5;
        if (x<20)
            x=20;
        if (x> getWidth() -20)
            x= getWidth()-20;
        if(y<20)
            y=20;
        if (y>getHeight()-20)
            y = y -20;

        objMyBallOrig = new MyBall(nRotation, nImageNum);
        addObject(objMyBallOrig, x, y); 
        int nNumOfBallsCreated = 0;
        while (nNumOfBallsCreated < nMaxBall)
        {
            nRotation = Greenfoot.getRandomNumber(360);
            nImageNum = Greenfoot.getRandomNumber(1000) % 4 + 1; 
            addObject ((new MyBall(nRotation, nImageNum)), Greenfoot.getRandomNumber(580)+20, Greenfoot.getRandomNumber(380)+15);

            nNumOfBallsCreated++;
        }
    }
    private void gameOver()
    {
        addObject(new ScoreBoard(nTotalScore), getWidth()/2, getHeight()/2);
        Greenfoot.stop();//this should will stop the game, and internally all objects are destroyed and memory re-claimed
    }
}