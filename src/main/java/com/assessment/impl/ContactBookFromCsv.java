package com.assessment.impl;

import com.assessment.logic.ContactBookResource;
import com.assessment.model.Contact;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactBookFromCsv implements ContactBookResource {

    /**
     * Returns contactbook from a resource.
     * @return list of contact book.
     */
    public List<Contact> getContactBookResource(String resource) {

        String basePath = this.getClass().getClassLoader().getResource("").getPath();
        try (Stream<String> lines = Files.lines(Paths.get(basePath + resource))) {
            var contactBook = lines
                    .skip(1)
                    .map(line-> Normalizer.normalize(line, Normalizer.Form.NFKD))
                    .map(String::toLowerCase)
                    .map(Contact::build)
                    .collect(Collectors.toList());
            return contactBook;
        } catch (IOException ioException) {
            System.out.println("File not found...");
        }
        return Collections.emptyList();
    }

}
