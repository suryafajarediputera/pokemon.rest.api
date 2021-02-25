package com.pokemon.rest.api.repository;

import java.util.Optional;

import com.pokemon.rest.api.model.Pokemon;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Optional<Pokemon> findByType(String type);
    Page<Pokemon> findAllByNameLikeOrTypeLike(String name, String type, Pageable pageable);

}
