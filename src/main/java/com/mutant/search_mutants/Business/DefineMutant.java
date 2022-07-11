package com.mutant.search_mutants.Business;

import com.mutant.search_mutants.Common.Stat;
import com.mutant.search_mutants.Entity.Mutant;
import com.mutant.search_mutants.Repository.MutantRepository;
import com.mutant.search_mutants.Utils.IMutantValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefineMutant implements IDefineMutant {
    @Autowired
    private MutantRepository repository;

    @Autowired
    private IMutantValidation mutantValidation;

    public boolean dnaStructureValidator(List<String> dna) {

        String[] dnaStructure = dna.toArray(new String[0]);

        boolean validation = mutantValidation.isValidation(dnaStructure);

        saveRepository(dna, validation);

        return validation;
    }

    public Stat getStats() {
        return new Stat(repository.selectTotalsMutants(), repository.selectTotalsHumans());
    }

    private void saveRepository(List<String> dna, boolean validation) {
        Mutant mutant = new Mutant(dna.toString(), validation);
        repository.save(mutant);
    }
}
