package com.springbatch.springbatch.repositories;

import com.springbatch.springbatch.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface IPersonRepository extends CrudRepository<Person, Long> {
}