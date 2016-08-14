package com.hansheng.sort;

/**
 * Created by hansheng on 2016/8/14.
 * 在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整，
 * 让较大的数往下沉，较小的往上冒。即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
 */
public class bubblesort {

    int a[]={1,54,6,3,78,34,12,45};

    int temp=0;

   public void bubblesort(){
       for(int i=0;i<a.length;i++){
           for(int j=i+1;j<a.length;j++){
               if(a[i]>a[j]){
                   temp=a[i];
                   a[i]=a[j];
                   a[j]=temp;
               }
           }
       }
       for(int i=0;i<a.length;i++){
           System.out.println(a[i]);
       }
   }


    public static void main(String... args){
           new bubblesort().bubblesort();
    }
}
