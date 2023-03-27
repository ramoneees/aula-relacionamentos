package com.example.relationships.demo.dao;


import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity(name = "categoria")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    public CategoriaDAO pai;

    @OneToMany(mappedBy = "pai")
    public List<CategoriaDAO> filhos;

    @ManyToMany(mappedBy = "categorias")
    private Collection<LivroDAO> livros;
}
