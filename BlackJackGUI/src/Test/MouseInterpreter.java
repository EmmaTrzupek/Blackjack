/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 *
 * @author Emily
 */
public class MouseInterpreter implements MouseListener{
    ArrayList<Boolean> buttonsClicked;
    ArrayList<Button> buttons;
    public MouseInterpreter(){
        buttonsClicked=new ArrayList<>();
        buttons=new ArrayList<>();
    }
    public void setButton(Button b){
        buttons.add(b);
        buttonsClicked.add(false);
    }
    
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        for(int i=0;i<buttons.size();i++)
            if(buttons.get(i).collides(x, y)){
                buttonsClicked.set(i, Boolean.TRUE);
            }
    }
    public Button getButton(String name) {
        for(Button b:buttons)
            if(b.text.equals(name))
                return(b);
        return(null);
    }
    public int getButtonIndex(String name) {
        for(int i=0;i<buttons.size();i++)
            if(buttons.get(i).text.equals(name))
                return(i);
        return(-1);
    }
    public boolean isClicked(int index){
        if(buttonsClicked.get(index)){
            buttonsClicked.set(index, Boolean.FALSE);
            return true;
        }
        return(false);
    }
    public boolean isClicked(String name){
        int index=getButtonIndex(name);
        return(isClicked(index));
    }
    public void display(String name,Graphics g){
        int index=getButtonIndex(name);
        display(index,g);
    }
    public void display(int index,Graphics g){
        buttons.get(index).display(g);
    }
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        for(int i=0;i<buttons.size();i++)
            if(buttons.get(i).collides(x, y)){
                buttonsClicked.set(i, Boolean.TRUE);
            }
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }
}
