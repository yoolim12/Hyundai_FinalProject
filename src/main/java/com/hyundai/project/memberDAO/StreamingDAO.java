package com.hyundai.project.memberDAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.StreamingDTO;

@Mapper
public interface StreamingDAO {

	public List<StreamingDTO> getList() throws Exception;

	public void insertStreaming(StreamingDTO dto) throws Exception;
	
	public void deleteStreaming(int sno) throws Exception;

	public StreamingDTO getReplay(int sno);
	
}
