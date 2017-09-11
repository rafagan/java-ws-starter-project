package vetorlog.util;

import io.sentry.SentryClient;
import io.sentry.SentryClientFactory;
import io.sentry.event.Event;
import io.sentry.event.EventBuilder;

public class SentryLogger {
    private SentryClient sentryClient;

    private SentryClient getSentryClient() {
        if(sentryClient == null)
            sentryClient = SentryClientFactory.sentryClient();
        return sentryClient;
    }

    public SentryLogger addExtra(String key, Object value) {
        getSentryClient().addExtra(key, value);
        return this;
    }

    public SentryLogger addTag(String key, String value) {
        getSentryClient().addTag(key, value);
        return this;
    }

    public <T> void debug(String text, Class<T> eventClass) {
        EventBuilder eventBuilder = new EventBuilder()
                .withMessage(text)
                .withLevel(Event.Level.WARNING)
                .withLogger(eventClass.getName());

        getSentryClient().sendEvent(eventBuilder);
    }

    public <T> void info(String text, Class<T> eventClass) {
        EventBuilder eventBuilder = new EventBuilder()
                .withMessage(text)
                .withLevel(Event.Level.INFO)
                .withLogger(eventClass.getName());

        getSentryClient().sendEvent(eventBuilder);
    }

    public <T> void warn(String text, Class<T> eventClass) {
        EventBuilder eventBuilder = new EventBuilder()
                .withMessage(text)
                .withLevel(Event.Level.WARNING)
                .withLogger(eventClass.getName());

        getSentryClient().sendEvent(eventBuilder);
    }

    public <T> void error(String text, Class<T> eventClass) {
        EventBuilder eventBuilder = new EventBuilder()
                .withMessage(text)
                .withLevel(Event.Level.WARNING)
                .withLogger(eventClass.getName());

        getSentryClient().sendEvent(eventBuilder);
    }

    public <T> void fatal(String text, Class<T> eventClass) {
        EventBuilder eventBuilder = new EventBuilder()
                .withMessage(text)
                .withLevel(Event.Level.WARNING)
                .withLogger(eventClass.getName());

        getSentryClient().sendEvent(eventBuilder);
    }

    public void exception(Throwable exception) {
        getSentryClient().sendException(exception);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        sentryClient.clearContext();
        sentryClient.closeConnection();
    }
}
