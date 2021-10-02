package com.example.webflux;

import com.example.webflux.TestEntity;
import com.example.webflux.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MyReactiveService {

    @Autowired
    TestRepository testRepository;

    public void save(TestEntity e)
    {
        //Hep yeni kabul ediyoruz bu senaryoda bu değeri ayarlamak gerekiyor yoksa
        e.setAsNew();
        testRepository.save(e).subscribe();
        //testRepository.save(e).block();
    }

    public Flux<TestEntity> getAllEntity()
    {
      return   testRepository.findAll();
    }

    public Mono<TestEntity> getAllEntityMono()
    {
        return   testRepository.findById(1);
    }

    public Mono<TestEntity> findByIDMono(Integer id)
    {
        return   testRepository.findById(id);
    }
}
