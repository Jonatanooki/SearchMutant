package com.mutant.search_mutants.Business;

import com.mutant.search_mutants.Common.Stat;
import com.mutant.search_mutants.Entity.Mutant;
import com.mutant.search_mutants.Repository.MutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Convert;
import java.util.List;

@Component
public class DefineMutant implements IDefineMutant {
    @Autowired
    private MutantRepository repository;
    static int k1 = 0, k2 = 0;
    static boolean flag = true;
    static char verifD = ' ';
    static int countD = 1;

    public boolean dnaStructureValidator(List<String> dna) {

        String[] input = dna.toArray(new String[dna.size()]);
        boolean validation = false;

        validation = verifHorizontal(input);
        if (!validation)
            validation = verifVertical(input);
        if (!validation)
            validation = validatorDiagonal(input, 0, 0);
        if (!validation)
            validation = validatorDiagonalInverse(input);


        Mutant mutant = new Mutant();
        mutant.setADN(dna.toString());
        mutant.setMutant(validation);

        repository.save(mutant);
        return validation;

    }

    private boolean verifHorizontal(String[] entrada) {
        char verif = ' ';
        int conteo = 1;
        int parada = 0;
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                char c;
                c = entrada[x].charAt(y);

                if (verif == c) {
                    conteo++;
                } else {
                    conteo = 1;
                    verif = c;
                }
                if (conteo == 4) {
                    parada = 1;
                    break;
                }
                //System.out.print(conteo);
            }
            if (parada == 1) {
                return true;
            }
            //System.out.println("");
        }
        return false;
    }

    private boolean verifVertical(String[] entrada) {
        char verif = ' ';
        int conteo = 1;
        int parada = 0;
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                char c;
                c = entrada[y].charAt(x);

                if (verif == c) {
                    conteo++;
                } else {
                    conteo = 1;
                    verif = c;
                }
                if (conteo == 4) {
                    parada = 1;
                    break;
                }
                //System.out.print(conteo);
            }
            if (parada == 1) {
                return true;
            }
            //System.out.println("");
        }
        return false;
    }

    private boolean validatorDiagonal(String[] m, int i, int j) {
        if (i >= 6 || j >= 6) {
            if (flag) {
                int a = k1;
                k1 = k2;
                k2 = a;
                flag = !flag;
                k1++;
            } else {
                int a = k1;
                k1 = k2;
                k2 = a;
                flag = !flag;
            }
            System.out.println();
            return false;
        }
        //System.out.print(m[i].charAt(j) + " ");
        char c;
        c = m[i].charAt(j);

        if (verifD == c) {
            countD++;
        } else {
            countD = 1;
            verifD = c;
        }
        if (countD == 4) {
            return true;
        }

        if (validatorDiagonal(m, i + 1, j + 1)) {
            return true;
        }

        if (validatorDiagonal(m, k1, k2)) {
            return true;
        }
        return false;
    }

    private boolean validatorDiagonalInverse(String[] m) {
        String[] n = {"", "", "", "", "", ""};
        boolean flag;

        for (int j = 0; j < 6; j++) {
            char aux = ' ';
            for (int i = 5; i >= 0; i--) {
                aux = m[j].charAt(i);
                //System.out.print(aux);
                n[j] += aux;
            }
            //System.out.println();
        }
        flag = validatorDiagonal(n, 0, 0);
        return flag;
    }

    public Stat getStats() {
        Stat stat = new Stat();
        stat.setCountMutantDna(repository.selectTotalsMutants());
        stat.setCountHumanDna(repository.selectTotalsHumans());

        if (stat.getCountHumanDna() == 0 && stat.getCountMutantDna() == 0) {
            stat.setRatio(0);
        } else if (stat.getCountHumanDna() == 0 && stat.getCountMutantDna() > 0) {
            stat.setRatio(1);
        } else {
            stat.setRatio( (float)stat.getCountMutantDna() / ((float) stat.getCountMutantDna() + (float) stat.getCountHumanDna()));
        }

        return stat;
    }
}
