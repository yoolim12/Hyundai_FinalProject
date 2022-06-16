package com.hyundai.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {	
	
	private int pageNum; // 페이지 번호	
	private int amount; // 한 페이지 당 데이터 수
	
	private String type;
	private String keyword;
	
	public String[] getTypeArr() {		
		//삼항식[Condition Operator]      ?   TURE   : FALSE 
		return (type == null) ? new String[] {}  :  type.split("");		
	}//end getType..

	public Criteria() {
		this(1,12);
	}//end cri...	

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}//end Cri..	

}//end class
