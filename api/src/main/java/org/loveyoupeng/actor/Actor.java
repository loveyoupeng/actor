package org.loveyoupeng.actor;

public interface Actor extends AutoCloseable, Identifiable {

  default void start(final Context context) {
  }

  default void stop(final Context context) {
  }

  @Override
  default void close() {
  }

  void receive(final Event event, final Context context);
}
