package br.com.senai.service;

import br.com.senai.model.Contact;
import br.com.senai.repository.ContactRepository;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public Contact save(Contact contact) {
        if (contact != null) {
            return contactRepository.save(contact);
        }
        //verificar o que será retornado caso if seja false
        return null;
    }
}
