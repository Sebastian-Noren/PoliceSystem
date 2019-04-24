package pust.model.notification_system;

/*
 * TODO This class should represent a notification for the active user
 */

import pust.model.entity.Address;

public class Notification {

    private Address address;
    private String description;

    public Notification(Address address, String description) {
        this.address = address;
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }
}
