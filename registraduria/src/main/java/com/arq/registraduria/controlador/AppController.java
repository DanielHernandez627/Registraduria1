package com.arq.registraduria.controlador;

import com.arq.registraduria.dto.ListadoDto;
import com.arq.registraduria.entidades.Persona;
import com.arq.registraduria.entidades.TipoDocumento;
import com.arq.registraduria.servicios.servicioPersona;
import com.arq.registraduria.servicios.servicioTDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    servicioPersona servicePersona;
    @Autowired
    servicioTDocumento serviciotDocumento;

    @GetMapping
    public String iniciar(Model model){
        return "index";
    }

    @GetMapping("/crear_personas")
    public String ui_crear_personas(Model model){
        return "crear_personas";
    }

    @GetMapping("/actualizar_persona")
    public String ui_actualizar_personas(Model model){
        return "actualizar_persona";
    }

    @GetMapping({"/listar_personas"})
    public String ui_listar_personas(Model model){
        List<ListadoDto> listadoDtoList = new ArrayList<>();
        List<Persona> personaList = servicePersona.getAllPersona();

        for (Persona persona : personaList) {
            ListadoDto listadoDto = new ListadoDto();

            listadoDto.setNumero_documento(persona.getNumero_documento());
            listadoDto.setPrimerNombre(persona.getPrimerNombre());
            listadoDto.setSegundoNombre(persona.getSegundoNombre());
            listadoDto.setPrimerApellido(persona.getPrimerApellido());
            listadoDto.setSegundoApellido(persona.getSegundoApellido());
            listadoDto.setFecha_Nacimiento(persona.getFecha_Nacimiento());
            listadoDto.setNombre_tipo_documento(getNombreTipoDocumento(persona.getIdTipoDocumento()));

            listadoDtoList.add(listadoDto);
        }


        model.addAttribute("personas",listadoDtoList);
        return "listar_personas";
    }

    public String getNombreTipoDocumento(long id_tipo_documento){
        List<TipoDocumento> tipoDocumentoList = serviciotDocumento.getAllDocumentos();
        String nombre_tipo_documento = "";

        for (TipoDocumento tipoDocumento : tipoDocumentoList){
            if(tipoDocumento.getId() == id_tipo_documento){
                nombre_tipo_documento = tipoDocumento.getNombre();
                break;
            }
        }

        return nombre_tipo_documento;
    }

}
