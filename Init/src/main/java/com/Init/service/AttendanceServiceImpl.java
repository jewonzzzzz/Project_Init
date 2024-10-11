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

	@Override
	public void checkIn(AttendanceVO attendance) {
	    // 출근 기록을 저장하는 로직
	    AttendanceVO checkIn = new AttendanceVO();
	    checkIn.setEmp_id(attendance.getEmp_id()); // attendance 객체에서 emp_id 가져오기
	    attendanceDAO.checkIn(checkIn);
	}

	@Override
	public void checkOut(AttendanceVO attendance) {
	    // 퇴근 기록을 저장하는 로직
	    AttendanceVO checkOut = new AttendanceVO();
	    checkOut.setEmp_id(attendance.getEmp_id()); // attendance 객체에서 emp_id 가져오기
	    attendanceDAO.checkOut(checkOut);
	}
	
	// QR출근 처리	
	@Override
	public void recordCheckIn(String emp_id) {
		try {
			attendanceDAO.recordCheckIn(emp_id);
		} catch (Exception e) {
			  throw new RuntimeException("출근 기록 중 오류가 발생했습니다.", e);
			
		}
	}

	@Override
	public void recordCheckout(String emp_id) {
		try {
			attendanceDAO.recordCheckout(emp_id);
		} catch (Exception e) {
			// 예외 처리 코드
			e.printStackTrace();
		}
	}
	// 출근했는지에대한 로직
	@Override
	public boolean checkIfCheckedIn(String emp_id) {
		return attendanceDAO.checkIfCheckedIn(emp_id);
	}
	
	// 최근 출퇴근 기록 조회
	@Override
	 public List<AttendanceVO> getRecentCheckTime(String emp_id) {
	        return attendanceDAO.getRecentCheckTime(emp_id);
	    }
	
	



}