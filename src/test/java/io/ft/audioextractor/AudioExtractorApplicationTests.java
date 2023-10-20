package io.ft.audioextractor;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.job.FFmpegJob;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.libfreenect2.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@SpringBootTest
class AudioExtractorApplicationTests {

	private static final String VIDEO = "D:\\test\\test.MOV";

	String ffmpegPath = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);

	@Test
	void test() throws IOException {
//		URL resource = getClass().getClassLoader().getResource(VIDEO);
//		File vedioFile = new File(resource.getPath());
//		File audioFile = new File(vedioFile.getPath().replace(".gif", ".mp4"));

		FFmpegBuilder builder = new FFmpegBuilder()
				.overrideOutputFiles(true)
//				.addExtraArgs("-ac","2")
//				.addExtraArgs("-f","MOV")
//				.setInput(vedioFile.getPath())
				.setInput("D:/test/test.MOV")
				.addOutput("D:/test/test.wav")
				.done();

		FFmpegExecutor executor = new FFmpegExecutor(new FFmpeg(ffmpegPath));

		try {
			executor.createJob(builder).run();
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}

}
