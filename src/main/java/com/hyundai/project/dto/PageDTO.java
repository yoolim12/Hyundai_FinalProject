package com.hyundai.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/* 
 * 작성자 : 문혁
 * 
 * 페이징 정보를 저장하는 DTO 객체
 */
@Getter
@Setter
@ToString
public class PageDTO {
	// 현재 페이지에서 보여줄 게시글을 구할 때 필요한 속성
	private int curPage;
	private int startNum;
	private int endNum;
	
	// 페이지 리스트에 사용되는 속성 
	private long total;
	private int start;
	private int last;
	
	public PageDTO(int total) {
		this.total = total;
		
		this.startNum = 1;
		this.endNum = 10;
	}
	// 4개의 파라미터를 받는 생성자. 해당 파라미터로 start, last 페이지 구하는 로직
	public PageDTO(int curPage, int startNum, int endNum, long total) {
		this.curPage = curPage;
		this.startNum = startNum;
		this.endNum = endNum;
		this.total = total;
		this.start = curPage - (curPage-1)%10;
		this.last = (int) Math.ceil((double) total/10);
	}
}// end class
