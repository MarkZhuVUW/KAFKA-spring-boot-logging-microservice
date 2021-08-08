package net.markz.services.event;

import java.util.Objects;

/**
 * The Event class defines an event that happens in the application. The fields define some
 * important attributes of an event.
 */
public final class Event {
  /** The start time of an event. */
  private final long startTime;
  /** The end time of an event. May not exist for certain event types. */
  private final Long endTime;
  /** The id that uniquely identifies the event. */
  private final String uniqueId;
  /** The type of the event. */
  private final EventType type;
  /**
   * The value of the event. Could be anything from integer, string, list as long as it makes sense
   * and helps add information about the event.
   */
  private final Object value;

  // Self documenting code here but for the sake of showcasing, private constructor forces users to
  // create Event objects through the build method in the Builder class in case in the future the
  // Event class becomes complicated and we need only partial data to create an Event object.
  private Event(Builder builder) {
    this.startTime = builder.startTime;
    this.endTime = builder.endTime;
    this.type = builder.type;
    this.uniqueId = builder.uniqueId;
    this.value = builder.value;
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

  public Object getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Event event = (Event) o;
    return getUniqueId().equals(event.getUniqueId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUniqueId());
  }

  /** Users can only create an event object through the build method. */
  public static class Builder {
    private long startTime;
    private Long endTime;
    private String uniqueId;
    private EventType type;
    private Object value;

    public Builder withStartTime(long startTime) {
      this.startTime = startTime;
      return this;
    }

    public Builder withEndTime(Long endTime) {
      this.endTime = endTime;
      return this;
    }

    public Builder withUniqueId(String uniqueId) {
      if (uniqueId == null) {
        throw new IllegalArgumentException("Event type cannot be null");
      }
      this.uniqueId = uniqueId;
      return this;
    }

    public Builder withValue(Object value) {
      this.value = value;
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
      if (uniqueId == null || type == null)
        throw new IllegalArgumentException(
            String.format(
                "An event must have uniqueId and type. Received: {%s} {%s}", uniqueId, type));
      return new Event(this);
    }
  }
}
