package com.entreprise.demo.validator;

public interface IGenericValidator<C, U> {
    void beforeUpdate(U updateRequest);

    void beforeSave(C createRequest);
}

