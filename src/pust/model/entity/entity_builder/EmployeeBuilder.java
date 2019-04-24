package pust.model.entity.entity_builder;

public abstract class EmployeeBuilder extends PersonBuilder {

    protected int salary;
    protected Enum title;
    protected int id;
    protected String userName;
    protected String password;

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

}
