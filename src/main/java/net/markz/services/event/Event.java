package net.markz.services.event;

public final class Event {
  private final Long startTime;
  private final Long endTime;
  private final String uniqueId;
  private final EventType type;

  private Event(Builder builder) {
    this.startTime = builder.startTime;
    this.endTime = builder.endTime;
    this.type = builder.type;
    this.uniqueId = builder.uniqueId;
  }

  public Long getStartTime() {
    return this.startTime;
  }

  public Long getEndTime() {
    return this.endTime;
  }

  public String getUniqueId() {
    return uniqueId;
  }

  public EventType getType() {
    return type;
  }

  public String toString() {
    return String.format(
        "Event: {%s} lasted {%s} milliseconds", this.type.toString(), this.startTime);
  }

  public static class Builder {
    private Long startTime;
    private Long endTime;
    private String uniqueId;
    private EventType type;

    public Builder withStartTime(Long startTime) {
      if (startTime == null) {
        throw new IllegalArgumentException("Event start time cannot be null");
      }
      this.startTime = startTime;
      return this;
    }

    public Builder withEndTime(Long endTime) {
      if (endTime == null) {
        throw new IllegalArgumentException("Event end time cannot be null");
      }
      this.endTime = endTime;
      return this;
    }

    public Builder withUniqueId(String uniqueId) {
      this.uniqueId = uniqueId;
      return this;
    }

    public Builder withType(EventType type) {
      if (type == null) {
        throw new IllegalArgumentException("Event type cannot be null");
      }
      this.type = type;
      return this;
    }

    public Event build() {
      return new Event(this);
    }
  }
}
