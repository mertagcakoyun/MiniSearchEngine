/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minisearchenginepart02;

/**
 * @file NodeBST.java
 * @description : Programimiz bir klasorde bulunan onceden belirlenmis
 * istenmeyen kelimeler haricindeki kelimeleri alıp BST ye aktarılır. Preorder
 * sıralamasıyla sıraya sokulur ve sıralı halde başka bir dosyaya yazılır Aynı
 * zamanda bir kelimenin hangi dosyada kaç tane geçtigini de hesaplayıp hafızada
 * tutabilmektedir. Gelen ikinci versiyonda ise kullanıcdan aldığı sorguyu
 * olusturdugu binary search tree den yola çıkarak alakalık derecesine gore
 * buyukten kucuge siralar
 * @assignment Homework-02
 * @date May 10, 2019 , 5:40:50 PM
 * @author mertagcakoyun ||contact: mert_agcakoyun@hotmail.com
 */
public class NodeBST<T> {

    String word;
    LinkedList wordList;
    NodeBST left;
    NodeBST right;

    public NodeBST(String word) {
        this.word = word;
    }

}
