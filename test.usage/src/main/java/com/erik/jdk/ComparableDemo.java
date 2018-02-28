package com.erik.jdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by nali on 2017/9/11.
 */
public class ComparableDemo {


    public static class Product implements Comparable<Product>{
        int sequence;
        String name;
        int No;

        public Product(int sequence, String name, int no) {
            this.sequence = sequence;
            this.name = name;
            No = no;
        }

        public int getSequence() {
            return sequence;
        }

        public void setSequence(int sequence) {
            this.sequence = sequence;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNo() {
            return No;
        }

        public void setNo(int no) {
            No = no;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "sequence=" + sequence +
                    ", name='" + name + '\'' +
                    ", No=" + No +
                    '}';
        }

        @Override
        public int compareTo(Product o) {
            return  ((Integer)sequence).compareTo(o.getSequence());
        }
    }

    public static List<Product> getProducts(){
        List<Product> products=new ArrayList<Product>();

        products.add(new Product(1,"ccc",6));
        products.add(new Product(3,"bbb",1));
        products.add(new Product(2,"aaa",3));
        return products;
    }


    public static void main(String[] args) {


        List<Product> productList = getProducts();

        System.out.println(productList);
        Collections.sort(productList);
        System.out.println(productList);

        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        Collections.reverse(productList);
        System.out.println(productList);

    }

}
