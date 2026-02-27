package com.mycompany.finalsubmission;

/**
 * Represents an attendance slip for reasons outside medical or transport categories.
 * Extends AbstractSlip and adds the extraDetails field.
 */
public class OtherSlip extends AbstractSlip {

    private final String extraDetails;

    public OtherSlip(String slipId, String studentId, String courseCode,
                     String reason, String extraDetails) {
        super(slipId, studentId, courseCode, reason);
        this.extraDetails = extraDetails != null ? extraDetails : "";
    }

    public String getExtraDetails() { return extraDetails; }

    @Override
    public String toFileRecord() {
        return "Other|" + baseRecord() + "|" + extraDetails;
    }

    @Override
    public String toString() {
        return "Other Slip|" +
               "Slip ID:" + slipId + "|" +
               "Student ID:" + studentId + "|" +
               "Course Code:" + courseCode + "|" +
               "Reason:" + reason + "|" +
               "Status:" + status + "|" +
               "Extra details:" + extraDetails;
    }
}
