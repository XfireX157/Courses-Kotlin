package br.com.alura.restFull.controller

import br.com.alura.restFull.service.TopicoService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/relatorio")
class RelatorioController(
    private val topicoService: TopicoService
) {
    @GetMapping
    fun relatorio(model: Model): String {
        model.addAttribute("topicosPorCategorias", topicoService.relatorio())
        return "relatorio"
    }
}