package com.br.clinica.paciente.repository;

import com.br.clinica.consulta.Consulta;
import com.br.clinica.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,String> {
    void deleteByCpf(String id);

}
