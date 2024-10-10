package com.Init.controller;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Init.domain.AttendanceVO;
import com.Init.service.AttendanceService;
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
@Controller
@RequestMapping(value = "/Attendance/*")
public class AttendanceController {
    
	 @Autowired
	    private AttendanceService attendanceService;

    

    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);
    
    // 템플릿 적용 확인
    // http://localhost:8088/Attendance/main
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage() {
        logger.debug(" /main -> mainPage() 실행");
        logger.debug(" /views/Attendance/main.jsp 뷰페이지 연결");
        
        return "Attendance/main";
        
    }
    
    // 메인
    // http://localhost:8088/Attendance/attendanceMain
    @GetMapping(value = "/attendanceMain")
    public String getAllTime() {
        logger.debug(" /attendanceMain -> page 실행 ");
        logger.debug(" /views/Attendance/attendanceMain.jsp 뷰페이지 연결");
        return "Attendance/attendanceMain";
    }
    
   

    // http://localhost:8088/Attendance/attendanceAdmin
    @RequestMapping(value = "attendanceAdmin", method = RequestMethod.GET)
    public String getCheckTimePage() {
        // 초기 로드 시 데이터 조회는 하지 않음
    	logger.debug(" /attendanceAdmin -> page 실행 ");
        logger.debug(" /views/Attendance/attendanceAdmin.jsp 뷰페이지 연결");
        return "Attendance/attendanceAdmin"; // 이동할 JSP 페이지
    }

    // POST 메서드는 AJAX를 통해 호출
    @RequestMapping(value = "attendanceData", method = RequestMethod.POST)
    @ResponseBody
    public List<AttendanceVO> getAllCheckTime(@RequestParam("emp_id") String emp_id) {
        // 서비스 호출해서 결과값 가져오기
    	
    	 logger.debug("결과값"+emp_id);
    	
        return attendanceService.getAllCheckTime(emp_id); // List<AttendanceVO>를 반환
       
    }


    // 출근 기록 메소드
    @PostMapping("/checkIn")
    @ResponseBody // JSON 형태로 응답을 반환하도록 설정
    public String checkIn(@RequestParam("emp_id") String emp_id) {
        AttendanceVO attendance = new AttendanceVO();
        attendance.setEmp_id(emp_id); // emp_id를 설정
        attendanceService.checkIn(attendance);
        logger.debug("출근 기록 완료: " + emp_id);
        return "출근 시간이 기록되었습니다."; // 성공 메시지 반환
    }

    // 퇴근 기록 메소드
    @PostMapping("/checkOut")
    @ResponseBody // JSON 형태로 응답을 반환하도록 설정
    public String checkOut(@RequestParam("emp_id") String emp_id) {
        AttendanceVO attendance = new AttendanceVO();
        attendance.setEmp_id(emp_id); // emp_id를 설정
        attendanceService.checkOut(attendance);
        logger.debug("퇴근 기록 완료: " + emp_id);
        return "퇴근 시간이 기록되었습니다."; // 성공 메시지 반환
    }
    
    //이미지 생성 
    
    @PostMapping("/generateQr")
    @ResponseBody
    public String generateQr(@RequestParam("emp_id") String emp_id) {
        try {
            String qrCodeBase64 = attendanceService.generateQrCode(emp_id);
            if (qrCodeBase64 != null) {
                return "data:image/png;base64," + qrCodeBase64; // Base64 이미지 URL 반환
            } else {
                return "Error generating QR code"; // QR 코드 생성 실패
            }
        } catch (Exception e) {
            logger.error("QR 코드 생성 실패: " + e.getMessage());
            return "Error generating QR code";
        }
    }
  
}
