
package Snake.drab.attributes;

import java.awt.Color;

/**
 *
 * @author jerome.lombard
 */
public final class Square {
    private String type;
    private Color color;

    public Square(String type){
        this.type = type;
        this.setColor(this.type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        this.setColor(type);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(String type) {
        switch(type){
            case "SnackHead":
                this.color = new Color(30, 144, 255);
                break;
            case "default":
                this.color = new Color(192, 192, 192);
                break;
            default:
                this.color = new Color(192, 192, 192);
                break;
        } 
    }   
    
    
    
}
