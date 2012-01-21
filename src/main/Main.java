
package main;

import gameStates.TestGameState;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintStream;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Main extends StateBasedGame
{
    public Main()
    {
        super("Orb Experiment");
    }
    
    /**
     * @param arguments
     * @throws FileNotFoundException
     */
    public static void main(String[] arguments) throws FileNotFoundException
    {        
    	//FIXME: Stream errors to file
        //File outputFile = new File("appOut.txt");
        //PrintStream errorStream = new PrintStream(outputFile);
        //System.setOut(errorStream);
        
        //Load application
        try
        {            
            AppGameContainer app = new AppGameContainer(new Main());
            //app.setIcon("icon.png");
            app.setDisplayMode(800, 640, false);
            app.setShowFPS(true);
            app.setVSync(true);
            //app.setTargetFrameRate(60);
            app.start();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

    BasicGameState m_TestGameState;
    
	@Override
	public void initStatesList(GameContainer _gc) throws SlickException 
	{
		// TODO Auto-generated method stub
		m_TestGameState = new TestGameState();
		addState(m_TestGameState);
		
		enterState(0);
	}
}
