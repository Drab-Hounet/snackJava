
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

    public ArrayList<ArrayList<Square>> board = new ArrayList();
    
    private ArrayList<Square> snakeBody = new ArrayList();
    
    private ArrayList<String> listMoveForBody = new ArrayList();
    
    private Square snakeHead = new Square("SNAKEHEAD", 2, 2);
    
    private final int[] placementPresentOfHead = {1,1}; // {y,x} 
    private final int sizeSquare = 20;
    private final SquareGraphics  squareGraphics = new SquareGraphics(board, this.sizeSquare);
    private String direction = "RIGHT";
    private final boolean gameContinue = true;
    
    private int NumberOfBody = 0;
    
     
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

        this.board.add(rowSquareWallFullTop);
        for(int j = 0 ; j < (this.sizeSquare - 2) ; j ++  ){
            ArrayList<Square> rowSquareWallStd = new ArrayList();
            rowSquareWallStd.add(new Square("WALL"));
            for(int i = 0 ; i < (this.sizeSquare-2) ; i ++ ){
                rowSquareWallStd.add(new Square("default"));
            }
            rowSquareWallStd.add(new Square("WALL"));
            this.board.add(rowSquareWallStd);
        }
        this.board.add(rowSquareWallFullBottom);       

        //make origin
        this.board.get(this.snakeHead.getCoordY()).set(this.snakeHead.getCoordX(), snakeHead);

        this.setApple();
        this.setContentPane(squareGraphics);
    }
    
    public void runSnake() {
        
        System.out.println("number of body : " + this.NumberOfBody);
        
        this.listMoveForBody.add(this.direction);
        if(this.listMoveForBody.size() != this.NumberOfBody){
            this.listMoveForBody.remove(0);
        }
        
        //test

        for (String move : this.listMoveForBody){
            System.out.print(move);
        }
        System.out.println();
        
        String squareAfterMove;
        switch (this.direction) {

        case "LEFT" :
            //test if the snack is out of the board (left side)
            squareAfterMove = this.board.get(this.snakeHead.getCoordY()).get(this.snakeHead.getCoordX() - 1).getType();
            //squareAfterMove = this.board.get(this.placementPresentOfHead[0]).get(this.placementPresentOfHead[1] - 1).getType();
            if(this.getIfWall(squareAfterMove)){
               break;
            }
            this.moveHead(this.getIfApple(squareAfterMove));
            break;
            
        case "RIGHT":
            //test if the snack is out of the board right side)      
            squareAfterMove = this.board.get(this.snakeHead.getCoordY()).get(this.snakeHead.getCoordX() + 1).getType();
            //squareAfterMove = this.board.get(this.placementPresentOfHead[0]).get(this.placementPresentOfHead[1] + 1).getType();
            if(this.getIfWall(squareAfterMove)){
               break;
            }
            this.moveHead(this.getIfApple(squareAfterMove));
            break;
        
        case "TOP":
            //test if the snack is out of the board (top side)
            squareAfterMove = this.board.get(this.snakeHead.getCoordY() - 1).get(this.snakeHead.getCoordX()).getType();
            //squareAfterMove = this.board.get(this.placementPresentOfHead[0] - 1).get(this.placementPresentOfHead[1]).getType();
            if(this.getIfWall(squareAfterMove)){
               break;
            }
            this.moveHead(this.getIfApple(squareAfterMove));
            break;
            
        case "BOTTOM":
            //test if the snack is out of the board (bottom side)
            squareAfterMove = this.board.get(this.snakeHead.getCoordY() + 1).get(this.snakeHead.getCoordX()).getType();
            //squareAfterMove = this.board.get(this.placementPresentOfHead[0] + 1).get(this.placementPresentOfHead[1]).getType(); 
            if(this.getIfWall(squareAfterMove)){
               break;
            }
            this.moveHead(this.getIfApple(squareAfterMove));
            break;
            
        default:
            break;
        } 
    }

    public boolean getIfApple(String squareAfterMove){
        if(squareAfterMove.equals("APPLE")){
            this.setApple();
            this.NumberOfBody++;
            return true;
        } 
        return false;
    }
    
    public boolean getIfWall(String squareAfter){
        if(squareAfter.equals("WALL")){
            System.out.println("out");
            return true;
        } 
        return false;
    }
    
    public void setApple(){
        //set a new position to the apple    
        
        Square apple = new Square("APPLE");
      
        do {
            apple.setCoordX(ThreadLocalRandom.current().nextInt(1, (this.sizeSquare) - 1));
            apple.setCoordY(ThreadLocalRandom.current().nextInt(1, (this.sizeSquare) - 1));     
            System.out.println(this.board.get(apple.getCoordY()).get(apple.getCoordX()).getType());
        }while(!this.board.get(apple.getCoordY()).get(apple.getCoordX()).getType().equals("default"));

        System.out.println("apple : " + apple.getCoordX() + " -- " +  apple.getCoordY());
        
        board.get(apple.getCoordY()).set(apple.getCoordX(), apple);
        squareGraphics.updateGraphics(board);
        repaint();       
    }
    
    public void moveHead(boolean apple){
        
        switch(this.direction){
            case "LEFT":
                if(apple){
                    this.board.get(this.snakeHead.getCoordY()).set(this.snakeHead.getCoordX(), new Square("SNAKEBODY"));
                    //board.get(placementPresentOfHead[0]).get(this.placementPresentOfHead[1]).setType("SNAKEBODY");
                }else{
                    this.board.get(this.snakeHead.getCoordY()).set(this.snakeHead.getCoordX(), new Square("default"));
                    //board.get(this.placementPresentOfHead[0]).get(this.placementPresentOfHead[1]).setType("default");
                }
                this.snakeHead.setCoordX(this.snakeHead.getCoordX() - 1 );
                //this.placementPresentOfHead[1]--;
                this.board.get(this.snakeHead.getCoordY()).set(this.snakeHead.getCoordX(), snakeHead);
                //listBoard.get(this.placementPresentOfHead[0]).get(this.placementPresentOfHead[1]).setType("SNAKEHEAD");
                squareGraphics.updateGraphics(board);
                repaint();
                break;
            case "RIGHT":
                if(apple){
                    this.board.get(this.snakeHead.getCoordY()).set(this.snakeHead.getCoordX(), new Square("SNAKEBODY"));
                    //board.get(this.placementPresentOfHead[0]).get(this.placementPresentOfHead[1]).setType("SNAKEBODY");
                }else{
                    this.board.get(this.snakeHead.getCoordY()).set(this.snakeHead.getCoordX(), new Square("default"));
                    //board.get(this.placementPresentOfHead[0]).get(this.placementPresentOfHead[1]).setType("default");
                }
                //this.placementPresentOfHead[1]++;
                this.snakeHead.setCoordX(this.snakeHead.getCoordX() + 1 );
                this.board.get(this.snakeHead.getCoordY()).set(this.snakeHead.getCoordX(), snakeHead);
                //board.get(this.placementPresentOfHead[0]).get(this.placementPresentOfHead[1]).setType("SNAKEHEAD");
                squareGraphics.updateGraphics(board);
                repaint();  
                break;
            case "TOP":
                if(apple){
                    this.board.get(this.snakeHead.getCoordY()).set(this.snakeHead.getCoordX(), new Square("SNAKEBODY"));                   
                    //board.get(this.placementPresentOfHead[0]).get(this.placementPresentOfHead[1]).setType("SNAKEBODY");
                }else{
                    this.board.get(this.snakeHead.getCoordY()).set(this.snakeHead.getCoordX(), new Square("default"));                    
                    //board.get(this.placementPresentOfHead[0]).get(this.placementPresentOfHead[1]).setType("default");
                }
                //this.placementPresentOfHead[0]--;
                this.snakeHead.setCoordY(this.snakeHead.getCoordY() - 1 );
                this.board.get(this.snakeHead.getCoordY()).set(this.snakeHead.getCoordX(), snakeHead);
                //board.get(this.placementPresentOfHead[0]).get(this.placementPresentOfHead[1]).setType("SNAKEHEAD");
                squareGraphics.updateGraphics(board);
                repaint();      
                break;
            case "BOTTOM":
                if(apple){
                    this.board.get(this.snakeHead.getCoordY()).set(this.snakeHead.getCoordX(), new Square("SNAKEBODY"));                   
                    //board.get(this.placementPresentOfHead[0]).get(this.placementPresentOfHead[1]).setType("SNAKEBODY");
                }else{
                    this.board.get(this.snakeHead.getCoordY()).set(this.snakeHead.getCoordX(), new Square("default"));                            
                    //board.get(this.placementPresentOfHead[0]).get(this.placementPresentOfHead[1]).setType("default");
                }
                this.snakeHead.setCoordY(this.snakeHead.getCoordY() + 1 );               
                //this.placementPresentOfHead[0]++;
                this.board.get(this.snakeHead.getCoordY()).set(this.snakeHead.getCoordX(), snakeHead);
                //board.get(this.placementPresentOfHead[0]).get(this.placementPresentOfHead[1]).setType("SNAKEHEAD");
                squareGraphics.updateGraphics(board);
                repaint();  
        }
    }
    
    public void moveBody(){
        
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
