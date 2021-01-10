package org.loveyoupeng.actor;

public interface Actor extends AutoCloseable, Identifiable {
  void start(final Context context);

  void stop(final Context context);

  void receive(final Event event, final Context context);
}
