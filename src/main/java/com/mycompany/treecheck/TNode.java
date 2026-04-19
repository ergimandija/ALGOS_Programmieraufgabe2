/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.treecheck;

/**
 *
 * @author User
 */
public class TNode {
    private TNode left;
    private TNode right;
    private int key;
    
    TNode(int key, TNode left, TNode right){
           this.key = key;
           this.left = left;
           this.right = right;
    }
    
    void setRight(TNode right){
        this.right = right;
    }
    void setLeft(TNode left){
        this.left =  left;
    }
    
    int getKey(){
        return key;
    }
    
    TNode getLeft(){
        return left;
    }
    
    TNode getRight(){
        return right;
    }
    
}
