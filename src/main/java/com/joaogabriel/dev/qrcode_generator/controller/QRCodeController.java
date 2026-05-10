package com.joaogabriel.dev.qrcode_generator.controller;

import java.awt.image.BufferedImage;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.joaogabriel.dev.qrcode_generator.dto.QRCodeGeneratorRequest;
import com.joaogabriel.dev.qrcode_generator.service.QRCodeGeneratorService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

	private final QRCodeGeneratorService service;

	public QRCodeController(QRCodeGeneratorService service) {
		super();
		this.service = service;
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cria o QRCode com a URL vinda do body e retorna a imagem criada"),
			@ApiResponse(responseCode = "400", description = "URL inválida ou vazia")
	})
	@PostMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> generate(@Valid @RequestBody QRCodeGeneratorRequest request){
		BitMatrix bitMatrix = service.generate(request);
		return ResponseEntity.ok().body(MatrixToImageWriter.toBufferedImage(bitMatrix));
	}
}
