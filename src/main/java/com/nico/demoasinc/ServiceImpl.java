package com.nico.demoasinc;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class ServiceImpl {

    @Async
    public CompletableFuture<String> voidMethodString(int aaa) {
        try {
            Thread.sleep(aaa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CompletableFuture.completedFuture("MederuQ: " + aaa);

        if(aaa==11000){
            throw new RuntimeException("ex a 11 seg");
        }

        System.out.println("MederuQ: " + aaa);
        return CompletableFuture.completedFuture("MederuQ: " + aaa);
    }


    @Async
    public CompletableFuture<Integer> voidMethodInteger(int aaa) {
        try {
            Thread.sleep(aaa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MederuQ: " + aaa);
        return CompletableFuture.completedFuture(new Integer(102030));
    }


    @Async
    public CompletableFuture<Double> voidMethodDouble(int aaa) {
        try {
            Thread.sleep(aaa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MederuQ: " + aaa);
        return CompletableFuture.completedFuture(new Double(203040.11));
    }

    @Async
    public String otroMetodo(int asd){
        return "Hola: " + asd;

    }
}
