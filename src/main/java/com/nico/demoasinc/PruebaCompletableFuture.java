package com.nico.demoasinc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class PruebaCompletableFuture {

    public static void main(String[] args){

        System.out.println("Inicio metodo           " + LocalTime.now() + " thread: " + Thread.currentThread().getName());

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("    Iniciando SA1 " + LocalDateTime.now() + " thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(10000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("    Terminando SA1 " + LocalDateTime.now());
            return "        Future 1 " + LocalTime.now();
            //throw new RuntimeException("Error 1: " + LocalTime.now());
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("    Iniciando SA2 " + LocalDateTime.now()+ " thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("    Terminando SA2 " + LocalDateTime.now());
            return "        Future 2 " + LocalTime.now();
            //throw new RuntimeException("Error 2: " + LocalTime.now());
        });

        System.out.println("Terminando metodo       " + LocalTime.now());

        try {

            System.out.println("");
            System.out.println("Empenzando obtencion       " + LocalTime.now());
            System.out.println("    promotio:" + future2.get() +" / " + LocalDateTime.now()+ " thread: " + Thread.currentThread().getName());

            System.out.println("    validate:" + future1.get() +" / " + LocalDateTime.now()+ " thread: " + Thread.currentThread().getName());

            System.out.println("Terminando obtencion       " + LocalTime.now());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

}
