package com.mutant.search_mutants.Service;

import com.mutant.search_mutants.Common.Stat;

import java.util.List;

public interface IMutantService {
    public Boolean ValidateMutant(List<String>  dna);

    public Stat getStats();

}
