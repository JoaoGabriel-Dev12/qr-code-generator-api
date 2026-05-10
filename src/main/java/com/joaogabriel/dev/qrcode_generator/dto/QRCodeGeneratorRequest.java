package com.joaogabriel.dev.qrcode_generator.dto;

import jakarta.validation.constraints.NotBlank;

public record QRCodeGeneratorRequest(@NotBlank(message = "A URL não pode ser vazia") String url) {

}
