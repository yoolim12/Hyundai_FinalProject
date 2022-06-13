package com.hyundai.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.BoardAttachDTO;
import com.hyundai.project.dto.BoardDTO;
/* 작성자 : 문혁
 * 첨부파일 추가, 조회, 삭제 쿼리에 관련된 SQL을 호출하는 인터페이스
 */
@Mapper
public interface BoardAttachDAO {
	void insert(BoardAttachDTO attachDTO) throws SQLException; 
	
	void delete(long bno) throws SQLException;
	
	List<BoardAttachDTO> findByBno(long bno) throws SQLException;
	
}//end int..
