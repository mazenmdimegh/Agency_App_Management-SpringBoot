package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Historique;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.JpaRepository;

@CrossOrigin("*")
@Repository
public interface HistoriqueRepository extends JpaRepository<Historique,Long> {
}
