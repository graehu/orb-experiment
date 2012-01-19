
package Main;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintStream;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import GameStates.TestGameState;

public class Main extends StateBasedGame
{
    public Main()
    {
        super("Orb Experiment");
    }
    
    public static void main(String[] arguments) throws FileNotFoundException
    {        
    	//Stream errors to file
        File outputFile = new File("appOut.txt");
        PrintStream errorStream = new PrintStream(outputFile);
        System.setOut(errorStream);
        
        //Load application
        try
        {            
            AppGameContainer app = new AppGameContainer(new Main());
            //app.setIcon("icon.png");
            app.setDisplayMode(640, 480, false);
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

    BasicGameState mTestGameState;
    
	@Override
	public void initStatesList(GameContainer _gc) throws SlickException 
	{
		// TODO Auto-generated method stub
		mTestGameState = new TestGameState();
		addState(mTestGameState);
		
		enterState(0);
	}
}
