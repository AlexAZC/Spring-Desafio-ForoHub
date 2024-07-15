package com.desafioForoHub.Alura.topico;

public record TopicoResponseOTD(
        Long id,
        String mensaje,
        String nombreCurso,
        String titulo
) {


    /* RECORD CONSTRUCTOR */
    public TopicoResponseOTD(Topico topico){
        this(topico.getId(), topico.getMensaje(), topico.getNombreCurso(), topico.getTitulo());
    }

}
