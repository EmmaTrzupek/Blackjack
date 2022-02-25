/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.awt.Graphics;

/**
 *
 * @author Emily
 */
public class Button {
    int x;
    int y;
    int width;
    int height;
    String text;
    public Button(int x, int y, int width, int height, String message){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.text=message;
    }
    public boolean collides(int x, int y){
        if(     x>this.x&&
                y>this.y&& 
                x<this.x+this.width&&
                y<this.y+this.height){
            return(true);
        }
        return(false);
    }
    public void display(Graphics g){
        g.clearRect(x, y, width, height);
        g.drawString(text, x+width/5, y+height/5);
    }
}
