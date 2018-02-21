package main;

/**
 * Created by Kyle on 5/17/2017.
 */
public class BeginRunnerDraftListener {
    private static boolean wasABeginRunnerDraftEventJustTriggered = false;

    public static void triggerABeginRunnerDraftEvent() {
        wasABeginRunnerDraftEventJustTriggered = true;
    }

    public static void checkForBeginRunnerDraftEvent() {
        if (wasABeginRunnerDraftEventJustTriggered) {
            wasABeginRunnerDraftEventJustTriggered = false;
            Draft.beginRunnerDraft();
        }
    }
}
