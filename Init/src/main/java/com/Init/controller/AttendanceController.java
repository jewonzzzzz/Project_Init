package com.Init.controller;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Init.service.AttendanceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Init.domain.AttendanceVO;
import com.Init.service.AttendanceService;

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



    // 모든 시간 수정
    @PostMapping("/updateAllTime")
    public String updateAllTime(@ModelAttribute AttendanceVO attendance) {
        // 서비스 호출하여 시간 정보 업데이트
        attendanceService.updateAllTime(attendance);
        // 수정 후 리다이렉트 또는 다른 페이지로 이동
        return "redirect:/attendance/getAllCheckTime?emp_id=" + attendance.getEmp_id();
    }

    // 직원 근무 상태 조회
    @RequestMapping("/getMemberWorkStatus")
    public String getMemberWorkStatus(@RequestParam("emp_id") String emp_id, Model model) {
        // 서비스 호출해서 근무 상태 정보 가져오기
        List<AttendanceVO> workStatus = attendanceService.getMemberWorkStatus(emp_id);
        model.addAttribute("workStatus", workStatus);
        return "attendance/getMemberWorkStatus";
    }

    // 근무 캘린더 조회
    @RequestMapping("/getMemberCalendar")
    public String getMemberCalendar(@ModelAttribute AttendanceVO attendance, Model model) {
        // 서비스 호출해서 캘린더 정보 가져오기
        List<AttendanceVO> calendar = attendanceService.getMemberCalendar(attendance);
        model.addAttribute("calendar", calendar);
        return "attendance/getMemberCalendar";
    }

    // 모든 시간 추가
    @PostMapping("/insertAllTime")
    public String insertAllTime(@ModelAttribute AttendanceVO attendance) {
        // 서비스 호출하여 새로운 시간 정보 추가
        attendanceService.insertAllTime(attendance);
        return "redirect:/attendance/getAllCheckTime?emp_id=" + attendance.getEmp_id();
    }

    // 근무 형태 삽입
    @PostMapping("/insertWorkStatus")
    public String insertWorkStatus(@RequestParam("workform_status") String workform_status) {
        // 서비스 호출하여 근무 형태 추가
        attendanceService.insertWorkStatus(workform_status);
        return "redirect:/attendance/getAllCheckTime"; // 필요한 페이지로 리다이렉트
    }

    // 근무 형태 수정
    @PostMapping("/updateWorkStatus")
    public String updateWorkStatus(@RequestParam("emp_id") String emp_id, @RequestParam("workform_status") String workform_status) {
        // 서비스 호출하여 근무 형태 수정
        attendanceService.updateWorkStatus(emp_id, workform_status);
        return "redirect:/attendance/getMemberWorkStatus?emp_id=" + emp_id;
    }

    // 출근 시간 삽입
    @PostMapping("/insertCheckin")
    public String insertCheckin(@RequestParam("check_in") String check_in) {
        // 서비스 호출하여 출근 시간 삽입
        attendanceService.insertCheckin(check_in);
        return "redirect:/attendance/getAllCheckTime";
    }

    // 퇴근 시간 삽입
    @PostMapping("/insertCheckOutTime")
    public String insertCheckOutTime(@RequestParam("check_out") String check_out) {
        // 서비스 호출하여 퇴근 시간 삽입
        attendanceService.insertCheckOutTime(check_out);
        return "redirect:/attendance/getAllCheckTime";
    }

    // 출퇴근 시간 조회
    @RequestMapping("/getCheckTime")
    public String getCheckTime(@RequestParam("emp_id") String emp_id, Model model) {
        // 서비스 호출하여 출퇴근 시간 조회
        AttendanceVO checkTime = attendanceService.getCheckTime(emp_id);
        model.addAttribute("checkTime", checkTime);
        return "attendance/getCheckTime";
    }

    // 출퇴근 시간 수정
    @PostMapping("/updateCheckTime")
    public String updateCheckTime(@ModelAttribute AttendanceVO attendance) {
        // 서비스 호출하여 출퇴근 시간 수정
        attendanceService.updateCheckTime(attendance);
        return "redirect:/attendance/getCheckTime?emp_id=" + attendance.getEmp_id();
    }

    // 근무 상태 조회
    @RequestMapping("/getWorkStatus")
    public String getWorkStatus(@RequestParam("emp_id") String emp_id, Model model) {
        // 서비스 호출하여 근무 상태 정보 조회
        AttendanceVO workStatus = attendanceService.getWorkStatus(emp_id);
        model.addAttribute("workStatus", workStatus);
        return "attendance/getWorkStatus";
    }
    	
  
}
turn "attendance/getWorkStatus";
    }
    	
  
}
