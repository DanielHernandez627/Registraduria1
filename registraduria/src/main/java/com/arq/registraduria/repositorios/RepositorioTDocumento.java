package com.arq.registraduria.repositorios;

import com.arq.registraduria.entidades.Persona;
import com.arq.registraduria.entidades.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface RepositorioTDocumento extends JpaRepository<TipoDocumento,Long>, JpaSpecificationExecutor<TipoDocumento> {
    //List<TipoDocumento> getAllDocumentos();
}
