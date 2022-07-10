package com.mutant.search_mutants.Repository;

import com.mutant.search_mutants.Entity.Mutant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantRepository extends JpaRepository<Mutant,Long> {
}
