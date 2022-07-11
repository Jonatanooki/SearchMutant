package com.mutant.search_mutants.Common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Stat {
    @JsonProperty("count_mutant_dna")
    private Long countMutantDna;
    @JsonProperty("count_human_dna")
    private Long countHumanDna;
    @JsonProperty("ratio")
    private float ratio;

    public Stat(Long countMutantDna, Long countHumanDna) {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;

        if (countHumanDna == 0 && countMutantDna == 0) {
            this.ratio =0;
        } else if (countHumanDna == 0 && countMutantDna > 0) {
            this.ratio =1;
        } else {
            this.ratio = ((((float) countMutantDna * (float) countHumanDna) / (float) countHumanDna) / 10);
        }
    }
}
