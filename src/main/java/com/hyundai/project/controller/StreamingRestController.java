package com.hyundai.project.controller;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.hyundai.project.dto.StreamingDTO;
import com.hyundai.project.service.StreamingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/back/streaming")
public class StreamingRestController {
	
	@Value("${cloud.aws.credentials.accessKey2}")
    private String accessKey;
	
	@Value("${cloud.aws.credentials.secretKey2}")
    private String secretKey;
	
    @Value("${cloud.aws.s3.bucket2}")
    private String bucketName;
    
    @Value("${cloud.aws.s3.path}")
    private String folderName;
    
    @Value("${cloud.aws.cdn}")
    private String cdn;
    
    @Autowired
    private StreamingService service;
    
    @GetMapping("/list")
	public ResponseEntity<List<StreamingDTO>> getStreaming() {
        ResponseEntity<List<StreamingDTO>> entry = null;
        
        try {
            entry = new ResponseEntity<List<StreamingDTO>>(service.getList(), HttpStatus.OK);
            //log.info(entry);
        } catch (Exception e) {
            e.printStackTrace();
            entry = new ResponseEntity<List<StreamingDTO>>(HttpStatus.BAD_REQUEST);
        } // end try
        return entry;
	}
    
    
    @PostMapping("/upload")
	public String uploadStreaming(@RequestBody StreamingDTO dto) {
		Instant ins = Instant.now();

		OffsetDateTime odt = ins.atOffset(ZoneOffset.UTC);
		ZonedDateTime zdt = ins.atZone(ZoneId.of("UTC"));
		
		Date utc = Date.from(ins);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		
		String folderPath = folderName + "/" + sdf.format(utc).replace('-','/');
		
        AWSCredentials crd = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(crd))
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();
        
        try {
        ListObjectsV2Result result = s3Client.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
//        String id = objects.get(0).getKey();
        String surl = "";
        String simg = "";
        for (S3ObjectSummary os : objects) {
        	if(os.getKey().endsWith("master.m3u8")) {
        		surl = os.getKey();
        	} else if(os.getKey().endsWith("thumb0.jpg")) {
        		simg = os.getKey();
        	}
        }
        dto.setSurl(cdn+surl);
        dto.setSimg(cdn+simg);
		service.uploadStreaming(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	
        return "streaming upload success";
    }
	
	@DeleteMapping("/delete")
	public String deleteStreaming(@RequestBody StreamingDTO dto) {
		try {
			service.deleteStreaming(dto.getSno());
        } catch (Exception e) {
            e.printStackTrace();
        } // end try
        return "streaming delete success";
	}
	
	@GetMapping("/replay/{sno}")
	public ResponseEntity<StreamingDTO> getReplay(@PathVariable("sno") int sno) {
		ResponseEntity<StreamingDTO> entry = null;
		try {
            entry = new ResponseEntity<StreamingDTO>(service.getReplay(sno), HttpStatus.OK);
            //log.info(entry);
        } catch (Exception e) {
            e.printStackTrace();
            entry = new ResponseEntity<StreamingDTO>(HttpStatus.BAD_REQUEST);
        } // end try
        return entry;
	}
}