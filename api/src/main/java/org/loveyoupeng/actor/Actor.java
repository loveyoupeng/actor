package org.loveyoupeng.actor;

public interface Actor extends Identifiable, Cloneable{
  void receive(final Event event, final Context context);
  void start(final Context context);
}
