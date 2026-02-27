package com.mycompany.finalsubmission;

/**
 * FinalSubmission - Entry point for the Amity Attendance Slip Tracker System (AmiSTS).
 * Demonstrates polymorphism, interface references, file export/import, and exception handling.
 *
 * @author Syed Jaabir, Bushra Zehra Mehdi, Nimah Nazmin Jaleel, Saqer Alsuwaidi
 * Course: CSCI 205 - Object Oriented Programming using Java
 * Amity University Dubai
 */
public class FinalSubmission {

    public static void main(String[] args) {

        SlipService services = new SlipService();

        // --- Create 5 mixed slip objects using the Slip interface reference ---
        Slip s1 = new MedicalSlip("S01", "AUD31013", "CSCI200",
                                   "Doctor recommended rest due to fever", "Aster Hospital");
        Slip s2 = new TransportSlip("S02", "AUD32433", "CSCI205",
                                    "Amity Bus reached late due to fog and traffic", 25);
        Slip s3 = new OtherSlip("S03", "AUD31001", "MATH120",
                                 "Family issue", "Emergency travel to home country");
        Slip s4 = new MedicalSlip("S04", "AUD29874", "PHYS105",
                                   "Broken limb and cannot walk", "Prime Qasais Clinic");
        Slip s5 = new TransportSlip("S05", "AUD27843", "CSCI215",
                                    "Car broke down in the middle of road", 45);

        services.addSlip(s1);
        services.addSlip(s2);
        services.addSlip(s3);
        services.addSlip(s4);
        services.addSlip(s5);

        // --- Demonstrate setStatus with PENDING reset prevention ---
        Slip found = services.findById("S02");
        if (found != null) {
            found.setStatus(SlipStatus.APPROVED);
            found.setStatus(SlipStatus.PENDING); // Should print warning and be ignored
        }

        System.out.println("\n========== Slips Before Export ==========");
        services.printAll();

        // --- Export slips to file ---
        String exportPath = "slips_export.txt";
        services.exportToFile(exportPath);

        // --- Import slips from a valid file ---
        SlipService importedServices = new SlipService();
        try {
            importedServices.importFromFile(exportPath);
        } catch (InvalidSlipFormatException e) {
            System.out.println("Custom Exception: " + e.getMessage());
        }

        System.out.println("\n========== Slips After Import ==========");
        importedServices.printAll();

        // --- Demonstrate exception handling with a bad file ---
        System.out.println("\n========== Testing Invalid File Handling ==========");
        SlipService badImport = new SlipService();
        try {
            badImport.importFromFile("nonexistent_file.txt");
        } catch (InvalidSlipFormatException e) {
            System.out.println("Custom Exception: " + e.getMessage());
        }
    }
}
