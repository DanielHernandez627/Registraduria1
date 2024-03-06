package com.arq.registraduria.repositorios;

import com.arq.registraduria.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableJpaRepositories
public interface RepositorioPersona extends JpaRepository<Persona,Long> , JpaSpecificationExecutor<Persona> {


   /* void SavePersona(Persona persona);

    Persona getPersonaByDocumento(long numero_documento);

    List<Persona> getAllPersona();*/

}
