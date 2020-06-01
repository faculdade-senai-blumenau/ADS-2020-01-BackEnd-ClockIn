package br.com.senai.converter;

import br.com.senai.model.ContactDTO;
import br.com.senai.model.Contact;

public class ContactConverter {

    public static Contact toEntity(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setName(contactDTO.getName());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhone(contactDTO.getPhone());
        return contact;
    }

    public static ContactDTO toDTO(Contact contact) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setName(contact.getName());
        contactDTO.setEmail(contact.getEmail());
        contactDTO.setPhone(contact.getPhone());
        return contactDTO;
    }
}
