/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minisearchenginepart02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @file Test.java
 * @description : Programimiz bir klasorde bulunan onceden belirlenmis
 * istenmeyen kelimeler haricindeki kelimeleri alıp BST ye aktarılır. Preorder
 * sıralamasıyla sıraya sokulur ve sıralı halde başka bir dosyaya yazılır Aynı
 * zamanda bir kelimenin hangi dosyada kaç tane geçtigini de hesaplayıp hafızada
 * tutabilmektedir. Gelen ikinci versiyonda ise kullanıcdan aldığı sorguyu
 * olusturdugu binary search tree den yola çıkarak alakalık derecesine gore
 * buyukten kucuge siralar.
 * @assignment Homework-02
 * @date May 10, 2019 , 5:41:15 PM
 * @author mertagcakoyun ||contact: mert_agcakoyun@hotmail.com
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("\n\nMini Search Engine Part 1\n\n");
        File document = new File("belgeler");
        BinarySearchTree bst = new BinarySearchTree();
        bst.addtoBST(document);
        bst.preorder();
        System.out.println("\n\nBinary Search Tree Size : " + bst.sizeRecursive());
        bst.writeToFile("dosya/deneme.txt");

        //Program tarafından olusturulacak dosya dosya klasoru icinde olusturulacak.
        
        System.out.println("\n\nMini Search Engine Part 2\n\n");

        Heap heap = new Heap(document.listFiles().length);
        Scanner sc = new Scanner(System.in);
        System.out.println("Aramak istediginiz cumleyi/kelimeyi girin");
        String query = sc.nextLine();
        heap.insertFromBST(bst, query);
        heap.printArray();

    }

}


//            
