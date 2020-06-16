package sks.spring.boot.app.ems.scheduler;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	
	@Scheduled(fixedRate = 500000, initialDelay =100000)
	public void  doExecuteJob() {
		
		
		System.out.println("Printing" + new Date());
		
		
	}

}
