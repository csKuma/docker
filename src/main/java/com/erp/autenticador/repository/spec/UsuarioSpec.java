package com.erp.autenticador.repository.spec;


import com.erp.autenticador.model.Usuario;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class UsuarioSpec {

    public static Specification<Usuario> comCpf(String cpf) {
        return (root, query, builder) -> {
            if (Objects.isNull(cpf)) {
                return builder.conjunction();
            }
            return builder.equal(root.get("cpfCnpj"), cpf);
        };
    }
    public static Specification<Usuario> comEmail(String email) {
        return (root, query, builder) -> {
            if (Objects.isNull(email)) {
                return builder.conjunction();
            }
            return builder.equal(root.get("email"), email);
        };
    }
}
