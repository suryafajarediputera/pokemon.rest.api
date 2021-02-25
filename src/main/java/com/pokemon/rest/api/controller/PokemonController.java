package com.pokemon.rest.api.controller;

import com.pokemon.rest.api.model.Pokemon;
import com.pokemon.rest.api.model.request.PokemonCreationRequest;
import com.pokemon.rest.api.service.PokemonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * PokemonController
 */

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PokemonController {

    private final PokemonService service;
    final Logger log = LoggerFactory.getLogger(getClass());

    @Transactional
    @GetMapping(value = "/pokemon", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getPokemon(@RequestParam(required = false) Long id) {
        if (id == 0) {
            return ResponseEntity.ok(service.getAll());
        }
        return ResponseEntity.ok(service.get(id));
    }

    @Transactional
    @GetMapping(value = "/pokemon/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @Transactional
    @GetMapping(value = "/pokemon/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getPokemons(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @Transactional
    @GetMapping(value = "/pokemon/search/sorting", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getPokemonsWithSorting(Pageable pageable) {
        return ResponseEntity.ok(service.getPokemonsWithSorting(pageable));
    }

    @Transactional
    @GetMapping(value = "/pokemon/search/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getPokemonsWithFilter(@RequestParam("query") String query, Pageable pageable) {
        return ResponseEntity.ok(service.filterPokemons(query, pageable));
    }

    @Transactional
    @PostMapping(value = "/pokemon", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pokemon> createPokemon(@RequestBody PokemonCreationRequest request) {
        return ResponseEntity.ok(service.createPokemon(request));
    }

    @Transactional
    @PatchMapping(value = "/pokemon/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable("id") Long id,
            @RequestBody PokemonCreationRequest request) {
        return ResponseEntity.ok(service.updatePokemon(id, request));
    }

    @Transactional
    @DeleteMapping(value = "/pokemon/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
        service.deletePokemon(id);
        return ResponseEntity.ok().build();
    }
}