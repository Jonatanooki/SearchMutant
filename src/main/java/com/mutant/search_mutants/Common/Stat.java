package com.mutant.search_mutants.Common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Stat {

    public Long getCountMutantDna() {
        return CountMutantDna;
    }

    public void setCountMutantDna(Long countMutantDna) {
        CountMutantDna = countMutantDna;
    }

    public Long getCountHumanDna() {
        return CountHumanDna;
    }

    public void setCountHumanDna(Long countHumanDna) {
        CountHumanDna = countHumanDna;
    }

    public float getRatio() {
        return Ratio;
    }

    public void setRatio(float ratio) {
        Ratio = ratio;
    }
    @JsonProperty("count_mutant_dna")
    private Long  CountMutantDna;
    @JsonProperty("count_human_dna")
    private Long CountHumanDna;
    @JsonProperty("ratio")
    private float Ratio;

}
