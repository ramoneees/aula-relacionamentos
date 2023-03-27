package com.example.relationships.demo.repository;

import com.example.relationships.demo.dao.FotoDAO;
import com.example.relationships.demo.dao.LivroDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoRepository extends JpaRepository<FotoDAO,Long> {
}
