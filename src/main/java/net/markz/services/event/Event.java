package net.markz.services.event;

import java.util.Objects;

public final class Event {
  private final long startTime;
  private final long endTime;
  private final String uniqueId;
  private final EventType type;

  private Event(Builder builder) {
    this.startTime = builder.startTime;
    this.endTime = builder.endTime;
    this.type = builder.type;
    this.uniqueId = builder.uniqueId;
  }

  public long getStartTime() {
    return this.startTime;
  }

  public long getEndTime() {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    var event = (Event) o;
    // I use getters to get the fields here to in case the Event class
    return getStartTime() == event.getStartTime()
        && getEndTime() == event.getEndTime()
        && getUniqueId().equals(event.getUniqueId())
        && getType() == event.getType();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getStartTime(), getEndTime(), getUniqueId(), getType());
  }

  public static class Builder {
    private long startTime;
    private long endTime;
    private String uniqueId;
    private EventType type;

    public Builder withStartTime(long startTime) {
      this.startTime = startTime;
      return this;
    }

    public Builder withEndTime(long endTime) {
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
