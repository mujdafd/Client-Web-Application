package ca.sheridancollege.controllers;

import ca.sheridancollege.beans.Contact;
import ca.sheridancollege.beans.User;
import ca.sheridancollege.database.ContactRepository;
import ca.sheridancollege.database.DatabaseAccess;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

	@Autowired
	private DatabaseAccess databaseAccess;

	@Autowired
	private ContactRepository contactRepository;

	@GetMapping ("/")
	public String index() {
		return "index";
	}

	@GetMapping ("/user-register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "user-register";
	}

	@PostMapping ("/user-register")
	public String registerUser(Model model, @ModelAttribute User user) {
		databaseAccess.addUser(user);
		databaseAccess.addUserRole(user.getEmail(), user.getRole());
		model.addAttribute("user", new User());
		model.addAttribute("message", "Register user successfully");
		return "user-register";
	}

	@GetMapping ("/add-contact")
	public String addContact(Model model) {
		model.addAttribute("contact", new Contact());
		return "/secure/add-contact";
	}

	@GetMapping ("/list-contact")
	public String listContact(Model model) throws JsonProcessingException {
		List<Contact> contacts = contactRepository.getListContact();
		model.addAttribute("contactList", contacts);
		return "/secure/list-contact";
	}

	@PostMapping("/add-contact")
	public String addContact(Model model, @ModelAttribute Contact contact) {
		contactRepository.addContact(contact);
		return "redirect:/list-contact";
	}

	@GetMapping("/delete-contact/{id}")
	public String deleteContactById(Model model,@PathVariable Long id) {
		contactRepository.deleteContact(id);
		return "redirect:/list-contact";
	}

	@GetMapping("/edit-contact/{id}")
	public String editContactById(Model model, @PathVariable Long id) {
		Contact contact = contactRepository.getContactById(id);
		model.addAttribute("contact", contact);
		return "secure/edit-contact";
	}


	@PostMapping("/edit-contact")
	public String editStudentById(Model model, @ModelAttribute Contact contact) {
		contactRepository.editContact(contact);
		return "redirect:/list-contact";
	}

	@GetMapping ("/login")
	public String login() {
		return "login";
	}

	@GetMapping ("/secure")
	public String secureIndex() {
		return "/secure/index";
	}

	@GetMapping ("/Access_Denied")
	public String accessDenied() {
		return "/error/Access_Denied";
	}

}
