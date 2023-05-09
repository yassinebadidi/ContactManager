package fr.training.contactmanager.service;

import fr.training.contactmanager.model.entity.Contact;
import fr.training.contactmanager.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> fetchAllContacts(){
        return contactRepository.findAll();
    }

    public void addContact(Contact contact){
        contactRepository.save(contact);
    }

    public Contact findById(Integer id) throws ChangeSetPersister.NotFoundException {
        Optional<Contact> contactOpt = contactRepository.findById(id);
        if(contactOpt.isPresent()){
            return contactOpt.get();
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public void updateContact(Contact contact) {
        contactRepository.save(contact);
    }

    public void deleteContact(Integer id) {
        Optional<Contact> contactOpt = contactRepository.findById(id);
        if(contactOpt.isPresent()){
            contactRepository.delete(contactOpt.get());
        }
    }

    public List<Contact> getContactsByLastname(String search) {
        return contactRepository.findContactByLastnameContaining(search);
    }

    public Optional<Contact> getContact(Integer id) {
        return contactRepository.findById(id);
    }

}
