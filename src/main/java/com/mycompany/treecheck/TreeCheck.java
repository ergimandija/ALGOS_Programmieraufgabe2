package com.mycompany.treecheck;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class TreeCheck {

    private static BinaryTree mainTree = null;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Baum-Menü ---");
            System.out.println("1: Baum einlesen & Statistik ausgeben");
            System.out.println("2: Pfadsuche (Key)");
            System.out.println("3: Subtree Suche");
            System.out.println("0: Beenden");
            System.out.print("Wahl: ");

            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    mainTree = readTreeFromFile(scan);
                    if (mainTree != null) {
                        mainTree.ComputeTreeBalance(mainTree.getStartNode());
                        System.out.println("AVL: " + (mainTree.isAVL() ? "ja" : "nein"));
                        mainTree.collectStatistics(mainTree.getStartNode());
                        mainTree.showStatistics();
                    }
                    break;

                case "2":
                    System.out.println("Datei für den Suchbaum angeben:");
                    mainTree = readTreeFromFile(scan);
                    if (mainTree != null) {
                        System.out.print("Gesuchter Key: ");
                        int key = Integer.parseInt(scan.nextLine());
                        ArrayList<Integer> path = mainTree.searchPath(key);
                        if (path == null) {
                            System.out.println(key + " nicht gefunden!");
                        } else {
                            System.out.print(key + " gefunden. Pfad: ");
                            System.out.println(path);
                        }
                    }
                    break;

                case "3":
                    System.out.println("Datei für den Suchbaum angeben:");
                    mainTree = readTreeFromFile(scan);
                    if (mainTree != null) {
                        System.out.println("Datei für den Subtree angeben:");
                        BinaryTree subTree = readTreeFromFile(scan);
                        if (subTree != null) {
                            boolean contains = mainTree.containsSubtree(subTree);
                            System.out.println("Subtree enthalten: " + (contains ? "Ja" : "Nein"));
                        }
                    }
                    break;

                case "0":
                    running = false;
                    break;

                default:
                    System.out.println("Ungültige Option.");
            }
        }
        scan.close();
    }

    /**
     * Hilfsmethode zum Einlesen eines Baums aus einer Datei.
     */
    private static BinaryTree readTreeFromFile(Scanner scan) {
        System.out.print("Dateiname eingeben: ");
        String fileName = scan.nextLine();
        BinaryTree bTree = new BinaryTree();
        try {
            Path p = Paths.get(fileName);
            try (Stream<String> fileContent = Files.lines(p)) {
                fileContent.forEach(line -> {
                    if (!line.trim().isEmpty()) {
                        bTree.addNode(new TNode(Integer.parseInt(line.trim()), null, null));
                    }
                });
            }
            System.out.println("Datei erfolgreich eingelesen.");
            return bTree;
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            System.err.println("Fehler: Datei enthält ungültiges Zahlenformat.");
            return null;
        }
    }
}