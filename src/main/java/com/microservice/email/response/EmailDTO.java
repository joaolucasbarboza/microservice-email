package com.microservice.email.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailDTO {

    @NotBlank
    private String ownerRef;

    @NotBlank
    @Email
    private String emailFrom;

    @NotBlank
    private String emailTo;

    @NotBlank
    private String subject;

    @NotBlank
    private String body;
}
