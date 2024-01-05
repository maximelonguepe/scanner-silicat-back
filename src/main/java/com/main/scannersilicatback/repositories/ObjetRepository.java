package com.main.scannersilicatback.repositories;

import com.main.scannersilicatback.entities.Objet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ObjetRepository extends JpaRepository<Objet,Integer> {

    List<Objet> findByType(String type);
    List<Objet> findByTypeAndId(String type,Integer id);
}
