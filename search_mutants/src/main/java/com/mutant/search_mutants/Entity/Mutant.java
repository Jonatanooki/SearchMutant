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
    private static final long serialVersionUID = -4214521834599952692L;

    public Mutant() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMutant;

    @Column(length = 8000)
    private String DNA;

    @Column
    private Boolean IsMutant;

    public Boolean getMutant() {
        return IsMutant;
    }

    public void setMutant(Boolean mutant) {
        IsMutant = mutant;
    }

    public Long getIdMutant() {
        return idMutant;
    }

    public void setIdMutant(long idMutant) {
        this.idMutant = idMutant;
    }

    public String getDNA() {
        return DNA;
    }

    public void setADN(String dna) {
        DNA = dna;
    }


}
