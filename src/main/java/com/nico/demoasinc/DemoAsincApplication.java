package com.nico.demoasinc;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.sun.org.glassfish.external.statistics.impl.StatisticImpl.START_TIME;

@SpringBootApplication
@EnableAsync
public class DemoAsincApplication implements CommandLineRunner {

	@Autowired
	ServiceImpl servicio;

	public static void main(String[] args) {
		SpringApplication.run(DemoAsincApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		LocalTime inicio = LocalTime.now();

		List<Integer> integerList = new ArrayList<>();
		integerList.add(5000);
		integerList.add(5000);
		integerList.add(11000);
		integerList.add(15000);

		System.out.println("inicio");
		List<CompletableFuture> completableFutureList = integerList
				.stream()
				.map( x -> servicio.voidMethodString(x)).collect(Collectors.toList());


		System.out.println("antes allof");
		CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()]));


		System.out.println("antes join");
		//CompletableFuture<List<Object>> listCompletableFuture = voidCompletableFuture.thenApply(x -> {
		//	return completableFutureList.stream().map(y -> y.join()).collect(Collectors.toList());
		//});




		System.out.println("antes obtencion");
		List<Object> collect = voidCompletableFuture.thenApply(x -> {
			System.out.println("antes de join");
			return completableFutureList.stream().map(y -> y.exceptionally((ex) -> {

				//throw new RuntimeException("execption cuando hay error en llamada a servicio");
				return null;

			}).join()).collect(Collectors.toList());
		}).get();




		//System.out.println("antes obtencion");
		//List<Object> collect = listCompletableFuture.get().stream().collect(Collectors.toList());


		System.out.println("lista:" + collect);


		long serviceTime = ChronoUnit.MILLIS.between( inicio, LocalTime.now());

		System.out.println("termino:" + serviceTime);
		//allof.get();

		//if (allof.isDone ()) {
			//System.out.println("Future result " + stringCompletableFuture.get() + " | " + stringCompletableFuture2.get() + " | " + stringCompletableFuture3.get());
		//} else {
		//	System.out.println("Futures are not ready");
		//}





	}

	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new CustomAsyncExceptionHandler();
	}

	public int handleError(Throwable throwable){
		return  10;
	}

}
