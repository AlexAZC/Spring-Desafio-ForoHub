package com.desafioForoHub.Alura.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @Column(name = "nombrecurso")
    private String nombreCurso;
    private String titulo;
    @Column(insertable = false)
    private Boolean activo;

    /* CONSTRUCTOR */
    public Topico(TopicoDTO json){
        this.mensaje = json.mensaje();
        this.nombreCurso = json.nombreCurso();
        this.titulo = json.titulo();
        this.activo = true;
    }

    /* METODO DELETE */
    public void desactivarTopico(){
        this.activo = false;
    }

    /* METODO PUT */
    public void actualizarDatos(TopicoActualizado topicoActualizado) {
        if(topicoActualizado.mensaje() != null){
            this.mensaje = topicoActualizado.mensaje();
        }
        if(topicoActualizado.nombreCurso() != null){
            this.nombreCurso = topicoActualizado.nombreCurso();
        }
        if(topicoActualizado.titulo() != null){
            this.titulo = topicoActualizado.titulo();
        }

    }



}
