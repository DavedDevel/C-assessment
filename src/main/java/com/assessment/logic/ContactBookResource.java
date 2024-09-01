package com.assessment.logic;

import com.assessment.model.Contact;

import java.util.List;

/**
 * Returns contactbook from a resource.
 * @return list of contact book.
 */
public interface ContactBookResource {
    List<Contact> getContactBookResource();

}
