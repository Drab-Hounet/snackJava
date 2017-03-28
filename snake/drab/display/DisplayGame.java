
package snake.drab.display;

import Snake.drab.attributes.Square;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author jerome.lombard
 */
public class DisplayGame extends javax.swing.JFrame implements KeyListener {

    public ArrayList<ArrayList<Square>> listBoard = new ArrayList();
    private final int[] placementPresent = {0,0}; // {y,x} 
    private final int[] placementApple = {0,0};
    private final int sizeSquare = 25;
    private final SquareGraphics  squareGraphics = new SquareGraphics(listBoard, this.sizeSquare);
    private String direction = "RIGHT";
     
    public DisplayGame() {
        initComponents();   
    }
    
    /**
     *
     * @return
     */
    public int getSizeSquare(){
        return this.sizeSquare;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(this.placementPresent[1] + " - "  + this.placementPresent[0]);
        //System.out.println("--> apple : " + this.placementApple[1] + " - "  + this.placementApple[0]);
        switch (e.getKeyCode()) {
            case 37 :
                //test if the snack is out of the board (left side)
                this.direction = "LEFT";
                this.runSnack();
                break;
            case 39:
                //test if the snack is out of the board right side)
                this.direction = "RIGHT";
                this.runSnack();
                break;
            case 38:
                //test if the snack is out of the board (top side)
                this.direction = "TOP";
                this.runSnack();
                break;
            case 40:
                //test if the snack is out of the board (bottom side)
                this.direction = "BOTTOM";
                this.runSnack();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
    public void init(){
        //making of the board
        addKeyListener (this);

        //initialisation of the board
        for(int j = 0 ; j < this.sizeSquare ; j ++  ){
            ArrayList<Square> rowSquare = new ArrayList();
            for(int i = 0 ; i < this.sizeSquare ; i ++ ){
                rowSquare.add(new Square("default"));
            }            
            listBoard.add(rowSquare);
        } 
        //make origin
        this.listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("SNACKHEAD");
        this.setApple();
        this.setContentPane(squareGraphics);
    }
    
    public void runSnack() {
        boolean game = true;
        
            switch (this.direction) {
            case "LEFT" :
                //test if the snack is out of the board (left side)
                if(this.placementPresent[1] == 0){
                    System.out.println("out");
                    game = false;
                    break;
                }
                this.getIfApple("LEFT");
                this.direction = "LEFT";
                this.goTo(this.direction);
                break;
            case "RIGHT":
                //test if the snack is out of the board right side)
                if(this.placementPresent[1] == listBoard.get(0).size() - 1 ){ 
                    System.out.println("out -- R");
                    game = false;
                    break;
                }
                this.getIfApple("RIGHT");
                direction = "RIGHT";
                this.goTo(this.direction);
                break;
            case "TOP":
                //test if the snack is out of the board (top side)
                if(this.placementPresent[0] == 0){
                    System.out.println("out");
                    game = false;                    
                    break;
                }
                this.getIfApple("TOP");
                direction = "TOP";
                this.goTo(this.direction);
                
                break;
            case "BOTTOM":
                //test if the snack is out of the board (bottom side)
                if(this.placementPresent[0] == listBoard.get(0).size() - 1){
                    System.out.println("out");
                    game = false;
                    break;
                } 
                this.getIfApple("BOTTOM");
                direction = "BOTTOM";
                this.goTo(this.direction);
                break;
            default:
                break;
            } 
    }

    public void getIfApple(String deplacement){
        switch(deplacement){
            case "LEFT":
                if(this.placementPresent[0] == this.placementApple[0] && (this.placementPresent[1] - 1) == this.placementApple[1]){
                    this.setApple();
                } 
                break;
            case "RIGHT":
                if(this.placementPresent[0] == this.placementApple[0] && (this.placementPresent[1] + 1) == this.placementApple[1]){
                    this.setApple();
                } 
                break;
            case "BOTTOM":
                if((this.placementPresent[0] + 1) == this.placementApple[0] && (this.placementPresent[1]) == this.placementApple[1]){
                    this.setApple();
                } 
                break;
            case "TOP":
                if((this.placementPresent[0] - 1)  == this.placementApple[0] && (this.placementPresent[1]) == this.placementApple[1]){
                    this.setApple();
                } 
                break;
        }
    }
    
    public void setApple(){
        //        
        this.placementApple[0] = ThreadLocalRandom.current().nextInt(0, (this.sizeSquare)) ;
        this.placementApple[1] = ThreadLocalRandom.current().nextInt(0, (this.sizeSquare)) ;
        
        System.out.println("apple : " + this.placementApple[0] + " - " +  this.placementApple[1]);
        listBoard.get(this.placementApple[0]).get(this.placementApple[1]).setType("APPLE");
        squareGraphics.updateGraphics(listBoard);
        repaint();       
    }
    
    public void goTo(String direction){

        switch(direction){
            case "LEFT":
                listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("default");
                this.placementPresent[1]--;
                listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("SNACKHEAD");
                squareGraphics.updateGraphics(listBoard);
                repaint();
                break;
            case "RIGHT":
                listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("default");
                this.placementPresent[1]++;
                listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("SNACKHEAD");
                squareGraphics.updateGraphics(listBoard);
                repaint();  
                break;
            case "TOP":
                listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("default");
                this.placementPresent[0]--;
                listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("SNACKHEAD");
                squareGraphics.updateGraphics(listBoard);
                repaint();      
                break;
            case "BOTTOM":
                listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("default");
                this.placementPresent[0]++;
                listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("SNACKHEAD");
                squareGraphics.updateGraphics(listBoard);
                repaint();  
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
