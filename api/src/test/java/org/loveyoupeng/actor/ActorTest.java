package org.loveyoupeng.actor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;

public class ActorTest {
  @Test
  public void test_ping_pong() throws InterruptedException {
    final Identifier pongId = new TestIdentifier();
    final AtomicInteger counter = new AtomicInteger(0);
    final CountDownLatch latch = new CountDownLatch(1);
    final Actor ping = new Actor() {
      private final Identifier id = new TestIdentifier();
     @Override
      public void start(final Context context) {
        context.send(pongId, null);
      }

      @Override
      public void receive(final Event event, final Context context) {
        if(counter.incrementAndGet() <= 3)
          context.send(pongId, null);
      }

      @Override
      public Identifier id() {
        return id;
      }
    };

    final Actor pong = new Actor() {
      @Override
      public void receive(final Event event, final Context context) {
        context.send(event.source(), event.payload());
      }

      @Override
      public Identifier id() {
        return pongId;
      }
    };

    final Domain domain = mock(Domain.class);
    domain.addActor(pong);
    domain.addActor(ping);
    latch.await();
    assertEquals(4, counter.get());
  }

  interface MessageHandler extends Dispatcher<MessageHandler> {
    void on(final StringMessage msg);
    void on(final IntMessage msg);
  }

  private static abstract class Message<M extends Payload<MessageHandler, M>> implements Payload<MessageHandler, M> {

  }

  private static final class StringMessage extends Message<StringMessage> {

    @Override
    public void dispatch(final MessageHandler dispatcher) {
      dispatcher.on(this);
    }
  }

  private static final class IntMessage extends Message<IntMessage> {

    @Override
    public void dispatch(final MessageHandler dispatcher) {
      dispatcher.on(this);
    }
  }

  @Test
  public void test_typed_actor() {
    final Context testContext = mock(Context.class);
    final Identifier id = new TestIdentifier();
    final StringMessage stringMessage = new StringMessage();
    final IntMessage intMessage = new IntMessage();
    final TypedActor actor = new TypedActor() {
      @Override
      public Identifier id() {
        return id;
      }

      public void on(final StringMessage message, final Context context) {
        assertSame(stringMessage, message);
        assertSame(testContext, context);
      }

      public void on(final IntMessage message, final Context context) {
        assertSame(intMessage, message);
        assertSame(testContext, context);
      }
    };
    final Domain domain = mock(Domain.class);
    domain.addActor(actor);
    domain.send(id, stringMessage);
    domain.send(id, intMessage);
  }


}
