package com.vaga.java.computeFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Description 商店
 * @Date 2019/4/16 20:49
 * @Version 1.0
 **/
public class ShopDemo {
    public static class Shop {
        public double getPrice(String product) {
            return calcPrice(product);
        }

        private double calcPrice(String product) {
            delay();
            return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
        }

        public void delay() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public Future<Double> getPriceAsync(String product) {
            return CompletableFuture.supplyAsync(() -> calcPrice(product));
        }
    }

    public static void main(String[] args) {
        Shop shop = new ShopDemo.Shop();
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = (System.nanoTime() - start) / 1000000;
        System.out.println("invocation returned after " + invocationTime + " msecs");
        try {
            Thread.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long retrievalTime = (System.nanoTime() - start) / 1000000;
        System.out.println("price returned after " + retrievalTime + " msecs");
    }
}
