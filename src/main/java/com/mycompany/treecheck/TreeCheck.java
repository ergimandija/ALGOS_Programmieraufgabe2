/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.treecheck;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 * @author User
 */
public class TreeCheck {

    public static void main(String[] args) {
        try {
            BinaryTree bTree = new BinaryTree();
            System.out.println("Give in the file name");
            Scanner scan  = new Scanner(System.in);
            
            Path p = Paths.get(scan.nextLine());
            Stream<String> fileContent =  Files.lines(p);
            fileContent.forEach((String a) -> {
                bTree.addNode(new TNode(Integer.parseInt(a),null,null));
            });
            
            bTree.ComputeTreeBalance(bTree.getStartNode());
            if(bTree.isAVL()){
                System.out.println("AVL: yes");
            } else {
                System.out.println("AVL: no");
            }
            bTree.collectStatistics(bTree.getStartNode());
            bTree.showStatistics();
        } catch (IOException ex) {
            System.getLogger(TreeCheck.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
}
