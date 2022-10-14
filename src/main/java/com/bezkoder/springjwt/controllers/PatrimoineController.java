package com.bezkoder.springjwt.controllers;

//import com.bezkoder.springjwt.models.Favoris;
import com.bezkoder.springjwt.models.Historique;
import com.bezkoder.springjwt.models.Patrimoine;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.HistoriqueRepository;
import com.bezkoder.springjwt.repository.PatrimoineRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/patrimoines")
public class PatrimoineController {
    @Autowired
    public PatrimoineRepository patrimoineRepository ;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public HistoriqueRepository historiqueRepository;

    @GetMapping("/all")
    public List<Patrimoine> allAccess(){
        return patrimoineRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Patrimoine> add(@RequestBody Patrimoine patrimoine) {
        try {
//          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
//          LocalDate localDate = LocalDate.now();
//          dtf.format(localDate

            Patrimoine patrimoine1 = patrimoineRepository
                    .save(new Patrimoine(patrimoine.getTitre(), patrimoine.getDescription(), patrimoine.getLieu(),patrimoine.getType(),patrimoine.getPrix(),LocalDate.now(),patrimoine.getImage_name()));
            return new ResponseEntity<>(patrimoine1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/upload")
    public ResponseEntity<Patrimoine> uploadimage(@RequestParam("file") MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        List<Patrimoine> tutorialData = patrimoineRepository.findAll();
        for (Patrimoine element : tutorialData){

            if (fileName.equals(element.getImage_name())){
                    System.out.println("existtt");
                try {
                Patrimoine user = element;
                user.setData(file.getBytes());
                     return new ResponseEntity<>(patrimoineRepository.save(user), HttpStatus.CREATED);
                 } catch (Exception e) {
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<Patrimoine> tutorialData = patrimoineRepository.findById(id);
        Patrimoine fileDB = tutorialData.get();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getImage_name() + "\"")
                .body(fileDB.getData());
    }

    @DeleteMapping("/deleteById/{id_Pat}")
    public void deleteById(@PathVariable(name = "id_Pat")Long id_Pat){
        patrimoineRepository.deleteById(id_Pat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patrimoine> updateTutorial(@PathVariable("id") long id, @RequestBody Patrimoine tutorial) {
        Optional<Patrimoine> tutorialData = patrimoineRepository.findById(id);

        if (tutorialData.isPresent()) {
            Patrimoine user = tutorialData.get();
            user.setTitre(tutorial.getTitre());
            user.setPrix(tutorial.getPrix());
            user.setDate(LocalDate.now());
            user.setDescription(tutorial.getLieu());
            user.setLieu(tutorial.getLieu());
            user.setImage_name(tutorial.getImage_name());
            return new ResponseEntity<>(patrimoineRepository.save(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/2/{id}")
    public ResponseEntity<Patrimoine> update2Tutorial(@PathVariable("id") long id, @RequestBody Patrimoine tutorial) {
        Optional<Patrimoine> tutorialData = patrimoineRepository.findById(id);

        if (tutorialData.isPresent()) {
            Patrimoine user = tutorialData.get();
            user.setTitre(tutorial.getTitre());
            user.setPrix(tutorial.getPrix());
            user.setDate(LocalDate.now());
            user.setDescription(tutorial.getDescription());
            user.setLieu(tutorial.getLieu());
            return new ResponseEntity<>(patrimoineRepository.save(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/demande")
    public ResponseEntity<Historique> Demande(@RequestBody Historique historique) {
        try {

            Historique historique1 = historiqueRepository
                    .save(new Historique(historique.getUsername(),LocalDate.now(), historique.getTitre(),"waiting"));
            return new ResponseEntity<>(historique1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/alldemandes")
    public List<Historique> allAccesss(){
        return historiqueRepository.findAll();
    }
    @PutMapping("/demande/{id}")
    public ResponseEntity<Historique> Demande(@PathVariable("id") long id,@RequestBody Historique historique) {
        try {
            Optional<Historique> historique2=historiqueRepository.findById(id);
            Historique historique1 = historique2.get();
            historique1.setStatus(historique.getStatus());
            historiqueRepository.save(historique1);
            return new ResponseEntity<>(historique1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
