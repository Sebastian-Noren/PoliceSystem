package pust.model.database_functionality;

import pust.model.entity.Employee;
import pust.model.entity.Person;
import pust.model.entity.PersonalNumber;

public class PoliceCommand implements Interactable {

    public void applyForPassport(PersonalNumber personalNumber){

    }

    public void applyForId(PersonalNumber personalNumber){

    }

    @Override
    public Person viewPerson(PersonalNumber personalNumber) {
        //TODO Should display all information
        return null;
    }

    @Override
    public Employee viewEmployee(PersonalNumber personalNumber) {
        //TODO Should display only the logged in police employment information
        return null;
    }

    @Override
    public void resetPassword(String userName) {
        //TODO Should only reset the police objects own password
    }
}
