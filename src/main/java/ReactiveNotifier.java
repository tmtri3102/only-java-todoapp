package main.java;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class ReactiveNotifier {
    private final SubmissionPublisher<Task> publisher = new SubmissionPublisher<>();

    public void subscribe(Flow.Subscriber<Task> subscriber) {
        publisher.subscribe(subscriber);
    }

    public void notify(Task task) {
        publisher.submit(task);
    }
}
