package com.davbossotto.libreria.rest;

import com.davbossotto.libreria.entities.Libro;
import com.davbossotto.libreria.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/libri")
public class LibroRestController {

    private final LibroService libroService;
    @Autowired
    public LibroRestController(LibroService libroService){
        this.libroService = libroService;
    }

    @GetMapping("")
    public List<Libro> findAll(){
        return libroService.findAll();
    }

    @GetMapping("/{idLibro}")
    public Libro findById(@PathVariable int idLibro){
        return libroService.findById(idLibro);
    }

    //Filtra la tabella libra e restituisce tutti i libri senza data di eliminazione o con data di eliminazione
    // in base alla pathvariable

    @GetMapping("/eliminati")
    public List<Libro> findLibriEliminati(){
        return libroService.findLibriEliminati();
    }

    @GetMapping("/disponibili")
    public List<Libro> findLibriDisponibili(){
        return libroService.findLibriDisponibili();
    }

    @PostMapping("")
    public Libro addLibro(@RequestBody Libro libro){
        //Setto l'id a 0 per essere sicuro di evitare problemi con il valore
        //passato tramite il JSON
        libro.setId(0);
        return libroService.save(libro);
    }

    @PutMapping("")
    public Libro updateLibro(@RequestBody Libro libro){
        return libroService.save(libro);
    }


    @DeleteMapping("{idLibro}")
    public String deleteLibro(@PathVariable int idLibro){
        //Libro libro = libroService.findById(idLibro);
        //if(libro == null){
        //   throw new RuntimeException("Libro non trovato all'id: " + idLibro);
        //}

        return libroService.deleteByid(idLibro);

    }


}
