package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Details;
import com.bezkoder.springjwt.models.Patrimoine;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.DetailsRepository;
import com.bezkoder.springjwt.repository.PatrimoineRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public PatrimoineRepository offreRepository ;
    @Autowired
    public DetailsRepository detailsRepository;
    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/all")
    public List<User> allAccess(){
        return userRepository.findAll();
    }


    @DeleteMapping("/deleteById/{id_user}")
    public void deleteById(@PathVariable(name = "id_user")Long id_user){
        userRepository.deleteById(id_user);

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateTutorial(@PathVariable("id") long id, @RequestBody User tutorial) {
        Optional<User> tutorialData = userRepository.findById(id);

        if (tutorialData.isPresent()) {
            User user = tutorialData.get();
            user.setUsername(tutorial.getUsername());
            user.setNcin(tutorial.getNcin());
            user.setTel(tutorial.getTel());
            user.setGouvernorat(tutorial.getGouvernorat());
            user.setEmail(tutorial.getEmail());
            user.setPassword(encoder.encode(tutorial.getPassword()));
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/MesOffres/{id_user}")
//    public Stream<Patrimoine> MesOffres(@PathVariable(name = "id_user")Long id_user){
//        List<Patrimoine> offre = offreRepository.findAll();
//        return offre.stream().filter(offre1 -> offre1.getId_entrepreneur()==id_user);
//    }

//
//    @PostMapping("/date")
//    public void test(){
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
//        LocalDate localDate = LocalDate.now();
//        System.out.println(dtf.format(localDate));
//    }
    @GetMapping("/{id_user}")
    public Optional<User> allAccess(@PathVariable(name = "id_user")Long id_user){
        return userRepository.findById(id_user);
    }

//    @PostMapping(value="/AddMesCandidatures/{id_user}/{id_offre}",produces = "application/json")
//    public User updateUser(@PathVariable(name = "id_user")Long id_user,@PathVariable(name = "id_offre")Long id_offre){
//        Optional<User> user = userRepository.findById(id_user);
//        Optional<Patrimoine> offre = offreRepository.findById(id_offre);
//        List<Patrimoine> Mescandidatures=user.get().getMesCandidature();
//        Mescandidatures.add(offre.get());
//        user.get().setMesCandidatures(Mescandidatures);
//        userRepository.save(user.get());
//        return user.get();
//    }
    @PostMapping("/userDetails")
    public void userDetails(@RequestBody Details details) {
        Stream<Details> r= detailsRepository.findById_Candidat(details.getId_Candidat());
        r.forEach(details1 -> detailsRepository.deleteById(details1.getId()));
//                    detailsRepository.deleteById;

         detailsRepository.save(details);
    }
    @PutMapping("/userDetails")
    public void userDetailss(@PathVariable(name = "id_user")Long id_user,@RequestBody Details details) {
        /*Optional<Details> details1 = detailsRepository.findById_Candidat(id_user);

        if (details1==null) {
            Details details2 = new Details();

            details2.setGithub(details.getGithub());
            details2.setLettreMotivation(details.getLettreMotivation());
            details2.setLinkedIn(details.getLinkedIn());
            details2.setSiteInternet(details.getSiteInternet());
            details2.setTitre(details.getTitre());
            detailsRepository.save(details2);
        }else{
//            detailsRepository.deleteById(details1.);
        }*/
    }
    @GetMapping("/userDetails/{id_user}")
    public Stream<Details> userDetails(@PathVariable(name = "id_user")Long id_user) {

        Stream<Details> details = detailsRepository.findById_Candidat(id_user);

        return details;
    }
    @GetMapping("/userDetails")
    public List<Details> userDetails() {

        List<Details> details = detailsRepository.findAll();

        return details;
    }
}

