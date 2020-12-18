package ca.sheridancollege.service;

import ca.sheridancollege.beans.Contact;
import ca.sheridancollege.database.ContactDatabaseAcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

	@Autowired
	private ContactDatabaseAcess da;

	public void updateContact(Contact contact) {
		da.updateContact(contact);
	}

	public void createContact(Contact contact) {
		da.createContact(contact);
	}

	public Contact findContactById(int id) {
		return da.findContactById(id);
	}

	public void deleteContact(int id) {
		da.deleteContactById(id);
	}


	public List<Contact> findAllContact() {
		return da.findAllContact();
	}
}
