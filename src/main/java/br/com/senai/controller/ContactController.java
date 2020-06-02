package br.com.senai.controller;

import br.com.senai.model.Contact;
import br.com.senai.repository.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/contacts"})
public class ContactController {

    private ContactRepository contactRepository;

    ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    @GetMapping //Recuperar todos os registros (GET / contatos)
    public ResponseEntity<List<Contact>> getContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping(path = {"/{id}"}) //Recuperar um registro por ID (GET / contatos / {id})
    public ResponseEntity<Contact> findById(@PathVariable long id){
        return contactRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping //Crie um novo registro (POST / contatos)
    public Contact create(@RequestBody Contact contact){
        return contactRepository.save(contact);
    }


    @PutMapping(value="/{id}") //Atualizar um registro (PUT / contatos)
    public ResponseEntity<Contact> update(@PathVariable("id") long id,
                                          @RequestBody Contact contact){
        return contactRepository.findById(id)
                .map(record -> {
                    record.setName(contact.getName());
                    record.setEmail(contact.getEmail());
                    record.setPhone(contact.getPhone());
                    Contact updated = contactRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"}) // Remover um registro (DELETE / Contacts / {id})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return contactRepository.findById(id)
                .map(record -> {
                    contactRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
