package com.bezkoder.springjwt.controllers;

//import com.bezkoder.springjwt.models.Favoris;
import com.bezkoder.springjwt.models.Patrimoine;
import com.bezkoder.springjwt.models.User;
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
            user.setDescription(tutorial.getLieu());
            user.setLieu(tutorial.getLieu());
            return new ResponseEntity<>(patrimoineRepository.save(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/addOffres/{id_user}")
//    public void addNewOffres(@RequestBody Patrimoine offre ,@PathVariable(name = "id_user")Long id_user){
//        Optional<User> user = userRepository.findById(id_user);
//        offre.setId_entrepreneur(user.get().getId());
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
//        LocalDate localDate = LocalDate.now();
//        System.out.println(dtf.format(localDate));
//        offre.setDate(localDate);
//        offreRepository.save(offre);
//        System.out.println(offre);
//    }
    @PostMapping("/addd")
    public void addd(@PathVariable(name = "id_offre")Long id_offre,@PathVariable(name = "id_user")Long id_user) {
        Optional<Patrimoine> offre = patrimoineRepository.findById(id_offre);
        Optional<User> user = userRepository.findById(id_user);
        List<User> candidat = offre.get().getCandidats();
        candidat.add(user.get());
        offre.get().setCandidats(candidat);

        /*List<Long> Mescandidature = user.get().getMesCandidature();
        Mescandidature.add(offre.get());
        user.get().setMesCandidature(Mescandidature);
        userRepository.save(user.get());*/
        patrimoineRepository.save(offre.get());
        System.out.println("done");

    }
    @GetMapping("/OffreById/{id_offre}")
    public Patrimoine OffreById(@PathVariable(name = "id_offre")Long id_offre) {
        Optional<Patrimoine> offre = patrimoineRepository.findById(id_offre);
        return offre.get();
    }
//    @GetMapping("/OffresById_entrepreneur/{id_offre}")
//    public Stream<Patrimoine> OffresById_entrepreneur(@PathVariable(name = "id_offre")Long id_offre) {
//        Stream<Patrimoine> offre = patrimoineRepository.findById_entrepreneur(id_offre);
//        return offre;
//    }

//    @PostMapping("/OffreFavorisByArray")
//    public Stream<Offre> OffreFavorisByArray(@RequestBody Favoris favoris) {
//
//        Stream<Offre> offres = offreRepository.findAll()
//                .stream()
//                .filter(offre -> favoris.getids().contains(offre.getId()));
//       return offres;
//    }

//    @GetMapping("/Candidats/{id_offre}")
//    public List<User> Candidats(@PathVariable(name = "id_offre")Long id_offre) {
//        List<User> users = userRepository.findAll();
//        List<User> u = userRepository.findAll();
//        u.clear();
//        for (User element : users){
//            for (Patrimoine el : element.getMesCandidature()) {
//                if (el.getId()==id_offre){
//                    System.out.println("existtt");
//                    u.add(element);
//                }
//            }
//        }
//
//        return u;
//    }





}
