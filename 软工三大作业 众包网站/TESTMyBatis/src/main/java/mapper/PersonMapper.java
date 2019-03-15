package mapper;

import model.Person;

public interface PersonMapper {
    public void insertPerson(Person person);
    public void deleteById(int id);
    public Person queryPersonById(int id);
    public Person queryPersonByName(String name);
    public void updatePerson(Person person);
}
