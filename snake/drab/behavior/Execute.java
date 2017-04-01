
package snake.drab.behavior;

import snake.drab.display.*;

/**
 *
 * @author jerome.lombard
 */
public final class Execute {
    
    public static void loop(){
        DisplayGame game = new DisplayGame();
        game.init();
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                while(game.getGameContinue()){
                    game.runSnake();
                    try{
                        Thread.sleep(200);
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
