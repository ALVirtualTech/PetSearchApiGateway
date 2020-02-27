package ru.airlightvt.onlinerecognition.common.saga;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Saga {
    // The name/type of the saga, e.g.: "ReviewCheckingSaga". There will exist many
    // "instances" of a saga. For example, every review will start it's own new saga.
    private String name;

    // Unique key of the saga, e.g. the ID of the review which is checked.
    private String key;

    // A reference to a service which is called by the saga in case an event occurs.
    // In my example, it's the name of a Spring Bean ("reviewCheckingService").
    private String handler;

    // The names of all events which make up the saga, e.g.:
    // { "REVIEW_SUBMITTED_EVENT", "REVIEW_IN_EXAMINATION_EVENT", "REVIEW_APPROVED_EVENT }
    private List<String> requiredEvents = new ArrayList<>();

    // The name of the rollback event which cancels the saga. Note that I've just used a
    // single event, but it would be perfectly fine to use a list of events here. Just as
    // you need.
    private String rejectionEvent;

    // All events which already have occurred.
    private final List<Object> events = new ArrayList<>();
}
