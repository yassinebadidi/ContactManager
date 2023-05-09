package fr.training.contactmanager.repository;

import fr.training.contactmanager.model.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {
    List<Contact> findContactByLastnameContaining(String search);
}
