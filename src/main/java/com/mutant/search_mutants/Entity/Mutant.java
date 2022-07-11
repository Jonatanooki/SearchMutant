package com.mutant.search_mutants.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "tbl_mutants")
public class Mutant {
    public Mutant() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMutant;

    @Column(length = 8000)
    private String DNA;

    @Column
    private Boolean IsMutant;

    public Mutant(String DNA, Boolean isMutant) {
        this.DNA = DNA;
        IsMutant = isMutant;
    }

    public Boolean getMutant() {
        return IsMutant;
    }

    public void setMutant(Boolean mutant) {
        IsMutant = mutant;
    }

}
