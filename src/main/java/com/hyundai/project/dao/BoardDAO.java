package com.hyundai.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.PageDTO;
/* 작성자 : 문혁
 * 게시판 추가, 상세조회, 수정, 삭제 쿼리에 관련된 SQL을 호출하는 인터페이스
 */
@Mapper
public interface BoardDAO {
	void insertBoard(BoardDTO boardDTO) throws SQLException; 
	
	List<BoardDTO> getBoardList(PageDTO pageDTO) throws SQLException;	

	BoardDTO getDetail(long no) throws SQLException;
	
	int deleteBoard(BoardDTO boardDTO) throws SQLException;
	
	int updateBoard(BoardDTO boardDTO) throws SQLException;
	
	// 게시글 총 개수 조회
	long getTotal();


	

}//end int..
