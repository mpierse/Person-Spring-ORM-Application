package io.zipcoder.crudapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class PersonControllerTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonController controller = new PersonController();


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createPersonTest(){
        Person person = new Person("a", "b");
        Person savedPerson = new Person();
        when(repository.save(person)).thenReturn(savedPerson);

        ResponseEntity<Person> actual = controller.createPerson(person);

        Assert.assertEquals(actual.getStatusCode(), HttpStatus.CREATED);
        Assert.assertEquals(actual.getBody(), savedPerson);
    }

    @Test
    public void getPersonTest(){
        Person person = new Person();
        person.setId(88L);
        when(repository.findOne(88L)).thenReturn(person);

        ResponseEntity<Person> actual = controller.getPerson(88);

        Assert.assertEquals(actual.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(actual.getBody(), person);
    }

    @Test
    public void getPersonListTest(){
        Iterable<Person> personList = new ArrayList<>();
        when(repository.findAll()).thenReturn(personList);

        ResponseEntity<Iterable<Person>> actual = controller.getPersonList();

        Assert.assertEquals(actual.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(actual.getBody(), personList);
    }

    @Test
    public void updatePersonTest(){
        Person p = new Person("a", "b");
        Person update = new Person("a","b");
        when(repository.save(p)).thenReturn(update);

        ResponseEntity<Person> actual = controller.updatePerson(p);

        Assert.assertEquals(actual.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(actual.getBody(), update);
    }

    @Test
    public void deletePersonTest(){
        Person p = new Person("a", "b");
        p.setId(8783L);
        doAnswer(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                if (arguments != null && arguments.length > 1 && arguments[0] != null && arguments[1] != null) {

                    controller.deletePerson(8783);

                }
                return null;
            }
        }).when(repository).delete(8783L);

        ResponseEntity<Person> actual = controller.deletePerson(8783);

        Assert.assertEquals(actual.getStatusCode(), HttpStatus.NO_CONTENT);
        Assert.assertEquals(actual.getBody(), null);
    }
}
