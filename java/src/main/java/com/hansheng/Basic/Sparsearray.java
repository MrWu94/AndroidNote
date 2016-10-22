package com.hansheng.Basic;

/**
 * Created by hansheng on 16-10-21.
 * SparseArray是android提供的一个工具类，它可以用来替代hashmap进行对象的存储，其内部实现了一个矩阵压缩算法，很适合存储稀疏矩阵的。
 * 和Hashmap的对比
 * <p>
 * 既然android推荐用这个东西，自然有用它的道理。其内部实现了压缩算法，可以进行矩阵压缩，大大减少了存储空间，
 * 节约内存。此外它的查找算法是二分法，提高了查找的效率。
 * 如果用到了： HashMap<Integer, E> hashMap = new HashMap<Integer, E>();
 * <p>
 * 可以替换为：SparseArray<E> sparseArray = new SparseArray<E>();
 * <p>
 * 2>
 * <p>
 * 如果用到了：HashMap<Integer, Boolean> hashMap = new HashMap<Integer, Boolean>
 * <p>
 * 可以替换为：SparseBooleanArray array = new SparseBooleanArray();
 * <p>
 * 3>
 * <p>
 * 如果用到了：HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>
 * <p>
 * 可以替换为：SparseIntArray array = new SparseIntArray();
 */

public class Sparsearray {
    public static void main(String... args) {

    }
}
