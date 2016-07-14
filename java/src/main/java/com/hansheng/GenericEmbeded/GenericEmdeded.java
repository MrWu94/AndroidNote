package com.hansheng.GenericEmbeded;

/**
 * Created by hansheng on 2016/7/14.
 */
public class GenericEmdeded {
    public static void main(String... args){
        Info<String,Integer> info=new Info<String,Integer>("hansheng",22);

        Demo<Info<String,Integer>> d=new Demo<Info<String,Integer>>(info);

        System.out.println(d.getInfo());
    }
    static class Info<K,V>{
        private K key;

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return Value;
        }

        public void setValue(V value) {
            Value = value;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "key=" + key +
                    ", Value=" + Value +
                    '}';
        }

        private V Value;

        public Info(K key, V value) {
            this.key = key;
            Value = value;
        }
    }

    static class Demo<S>{
        public S getInfo() {
            return info;
        }

        public void setInfo(S info) {
            this.info = info;
        }

        private S info;

        public Demo(S info) {
            this.info = info;
        }
    }

}
