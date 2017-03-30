
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
            case "SNAKEHEAD":
                this.color = new Color(0, 142, 142);
                break;
            case "SNAKEBODY":
                this.color = new Color(22, 184, 78);
                break;
            case "WALL":
                this.color = new Color(11, 22, 22);
                break;
            case "default":
                this.color = new Color(192, 192, 192);
                break;
            case "APPLE":
                this.color = new Color(150, 0, 24);
                break;
            default:
                this.color = new Color(192, 192, 192);
                break;
        } 
    }   
    
    
    
}
