# PHASE 0 – EARLY ERRORS, WARNINGS & DEBUGGING ANALYSIS

---

# 1. NullPointerException (Uninitialized State)

Cause:
Object fields declared but not initialized.

Fix:
Initialize in constructor.

Lesson:
Object invariants must be established during creation.

---

# 2. Raw Type Warning

Cause:
Using raw collections without generics.

Fix:
Specify type parameters.

Lesson:
Compile-time type safety reduces runtime risk.

---

# 3. Duplicate Detection Failure

Cause:
Improper equals/hashCode implementation.

Fix:
Override both methods consistently.

Lesson:
Collection correctness depends on equality contract.

---

# 4. Overloaded Responsibility (God Class Risk)

Cause:
Service accumulating multiple responsibilities.

Fix:
Separated validation logic.

Lesson:
SRP must be enforced early.

---

# 5. Debugging Techniques Used

* Stack trace reading (top-to-root cause)
* Breakpoints
* Controlled test inputs
* Logging inspection

---

# 6. Meta Engineering Insight

Warnings are early structural alarms.
Ignoring them leads to compound failure later.

Phase 0 debugging refined architectural discipline.
