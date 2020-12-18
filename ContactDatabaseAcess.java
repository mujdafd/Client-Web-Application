package ca.sheridancollege.database;

import ca.sheridancollege.beans.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ContactDatabaseAcess {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public Contact findContactById(int id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        String query = "select * from contact where id=:id";
        parameters.addValue("id", id);
        ArrayList<Contact> contacts = (ArrayList<Contact>) jdbc.query(query, parameters, new
                BeanPropertyRowMapper<Contact>(Contact.class));
        if (contacts.size() > 0)
            return contacts.get(0);
        else
            return null;
    }

	public void deleteContactById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		String query = "delete from contact where id=:id";
		parameters.addValue("id", id);
		jdbc.update(query, parameters);
	}

    public void updateContact(Contact contact) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        String query = "update contact " +
                "set name = :name, " +
                "phoneNumber = :phoneNumber, " +
                "address = :address, " +
                "email = :email, " +
                "role = :role " +
                "where id = :id";
        parameters.addValue("id", contact.getId());
        parameters.addValue("address", contact.getAddress());
        parameters.addValue("name", contact.getName());
        parameters.addValue("phoneNumber", contact.getPhoneNumber());
        parameters.addValue("email", contact.getEmail());
        parameters.addValue("role", contact.getRole());
        jdbc.update(query, parameters);
    }

    public void createContact(Contact contact) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        String query = "insert into contact (name, phoneNumber, address, email, role) " +
				"VALUES (:name, :phoneNumber, :address, :email, :role)";
        parameters.addValue("address", contact.getAddress());
        parameters.addValue("name", contact.getName());
        parameters.addValue("phoneNumber", contact.getPhoneNumber());
        parameters.addValue("email", contact.getEmail());
        parameters.addValue("role", contact.getRole());
        jdbc.update(query, parameters);
    }

	public List<Contact> findAllContact() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "select * from contact";
		return jdbc.query(query, parameters, new
				BeanPropertyRowMapper<>(Contact.class));
	}
}
