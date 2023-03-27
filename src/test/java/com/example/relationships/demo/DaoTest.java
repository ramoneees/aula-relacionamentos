package com.example.relationships.demo;

import com.example.relationships.demo.dao.FotoDAO;
import com.example.relationships.demo.dao.LivroDAO;
import com.example.relationships.demo.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DaoTest {

    @Autowired
    private LivroRepository livroRepository;


    @Test
    public void persiste_livro(){
        livroRepository.save(LivroDAO
                .builder()
                .nome("Clean Code")
                        .foto(FotoDAO.builder().path("path/to/photo").build())
                .build());
        Optional<LivroDAO> result = livroRepository.findById(Long.valueOf("1"));
        assertEquals(true, result.isPresent());
        assertEquals(1, result.get().getId());
        assertEquals("Clean Code", result.get().getNome());
        assertEquals(1, result.get().getFoto().getId());
        assertEquals("path/to/photo", result.get().getFoto().getPath());

    }

}
