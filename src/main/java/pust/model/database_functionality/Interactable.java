package pust.model.database_functionality;

import pust.model.entity.Employee;
import pust.model.entity.Person;
import pust.model.entity.PersonalNumber;

/*
 * //TODO Change the class name to something meaningful
 */

public interface Interactable {

    /*
     * TODO Check the parameters, are they correct?
     */

    public Person viewPerson(PersonalNumber personalNumber);

    public Employee viewEmployee(PersonalNumber personalNumber);

    public void resetPassword(String userName);
}
