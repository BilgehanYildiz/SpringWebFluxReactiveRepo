package com.example.webflux;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TestRepository extends ReactiveCrudRepository<TestEntity,Integer>
{
}
