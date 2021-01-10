package org.loveyoupeng.actor;

public interface Context {
  void sendTo(final Identity identity, final Event event);

  Domain domain();
}
