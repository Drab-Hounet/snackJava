
package snake.drab.display;

import Snake.drab.attributes.Square;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class SquareGraphics extends JPanel{

    public ArrayList<ArrayList<Square>> listBoard;
    
    public SquareGraphics(ArrayList<ArrayList<Square>> listBoard){
        this.listBoard = listBoard;  
    }
    
    public SquareGraphics(){
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        int width = 100;
        for (int i = 0 ; i < this.listBoard.size() ; i ++){
            for(int j = 0 ; j < this.listBoard.get(i).size() ; j ++){
                g.setColor(this.listBoard.get(i).get(j).getColor());
                g.fillRect(j * width, i * width, width, width);
            }
        }
    }   
    
    public void updateGraphics(ArrayList<ArrayList<Square>> listboard) {
        this.listBoard = listboard;
        repaint();
    }
    
}
