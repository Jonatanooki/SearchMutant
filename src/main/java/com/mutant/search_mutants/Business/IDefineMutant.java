package com.mutant.search_mutants.Business;

import com.mutant.search_mutants.Common.Stat;

import java.util.List;

public interface IDefineMutant {
    public boolean dnaStructureValidator(List<String> dna);

    public Stat getStats();
}
