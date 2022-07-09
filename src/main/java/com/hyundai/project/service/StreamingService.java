package com.hyundai.project.service;

import com.hyundai.project.dto.StreamingDTO;

public interface StreamingService {
	
	public void uploadStreaming(StreamingDTO dto) throws Exception;
	
	public void deleteStreaming(int sno) throws Exception;
}
