package pust.model.entity;

import pust.model.administrative_functions.application_functions.Identification;
import pust.model.administrative_functions.report_system.Crime;
import pust.model.administrative_functions.report_system.record.Record;


public abstract class Employee extends Person {

    private int salary;
    private Enum title;
    private int id;
    private String userName;
    private String password;

    public Employee(
            String firstName,
            String surname,
            PersonalNumber personalNumber,
            Address address,
            Record<Crime> crimeRecord,
            int height,
            Identification identification,
            int salary,
            Enum title,
            int id,
            String userName,
            String password
    ) {
        super(
                firstName,
                surname,
                personalNumber,
                address,
                crimeRecord,
                height,
                identification
        );
        this.salary = salary;
        this.title = title;
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public int getSalary() {
        return salary;
    }

    public Enum getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
