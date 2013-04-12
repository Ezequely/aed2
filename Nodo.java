/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtree;

/**
 *
 * @author Teixeira
 */
public class Nodo {
    private int key;
    private char color;
    private Nodo left;
    private Nodo right;
    
    public Nodo(int key){
        this.key = key;
        this.color = 'R';
        left = null;
        right = null;
    }
    
    public void setKey(int key){
        this.key = key;
    }
    
    public int getKey(){
        return key;
    }
    
    public void setColor(char color){
        this.color = color;
    }
    
    public char getColor(){
        return color;
    }
    
    public void setLeft(Nodo novo){
        left = novo;
    }
    
    public Nodo getLeft(){
        return left;
    }
    
    public void setRight(Nodo novo){
        right = novo;
    }
    
    public Nodo getRight(){
        return right;
    }
}
