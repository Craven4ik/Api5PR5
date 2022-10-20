package com.example.PR5.repository;

import com.example.PR5.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
    Contact findContactById(long id);
    void deleteContactById(long id);
}
