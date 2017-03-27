
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
        game.run();
        game.setVisible(true);
    }
}
