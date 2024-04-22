package com.arq.registraduria.controlador;

import com.arq.registraduria.entidades.Persona;
import com.arq.registraduria.servicios.servicioPersona;
import com.arq.registraduria.utilidades.Utilidades;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Controller
public class ctlPersona {

    @Autowired
    servicioPersona spersona = new servicioPersona();

    Utilidades utilidades = new Utilidades();

    @PostMapping("/insertPersona")
    public String savePersona(@ModelAttribute("persona")Persona persona){
        String redirectFailed = "redirect:/insertUser?error";
        persona.setIdTipoDocumento(1);
        persona.setNumero_documento(utilidades.generarNumeroAleatorio());
        if (!spersona.SavePersona(persona)){
            return redirectFailed;
        }

        return "index";
    }

    @GetMapping("/buscar/{numero_documento}")
    public String getPersonaByDocumento(@PathVariable String numero_documento, Model model){
        System.out.println(numero_documento);
        long numero = Long.parseLong(numero_documento);
        model.addAttribute("persona",spersona.getPersonaByDocumento(numero));
        return "redirect:/actualizar_persona";
    }

    /*
    public List<Persona> getAllPersonas(){
        return spersona.getAllPersona();
    }*/

}
