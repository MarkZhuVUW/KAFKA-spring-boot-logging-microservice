package net.markz;

import net.markz.services.event.EventService;
import net.markz.services.event.EventServiceImpl;
import net.markz.services.event.LogEventListener;
import net.markz.services.fileread.FileReadingService;
import net.markz.services.fileread.FileReadingServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
  private static final Logger LOGGER = LogManager.getLogger(Main.class);

  public static void main(String[] args) {
    //    EventService eventService = new EventServiceImpl();
    FileReadingService fileReadingService = new FileReadingServiceImpl();
    try (Stream<String> stream = Files.lines(Paths.get("./src/test/scripts/numbers.txt"))) {
      stream
          .map(fileReadingService::readLineIntoConfig)
          .map(fileReadingService::calculateETA)
          .forEach(eta -> LOGGER.error(String.format("ETA: {%s} ", eta)));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
