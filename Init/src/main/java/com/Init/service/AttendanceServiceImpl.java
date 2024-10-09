package com.Init.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.Init.domain.AttendanceVO;
import com.Init.persistence.AttendanceDAO;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Inject
    private AttendanceDAO attendanceDAO;

    @Override
    public List<AttendanceVO> getAllCheckTime(String emp_id) {
        return attendanceDAO.getAllCheckTime(emp_id);
    }

    @Override
    public void updateAllTime(AttendanceVO uvo) {
        attendanceDAO.updateAllTime(uvo);
    }

    @Override
    public List<AttendanceVO> getMemberWorkStatus(String emp_id) {
        return attendanceDAO.getMemberWorkStatus(emp_id);
    }

    @Override
    public List<AttendanceVO> getMemberCalendar(AttendanceVO gvo) {
        return attendanceDAO.getMemberCalendar(gvo);
    }

    @Override
    public void insertAllTime(AttendanceVO attendance) {
        attendanceDAO.insertAllTime(attendance);
    }

    @Override
    public void insertWorkStatus(String workform_status) {
        attendanceDAO.insertWorkStatus(workform_status);
    }

    @Override
    public void updateWorkStatus(String emp_id, String workform_status) {
        attendanceDAO.updateWorkStatus(emp_id, workform_status);
    }

    @Override
    public void insertCheckin(String check_in) {
        attendanceDAO.insertCheckin(check_in);
    }

    @Override
    public void insertCheckOutTime(String check_out) {
        attendanceDAO.insertCheckOutTime(check_out);
    }

    @Override
    public AttendanceVO getCheckTime(String emp_id) {
        return attendanceDAO.getCheckTime(emp_id);
    }

    @Override
    public void updateCheckTime(AttendanceVO attendance) {
        attendanceDAO.updateCheckTime(attendance);
    }

    @Override
    public AttendanceVO getWorkStatus(String emp_id) {
        return attendanceDAO.getWorkStatus(emp_id);
    }
}