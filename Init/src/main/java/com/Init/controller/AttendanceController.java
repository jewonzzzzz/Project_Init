package com.Init.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Init.service.AttendanceService;


@Controller
@RequestMapping(value = "/Attendance/*")
public class AttendanceController {
    
    @Autowired
    private AttendanceService mService;

    

    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);
    
    // 템플릿 적용 확인
    // http://localhost:8088/Attendance/main
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public void mainPage() {
        logger.debug(" /main -> mainPage() 실행");
        logger.debug(" /views/member/main.jsp 뷰페이지 연결");
    }
    
    // 시간 조회 
    // http://localhost:8088/Attendance/attendanceMain
    @GetMapping(value = "/attendanceMain")
    public String getAllTime() {
        logger.debug(" /attendanceMain -> page 실행 ");
        logger.debug(" /views/Attendance/attendanceMain.jsp 뷰페이지 연결");
        return "Attendance/attendanceMain";
    }
    
    // 캘린더 API 호출
  
}
