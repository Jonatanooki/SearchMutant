package com.mutant.search_mutants.Repository;

import com.mutant.search_mutants.Entity.Mutant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantRepository extends JpaRepository<Mutant, Long> {
    @Query(value = "SELECT COUNT(*) AS HumanDna FROM db_mutants.tbl_mutants WHERE is_mutant =0;",nativeQuery = true)
    Long selectTotalsHumans();

    @Query(value = "SELECT COUNT(*) AS MutantDna FROM db_mutants.tbl_mutants WHERE is_mutant =1;",nativeQuery = true)
    Long selectTotalsMutants();
}
