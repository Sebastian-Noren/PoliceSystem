package pust.model.entity;

import pust.model.administrative_functions.application_functions.Identification;
import pust.model.administrative_functions.report_system.report.CrimeReport;
import pust.model.administrative_functions.report_system.record.Record;
import pust.model.enumerations.Gender;


public abstract class Employee extends Person {

    private int salary;
    private Enum title;
    private int id;
    private String userName;
    private String password;
    private String email;

    public Employee(
            String firstName,
            String surname,
            PersonalNumber personalNumber,
            Address address,
            Record crimeRecord,
            int height,
            Identification identification,
            String phoneNumber,
            Gender gender,
            boolean isWanted,
            boolean isMissing,
            boolean inCustody,
            boolean isSuspect,
            int salary,
            Enum title,
            int id,
            String userName,
            String password,
            String email
    ) {
        super(
                firstName,
                surname,
                personalNumber,
                address,
                crimeRecord,
                height,
                identification,
                phoneNumber,
                gender,
                isWanted,
                isMissing,
                inCustody,
                isSuspect
        );
        this.salary = salary;
        this.title = title;
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }
}
