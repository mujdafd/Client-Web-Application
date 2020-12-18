package ca.sheridancollege.controllers;

import ca.sheridancollege.beans.Contact;
import ca.sheridancollege.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @DeleteMapping("/{id}")
    public String deleteContact(@PathVariable Integer id){
        contactService.deleteContact(id);
        return "OK";
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Integer id){
        return contactService.findContactById(id);
    }

    @PostMapping("/{id}")
    public String updateContact(@RequestBody Contact contact){
        contactService.updateContact(contact);
        return "OK";
    }

    @PostMapping(value = "")
    public String createContact(@RequestBody Contact contact){
        contactService.createContact(contact);
        return "OK";
    }

    @GetMapping("")
    public List<Contact> getAllContacts(){
        return contactService.findAllContact();
    }


}
