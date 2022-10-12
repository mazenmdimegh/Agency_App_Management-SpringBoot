package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Patrimoine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.stream.Stream;


@CrossOrigin("*")
@Repository
public interface PatrimoineRepository extends JpaRepository<Patrimoine,Long> {
    default Stream<Patrimoine> findByImage_name(String imagename) {

        return findAll().stream().filter(offre -> offre.getImage_name()==imagename);
    }

}
