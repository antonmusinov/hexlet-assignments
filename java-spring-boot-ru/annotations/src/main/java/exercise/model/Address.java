package exercise.model;

//import exercise.annotation.Inspect;

import exercise.annotation.Inspect;

public class Address {
    private final String city;
    private final int postalCode;

    public Address(String city, int postalCode) {
        this.city = city;
        this.postalCode = postalCode;
    }

    @Inspect
    public String getCity() {
        return city;
    }

    @Inspect
    public int getPostalCode() {
        return postalCode;
    }

    public String getFullAddress() {
        return city + " " + postalCode;
    }
}
