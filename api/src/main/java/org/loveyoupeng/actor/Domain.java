package org.loveyoupeng.actor;

public interface Domain {
  void addActor(final Actor actor);

  void send(final Identifier id, @SuppressWarnings("rawtypes") final Payload stringMessage);
}
