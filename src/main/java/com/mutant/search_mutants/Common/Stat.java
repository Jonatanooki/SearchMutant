package com.mutant.search_mutants.Common;

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

    private Long  CountMutantDna;
    private Long CountHumanDna;
    private float Ratio;

}
