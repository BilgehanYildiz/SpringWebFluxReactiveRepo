package com.example.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@RestController
@RequestMapping("api/reactive")
public class ReactiveController {

    @Autowired
    MyReactiveService myReactiveService;

    @GetMapping("alldb")
    public Flux<TestEntity> getAllFromDB() throws Exception
    {
        Flux<TestEntity> a= myReactiveService.getAllEntity();

        a.delayElements(Duration.ofMillis(1000)).doOnNext(u -> System.out.println("listUsers1 received " + u.getName())).
                subscribe();
        return a;
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<TestEntity>> getTestEntityId(@PathVariable int id){
        return this.myReactiveService.findByIDMono(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("alldbsample")
    public Flux<TestEntity> getAllFromDBSample() throws Exception
    {
        return myReactiveService.getAllEntity();
    }

    @GetMapping("alldb2")
    public Mono<TestEntity> getAllFromDB2()
    {
        return myReactiveService.getAllEntityMono();
    }

    @GetMapping("save")
    public Mono<String> BulkSave()
    {
        for (int i=5;i<10000;i++)
        {
            TestEntity t=new TestEntity();
            t.setId(i);
            t.setName("Bilgehan"+i);
            t.setInfo("x"+i);
            myReactiveService.save(t);

        }
        return Mono.just("Bitti");
    }

    @GetMapping("all")
    public Flux<String> getAll()
    {
        return null;
    }
}
