package com.desafioForoHub.Alura.controller;

import com.desafioForoHub.Alura.repositories.TopicoRepository;
import com.desafioForoHub.Alura.topico.Topico;
import com.desafioForoHub.Alura.topico.TopicoActualizado;
import com.desafioForoHub.Alura.topico.TopicoDTO;
import com.desafioForoHub.Alura.topico.TopicoResponseOTD;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;


    @PostMapping
    public ResponseEntity<TopicoResponseOTD> registrarTopico(@RequestBody @Valid TopicoDTO topico){
        //return ResponseEntity.created(topicoRepository.save(topico));
        //return new ResponseEntity<Topico>(topicoRepository.save(topico), HttpStatus.CREATED);
        Topico topicoGuardado = new Topico(topico);
        topicoRepository.save(topicoGuardado);

        TopicoResponseOTD topicoResponseOTD = new TopicoResponseOTD(topicoGuardado.getId(),
                topicoGuardado.getMensaje(),topicoGuardado.getNombreCurso(),topicoGuardado.getTitulo());

        return new ResponseEntity<TopicoResponseOTD>(topicoResponseOTD,HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<Page<TopicoResponseOTD>> getTopicos(Pageable paginas){
        var topicos = topicoRepository.findByActivoTrue(paginas).map(TopicoResponseOTD::new);
        return ResponseEntity.ok(topicos);
    }


    @GetMapping("{id}")
    public Topico getTopicoById(@PathVariable Long id){
         var identicatedTopico = topicoRepository.findById(id);

         if(identicatedTopico.isPresent()){
             return identicatedTopico.get();
         } else {
             throw new EntityNotFoundException();
         }

    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void eliminarTopico(@PathVariable Long id){
        var identicatedTopico = topicoRepository.findById(id);

        if(identicatedTopico.isPresent()){
            Topico topicoExiste = identicatedTopico.get();
            topicoExiste.desactivarTopico();
        } else {
            throw new EntityNotFoundException();
        }
    }


    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid TopicoActualizado topicoActualizado){
        var identicatedTopico = topicoRepository.findById(topicoActualizado.id());

        if(identicatedTopico.isPresent()){
            Topico topicoExiste = identicatedTopico.get();
            topicoExiste.actualizarDatos(topicoActualizado);

            TopicoResponseOTD topicoResponseOTD = new TopicoResponseOTD(topicoExiste.getId(),
                    topicoExiste.getMensaje(),topicoExiste.getNombreCurso(),topicoExiste.getTitulo());

            return ResponseEntity.ok(topicoResponseOTD);

        } else {
            throw new EntityNotFoundException();
        }
    }




}
