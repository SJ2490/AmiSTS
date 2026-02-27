# Amity Attendance Slip Tracker System (AmiSTS)

A console-based Java application built as part of **CSCI 205 – Object Oriented Programming using Java** at **Amity University Dubai**.

---

## Project Overview

AmiSTS allows students to submit and manage attendance slips for missed classes. It supports three slip types — **Medical**, **Transport**, and **Other** — and demonstrates core OOP principles through a clean, interface-driven design.

---

## OOP Concepts Demonstrated

| Concept | How It's Applied |
|---|---|
| **Abstraction** | `Slip` interface defines common behaviour for all slip types |
| **Encapsulation** | All fields are `private`; accessed via getters |
| **Polymorphism** | `SlipService` stores and processes all slip types via `List<Slip>` |
| **Virtual Functions** | `toString()` and `toFileRecord()` resolve at runtime per actual type |
| **Inheritance** | `AbstractSlip` eliminates code duplication across the three slip classes |
| **Exception Handling** | Custom `InvalidSlipFormatException`; try-with-resources for file I/O |

---

## Project Structure

```
src/com/mycompany/finalsubmission/
│
├── Slip.java                       # Interface defining common slip behaviours
├── AbstractSlip.java               # Abstract base class (reduces duplication)
├── MedicalSlip.java                # Slip for medical reasons (+ clinicName)
├── TransportSlip.java              # Slip for transport delays (+ delayMinutes)
├── OtherSlip.java                  # Slip for other reasons (+ extraDetails)
├── SlipStatus.java                 # Enum: PENDING, APPROVED, REJECTED
├── SlipService.java                # Central service: add, find, print, export, import
├── InvalidSlipFormatException.java # Custom checked exception
└── FinalSubmission.java            # Main class / entry point
```

---

## Key Features

- **Add slips** of any type through a unified `addSlip(Slip slip)` method
- **Find slip by ID** using `findById(String slipId)`
- **Print all slips** — each type renders its own formatted output via `toString()`
- **Export to file** in pipe-delimited format: `TYPE|slipId|studentId|courseCode|reason|status|customField`
- **Import from file** — reconstructs correct slip type and restores saved status
- **Status protection** — once APPROVED or REJECTED, a slip cannot be reset to PENDING

---

## How to Run

```bash
# Compile
javac -d out src/com/mycompany/finalsubmission/*.java

# Run
java -cp out com.mycompany.finalsubmission.FinalSubmission
```

---

## Sample Output

```
========== Slips Before Export ==========
Medical Slip|Slip ID:S01|Student ID:AUD31013|Course Code:CSCI200|Reason:Doctor recommended rest due to fever|Status:PENDING|Clinic Name:Aster Hospital
Transport Slip|Slip ID:S02|Student ID:AUD32433|Course Code:CSCI205|Reason:Amity Bus reached late due to fog and traffic|Status:APPROVED|Minutes delayed:25
...
```

---

## Team

| Name | Contribution |
|---|---|
| Syed Jaabir | Core Coding Implementation and Testing |
| Nimah Nazmin Jaleel | Design, Implementation, Report Writing |
| Bushra Zehra Mehdi | Technical Support |
| Saqer Alsuwaidi | Technical Support |

---

## Improvements Over Original Submission

- Added `AbstractSlip` base class to remove repeated code across all three slip classes
- Fixed `importFromFile()` to correctly **restore slip status** (APPROVED/REJECTED) from file
- Added input validation for `delayMinutes` (cannot be negative)
- Added line number reporting in exception messages for easier debugging
- Used `final` fields where values should never change after construction
