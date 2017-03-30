
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
    private final int[] placementPresent = {1,1}; // {y,x} 
    private final int sizeSquare = 4;
    private final SquareGraphics  squareGraphics = new SquareGraphics(listBoard, this.sizeSquare);
    private String direction = "RIGHT";
    private final boolean gameContinue = true;
     
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
    
    public boolean getGameContinue(){
        return this.gameContinue;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(this.placementPresent[1] + " - "  + this.placementPresent[0]);
        //System.out.println("--> apple : " + this.placementApple[1] + " - "  + this.placementApple[0]);
        if(this.gameContinue){
            switch (e.getKeyCode()) {
                case 37 :
                    //test if the snack is out of the board (left side)
                    if(!this.direction.equals("RIGHT")){
                       this.direction = "LEFT";
                    }
                    break;
                case 39:
                    //test if the snack is out of the board right side)
                    if(!this.direction.equals("LEFT")){
                        this.direction = "RIGHT";
                    }
                    break;
                case 38:
                    //test if the snack is out of the board (top side)
                    if(!this.direction.equals("BOTTOM")){
                        this.direction = "TOP";
                    }
                    break;
                case 40:
                    //test if the snack is out of the board (bottom side)
                    if(!this.direction.equals("TOP")){
                        this.direction = "BOTTOM";                    
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
    public void init(){
        //making of the board
        addKeyListener (this);
        
        ArrayList<Square> rowSquareWallFullTop = new ArrayList();
        for(int i = 0 ; i < this.sizeSquare ; i ++ ){
            rowSquareWallFullTop.add(new Square("WALL"));
        }
        
        ArrayList<Square> rowSquareWallFullBottom = new ArrayList();
        for(int i = 0 ; i < this.sizeSquare ; i ++ ){
            rowSquareWallFullBottom.add(new Square("WALL"));
        }

        this.listBoard.add(rowSquareWallFullTop);
        for(int j = 0 ; j < (this.sizeSquare - 2) ; j ++  ){
            ArrayList<Square> rowSquareWallStd = new ArrayList();
            rowSquareWallStd.add(new Square("WALL"));
            for(int i = 0 ; i < (this.sizeSquare-2) ; i ++ ){
                rowSquareWallStd.add(new Square("default"));
            }
            rowSquareWallStd.add(new Square("WALL"));
            this.listBoard.add(rowSquareWallStd);
        }
        this.listBoard.add(rowSquareWallFullBottom);       
        
        //initialisation of the board
//        for(int j = 0 ; j < this.sizeSquare ; j ++  ){
//            ArrayList<Square> rowSquares = new ArrayList();
//            for(int i = 0 ; i < this.sizeSquare ; i ++ ){
//                rowSquares.add(new Square("default"));
//            }            
//            listBoard.add(rowSquares);
//        } 

        //make origin
        this.listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("SNAKEHEAD");
        this.setApple();
        this.setContentPane(squareGraphics);
    }
    
    public void runSnake() {
        
            switch (this.direction) {
                
            case "LEFT" :
                //test if the snack is out of the board (left side)
                if(this.getIfWall()){
                   break;
                }
                
                this.goTo(this.getIfApple());
                break;
            case "RIGHT":
                //test if the snack is out of the board right side)
                if(this.getIfWall()){
                   break;
                }
                
                this.goTo(this.getIfApple());
                break;
            case "TOP":
                //test if the snack is out of the board (top side)
                if(this.getIfWall()){
                   break;
                }
                
                this.goTo(this.getIfApple());
                break;
            case "BOTTOM":
                //test if the snack is out of the board (bottom side)
                if(this.getIfWall()){
                   break;
                }
                
                this.goTo(this.getIfApple());
                break;
            default:
                break;
            } 
    }

    public boolean getIfApple(){
        switch(this.direction){
            case "LEFT":
                if(this.listBoard.get(this.placementPresent[0]).get(this.placementPresent[1] - 1).getType().equals("APPLE")){
                    this.setApple();
                    return true;
                } 
                break;
            case "RIGHT":
                if(this.listBoard.get(this.placementPresent[0]).get(this.placementPresent[1] + 1).getType().equals("APPLE")){
                    this.setApple();
                    return true;
                } 
                break;
            case "BOTTOM":
                if(this.listBoard.get(this.placementPresent[0] + 1).get(this.placementPresent[1]).getType().equals("APPLE")){
                    this.setApple();
                    return true;
                } 
                break;
            case "TOP":
                if(this.listBoard.get(this.placementPresent[0] - 1).get(this.placementPresent[1]).getType().equals("APPLE")){
                    this.setApple();
                    return true;                    
                } 
                break;
        }
        return false;
    }
    
    public boolean getIfWall(){
        switch(this.direction){
            case "LEFT":
                if(this.listBoard.get(this.placementPresent[0]).get(this.placementPresent[1] - 1).getType().equals("WALL")){
                    System.out.println("out -- L");
                    return true;
                } 
                break;
            case "RIGHT":
                if(this.listBoard.get(this.placementPresent[0]).get(this.placementPresent[1] + 1).getType().equals("WALL")){
                    System.out.println("out -- R");
                    return true;
                } 
                break;
            case "BOTTOM":
                if(this.listBoard.get(this.placementPresent[0] + 1).get(this.placementPresent[1]).getType().equals("WALL")){
                    System.out.println("out -- B");
                    return true;
                } 
                break;
            case "TOP":
                if(this.listBoard.get(this.placementPresent[0] - 1).get(this.placementPresent[1]).getType().equals("WALL")){
                    System.out.println("out -- T");
                    return true;
                } 
                break;
        }
        return false;
    }
    
    
    public void setApple(){
        //set a new position to the apple     
        int[] placementApple = {1,1};
        do {
            System.out.println(this.listBoard.get(placementApple[0]).get(placementApple[1]).getType());
            placementApple[0] = ThreadLocalRandom.current().nextInt(1, (this.sizeSquare) - 1) ;
            placementApple[1] = ThreadLocalRandom.current().nextInt(1, (this.sizeSquare) - 1) ;
        }while(!this.listBoard.get(placementApple[0]).get(placementApple[1]).getType().equals("default"));

        
        System.out.println("apple : " + placementApple[0] + " */* " +  placementApple[1]);
        listBoard.get(placementApple[0]).get(placementApple[1]).setType("APPLE");
        squareGraphics.updateGraphics(listBoard);
        repaint();       
    }
    
    public void goTo(boolean apple){

        switch(this.direction){
            case "LEFT":
                if(apple){
                    listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("SNAKEBODY");
                }else{
                    listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("default");
                }
                
                this.placementPresent[1]--;
                listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("SNAKEHEAD");
                squareGraphics.updateGraphics(listBoard);
                repaint();
                break;
            case "RIGHT":
                if(apple){
                    listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("SNAKEBODY");
                }else{
                    listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("default");
                }
                this.placementPresent[1]++;
                listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("SNAKEHEAD");
                squareGraphics.updateGraphics(listBoard);
                repaint();  
                break;
            case "TOP":
                if(apple){
                    listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("SNAKEBODY");
                }else{
                    listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("default");
                }
                this.placementPresent[0]--;
                listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("SNAKEHEAD");
                squareGraphics.updateGraphics(listBoard);
                repaint();      
                break;
            case "BOTTOM":
                if(apple){
                    listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("SNAKEBODY");
                }else{
                    listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("default");
                }
                this.placementPresent[0]++;
                listBoard.get(this.placementPresent[0]).get(this.placementPresent[1]).setType("SNAKEHEAD");
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
