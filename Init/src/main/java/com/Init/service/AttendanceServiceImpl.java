package com.Init.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.Init.domain.AttendanceVO;
import com.Init.persistence.AttendanceDAO;
import com.Init.persistence.AttendanceDAOImpl;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	private static final Logger logger = LoggerFactory.getLogger(AttendanceServiceImpl.class);

	@Inject
	private AttendanceDAO attendanceDAO;

	@Override
	public List<AttendanceVO> getAllCheckTime(String emp_id) {
		logger.debug("service 동작 ");

		return attendanceDAO.getAllCheckTime(emp_id);
	}

// 출퇴근 로직
	@Override
	public void checkIn(AttendanceVO attendance) {
		attendanceDAO.checkIn(attendance);
	}

	@Override
	public void checkOut(AttendanceVO attendance) {
		attendanceDAO.checkOut(attendance);
	}

	
	//qr  생성 로직
	public String generateQrCode(String emp_id) {
	    try {
	        QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        BitMatrix bitMatrix = qrCodeWriter.encode(emp_id, BarcodeFormat.QR_CODE, 200, 200);
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
	        
	        // Base64로 인코딩하여 반환
	        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
	    } catch (WriterException e) {
	        // QR 코드 생성 관련 예외 처리
	        System.err.println("QR 코드 작성 오류: " + e.getMessage());
	        return null;
	    } catch (IOException e) {
	        // I/O 관련 예외 처리
	        System.err.println("입출력 오류: " + e.getMessage());
	        return null;
	    } catch (Exception e) {
	        // 일반 예외 처리
	        System.err.println("일반 오류: " + e.getMessage());
	        return null;
	    }
	}
	
	
}