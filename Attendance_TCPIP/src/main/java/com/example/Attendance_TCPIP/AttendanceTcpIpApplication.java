package com.example.Attendance_TCPIP;

import com.example.Attendance_TCPIP.service.ZKDeviceLogWriter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AttendanceTcpIpApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceTcpIpApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ZKDeviceLogWriter writer) {
		return args -> {
			writer.fetchLogsToFile();
		};
	}
}
