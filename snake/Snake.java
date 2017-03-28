
package snake;

import snake.drab.display.DisplayGame;

/**
 *
 * @author jerome.lombard
 */
public class Snake {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DisplayGame game = new DisplayGame();
        game.init();
        
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                while(true){
                    game.runSnack();
                    try{
                        Thread.sleep(100);
                    }catch(InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };
        Thread th = new Thread(runner, "");
        th.start();
        
        
        
        
        
        
        game.setVisible(true);
    }
}
