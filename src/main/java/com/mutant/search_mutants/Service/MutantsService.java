package com.mutant.search_mutants.Service;

import com.mutant.search_mutants.Business.IDefineMutant;
import com.mutant.search_mutants.Common.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class MutantsService implements IMutantService {
    @Autowired
    private IDefineMutant defineMutant;

    public Boolean ValidateMutant(List<String> dna) {
        return defineMutant.dnaStructureValidator(dna);
    }

    public Stat getStats() {
        return defineMutant.getStats();
    }

}
