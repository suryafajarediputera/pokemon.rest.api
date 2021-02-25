package com.pokemon.rest.api.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.pokemon.rest.api.model.Pokemon;
import com.pokemon.rest.api.model.request.PokemonCreationRequest;
import com.pokemon.rest.api.model.response.ResultModel;
import com.pokemon.rest.api.repository.PokemonRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PokemonService {
    private final PokemonRepository pokemonRepository;

    public Pokemon get(long id) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);
        if (pokemon.isPresent()) {
            return pokemon.get();
        }
        throw new EntityNotFoundException("Cant find pokemon by ID");
    }

    public List<Pokemon> getAll() {
        return pokemonRepository.findAll();
    }

    public ResultModel getAll(Pageable pageable) {
        Page<Pokemon> pokemon = pokemonRepository.findAll(pageable);
        return ResultModel.builder().numberOfItems(pokemon.getTotalElements()).numberOfPages(pokemon.getTotalPages())
                .data(pokemon.getContent()).build();
    }

    public ResultModel getPokemonsWithSorting(Pageable pageable) {
        Page<Pokemon> pokemon = pokemonRepository.findAll(pageable);
        return ResultModel.builder().numberOfItems(pokemon.getTotalElements()).numberOfPages(pokemon.getTotalPages())
                .data(pokemon.getContent()).build();
    }

    public ResultModel filterPokemons(String query, Pageable pageable) {
        query = "%" + query + "%";
        Page<Pokemon> pokemon = pokemonRepository.findAllByNameLikeOrTypeLike(query, query, pageable);
        return ResultModel.builder().numberOfItems(pokemon.getTotalElements()).numberOfPages(pokemon.getTotalPages())
                .data(pokemon.getContent()).build();
    }

    public Pokemon createPokemon(PokemonCreationRequest Pokemon) {
        Pokemon _new = new Pokemon();
        BeanUtils.copyProperties(Pokemon, _new);
        return pokemonRepository.save(_new);
    }

    public Pokemon updatePokemon(Long id, PokemonCreationRequest request) {

        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);
        if (!optionalPokemon.isPresent()) {
            throw new EntityNotFoundException("Pokemon Not Found");
        }
        Pokemon pokemon = optionalPokemon.get();
        pokemon.setName(request.getName());
        pokemon.setType(request.getType());
        return pokemonRepository.save(pokemon);
    }

    public void deletePokemon(Long id) {
        pokemonRepository.deleteById(id);
    }
}
