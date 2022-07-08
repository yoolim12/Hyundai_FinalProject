package com.hyundai.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/streaming")
public class StreamS3Controller {
	@Value("${cloud.aws.credentials.accessKey2}")
    private String accessKey;
	
	@Value("${cloud.aws.credentials.secretKey2}")
    private String secretKey;
	
    @Value("${cloud.aws.s3.bucket2}")
    private String bucketName;
    
    @Value("${cloud.aws.s3.path}")
    private String folderName;
    
    
	@GetMapping("/list")
    public void ListS3(){
//		Date now = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
//		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
//	    Date gmt = new Date(sdf.format(now));
//	    log.info(gmt);
	    
//		String year, month, day;
//		String[] utc = sdf.format(now).split("-");
//		year = utc[0];
//		month = utc[1];
//		day = (Integer.parseInt(utc[2])) + "";  
//		String folderPath = folderName + "/"+ year + "/"+ month + "/" + day;
		
        AWSCredentials crd = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(crd))
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();

//        ObjectListing objects = s3Client.listObjects(bucketName, folderPath);
//        do { //1000개 단위로 읽음
//             for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
//                 log.info(objectSummary+"\n");
//             }
//             objects = s3Client.listNextBatchOfObjects(objects); // 1000개 단위로만 가져옴
//        } while (objects.isTruncated());
        ListObjectsV2Result result = s3Client.listObjectsV2(bucketName,folderName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        for (S3ObjectSummary os : objects) {
            System.out.println("* " + os.getKey());
        }
//        log.info(objects.getObjectSummaries());
//        log.info(objects.getObjectSummaries().get(1));
    }
}