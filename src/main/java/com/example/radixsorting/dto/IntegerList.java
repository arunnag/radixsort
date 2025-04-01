package com.example.radixsorting.dto;

import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** DTO class for list of floats */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IntegerList {
  /** List of floats */
  @NotNull private ArrayList<Integer> integers;
}
