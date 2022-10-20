package com.example.PR5.controller;

import com.example.PR5.entity.Contact;
import com.example.PR5.repository.ContactRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getContacts() {
        try {
            List<Contact> list = contactRepository.findAll(Sort.by("id"));
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> save(@RequestBody Contact Contact) {
        try {
            return new ResponseEntity<>(contactRepository.save(Contact), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getContact(@Parameter(description = "id контакта,который мы ищем",required = true,in = ParameterIn.PATH) @PathVariable Optional<Long> id) {
        Contact contact = contactRepository.findContactById(id.get());
        if (contact == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(contact, HttpStatus.OK);
        }
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> putContact(@RequestBody Contact contact) {
        try {
            return new ResponseEntity<>(contactRepository.save(contact), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/contacts")
    public ResponseEntity<HttpStatus> deleteContacts() {
        try {
            contactRepository.deleteAll();
            return new ResponseEntity<>((HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@Parameter(description = "id контакта,который мы хотим удалить",required = true,in = ParameterIn.PATH) @PathVariable Long id) {
        try {
            Optional<Contact> contact = contactRepository.findById(id);
            contact.ifPresent(value -> contactRepository.delete(value));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
