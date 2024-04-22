package com.arq.registraduria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

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
}
