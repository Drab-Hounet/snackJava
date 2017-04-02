
package Snake.drab.attributes;

import java.awt.Color;

/**
 *
 * @author jerome.lombard
 */
public final class Square {
    private String type;
    private Color fillColor;
    private Color outLineColor;
    private int coordX = 0;
    private int coordY = 0;
    
    public Square(String type){
        this.type = type;
        this.setColor(this.type);
    }

    public Square(String type, int coordX , int coordY){
        this.type = type;
        this.setColor(this.type);
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }
  
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        this.setColor(type);
    }

    public Color getFillColor() {
        return fillColor;
    }
    
    public Color getOutLineColor() {
        return outLineColor;
    }

    public void setColor(String type) {
        switch(type){
            case "SNAKEHEAD":
                this.fillColor =    new Color(32, 96, 128);
                this.outLineColor = new Color(255, 255, 255);                
                break;
            case "SNAKEBODY":
                this.fillColor =    new Color(32, 128, 160);
                this.outLineColor = new Color(255, 255, 255);                
                break;
            case "WALL":
                this.fillColor =    new Color(11, 22, 22);
                this.outLineColor = new Color(11, 22, 22);
                break;
            case "default":
                this.fillColor =    new Color(192, 160, 0);
                this.outLineColor = new Color(255, 255, 255);                
                break;
            case "APPLE":
                this.fillColor =    new Color(160, 0, 32);
                this.outLineColor = new Color(255, 255, 255);                
                break;
            default:
                this.fillColor =    new Color(192, 192, 192);
                this.outLineColor = new Color(255, 255, 255);                
                break;
        } 
    }   
    
    
    
}
