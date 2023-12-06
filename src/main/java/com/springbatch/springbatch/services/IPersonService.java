package com.springbatch.springbatch.services;

import com.springbatch.springbatch.entities.Person;

import java.util.List;

public interface IPersonService {
    void saveAll(List<Person> personList);

}
