package com.br.clinica.consulta.repository;

import com.br.clinica.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {


    void deleteById(Long id);

}
