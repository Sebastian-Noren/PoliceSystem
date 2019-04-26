package pust.model.database_functionality;

import pust.model.entity.Employee;
import pust.model.entity.Person;
import pust.model.entity.PersonalNumber;

import java.util.ArrayList;

public class PoliceChiefCommand implements Interactable{

    public ArrayList<Employee> viewEmployeeRecord(){

        return null;
    }

    @Override
    public Person viewPerson(PersonalNumber personalNumber) {
        return null;
    }

    @Override
    public Employee viewEmployee(PersonalNumber personalNumber) {
        return null;
    }

    @Override
    public void resetPassword(String userName) {
        //TODO The policeChief object should only be able to reset its own password
    }
}
