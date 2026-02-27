package com.mycompany.finalsubmission;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Central service class for managing attendance slips.
 * Uses the Slip interface reference (polymorphism) to handle all slip types uniformly.
 */
public class SlipService {

    private final List<Slip> slips = new ArrayList<>();

    /** Adds any slip type to the list via the common Slip interface. */
    public void addSlip(Slip slip) {
        if (slip == null) throw new IllegalArgumentException("Cannot add a null slip.");
        slips.add(slip);
    }

    /** Searches for a slip by its ID. Returns null if not found. */
    public Slip findById(String slipId) {
        if (slipId == null || slipId.isEmpty()) return null;
        for (Slip s : slips) {
            if (s.getSlipId().equals(slipId)) return s;
        }
        return null;
    }

    /** Prints all slips. Demonstrates virtual function behaviour via toString(). */
    public void printAll() {
        if (slips.isEmpty()) {
            System.out.println("No slips available.");
            return;
        }
        for (Slip s : slips) {
            System.out.println(s);
        }
    }

    /** Returns the total number of slips stored. */
    public int getCount() {
        return slips.size();
    }

    /**
     * Exports all slips to a text file using the pipe-delimited toFileRecord() format.
     * Uses try-with-resources to ensure the writer is always closed.
     */
    public void exportToFile(String path) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for (Slip s : slips) {
                pw.println(s.toFileRecord());
            }
            System.out.println("Export successful: " + path);
        } catch (IOException e) {
            System.out.println("File writing unsuccessful: " + e.getMessage());
        }
    }

    /**
     * Imports slips from a pipe-delimited text file.
     * Reconstructs the correct slip type based on the TYPE field.
     * Also restores the saved status for each slip.
     * Throws InvalidSlipFormatException for malformed records or unknown types.
     */
    public void importFromFile(String path) throws InvalidSlipFormatException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;

                String[] p = line.split("\\|");
                if (p.length != 7) {
                    throw new InvalidSlipFormatException(
                        "Invalid format on line " + lineNumber + ": expected 7 fields, got " + p.length);
                }

                Slip s;
                switch (p[0]) {
                    case "Medical":
                        s = new MedicalSlip(p[1], p[2], p[3], p[4], p[6]);
                        break;
                    case "Transport":
                        try {
                            s = new TransportSlip(p[1], p[2], p[3], p[4], Integer.parseInt(p[6].trim()));
                        } catch (NumberFormatException e) {
                            throw new InvalidSlipFormatException(
                                "Invalid delay minutes on line " + lineNumber + ": " + p[6]);
                        }
                        break;
                    case "Other":
                        s = new OtherSlip(p[1], p[2], p[3], p[4], p[6]);
                        break;
                    default:
                        throw new InvalidSlipFormatException(
                            "Unknown slip type on line " + lineNumber + ": " + p[0]);
                }

                // Restore the saved status (e.g. APPROVED or REJECTED)
                try {
                    SlipStatus savedStatus = SlipStatus.valueOf(p[5].trim());
                    s.setStatus(savedStatus);
                } catch (IllegalArgumentException e) {
                    throw new InvalidSlipFormatException(
                        "Invalid status on line " + lineNumber + ": " + p[5]);
                }

                slips.add(s);
            }
            System.out.println("Import successful: " + lineNumber + " line(s) processed from " + path);
        } catch (IOException e) {
            System.out.println("File reading unsuccessful: " + e.getMessage());
        }
    }
}
