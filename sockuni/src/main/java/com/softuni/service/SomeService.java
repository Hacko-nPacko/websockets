package com.softuni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by niakoi on 29/6/16.
 */
@Service
public class SomeService {

    @Autowired
    private Executor executor;

    public CompletableFuture<String> getMessage(String name) {
        return CompletableFuture.supplyAsync(() -> {
            heavyWork();
            return "hello async world[" + name + "]";
        }, executor);
    }

    private void heavyWork() {
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            Thread.interrupted();
        }
    }
}
