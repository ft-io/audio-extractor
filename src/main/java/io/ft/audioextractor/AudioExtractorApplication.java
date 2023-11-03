package io.ft.audioextractor;

import io.ft.audioextractor.downloader.YoutubeHttpInterface;
import io.ft.audioextractor.dto.YoutubeMetaRequestDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class AudioExtractorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AudioExtractorApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(YoutubeHttpInterface client) {
		return args -> {
			Map context = new HashMap();
			Map clientInfo = new HashMap();
			clientInfo.put("clientName", "WEB");
			clientInfo.put("clientVersion", "2.2022011");
			context.put("client", clientInfo);
			Map res = client.searchMeta(new YoutubeMetaRequestDto("60WonpQx1QY", context));
			System.out.println(res.toString());
		};
	}

}
