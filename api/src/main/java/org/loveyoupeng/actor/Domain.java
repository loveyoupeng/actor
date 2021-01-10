package org.loveyoupeng.actor;

public interface Domain extends Identifiable{
  void addActor(final Actor actor);
  Actor getActor(final Identity id);
  void start();
  void sendEvent(final Event event);
}
