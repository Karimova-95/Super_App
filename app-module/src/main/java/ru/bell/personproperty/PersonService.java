package ru.bell.personproperty;

import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonInfo user;

    public PersonService(PersonInfo user) {
        this.user = user;
    }
}
