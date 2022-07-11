package com.mutant.search_mutants.Business;

import com.mutant.search_mutants.Common.Stat;
import com.mutant.search_mutants.Entity.Mutant;
import com.mutant.search_mutants.Repository.MutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

        validation = verifHorizontal(input, input.length, input[0].length());
        if (!validation)
            validation = verifVertical(input, input.length, input[0].length());
        if (!validation)
            validation = validatorDiagonal(input, 0, 0, input.length, input[0].length());
        if (!validation)
            validation = validatorDiagonalInverse(input, input.length, input[0].length());


        Mutant mutant = new Mutant();
        mutant.setADN(dna.toString());
        mutant.setMutant(validation);

        repository.save(mutant);
        return validation;

    }

    private boolean verifHorizontal(String[] entrada, int sizex, int sizey) {
        char verif = ' ';
        int conteo = 1;
        int parada = 0;
        for (int x = 0; x < sizex; x++) {
            for (int y = 0; y < sizey; y++) {
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

    private boolean verifVertical(String[] entrada, int sizex, int sizey) {
        char verif = ' ';
        int conteo = 1;
        int parada = 0;
        for (int x = 0; x < sizex; x++) {
            for (int y = 0; y < sizey; y++) {
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

    private boolean validatorDiagonal(String[] m, int i, int j, int sizex, int sizey) {
        if (i >= sizex || j >= sizey) {
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

        if (validatorDiagonal(m, i + 1, j + 1, sizex, sizey)) {
            return true;
        }

        if (validatorDiagonal(m, k1, k2, sizex, sizey)) {
            return true;
        }
        return false;
    }

    private boolean validatorDiagonalInverse(String[] m, int sizex, int sizey) {
        String[] n = new String[sizex];
        boolean flag;

        for (int j = 0; j < sizey; j++) {
            char aux = ' ';
            for (int i = sizex - 1; i >= 0; i--) {
                aux = m[j].charAt(i);
                //System.out.print(aux);
                n[j] += aux;
            }
            //System.out.println();
        }
        flag = validatorDiagonal(n, 0, 0, sizex, sizey);
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
            stat.setRatio((((float) stat.getCountMutantDna() * (float) stat.getCountHumanDna()) / (float) stat.getCountHumanDna()) / 10);
        }

        return stat;
    }
}
