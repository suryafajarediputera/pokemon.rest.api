package com.pokemon.rest.api.model.request;


import lombok.Data;

@Data
public class PokemonCreationRequest {
    private String name;
    private String type;    
}
