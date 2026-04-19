/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.treecheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author User
 */
public class BinaryTree {
    private TNode startNode ;
    private boolean isAVL;
    private int min;
    private int max;
    private int sum;
    private int count;
    BinaryTree(){
      startNode = null;
      isAVL = true;
      min = Integer.MAX_VALUE;
      max =Integer.MIN_VALUE;
      sum = 0;
      count = 0;
    }
    
    public TNode getStartNode(){
        return startNode;
    }
    
    public boolean isAVL(){
        return isAVL;
    }
    
    public void addNode(TNode t){
        if(Objects.equals(startNode, null)){
            startNode  = t;
            count++;
            return;
        }
        TNode currentNode =  startNode;
        while(true){
           if(currentNode.getKey() == t.getKey()){
               break;
           } else if(t.getKey() < currentNode.getKey()){
                if(Objects.equals(currentNode.getLeft(), null)){
                   currentNode.setLeft(t);
                   break;
                }
               currentNode  = currentNode.getLeft();
              
           } else {
                if(Objects.equals(currentNode.getRight(), null)){
                   currentNode.setRight(t);
                   break;
                }
               currentNode = currentNode.getRight();
              
           }
        }
        count++;
    }
    public void ComputeTreeBalance(TNode currentNode){
        if(Objects.equals(currentNode, null)){
            return;
        }

        this.ComputeTreeBalance(currentNode.getRight());
        this.ComputeTreeBalance(currentNode.getLeft());
        int balance =  this.computeNodeBalance(currentNode);
        if(balance > 1 || balance < -1){
            this.isAVL = false;
        }
        System.out.println("bal("+ currentNode.getKey() + ") = " + balance + ((balance > 1 || balance < -1)? "(AVL violation!)" : ""));
        
    }
    public int computeNodeBalance(TNode currentNode){
        
       int rightTreeHeight = computeTreeHeight(currentNode.getRight());
       int leftTreeHeight = computeTreeHeight(currentNode.getLeft());
       int balance = rightTreeHeight - leftTreeHeight;
       return balance ;
        
    }
    
    public int computeTreeHeight(TNode root){
       
        if(Objects.equals(root, null)){
            return -1;
        }
        int leftHeight = computeTreeHeight(root.getLeft()) + 1;
        int rightHeight = computeTreeHeight(root.getRight()) + 1;
        if(leftHeight < rightHeight){
            return rightHeight;
        } else {
            return leftHeight;
        }
   
    }
    
    void showStatistics(){
        double avg = ((double) sum)/count;

        System.out.println("min: " + min + " max: " + max + " avg: " + avg);
    }
    void collectStatistics(TNode currentNode){
        if(Objects.equals(currentNode, null)){
            return ; 
        }
        collectStatistics(currentNode.getLeft());
        collectStatistics(currentNode.getRight());
        
        if(currentNode.getKey() < min ){
            min = currentNode.getKey();
        } 
        if(currentNode.getKey() > max){
            max = currentNode.getKey();
        }
        sum += currentNode.getKey();
        
    }
    
}
