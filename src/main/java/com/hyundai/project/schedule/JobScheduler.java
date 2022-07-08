package com.hyundai.project.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.hyundai.project.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class JobScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job ExampleJob;

    @Autowired
    private UpdateJobConfig exampleJobConfig;
    
    @Autowired
    private MemberService service;

    //@Scheduled(cron = "0 0/5 * * * ?")
    @Scheduled(fixedDelay = 2000000000)
    public void jobScheduled() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        
    	log.info("[등급 갱신] 스케줄러 실행");
		
		try {
			int res = service.updateMemberGrade();
			
			log.info("[등급 갱신] "+ res +"건 업데이트 되었습니다.");
		} catch (Exception e) {
			log.error("[등급 갱신] 스케줄러 실행중 오류가 발생 하였습니다.",e);
		}
    	
		
		log.info("[휴면 계정] 스케줄러 실행");
		try { 
			int res = service.updateMemberSleep();
			log.info("[휴면 계정] "+ res +"건 업데이트 되었습니다."); 
		} catch (Exception e) {
			log.error("[휴면 계정] 스케줄러 실행중 오류가 발생 하였습니다.",e); 
		}
		 
        Map<String, JobParameter> jobParameterMap = new HashMap<>();

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Date time = new Date();

        String time1 = format1.format(time);
        jobParameterMap.put("date", new JobParameter(time1));
        JobParameters parameters = new JobParameters(jobParameterMap);

        // ExampleJobConfig 의 ExampleJob 배치 실행
        JobExecution jobExecution = jobLauncher.run(exampleJobConfig.ExampleJob(), parameters);

        while (jobExecution.isRunning()) {
            log.info("...");
        }

        log.info("Job Execution: " + jobExecution.getStatus());
        log.info("Job getJobConfigurationName: " + jobExecution.getJobConfigurationName());
        log.info("Job getJobId: " + jobExecution.getJobId());
        log.info("Job getExitStatus: " + jobExecution.getExitStatus());
        log.info("Job getJobInstance: " + jobExecution.getJobInstance());
        log.info("Job getStepExecutions: " + jobExecution.getStepExecutions());
        log.info("Job getLastUpdated: " + jobExecution.getLastUpdated());
        log.info("Job getFailureExceptions: " + jobExecution.getFailureExceptions());
    }
    
    

}