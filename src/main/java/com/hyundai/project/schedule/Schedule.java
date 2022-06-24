package com.hyundai.project.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedule {
	@Autowired
	ScheduleService service;
	
	@Scheduled(fixedDelay = 500000)
	public void updateGrade() {
		// 등급 갱신
		service.jobUpdateMemberGrade();
	}
	
	@Scheduled(fixedDelay = 500000)
	public void updateSleep() {
		// 휴면 갱신
		service.jobUpdateMemberSleep();
	}
}
