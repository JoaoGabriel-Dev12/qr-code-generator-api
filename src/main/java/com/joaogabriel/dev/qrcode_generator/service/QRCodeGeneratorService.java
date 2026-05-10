package com.joaogabriel.dev.qrcode_generator.service;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.joaogabriel.dev.qrcode_generator.dto.QRCodeGeneratorRequest;

@Service
public class QRCodeGeneratorService {

	private static final int SIZE = 300;
	
	public BitMatrix generate(QRCodeGeneratorRequest request) {
		QRCodeWriter writer = new QRCodeWriter();
		try {
			return writer.encode(request.url(), BarcodeFormat.QR_CODE, SIZE, SIZE);
		} catch (WriterException e) {
			throw new RuntimeException("Erro ao gerar o QR Code", e);
		}
	}
}
