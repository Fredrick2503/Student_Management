# PHASE 0 – PROBLEM DEFINITION & REQUIREMENT ANALYSIS

---

# 1. Objective of Phase 0

Phase 0 focused on defining the system before writing implementation code. The goal was to convert an abstract idea ("Student Management System") into a formally structured engineering problem.

---

# 2. Functional Requirement Breakdown

Each feature was decomposed into system responsibilities:

## Add Student

* Validate input fields
* Ensure uniqueness
* Persist to database

## Update Student

* Verify existence
* Apply modifications
* Maintain identity integrity

## Delete Student

* Verify existence
* Remove from persistent storage

## Retrieve Student

* Query by unique identifier
* Map database row to object

## List Students

* Fetch all records
* Convert to collection of domain objects

---

# 3. Non-Functional Requirements

* Maintainability → Enforced through layered architecture
* Scalability → Loose coupling
* Reliability → Transaction safety
* Debuggability → Logging + structured error handling

---

# 4. Theory Applied

## Requirements Engineering

System defined before implementation to reduce ambiguity.

## Separation of Concerns

Responsibilities divided across architectural layers.

## Risk Identification

Potential failure points identified early.

---

# 5. Lessons Learned

* Clear requirement articulation prevents architectural drift.
* Functional and non-functional requirements must both be documented.
* Every future design decision maps back to these definitions.
