package com.pokemon.rest.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@ApiModel(description = "Class representing a pokemon in the application.")
@Getter
@Setter
@Entity
@Table(name = "pokemon")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NoArgsConstructor
public class Pokemon {

    @ApiModelProperty(notes = "Unique identifier of the Pokemon.", example = "1", required = true, position = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "Name of the pokemon.", example = "Pikachu", required = true, position = 1)
    @NonNull
    private String name;
    
    @ApiModelProperty(notes = "Type of the pokemon.", example = "Electric", required = true, position = 3)
    @NonNull
    private String type;
}
