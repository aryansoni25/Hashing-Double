package org.studyeasy;

import java.util.ArrayList;

public class Double {
    String[] hashTable;
    int usedCellNumber;
    Double(int size){
        hashTable=new String[size];
        usedCellNumber=0;
    }

    public int modASCII(String word,int M){
        char ch[];
        ch=word.toCharArray();
        int i,sum;
        for( sum=0,i=0;i<word.length();i++){
            sum=sum+ch[i];
        }
        return sum%M;
    }

    public void rehashKey(String word){
        ArrayList<String> data=new ArrayList<>();
        for(String s:hashTable){
            if(s!=null){
                data.add(s);
            }
            data.add(word);
            hashTable=new String[hashTable.length*2];
            for(String d:data){
                insertInHashtable(d);
            }
        }
    }

    private int AddAllDigit(int sum){
        int value=0;
        while(sum>0){
            value=sum%10;
            sum=sum/10;
        }
        return value;
    }

    public int secondHashFunction(String x,int M){
        char ch[];
        ch=x.toCharArray();
        int sum=0,i;
        for( i=0;i<x.length();i++){
            sum=sum+ch[i];
        }
        while(sum>hashTable.length){
            sum=AddAllDigit(sum);
        }
        return sum%M;
    }
    public void displayHashTable(){
        if(hashTable==null){
            System.out.println("The Hashtable does not exists ");
        }else{
            System.out.println("\n-------------Hashtable------------");
            for(int i=0;i<hashTable.length;i++){
                System.out.println("Index: "+i+",Key: "+hashTable[i]);
            }
        }
    }
    public double loadFactor(){
        double loadFactor=usedCellNumber*1.0/hashTable.length;
        return loadFactor;
    }

    public void insertInHashtable(String word){
        double load=loadFactor();
        if(load>=0.75){
            rehashKey(word);
        }else {
            int a = modASCII(word, hashTable.length);
            int b = secondHashFunction(word, hashTable.length);
            for (int i = 0; i < hashTable.length; i++) {
                int newIndex = (a + i * b) % hashTable.length;
                if (hashTable[newIndex] == null) {
                    hashTable[newIndex] = word;
                    System.out.println("The word " + word + " successfully inserted at the location: " + newIndex);
                    break;
                } else {
                    System.out.println(newIndex + " is already occupied .Trying next empty cell");
                }
            }
        }
        usedCellNumber++;
    }
}
