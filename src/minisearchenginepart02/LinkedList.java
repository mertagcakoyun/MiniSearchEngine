/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minisearchenginepart02;

import java.io.File;
import java.util.Scanner;

/**
 * @file LinkedList.java
 * @description : Programimiz bir klasorde bulunan onceden belirlenmis
 * istenmeyen kelimeler/semboller haricindeki kelimeleri alıp BST ye aktarılır. Preorder
 * sıralamasıyla sıraya sokulur ve sıralı halde başka bir dosyaya yazılır Aynı
 * zamanda bir kelimenin hangi dosyada kaç tane geçtigini de hesaplayıp hafızada
 * tutabilmektedir.
 * @assignment Homework-02
 * @date Apr 7, 2019 , 5:40:12 PM
 * @author mertagcakoyun ||contact: mert_agcakoyun@hotmail.com
 */
public class LinkedList<T> {

    Node<T> head;

    void addFirst(Node<T> newNode) {
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    void addFirst(String data, int freq) {
        addFirst(new Node<>(data, freq));
    }
    
     void addLast(String data, int freq) {
        Node<String> newNode = new Node<>(data,freq);
        
        if (head == null) {
            head = (Node<T>) newNode;
        } else {
            Node<T> temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = (Node<T>) newNode;
        }
    }

    boolean isExist(String word) {                                              //LinkedList icindeki kelimeler donulur aranan kelimeler var mı diye bakılır.
        boolean result = false;
        Node tmp = head;
        while (tmp != null) {
            if (tmp.data.equals(word)) {
                result = true;
                break;
            } else {
                result = false;

            }
            tmp = tmp.next;

        }
        return result;
    }
    Node<String> findNodeinList(String word) {                                  //LinkedList icindeki kelimeler donulur aranan kelimeler var mı diye bakılır ve node dondurulur
        Node tmp = head;
        while (tmp != null) {
            if (tmp.data.equals(word)) {
                return tmp;
                
            }
            tmp = tmp.next;

        }
        return null;
    }

    void print() {
        Node<T> temp = head;

        while (temp != null) {
            if (temp.frequency != 0) {
                System.out.print(temp.data + " dosyasında " + temp.frequency);
                if (temp.next!=null) {
                    System.out.print(" adet bulunur - ");
                }
                
            }temp = temp.next;

        }
    }

    int size() {
        Node<T> temp = head;
        int count = 0;

        while (temp != null) {
            count++;
            temp = temp.next;
        }

        return count;
    }
    
    
     String printForFile(){
    Node<T> temp = head;
    String s="";
        while (temp != null) {
           
            s+=(temp.data + " dosyasında " + temp.frequency+" adet bulunur - ");

            temp = temp.next;

        }
        return s;
        }

}
   