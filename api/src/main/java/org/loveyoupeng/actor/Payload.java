package org.loveyoupeng.actor;

public interface Payload<D extends Dispatcher<D>, E extends Payload<D, E>> {
  void dispatch(final D dispatcher);
}
