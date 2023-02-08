package com.br.clinica.endereco.repository;

import com.br.clinica.endereco.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    void deleteById(Long id);

    @org.springframework.data.jpa.repository.EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"paciente"})
    @Override
    Page<Endereco> findAll(Pageable pageable);
}

