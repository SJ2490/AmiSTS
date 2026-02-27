package com.mycompany.finalsubmission;

/**
 * Abstract base class implementing common Slip behaviour.
 * Eliminates code duplication across MedicalSlip, TransportSlip, and OtherSlip.
 * Each subclass only needs to handle its own unique field.
 */
public abstract class AbstractSlip implements Slip {

    protected final String slipId;
    protected final String studentId;
    protected final String courseCode;
    protected final String reason;
    protected SlipStatus status;

    protected AbstractSlip(String slipId, String studentId, String courseCode, String reason) {
        if (slipId == null || slipId.isEmpty() ||
            studentId == null || studentId.isEmpty() ||
            courseCode == null || courseCode.isEmpty()) {
            throw new IllegalArgumentException("Slip ID, Student ID, and Course Code cannot be empty.");
        }
        this.slipId    = slipId;
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.reason    = reason != null ? reason : "";
        this.status    = SlipStatus.PENDING;
    }

    @Override public String getSlipId()     { return slipId; }
    @Override public String getStudentId()  { return studentId; }
    @Override public String getCourseCode() { return courseCode; }
    @Override public String getReason()     { return reason; }
    @Override public SlipStatus getStatus() { return status; }

    /**
     * Updates slip status.
     * Once APPROVED or REJECTED, the status cannot be reset back to PENDING.
     */
    @Override
    public void setStatus(SlipStatus newStatus) {
        if (newStatus == null) return;
        if (status != SlipStatus.PENDING && newStatus == SlipStatus.PENDING) {
            System.out.println("Warning: Cannot reset status to PENDING once it is " + status + ".");
            return;
        }
        this.status = newStatus;
    }

    /** Common pipe-delimited prefix shared by all slip types. */
    protected String baseRecord() {
        return slipId + "|" + studentId + "|" + courseCode + "|" + reason + "|" + status;
    }
}
