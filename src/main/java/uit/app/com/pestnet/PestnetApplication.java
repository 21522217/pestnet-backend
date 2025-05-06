package uit.app.com.pestnet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.InetAddress;

@SpringBootApplication
public class PestnetApplication {

	@Value("${server.port}")
	private String serverPort;

	@Value("${server.servlet.context-path:/}")
	private String contextPath;

	public static void main(String[] args) {
		SpringApplication.run(PestnetApplication.class, args);
	}

	@Bean
	public CommandLineRunner logServerInfo() {
		return args -> {
			try {
				String hostAddress = InetAddress.getLocalHost().getHostAddress();
				System.out.println("\n----------------------------------------------------------");
				System.out.println("Application 'Pestnet' is running! Access URLs:");
				System.out.println("Local: \t\thttp://localhost:" + serverPort + contextPath);
				System.out.println("External: \thttp://" + hostAddress + ":" + serverPort + contextPath);
				System.out.println("----------------------------------------------------------\n");
			} catch (Exception e) {
				System.err.println("Failed to retrieve server info: " + e.getMessage());
			}
		};
	}
}
