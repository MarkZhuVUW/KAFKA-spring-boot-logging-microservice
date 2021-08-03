package net.markz.services;

public final class LogEvent {
  private final long startTime;
  private final long endTime;
  private final String desc;

  private LogEvent(final long startTime, String desc, final long endTime) {
    this.startTime = startTime;
    this.desc = desc;
    this.endTime = endTime;
  }

  public static class Builder {
    private long startTime;
    private String desc;
    private long endTime;

    public Builder setStartTime(long startTime) {
      this.startTime = startTime;
      return this;
    }

    public Builder setDesc(String desc) {
      this.desc = desc;
      return this;
    }

    public Builder setEndTime(long endTime) {
      this.endTime = endTime;
      return this;
    }

    public LogEvent build() {
      return new LogEvent(startTime, desc, endTime);
    }
  }
}
