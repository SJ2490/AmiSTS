package com.mycompany.finalsubmission;

/**
 * Interface defining the common behaviours for all attendance slip types.
 */
public interface Slip {
    String getSlipId();
    String getStudentId();
    String getCourseCode();
    String getReason();
    SlipStatus getStatus();
    void setStatus(SlipStatus newStatus);
    String toFileRecord();
}
