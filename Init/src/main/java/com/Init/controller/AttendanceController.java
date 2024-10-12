package com.Init.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
 // qr코드로 넘어온 파라메터값 출퇴근 로직을 처리하고 최근 출퇴근을 조회하는 메서드

    
    // 출근 시간 기록 및 조회
    @GetMapping(value = "attendanceMain")
    public String handleAttendance(@RequestParam(value = "emp_id", required = false) String emp_id, Model model, HttpSession session) {
        logger.debug(" /attendanceMain -> page 실행 ");
        
        if (emp_id != null && !emp_id.isEmpty()) {
            // 세션에 emp_id 저장
            session.setAttribute("emp_id", emp_id);

            // 출근 기록 삽입 (출근 시에만 실행)
            attendanceService.recordCheckIn(emp_id); // 매퍼를 통해 출근 기록 삽입

            // 방금 저장된 최신 출근 기록 조회
            AttendanceVO latestCheckInData = attendanceService.fetchLatestAttendanceRecord(emp_id);
            
            if (latestCheckInData != null) {
                model.addAttribute("checkInTime", formatTimestamp(latestCheckInData.getCheck_in()));
            }
            model.addAttribute("empId", emp_id);
        } else {
            logger.debug("emp_id가 존재하지 않거나 비어 있습니다."); // 로그 추가 가능
        }   
        
        return "Attendance/attendanceMain"; // JSP 페이지로 이동
    }


    
    
 // 퇴근 처리 요청 (GET)
    @GetMapping("/checkOut")
    public ResponseEntity<Map<String, Object>> checkOut(HttpSession session) {
        // 세션에서 emp_id 조회
        String emp_id = (String) session.getAttribute("emp_id");
        logger.debug("퇴근 요청: emp_id = {}", emp_id);
        
        Map<String, Object> response = new HashMap<>();
        
        // 현재 출근한 시간을 조회 (가장 최근 출근 기록을 가져옴)
        AttendanceVO latestCheckInData = attendanceService.fetchLatestAttendanceRecord(emp_id);

        if (latestCheckInData != null) {
            // 퇴근 기록 처리
            attendanceService.recordCheckout(emp_id);
            response.put("status", "success");
            response.put("message", "퇴근 처리 완료");
        } else {
            response.put("status", "error");
            response.put("message", "출근 기록이 없습니다.");
        }

        return ResponseEntity.ok(response);
    }
    
//    @GetMapping("/calculateWorkingTime")
//    public ResponseEntity<Map<String, Object>> calculateWorkingTime(HttpSession session) {
//        String emp_id = (String) session.getAttribute("emp_id");
//        Map<String, Object> response = new HashMap<>();
//        
//        // 근무 시간을 자동으로 계산하고 업데이트
//        attendanceService.calculateAndUpdateWorkingTime(emp_id);
//        
//        response.put("status", "success");
//        response.put("message", "근무 시간이 성공적으로 업데이트되었습니다.");
//
//        return ResponseEntity.ok(response);
//    }
//

    @GetMapping("/calculateWorkingTime")
    public ResponseEntity<Map<String, Object>> calculateWorkingTime(HttpSession session) {
        String emp_id = (String) session.getAttribute("emp_id");
        Map<String, Object> response = new HashMap<>();
        
        // 근무 시간을 자동으로 계산하고 업데이트
        attendanceService.calculateAndUpdateWorkingTime(emp_id);
        
        // 업데이트된 근무 시간을 조회
        double workingTime = attendanceService.getLatestWorkingTime(emp_id);
        
        response.put("status", "success");
        response.put("message", "근무 시간이 성공적으로 업데이트되었습니다.");
        response.put("workingTime", workingTime); // 근무 시간을 응답에 추가

        return ResponseEntity.ok(response);
    }
    
    
		 
    
    // 어드민 
	  // http://localhost:8088/Attendance/attendanceAdmin
	   @RequestMapping(value = "attendanceAdmin", method = RequestMethod.GET)
	   public String getCheckTimePage() {
	       // 초기 로드 시 데이터 조회는 하지 않음
		   logger.debug(" /attendanceAdmin -> page 실행 ");
	       logger.debug(" /views/Attendance/attendanceMain.jsp 뷰페이지 연결");
	        return "Attendance/attendanceAdmin"; // 이동할 JSP 페이지
    }


  
    
    
    
	 // 어드민페이지 날짜 시간 관련 포맷팅
	   @RequestMapping(value = "attendanceData", method = RequestMethod.POST)
	   @ResponseBody
	   public List<Map<String, Object>> getAllCheckTime(@RequestParam("emp_id") String empId) {
	       List<AttendanceVO> attendanceList = attendanceService.getAllCheckTime(empId); // AttendanceVO 리스트를 가져옴
	       List<Map<String, Object>> attendanceDataList = new ArrayList<>(); // 통합된 데이터를 저장할 리스트

	       // AttendanceVO 리스트를 Map으로 변환하여 포맷팅
	       for (AttendanceVO attendance : attendanceList) {
	           Map<String, Object> attendanceData = new HashMap<>();

	           // 포맷된 데이터 추가
	           attendanceData.put("check_in", formatTimestamp(attendance.getCheck_in())); // 출근 시간
	           attendanceData.put("check_out", formatTimestamp(attendance.getCheck_out())); //  퇴근 시간
	           attendanceData.put("return_time", formatTimestamp(attendance.getReturn_time())); // 복귀 수정 시간
	           attendanceData.put("workingoutside_time", formatTimestamp(attendance.getWorkingOutside_time())); // 포맷된 외출 시간
	           
	          
	           attendanceData.put("new_check_in", formatTimestamp(attendance.getNew_check_in())); // 출근 수정 시간
	           attendanceData.put("new_check_out", formatTimestamp(attendance.getNew_check_out())); // 퇴근 수정 시간
	           attendanceData.put("new_workingoutside_time", formatTimestamp(attendance.getNew_workingoutside_time())); // 외출 수정 시간
	           attendanceData.put("modified_time", formatTimestamp(attendance.getModified_time())); // 결재 수정 시간
	           attendanceData.put("created_at", formatTimestamp(attendance.getCreated_at())); // 결재일 

	           // 원본 데이터 추가
	           attendanceData.put("attendance_id", attendance.getAttendance_id()); // 근태 ID
	           attendanceData.put("emp_id", attendance.getEmp_id()); // 직원 ID
	           attendanceData.put("emp_cid", attendance.getEmp_cid()); // 직원 CID
	           attendanceData.put("status", attendance.getStatus()); // 상태
	           attendanceData.put("overtime", attendance.getOvertime()); // 초과 근무 시간
	           attendanceData.put("working_time", attendance.getWorking_time()); // 근무 시간
	           attendanceData.put("night_work_time", attendance.getNight_work_time()); // 야근 시간
	           attendanceData.put("special_working_time", attendance.getSpecial_working_time()); // 특별 근무 시간
	           attendanceData.put("modified_reason", attendance.getModified_reason()); // 수정 이유
	           attendanceData.put("modified_person", attendance.getModified_person()); // 수정자
	           attendanceData.put("workform_status", attendance.getWorkform_status()); // 근무 상태
	           
	           attendanceDataList.add(attendanceData); // 통합된 데이터 추가
	       }

	       return attendanceDataList; // 통합된 리스트 반환
	   }



	
	// Timestamp를 포맷된 문자열로 변환하는 메서드
	private String formatTimestamp(Timestamp timestamp) {
	    if (timestamp != null) {
	        LocalDateTime dateTime = timestamp.toLocalDateTime();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        return dateTime.format(formatter);
	    }
	    return ""; // null인 경우 빈 문자열 반환
	}

    
    
}