package com.mycompany.finalsubmission;

/**
 * Represents an attendance slip submitted due to a transport delay.
 * Extends AbstractSlip and adds the delayMinutes field.
 */
public class TransportSlip extends AbstractSlip {

    private final int delayMinutes;

    public TransportSlip(String slipId, String studentId, String courseCode,
                         String reason, int delayMinutes) {
        super(slipId, studentId, courseCode, reason);
        if (delayMinutes < 0) throw new IllegalArgumentException("Delay minutes cannot be negative.");
        this.delayMinutes = delayMinutes;
    }

    public int getDelayMinutes() { return delayMinutes; }

    @Override
    public String toFileRecord() {
        return "Transport|" + baseRecord() + "|" + delayMinutes;
    }

    @Override
    public String toString() {
        return "Transport Slip|" +
               "Slip ID:" + slipId + "|" +
               "Student ID:" + studentId + "|" +
               "Course Code:" + courseCode + "|" +
               "Reason:" + reason + "|" +
               "Status:" + status + "|" +
               "Minutes delayed:" + delayMinutes;
    }
}
