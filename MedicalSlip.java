package com.mycompany.finalsubmission;

/**
 * Represents an attendance slip submitted for medical reasons.
 * Extends AbstractSlip and adds the clinicName field.
 */
public class MedicalSlip extends AbstractSlip {

    private final String clinicName;

    public MedicalSlip(String slipId, String studentId, String courseCode,
                       String reason, String clinicName) {
        super(slipId, studentId, courseCode, reason);
        this.clinicName = clinicName != null ? clinicName : "";
    }

    public String getClinicName() { return clinicName; }

    @Override
    public String toFileRecord() {
        return "Medical|" + baseRecord() + "|" + clinicName;
    }

    @Override
    public String toString() {
        return "Medical Slip|" +
               "Slip ID:" + slipId + "|" +
               "Student ID:" + studentId + "|" +
               "Course Code:" + courseCode + "|" +
               "Reason:" + reason + "|" +
               "Status:" + status + "|" +
               "Clinic Name:" + clinicName;
    }
}
