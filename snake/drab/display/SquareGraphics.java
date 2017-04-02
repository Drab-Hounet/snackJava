
package snake.drab.display;

import Snake.drab.attributes.Square;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class SquareGraphics extends JPanel{

    private ArrayList<ArrayList<Square>> listBoard;
    private int sizeSquare;
    
    public SquareGraphics(ArrayList<ArrayList<Square>> listBoard, int sizeSquare){
        this.listBoard = listBoard;  
        this.sizeSquare = sizeSquare;
    }

    @Override
    public void paintComponent(Graphics g){
        
        int width = 800 / this.sizeSquare;
        for (int i = 0 ; i < this.listBoard.size() ; i ++){
            for(int j = 0 ; j < this.listBoard.get(i).size() ; j ++){
                g.setColor(this.listBoard.get(i).get(j).getFillColor());
                g.fillRect(j * width, i * width, width, width);
                g.setColor(this.listBoard.get(i).get(j).getOutLineColor());
                System.out.println(this.listBoard.get(i).get(j).getFillColor());
                System.out.println(this.listBoard.get(i).get(j).getOutLineColor());
                g.drawRect(j * width, i * width, width, width);
            }
        }
    }   
    
    public void updateGraphics(ArrayList<ArrayList<Square>> listboard) {
        this.listBoard = listboard;
        repaint();
    }
    
}
