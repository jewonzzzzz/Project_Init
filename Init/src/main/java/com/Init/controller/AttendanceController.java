package com.Init.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
 // qr코트로 넘어온 파라메터값 출퇴근 로직을 처리하고 최근 출퇴근을 조회하는 메서드
 // http://localhost:8088/Attendance/attendanceMain
    @GetMapping(value = "/attendanceMain")
    public String handleAttendance(@RequestParam(value = "emp_id", required = false) String emp_id, Model model) {
        logger.debug(" /attendanceMain -> page 실행 ");
        
        // emp_id가 있는 경우에만 출근 또는 퇴근 처리
        if (emp_id != null && !emp_id.isEmpty()) {
            logger.debug("emp_id가 존재합니다: {}", emp_id);

            // 현재 출근 상태인지 확인
            boolean isCheckedIn = attendanceService.checkIfCheckedIn(emp_id);

            if (isCheckedIn) {
                // 이미 출근 상태면 퇴근 처리
                attendanceService.recordCheckout(emp_id);
                logger.debug("emp_id: {}의 퇴근 기록이 저장되었습니다.", emp_id);
            } else {
                // 출근 상태가 아니면 출근 처리
                attendanceService.recordCheckIn(emp_id);
                logger.debug("emp_id: {}의 출근 기록이 저장되었습니다.", emp_id);
            }
        } else {
            logger.debug("emp_id가 존재하지 않거나 비어 있습니다.");
        }

        // 최근 출퇴근 기록 조회
        List<AttendanceVO> recentAttendanceData = attendanceService.getRecentCheckTime(emp_id);
        logger.debug("emp_id: {}에 대한 최근 출퇴근 기록 조회 완료.", emp_id);
        
        // 변환된 날짜를 저장할 리스트
        List<Map<String, String>> formattedAttendanceData = new ArrayList<>();

        if (!recentAttendanceData.isEmpty()) {
            AttendanceVO attendance = recentAttendanceData.get(0); // 가장 최근 기록을 가져옵니다.
            Map<String, String> formattedData = new HashMap<>();
            formattedData.put("formattedCheckInTime", formatTimestamp(attendance.getCheck_in()));
            formattedData.put("formattedCheckOutTime", formatTimestamp(attendance.getCheck_out()));
            formattedAttendanceData.add(formattedData);

            logger.debug("출근 시간: {}, 퇴근 시간: {}", attendance.getCheck_in(), attendance.getCheck_out());
        }

        model.addAttribute("attendanceData", formattedAttendanceData); // 변환된 데이터를 모델에 추가
        model.addAttribute("empId", emp_id); // emp_id도 모델에 추가하여 표시할 수 있게 함

        logger.debug("모델에 attendanceData와 empId 추가 완료.");
        return "Attendance/attendanceMain"; // JSP 페이지로 이동
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
    
    
    // 보완예정 삭제 ㄴ
    

//    @GetMapping("/attendanceCheck")
//    public String handleAttendance(@RequestParam String emp_id, Model model) {
//        try {
//            boolean isCheckedIn = attendanceService.checkIfCheckedIn(emp_id);
//            String message;
//
//            if (isCheckedIn) {
//                attendanceService.recordCheckout(emp_id);
//                message = emp_id + "님의 퇴근이 기록되었습니다.";
//            } else {
//                attendanceService.recordCheckIn(emp_id);
//                message = emp_id + "님의 출근이 기록되었습니다.";
//            }
//
//            // 출퇴근 기록 조회
//            List<AttendanceVO> attendanceData = attendanceService.getAllCheckTime(emp_id);
//            
//            // 변환된 날짜를 저장할 리스트
//            List<Map<String, String>> formattedAttendanceData = new ArrayList<Map<String, String>>();
//            
//            for (AttendanceVO attendance : attendanceData) {
//                Map<String, String> formattedData = new HashMap<String, String>();
//                formattedData.put("formattedCheckInTime", formatTimestamp(attendance.getCheck_in())); // 수정된 포맷 메서드 사용
//                formattedData.put("formattedCheckOutTime", formatTimestamp(attendance.getCheck_out())); // 수정된 포맷 메서드 사용
//                formattedAttendanceData.add(formattedData);
//            }
//            
//            model.addAttribute("attendanceData", formattedAttendanceData); // 변환된 데이터를 모델에 추가
//            model.addAttribute("message", message); // 메시지도 모델에 추가
//        } catch (Exception e) {
//            model.addAttribute("message", "출근/퇴근 처리 중 오류가 발생했습니다. 관리자에게 문의하세요.");
//            e.printStackTrace(); // 디버그 목적으로 로그를 남길 수 있습니다.
//        }
//        return "Attendance/attendanceMain"; // JSP 페이지로 이동
//    }
	
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