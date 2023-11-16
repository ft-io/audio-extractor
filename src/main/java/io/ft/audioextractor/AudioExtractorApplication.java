package io.ft.audioextractor;

import io.ft.audioextractor.downloader.Downloader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AudioExtractorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AudioExtractorApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(Downloader downloader) {
		return args -> {
			downloader.download("https://www.youtube.com/watch?v=b3-rPgRmJvE");
		};
	}

}
