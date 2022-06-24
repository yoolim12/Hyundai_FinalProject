package com.hyundai.project.schedule;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ScheduleService {
	@Autowired
	MemberService memberService;
	
	public void jobUpdateMemberGrade() {

		log.info("[등급 갱신] 스케줄러 실행");
		
		try {
			int res = memberService.updateMemberGrade();
			
			log.info("[등급 갱신] "+ res +"건 업데이트 되었습니다.");
		} catch (Exception e) {
			log.error("[등급 갱신] 스케줄러 실행중 오류가 발생 하였습니다.",e);
		}
		
	}
	
	/*
	 * public void jobUpdateMemberSleep() {
	 * 
	 * log.info("[휴면 계정] 스케줄러 실행");
	 * 
	 * try { int res = memberService.updateMemberSleep();
	 * 
	 * log.info("[휴면 계정] "+ res +"건 업데이트 되었습니다."); } catch (Exception e) {
	 * log.error("[휴면 계정] 스케줄러 실행중 오류가 발생 하였습니다.",e); }
	 * 
	 * }
	 */
}
