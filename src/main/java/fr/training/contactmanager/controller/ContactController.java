package fr.training.contactmanager.controller;

import fr.training.contactmanager.model.entity.Contact;
import fr.training.contactmanager.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.List;

@Controller
@RequestMapping(path="/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;


    @GetMapping(path="/all")
    public String getAllContacts(Model model) {
        List<Contact> contactList = contactService.fetchAllContacts();
        model.addAttribute("contacts", contactList);
        return "list-contacts";
    }

    @GetMapping(path="/add")
    public String addContactForm(Model model) {
        model.addAttribute("contact",new Contact());
        return "add-contact";
    }

    @PostMapping(path="/add")
    public String addContact(@ModelAttribute Contact contact){
        contactService.addContact(contact);
        return "redirect:/contacts/all";
    }

    @GetMapping(path="/edit/{id}")
    public String editContactForm(Model model, @PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        Contact contact = contactService.findById(id);
        model.addAttribute("contact",contact);
        return "edit-contact";
    }

    @PostMapping(path="/edit/{id}")
    public String editContact(@ModelAttribute Contact contact){
        contactService.updateContact(contact);
        return "redirect:/contacts/all";
    }

    @GetMapping(path="/delete/{id}")
    public String deleteContact(@PathVariable Integer id){
        contactService.deleteContact(id);
        return "redirect:/contacts/all";
    }

    @GetMapping(path="/search")
    public String searchUser(Model model, @RequestParam String search) {
        List<Contact> contactList = contactService.getContactsByLastname(search);
        model.addAttribute("contacts", contactList);
        return "list-contacts";
    }

    @GetMapping(path="/details/{id}")
    public String detailsContact(Model model, @PathVariable Integer id) {
        Optional<Contact> contactOpt = contactService.getContact(id);
        if(contactOpt.isPresent()){
            model.addAttribute("contact",contactOpt.get());
            return "details-contact";
        }
        return "not_found";
    }

}
