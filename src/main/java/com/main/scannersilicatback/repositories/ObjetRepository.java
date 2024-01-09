package com.main.scannersilicatback.repositories;

import com.main.scannersilicatback.entities.Objet;
import com.main.scannersilicatback.entities.ObjetType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ObjetRepository extends JpaRepository<Objet,Integer> {

    List<Objet> findByType(ObjetType type);
    List<Objet> findByTypeAndId(ObjetType type, Integer id);
}
