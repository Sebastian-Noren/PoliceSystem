package pust.model.entity;

import pust.model.entity.builder.VisitorBuilder;

public class Notifier {

    public Notifier() {
        Address address = new Address();
        Visitor visitor = (Visitor) new VisitorBuilder()
                .withFirstName("asdf")
                .withSurname("asdf")
                .withAddress(address)
                .build();
    }

}
