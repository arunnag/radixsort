package com.example.radixsorting.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A wrapper class for a list of integers. This class provides a container for an ArrayList of
 * Integer objects. It includes annotations for generating boilerplate code such as getters,
 * setters, constructors, and more.
 *
 * <p>Annotations used: - @Data: Generates getters, setters, toString, equals, and hashCode methods.
 * - @AllArgsConstructor: Generates a constructor with one parameter for each field.
 * - @NoArgsConstructor: Generates a no-argument constructor. - @Getter: Generates getter methods
 * for all fields. - @Setter: Generates setter methods for all fields. - @NotNull: Ensures that the
 * integers list cannot be null.
 *
 * <p>Fields: - integers: An ArrayList of Integer objects that cannot be null.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IntegerListWrapper {

  /** A list of integers that this wrapper class holds. */
  @NotNull(message = "The integers list cannot be null")
  @NotEmpty(message = "The integers list cannot be empty")
  private ArrayList<Integer> integers;
}
