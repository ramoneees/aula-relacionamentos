package com.example.relationships.demo.controller;

import com.example.relationships.demo.dao.CategoriaDAO;
import com.example.relationships.demo.dao.FotoDAO;
import com.example.relationships.demo.dao.LivroDAO;
import com.example.relationships.demo.repository.CategoriaRepository;
import com.example.relationships.demo.repository.FotoRepository;
import com.example.relationships.demo.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private FotoRepository fotoRepository;


    @Autowired
    private CategoriaRepository categoriaRepository;


    @PostMapping("/livro")
    public ResponseEntity<LivroDAO> salvarLivro(@RequestBody LivroDAO livro){
        LivroDAO livroSalvo = livroRepository.save(livro);
        return new ResponseEntity<LivroDAO>(livroSalvo, HttpStatus.CREATED);
    }

    @PostMapping("/foto")
    public ResponseEntity<FotoDAO> salvarFoto(@RequestBody FotoDAO foto){
        FotoDAO fotoSalva = fotoRepository.save(foto);
        return new ResponseEntity<FotoDAO>(fotoSalva, HttpStatus.CREATED);
    }

    @PostMapping("/categoria")
    public ResponseEntity<CategoriaDAO> salvarCategoria(@RequestBody CategoriaDAO categoria){
        Optional<CategoriaDAO> pai = categoria.getPai() == null ?
                                        Optional.empty() : categoriaRepository.findById(categoria.getPai().getId());
        if(pai.isPresent())
            categoria.setPai(pai.get());

        CategoriaDAO fotoSalva = categoriaRepository.save(categoria);
        return new ResponseEntity<CategoriaDAO>(fotoSalva, HttpStatus.CREATED);
    }

    @GetMapping("/categoria")
    public ResponseEntity<Collection<CategoriaDAO>> retornaCategorias(){
        return new ResponseEntity<Collection<CategoriaDAO>>(categoriaRepository.findAll(),HttpStatus.OK);
    }
    @GetMapping("/livro")

    public ResponseEntity<Collection<LivroDAO>> retornaLivros(){
        return new ResponseEntity<Collection<LivroDAO>>(livroRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("/foto")
    public ResponseEntity<Collection<FotoDAO>> retornaFotos(){
        return new ResponseEntity<Collection<FotoDAO>>(fotoRepository.findAll(),HttpStatus.OK);
    }
}
