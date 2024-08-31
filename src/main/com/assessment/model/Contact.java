package com.assessment.model;

/**
 * This class represents a contact book
 */
public record Contact(
    String id,
    String firstName,
    String lastName,
    String email,
    String zipCode,
    String address){

    public static Contact build(String line){
        var args = line.split(",");
        return new Contact(args[0], args[1], args[2], args[3], args[4], args[5]);
    }

    /**
     * Method equals will try to find equality by:
     * 1- email, first and last name
     * 2- location (zipCode), first and last name
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return
                this.email.equalsIgnoreCase(contact.email) && this.firstName.equalsIgnoreCase(contact.lastName)
                && this.lastName.equalsIgnoreCase(contact.lastName)
                ||
                this.email.equalsIgnoreCase(contact.email) && this.firstName.equalsIgnoreCase(contact.lastName)
                        && this.lastName.equalsIgnoreCase(contact.lastName);
    }

}


