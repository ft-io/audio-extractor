package io.ft.audioextractor;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.job.FFmpegJob;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.libfreenect2.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.IOException;

@SpringBootTest
class AudioExtractorApplicationTests {

	private static final String VIDEO = "C:\\Users\\07601\\Desktop\\ffmpeg\\IMG_2722.MOV";

	@Test
	void test() throws IOException {

		FFmpeg ffmpeg = new FFmpeg("C:\\Users\\07601\\Desktop\\ffmpeg\\bin\\ffmpeg.exe");
		FFprobe fFprobe = new FFprobe("C:\\Users\\07601\\Desktop\\ffmpeg\\bin\\ffprobe.exe");

		FFmpegBuilder builder = new FFmpegBuilder()
				.overrideOutputFiles(true)
//				.addExtraArgs("-ac","2")
//				.addExtraArgs("-f","MOV")
				.setInput(VIDEO)
				.addOutput("C:\\Users\\07601\\Desktop\\ffmpeg\\result.mp3")
				.done();

		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, fFprobe);

		try {
			executor.createJob(builder).run();
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}

}
