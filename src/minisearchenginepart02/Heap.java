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
 * tutabilmektedir.
 * @assignment Homework-02
 * @date May 10, 2019 , 5:45:20 PM
 * @author mertagcakoyun ||contact: mert_agcakoyun@hotmail.com
 */
public class Heap {

    private Node[] heap;
    private int size;

    public Heap(int capacity) {
        heap = new Node[capacity];
        size = 0;
    }

    void insertFromBST(BinarySearchTree bst, String query) {
        String queryArray[] = query.toLowerCase().split(" ");                   //queyde girilen string kelimelerine ayrıldı. Büyük küçük harf duyarlılığını kaldırmak için lowerCase yapıldı.
        for (int i = 0; i < queryArray.length; i++) {                           //querydeki kelime sayisi kadar arama ve ekleme islemi yapilcak
            if (bst.search(queryArray[i])) {                                    // query deki kelime bst de bulunuyorsa heap üzerinde degisiklik olur
                NodeBST foundNode = bst.findNodeinBST(queryArray[i]);
                Node foundNodeTmp = foundNode.wordList.head;
                while (foundNodeTmp != null) {                                  //bulunan NodeBST(kelime) nin içinde bulunan linkedListte dolaşır
                    if (isExistinHeap(foundNodeTmp.data)) {                     //dosya ismi heap e daha once eklenmisse frequency(alaka duzeyi) arttırılır
                        increaseKey(foundNodeTmp.data, foundNodeTmp.frequency);
                    } else {                                              //bulunmuyorsa insert islemi yapilir
                        if (size < heap.length) {
                            heap[size] = foundNodeTmp;
                            int current = size++;
                            while (heap[current].frequency < heap[parent(current)].frequency) {
                                swap(current, parent(current));
                                current = parent(current);
                            }
                        } else {
                            System.out.println("array is full !");
                        }
                    }
                    foundNodeTmp = foundNodeTmp.next;
                }

            } else {
                System.err.println("Query not found !");
            }

        }
        heapSort();
    }

    boolean isExistinHeap(String fileName) {

        for (int i = 0; i < size; i++) {
            if (heap[i].data.equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    Node findinHeap(String fileName) {
        Node flagNode = null;
        for (int i = 0; i < size; i++) {
            if (heap[i].data.equals(fileName)) {
                flagNode = heap[i];

            }
        }
        return flagNode;
    }

    ///////////// Ders Kodlari //////////////
    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void swap(int i, int j) {
        Node temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    void heapify() {
        int lastIndex = size - 1;

        for (int i = parent(lastIndex); i >= 0; i--) {
            minHeap(i);
        }
    }

    private void minHeap(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        int min = i;

        if (left < size && heap[min].frequency > heap[left].frequency) {
            min = left;
        }
        if (right < size && heap[min].frequency > heap[right].frequency) {
            min = right;
        }

        if (min != i) {
            swap(min, i);
            minHeap(min);
        }
    }

    void printArray() {
        for (int i = 0; i < heap.length; i++) {
            if (heap[i] == null) {
                break;
            }
            System.out.print(heap[i].data + " (" + heap[i].frequency + ")  -  ");

        }
        System.out.println();
    }

    // only for Integer MinHeap
    void increaseKey(String fileName, int amount) {
        for (int i = 0; i < size; i++) {
            if (heap[i].data.equals(fileName)) {
                heap[i].frequency += amount;
                minHeap(i);
                return;
            }
        }
    }

    // 25.04.2019 - SortingAlgorithms
    void heapSort() {
        // run heapify method to ensure it is a min heap
        heapify();

        int lastIndex = size - 1;
        for (int i = lastIndex; i > 0; i--) {
            swap(0, i);
            size--;
            minHeap(0);
        }
    }

}
