package net.markz.services.fileread;

public interface FileReadingService {
  Configuration readLineIntoConfig(String lineStr);

  long calculateETA(Configuration configuration);
}
