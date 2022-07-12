package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.StreamingDTO;

public interface StreamingService {

    public List<StreamingDTO> getList() throws Exception;

    public void uploadStreaming(StreamingDTO dto) throws Exception;

    public void deleteStreaming(int sno) throws Exception;

    public StreamingDTO getReplay(int sno) throws Exception;
}
