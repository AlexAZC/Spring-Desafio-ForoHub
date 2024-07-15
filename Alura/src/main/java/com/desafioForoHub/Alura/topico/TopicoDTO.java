package com.desafioForoHub.Alura.topico;

import jakarta.validation.constraints.NotBlank;

public record TopicoDTO(
        @NotBlank
        String mensaje,
        @NotBlank
        String nombreCurso,
        @NotBlank
        String titulo
) {
}
