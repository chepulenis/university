package com.foxminded.university.integration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.data.jpa.repository.JpaRepository;

public class Util {
    public static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    public static void verifyFindByIdIsCalledOnce(JpaRepository<?, Integer> repository) {
        Mockito.verify(repository, VerificationModeFactory.times(1)).findById(Mockito.anyInt());
        Mockito.reset(repository);
    }

    public static void verifyFindAllIsCalledOnce(JpaRepository<?, Integer> repository) {
        Mockito.verify(repository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(repository);
    }

}
