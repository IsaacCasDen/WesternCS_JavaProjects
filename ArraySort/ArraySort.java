
/*
 * 190
 * ArraySort
 * ArraySort.java
 * I Denney 
 */

import java.util.Random;

public class ArraySort {
  
  private final static int MAX=10;
  
  public static void main(String[] args) {
    
    long operationStart=System.currentTimeMillis();
    
    char[] thisArray=getCharArray(MAX,true);
    char[] sortedArray=getSortedCharArray(thisArray);
    
    long operationEnd=System.currentTimeMillis();
    display(thisArray);
    display(sortedArray);
    print("Operations took " + (operationEnd-operationStart) + " milliseconds. Sorted? " + isSorted(sortedArray));
    
    
  }
  private static char[] getCharArray(int size,boolean populate) {
    char[] result = new char[size];
    
    if (populate) {
      Random r=new Random();
      for (int i=0;i<result.length;i++) {
        char c=(char)(r.nextInt(26) + 'a');
        result[i]=c;
      }
    }
    
    return result;
  }
  private static char[] getSortedCharArrayV1(char[] charArray) {
    char[] result = new char[charArray.length];
    boolean[] index=new boolean[charArray.length];
    
    int minIndex=-1;
    char minVal='\0';
    
    for (int r=0;r<result.length;r++) {
      minIndex=-1; minVal='\0';
      for (int c=0;c<charArray.length;c++) {
       if (
           !index[c]&&
           (
            minIndex==-1||
            charArray[c]<minVal
           )
          ){
         minIndex=c;
         minVal=charArray[c];
       }
      }
       index[minIndex]=true;
       result[r]=minVal;
    }
    
    return result;
  }
  private static char[] getSortedCharArray(char[] charArray) {
    
    char[] result = new char[charArray.length];
    for (int i=0;i<result.length;i++) {
      result[i]=charArray[i];
    }
    
    char val;
    boolean sorted;
    
    do {
      sorted=true;
      for (int i=0;i<result.length-1;i++) {
        if (result[i]>result[i+1]) {
          val=result[i+1];
          result[i+1]=result[i];
          result[i]=val;
          if (sorted) { sorted=false; }
        }
      }
    } while (!sorted);
    
    return result;
    
  }
  
  private static boolean isSorted(char[] charArray) {
    
    boolean result=true;
    
    for (int i=1;i<charArray.length;i++) {
      if (charArray[i]<charArray[i-1]) {
        result=false;
        break;
      }
    }
    
    return result;
  }
  
  private static void display(char[] charArray) {
    
    String output="";
    
    output+="******\n";
    
    for (int i=0;i<charArray.length;i++) {
      output+=charArray[i] + " ";
    }
    output+="\n";
    
    output+="******\n";
    
    print(output);
  
  }
  private static void print(Object object) {
    print(true,object);
  }
  private static void print(boolean newLine,Object object) {
    if (newLine) {
      System.out.println(object);
    } else {
      System.out.print(object);
    }
  }
}