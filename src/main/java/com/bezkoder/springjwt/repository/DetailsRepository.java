package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;
import java.util.stream.Stream;
@CrossOrigin("*")

@Repository
public interface DetailsRepository  extends JpaRepository<Details,Long> {
    default Stream<Details> findById_Candidat(Long id) {
    return findAll().stream().filter(offre -> offre.getId_Candidat()==id);
}



//    void deleteById_Candidat(Details details);
//    Optional<Details> findById_Candidat(Long aLong);:
}
