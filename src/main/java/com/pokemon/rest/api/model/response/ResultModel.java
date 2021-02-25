package com.pokemon.rest.api.model.response;

import com.pokemon.rest.api.model.Pokemon;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResultModel {
    private List<Pokemon> data;
    private Long numberOfItems;
    private int numberOfPages;
}
