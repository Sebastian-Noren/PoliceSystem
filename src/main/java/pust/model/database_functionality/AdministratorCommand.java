package pust.model.database_functionality;

import pust.model.entity.Employee;
import pust.model.entity.Person;
import pust.model.entity.PersonalNumber;

public class AdministratorCommand implements Interactable {

    //TODO Create content for the method body

    public void changeUser(String userName) {

    }

    public void removeUser(String userName) {

    }

    public void addUser(Employee employee) {

    }

    @Override
    public Person viewPerson(PersonalNumber personalNumber) {
        /*
         * TODO When the admin view's a person the object returned should not contain personal data
         * Criminal record
         * Personal number
         */

        return null;
    }

    @Override
    public Employee viewEmployee(PersonalNumber personalNumber) {
        /*
         * TODO When the admin view's an employee the object returned should not contain personal data
         * Salary
         * Employee record
         */

        return null;
    }

    @Override
    public void resetPassword(String userName) {

    }
}
