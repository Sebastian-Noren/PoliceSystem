package pust.model.entity.entity_builder;

import pust.model.entity.Employee;

public abstract class EmployeeBuilder<T> extends PersonBuilder<Employee> {

    protected int salary;
    protected Enum title;
    protected int id;
    protected String userName;
    protected String password;
    protected String email;

    public EmployeeBuilder withSalary(int salary) {
        this.salary = salary;
        return this;
    }

    public EmployeeBuilder withTitle(Enum title) {
        this.title = title;
        return this;
    }

    public EmployeeBuilder withId(int id){
        this.id = id;
        return this;
    }

    public EmployeeBuilder withUserName (String userName){
        this.userName = userName;
        return this;
    }

    public EmployeeBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public EmployeeBuilder withEmail(String email){
        this.email = email;
        return this;
    }

}
