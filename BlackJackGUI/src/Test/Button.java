/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.awt.Color;
import java.awt.Font;
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
    boolean enabled;
    Color col;
    String text;
    public Button(int x, int y, int width, int height, String message,Color c){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.text=message;
        col=c;
        enabled=false;
    }
    public boolean collides(int x, int y){
        if(     x>this.x&&
                y>this.y&& 
                x<this.x+this.width&&
                y<this.y+this.height&&
                this.enabled==true
                ){
            return(true);
        }
        return(false);
    }
    public void enable(){
        this.enabled=true;
    }
    public void disable(){
        this.enabled=false;
    }
    public void display(Graphics g){
        enable();
        g.setColor(col);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.MONOSPACED, 0, 50));
        g.drawString(text, x+width/5, y+2*height/5);
    }
    
}
