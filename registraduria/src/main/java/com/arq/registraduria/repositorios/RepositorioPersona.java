package com.arq.registraduria.repositorios;

import com.arq.registraduria.dto.ListadoDto;
import com.arq.registraduria.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableJpaRepositories
public interface RepositorioPersona extends JpaRepository<Persona,Long> , JpaSpecificationExecutor<Persona> {

    @Query("SELECT u FROM Personas u WHERE u.numero_documento =?1")
    Persona findPersonaByNumero_documento(long numero_documento);

}
