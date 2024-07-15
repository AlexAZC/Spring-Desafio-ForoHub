package com.desafioForoHub.Alura.topico;

import jakarta.validation.constraints.NotNull;

public record TopicoActualizado(
        @NotNull
        Long id,
        String mensaje,
        String nombreCurso,
        String titulo
) {
}
