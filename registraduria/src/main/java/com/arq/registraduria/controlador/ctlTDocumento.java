package com.arq.registraduria.controlador;

import com.arq.registraduria.entidades.TipoDocumento;
import com.arq.registraduria.servicios.servicioTDocumento;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ctlTDocumento {

    servicioTDocumento tDocumento = new servicioTDocumento();

    public List<TipoDocumento> GetDocumentos(){
        return tDocumento.getAllDocumentos();
    }

}
