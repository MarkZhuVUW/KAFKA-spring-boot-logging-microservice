package net.markz.services.event;

import java.util.Objects;

/**
 * The {@link Event} class defines an event that happens in the application. The fields define some
 * important attributes of an event.
 */
public final class Event {
  /** The start time of an event. */
  private final long startTime;
  /** The end time of an event. */
  private final long endTime;
  /** The id that uniquely identifies the event. */
  private final String uniqueId;
  /** The type of the event. */
  private final EventType type;

  // Self documenting code here but for the sake of showcasing, private constructor forces users to
  // create Event objects through the build method in case in the future the Event class becomes
  // complicated and we need only partial data to create an Event object.
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
    // The unique id is the only thing that differentiates event objects.
    return getUniqueId().equals(event.getUniqueId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUniqueId());
  }

  /** Users can only create an event object through the build method. */
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
