//package com.erp.autenticador.model.exception;
//
//
//import jakarta.validation.ConstraintValidator;
//
//import java.util.Objects;
//import java.util.UUID;
//
//public class UUidValidator implements ConstraintValidator<UUIDValide, String> {
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        if (!Objects.isNull(value)) {
//            try {
//                UUID uuid1 = UUID.fromString(value);
//                return true;
//            } catch (IllegalArgumentException e) {
//                throw new BadRequest("id no formato incorreto");
//            }
//        }
//        return false;
//    }
//}
