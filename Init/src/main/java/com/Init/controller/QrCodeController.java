package com.Init.controller;



import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class QrCodeController {

    @RequestMapping(value = "/getQR", method = RequestMethod.GET)
    public void getQR(@RequestParam("code") String code,
                      @RequestParam(value = "width", defaultValue = "200") int width,
                      @RequestParam(value = "height", defaultValue = "200") int height,
                      HttpServletResponse response) throws IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            BitMatrix bitMatrix = qrCodeWriter.encode(code, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", os);
            os.flush();
            os.close();
        } catch (WriterException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
