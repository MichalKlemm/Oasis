package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import repository.UserRepository;
import dataUser.DataUser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")

public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<DataUser>> getAllUsers() {
        try {
            List<DataUser> dataUser = new ArrayList<DataUser>();

            dataUser = userRepository.findAll();

            if (dataUser.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(dataUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<DataUser> getUserById(@PathVariable("id") int id) {
        DataUser dataUser = userRepository.findById(id);

        if (dataUser != null) {
            return new ResponseEntity<>(dataUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@RequestBody DataUser dataUser) {

        if (dataUser != null) {

            try {

                File dataFile = new File("C:\\Users\\Public\\IntelliJ\\Oasis\\dataPersonId.txt");
                Scanner scanner = new Scanner(dataFile);

                dataUser.setPersonId("");

                while (scanner.hasNextLine()) {
                    String record = scanner.nextLine();

                    DataUser du = userRepository.findByPersonId(record);
                    if (du == null) {
                        dataUser.setPersonId(record);
                        break;
                    }

                }

                if (dataUser.getPersonId() == "") {

                    return new ResponseEntity<>("Neni k dispozici další personID.", HttpStatus.NOT_FOUND);
                }

                UUID uuid = UUID.randomUUID();
                dataUser.setUuid(uuid.toString());

                userRepository.save(dataUser);
                return new ResponseEntity<>("Uloženo.", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Došlo k chybě při ukládání dat.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Data pro uložení nenalezena.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/user")
    public ResponseEntity<String> updateUser(@RequestBody DataUser dataUser) {

        if (dataUser != null) {

            try {

                userRepository.update(dataUser);

                return new ResponseEntity<>("Opraveno.", HttpStatus.OK);

            } catch (Exception e) {
                return new ResponseEntity<>("Došlo k chybě při opravě dat.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>("Data pro opravu nenalezena.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {

        try {

            userRepository.deleteById(id);

            return new ResponseEntity<>("Smazáno.", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Došlo k chybě při mazání záznamu.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
