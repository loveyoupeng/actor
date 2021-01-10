package org.loveyoupeng.actor;

public interface Actor extends AutoCloseable, Identifiable {
  void receive(final Event event, final Context context);
}
