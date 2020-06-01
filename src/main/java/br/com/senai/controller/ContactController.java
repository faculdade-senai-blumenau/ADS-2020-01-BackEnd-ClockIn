package br.com.senai.controller;

import br.com.senai.converter.ContactConverter;
import br.com.senai.model.Contact;
import br.com.senai.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.senai.model.ContactDTO;

import java.util.List;

@RestController
@RequestMapping({"/contacts"})
public class ContactController {

    //@RequiredArgsConstructor cria construtor e injeta valor na propriedade final quando a classe é instanciada;
    private final ContactService contactService;
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getDomains() {
        List<Contact> contacts = contactService.findAll();
        return ResponseEntity.ok(contacts);
    }

    @PostMapping
    public ResponseEntity<ContactDTO> postContact(@RequestBody ContactDTO contactDTO) {
        Contact contact = ContactConverter.toEntity(contactDTO);
        Contact savedContact = contactService.save(contact);
        ContactDTO savedDTO = ContactConverter.toDTO(savedContact);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDTO> put(@PathVariable Long contactId, @RequestBody ContactDTO contactDTO) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContactDTO> delete(@PathVariable Long contactId) {
        return null;
    }
//
//    @GetMapping(path = {"/{id}"})
//    public static ResponseEntity<Contact> findById(@PathVariable long id){
//        return contactRepository.findById(id)
//                .map(record -> ResponseEntity.ok().body(record))
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public Contact create(@RequestBody Contact contact){
//        return this.save(contact);
//    }

//    @PutMapping(value="/{id}")
//    public ResponseEntity<Contact> update(@PathVariable("id") long id, @RequestBody Contact contact){
//        return this.findById(id)
//                .map(record -> {
//                    record.setName(contact.getName());
//                    record.setEmail(contact.getEmail());
//                    record.setPhone(contact.getPhone());
//                    Contact updated = repository.save(record);
//                    return ResponseEntity.ok().body(updated);
//                }).orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping(path ={"/{id}"})
//    public ResponseEntity<?> delete(@PathVariable("id") long id) {
//        return ContactService.findById(id)
//                .map(record -> {
//                    ContactService.deleteById(id);
//                    return ResponseEntity.ok().build();
//                }).orElse(ResponseEntity.notFound().build());
//    }

}
