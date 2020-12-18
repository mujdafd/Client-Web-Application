package ca.sheridancollege.database;

import ca.sheridancollege.beans.Contact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class ContactRepository {

    @Value("${api.contact.base-url}")
    private String baseUrl;

    public List<Contact> getListContact() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity(baseUrl, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.getBody(), new TypeReference<List<Contact>>(){});
    }

    public void editContact(Contact contact) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(baseUrl  + "/" + contact.getId(), contact, Void.class);
    }

    public void deleteContact(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(baseUrl + "/" + id);
    }

    public Contact getContactById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Contact response = restTemplate.getForObject(baseUrl + "/" + id, Contact.class);
        return response;
    }

    public void addContact(Contact contact) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(baseUrl, contact, Void.class);
    }
}
