package org.loveyoupeng.actor;

public interface Event {
  Identity target();

  Object payload();
}
