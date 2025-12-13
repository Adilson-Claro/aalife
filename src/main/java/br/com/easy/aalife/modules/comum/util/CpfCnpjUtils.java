package br.com.easy.aalife.modules.comum.util;

import br.com.easy.aalife.config.exceptions.ValidationException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CpfCnpjUtils {

    public boolean isCpf(String cpf) {
        if (cpf == null) return false;

        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11 || cpf.chars().distinct().count() == 1) {
            return false;
        }

        return validarDigitosCpf(cpf);
    }

    public boolean isCnpj(String cnpj) {
        if (cnpj == null) return false;

        cnpj = cnpj.replaceAll("\\D", "");

        if (cnpj.length() != 14 || cnpj.chars().distinct().count() == 1) {
            return false;
        }

        return validarDigitosCnpj(cnpj);
    }

    public boolean isCpfOuCnpj(String valor) {
        if (valor == null) return false;

        valor = valor.replaceAll("\\D", "");
        return isCpf(valor) || isCnpj(valor);
    }

    private boolean validarDigitosCpf(String cpf) {
        int soma1 = 0, soma2 = 0;

        for (int i = 0; i < 9; i++) {
            int num = cpf.charAt(i) - '0';
            soma1 += num * (10 - i);
            soma2 += num * (11 - i);
        }

        int digito1 = calcularDigito(soma1);
        soma2 += digito1 * 2;
        int digito2 = calcularDigito(soma2);

        return cpf.charAt(9) - '0' == digito1 &&
                cpf.charAt(10) - '0' == digito2;
    }

    private int calcularDigito(int soma) {
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    private boolean validarDigitosCnpj(String cnpj) {
        int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int digito1 = calcularDigitoCnpj(cnpj.substring(0, 12), peso1);
        int digito2 = calcularDigitoCnpj(cnpj.substring(0, 12) + digito1, peso2);

        return cnpj.equals(cnpj.substring(0, 12) + digito1 + digito2);
    }

    private int calcularDigitoCnpj(String str, int[] peso) {
        int soma = 0;

        for (int i = 0; i < str.length(); i++) {
            soma += (str.charAt(i) - '0') * peso[i];
        }

        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    private void validarCpf(String cpf) {
        if (!CpfCnpjUtils.isCpf(cpf)) {
            throw new ValidationException("CPF inválido");
        }
    }

    private void validarCnpj(String cnpj) {
        if (!CpfCnpjUtils.isCnpj(cnpj)) {
            throw new ValidationException("CNPJ inválido");
        }
    }

    public static void validarCpfOuCnpj(String cpf, String cnpj) {
        if (cpf != null && !cpf.isBlank()) {
            validarCpf(cpf);
        }

        if (cnpj != null && !cnpj.isBlank()) {
            validarCnpj(cnpj);
        }
    }
}
