/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minisearchenginepart02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import static java.io.FileDescriptor.out;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @file BinarySearchTree.java
 * @description : Programimiz bir klasorde bulunan onceden belirlenmis
 * istenmeyen kelimeler haricindeki kelimeleri alıp BST ye aktarılır. Preorder
 * sıralamasıyla sıraya sokulur ve sıralı halde başka bir dosyaya yazılır Aynı
 * zamanda bir kelimenin hangi dosyada kaç tane geçtigini de hesaplayıp hafızada
 * tutabilmektedir. Gelen ikinci versiyonda ise kullanıcdan aldığı sorguyu
 * olusturdugu binary search tree den yola çıkarak alakalık derecesine gore
 * buyukten kucuge siralar
 * @assignment Homework-02
 * @date May 10, 2019 , 5:40:10 PM
 * @author mertagcakoyun ||contact: mert_agcakoyun@hotmail.com
 */
public class BinarySearchTree< T extends Comparable<T>> {

    private NodeBST<T> root;
    LinkedList<T> wordBank = new LinkedList<>();   // Kac farkli kelime oldugunun kontrolu yapıldı. test sinifinda test edildi
    private int bstSize = 0;

    void addtoBST(File directory) throws FileNotFoundException {
        LinkedList<String> ignoreList = new LinkedList();
        String uzanti = "";
        for (int i = 0; i < directory.listFiles().length; i++) {
            uzanti = ".txt";
            if (directory.listFiles()[i].getName().endsWith(uzanti)) {          //Verilecek klasordeki dosyaları array olusturmadan donerek txt uzantılı olanı bulundu.
                Scanner scanner = new Scanner(directory.listFiles()[i]);        // Klasordeki dosyalar indexlerine gore teker teker okundu.
                String word;
                while ((scanner.hasNext())) {
                    ignoreList.addFirst(scanner.next(), 1);                      //IgnoreList dosyasındaki kelimeler  frequency degeri 1 olarak LinkedListe eklendi. 
                }

            }
        }
        for (int i = 0; i < directory.listFiles().length; i++) {
            uzanti = ".html";
            if (directory.listFiles()[i].getName().endsWith(uzanti)) {          //verilecek klasordeki dosyaları array olusturmadan donerek txt uzantılı olanı bulundu.
                Scanner sc = new Scanner(directory.listFiles()[i]);
                while (sc.hasNext()) {
                    String w = sc.next();                                        //sırayla alınan dosyadaki kelimeler 
                    if (!ignoreList.isExist(w)) {                               //ignoreList LinkedListimdeki kelimeleri dondurup  
                        if (!w.startsWith("<") && !w.equals(",") && !w.equals(".")) {   // istenmeyen ozellikte olan kelimeler dikkate alınmayıp istenenler alındı.
                            if (!search(w)) {                                   // gelen kelimenin Tree de olup olmadığı aranır bulunmuyorsa asagidaki islemler yapilir                                    
                                NodeBST bstNode = new NodeBST(w);               //BST icin node olusturulur
                                LinkedList ls = new LinkedList();               //BST icin olusturdugumuz node un ozelligi olan linkedList olusturulur
                                Node wordNode = new Node(directory.listFiles()[i].getName(), 1); // BST node umuzun listesinde bulunun farklı tipteki node olusturulur.
                                ls.addFirst(wordNode);                          // listeye en son olusturdugumuz node eklenir
                                bstNode.wordList = ls;                          // olusturdugumuz liste bst node umuzun liste ozelligine atanır.
                                insert(bstNode);                                // bst node umuz tree ye eklenir.
                            } else {                                            //tree mizde aranan kelime bulunursa asagıdaki islemler yapilir.  
                                if (!findNodeinBST(w).wordList.isExist(directory.listFiles()[i].getName())) { //findNodein bst ile kelimeyi iceren node da html dosyası bulunmuyorsa 
                                    Node newNode = new Node(directory.listFiles()[i].getName(), 1); //yeni gelen .html uzantılı dosyamız frequency degeri 1 olacak sekilde eklenir
                                    findNodeinBST(w).wordList.addFirst(newNode);
                                } else {
                                    findNodeinBST(w).wordList.findNodeinList(directory.listFiles()[i].getName()).frequency++; // eger .html dosyamız varsa frequency degeri arttırılır.
                                }

                            }
                        }

                    }

                }
            }

        }

    }

    void writeToFile(String path) throws IOException {

        writeToFile(root, path);

    }

    void writeToFile(NodeBST node, String path) throws IOException {            //Recursive olarak preorder metodu yardımıyla dosyaya yazma işlemi gerçekleştirilir.
        BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
        String s = "";
        if (node != null) {
            s += "" + node.word + " : { ";
            s += node.wordList.printForFile();
            s += "\n";
            bw.append(s);

            writeToFile(node.left, path);
            writeToFile(node.right, path);
        }
        bw.close();
    }

    NodeBST<T> findNodeinBST(String searchData) {   //Tree miz donulur. aranan kelimenin bulundugu node sonuc olarak dondurulur.
        if (root == null) {

        } else {
            NodeBST<T> temp = root;
            while (temp != null) {
                if (searchData.compareTo(temp.word) > 0) {
                    temp = temp.right;
                } else if (searchData.compareTo(temp.word) < 0) {
                    temp = temp.left;
                } else {
                    return temp;
                }
            }

        }
        return null;

    }

    //////////////////////////////////////////////////////////////////////////// Derste kullanılan kodlar
    void insert(NodeBST newNode) {

        if (root == null) {
            root = newNode;
        } else {
            NodeBST temp = root;

            while (temp != null) {
                if (newNode.word.compareTo(temp.word) > 0) {
                    if (temp.right == null) {
                        temp.right = newNode;
                        return;
                    }

                    temp = temp.right;
                } else if (newNode.word.compareTo(temp.word) < 0) {
                    if (temp.left == null) {
                        temp.left = newNode;
                        return;
                    }

                    temp = temp.left;
                } else {
                    return;
                }
            }

        }
    }

    boolean search(String searchData) {
        if (root == null) {

        } else {
            NodeBST<T> temp = root;
            while (temp != null) {
                if (searchData.compareTo(temp.word) > 0) {
                    temp = temp.right;
                } else if (searchData.compareTo(temp.word) < 0) {
                    temp = temp.left;
                } else {
                    return true;
                }
            }

        }
        return false;
    }

    void preorder() {
        System.err.println("preorder : ");
        preorder(root);

    }

    private void preorder(NodeBST node) {

        if (node != null) {

            System.out.print(node.word + " : { ");
            node.wordList.print();
            System.out.println(" }");
            preorder(node.left);
            preorder(node.right);
        }
    }

    int sizeRecursive() {
        return sizeRecursive(root);
    }

    // recursive size method
    private int sizeRecursive(NodeBST<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + sizeRecursive(node.left) + sizeRecursive(node.right);
        }
    }

    int size() {
        return bstSize;
    }
}
