# Client-Web-Application
I develop a Hybrid Spring Boot Applications that is made of a client web application with multiple web pages, for the purpose of managing peoples’ contact information. This client application borrows its database functionality from the second portion of my Spring application that is a Restful web service.

Restful web service
The Restful web service includes a contact bean. 

Database
The databse has schema SQL scripts which generate the “contacts” table in tge H2 database, based on the structure of the above contact bean 
I have created a series of dummy records for my application. I have included at least 4 contacts for each role. I have included the necessary methods in a Database Access class for all operations that need to be done on the database (addContact, ListContactByID, ListAll, RegisterUser, etc.)

Rest Controller
I have included the necessary methods in the RestController to implement the offered services (using internal HTTP methods such as GET, POST, PUT, etc.), and to invoke the corresponding database methods in order to perform the actual CRUD operations.

Security Measures
I included Spring Security which is connected to my database into my application. I made the home page to be accessible by anyone without having to log  in. I created roles for Admin, Member, and Guest.

Home Pahe 
The client web application has the following 4 hyperlinks to additional pages shown in the homepage; Home, Add Contact, List Contacts, Register User.

Register.html:
I created a user registration page that allows  me to create a new user (not a contact!). My registration page includes checkboxes for each of the 3 roles. The user is assigned roles based on the checkboxes selected.
All pages are password protected.

ListContacts.html:
I created a view page that allows me to view all of the contact information. I also included hyperlinks to edit/delete each contact.
 
AddContact.html:
I created a view page that allows me to enter the data for a new contact. The role is a <select> control where only one of the 3 roles is selected. 
