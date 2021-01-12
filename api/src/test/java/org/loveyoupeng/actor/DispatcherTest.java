package org.loveyoupeng.actor;

import static org.junit.Assert.assertSame;

import org.junit.Test;

public class DispatcherTest {

  @SuppressWarnings("rawtypes")
  @Test
  public void test() {
    final Payload aPayload = new APayload();
    final Payload<TestDispatcher, ?> bPayload = new BPayload();
    final TestDispatcher dispatcher = new TestDispatcher() {

      @Override
      public void setContext(final Context context) {

      }

      @Override
      public void on(final APayload event) {
        assertSame(aPayload, event);
      }

      @Override
      public void on(final BPayload event) {
        assertSame(bPayload, event);
      }
    };

    final Dispatcher rawDispatcher = dispatcher;

    aPayload.dispatch(rawDispatcher);
    bPayload.dispatch(dispatcher);
  }

  interface TestDispatcher extends Dispatcher<TestDispatcher> {

    void on(final APayload event);

    void on(final BPayload event);
  }

  static abstract class TestPayload<T extends Payload<TestDispatcher, T>> implements
      Payload<TestDispatcher, T> {

  }

  static final class APayload extends TestPayload<APayload> {

    @Override
    public void dispatch(final TestDispatcher dispatcher) {
      dispatcher.on(this);
    }
  }

  static final class BPayload extends TestPayload<BPayload> {

    @Override
    public void dispatch(final TestDispatcher dispatcher) {
      dispatcher.on(this);
    }
  }
}
