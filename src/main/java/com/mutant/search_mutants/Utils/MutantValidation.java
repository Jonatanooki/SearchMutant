package com.mutant.search_mutants.Utils;

import org.springframework.stereotype.Component;

@Component
public class MutantValidation implements IMutantValidation {

    private int k1 = 0, k2 = 0;
    private boolean flag = true;
    private char verifyDiagonal = ' ';
    private int countDiagonal = 1;

    public boolean isValidation(String[] input) {
        boolean validation;

        validation = VerifyHorizontalVertical(input, input.length, input[0].length(),true);

        if (!validation) {
            validation = VerifyHorizontalVertical(input, input.length, input[0].length(),false);
        }

        if (!validation) {
            validation = VerifyDiagonal(input, 0, 0, input.length, input[0].length());
        }

        if (!validation) {
            validation = VerifyDiagonalInverse(input, input.length, input[0].length());
        }
        return validation;
    }

    private boolean VerifyHorizontalVertical(String[] input, int sizex, int sizey, boolean typeVerify) {
        char verify = ' ';
        int count = 1;
        int stop = 0;
        for (int x = 0; x < sizex; x++) {
            verify= ' ';
            for (int y = 0; y < sizey; y++) {
                char c;
                if (typeVerify) {
                    c = input[x].charAt(y);
                } else {
                    c = input[y].charAt(x);
                }

                if (verify == c) {
                    count++;
                } else {
                    count = 1;
                    verify = c;
                }
                if (count == 4) {
                    stop = 1;
                    break;
                }
            }
            if (stop == 1) {
                return true;
            }
        }
        return false;
    }


    private boolean VerifyDiagonal(String[] input, int i, int j, int sizex, int sizey) {
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
            return false;
        }
        char c;

        c = input[i].charAt(j);

        if (verifyDiagonal == c) {
            countDiagonal++;
        } else {
            countDiagonal = 1;
            verifyDiagonal = c;
        }
        if (countDiagonal == 4) {
            return true;
        }

        if (VerifyDiagonal(input, i + 1, j + 1, sizex, sizey)) {
            return true;
        }

        if (VerifyDiagonal(input, k1, k2, sizex, sizey)) {
            return true;
        }
        return false;
    }

    private boolean VerifyDiagonalInverse(String[] input, int sizex, int sizey) {
        String[] n = new String[sizex];
        boolean flag;

        for (int j = 0; j < sizey; j++) {
            char aux = ' ';
            for (int i = sizex - 1; i >= 0; i--) {
                aux = input[j].charAt(i);
                n[j] += aux;
            }
        }
        flag = VerifyDiagonal(n, 0, 0, sizex, sizey);

        return flag;
    }
}
