package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public ResponseEntity<Person> createPerson(@RequestBody Person p){
        Person savedPerson = personRepository.save(p);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPerson(@PathVariable("id")int id){
       Person p = personRepository.findOne((long)id);
       return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @RequestMapping(value = "/people",method = RequestMethod.GET)
    public ResponseEntity<Iterable<Person>> getPersonList(){
        Iterable<Person> ppl = personRepository.findAll();
        return new ResponseEntity<>(ppl, HttpStatus.OK);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Person> updatePerson(@RequestBody Person p){
        Person person = personRepository.save(p);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Person> deletePerson(@PathVariable("id")int id){
        personRepository.delete((long)id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

}
