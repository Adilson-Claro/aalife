package br.com.easy.aalife.modules.comum.util;

import br.com.easy.aalife.config.exceptions.ValidationException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TelefoneUtils {

    public boolean isTelefoneValido(String telefone) {
        if (telefone == null || telefone.isBlank()) return false;

        var digitos = telefone.replaceAll("\\D", "");

        if (digitos.startsWith("55") && digitos.length() > 11) {
            digitos = digitos.substring(2);
        }

        if (digitos.length() != 10 && digitos.length() != 11) {
            return false;
        }

        var ddd = digitos.substring(0, 2);
        if (ddd.equals("00")) return false;

        if (digitos.length() == 11 && digitos.charAt(2) != '9') {
            return false;
        }

        return true;
    }

    public static void validarTelefone(String telefone) {
        if (!TelefoneUtils.isTelefoneValido(telefone)) {
            throw new ValidationException("Telefone inválido.");
        }
    }
}
