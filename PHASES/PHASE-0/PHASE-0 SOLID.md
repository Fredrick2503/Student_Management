# PHASE 0 – SOLID PRINCIPLES APPLICATION DOCUMENTATION

---

# 1. Single Responsibility Principle (SRP)

Each class must have one reason to change.

Applied As:

* Student → data holder only
* Repository → persistence logic only
* Service → business rules only

---

# 2. Open/Closed Principle (OCP)

Classes should be open for extension but closed for modification.

Applied As:

* Use of interfaces
* Service implementation replaceable

---

# 3. Liskov Substitution Principle (LSP)

Service implementations interchangeable without altering correctness.

---

# 4. Interface Segregation Principle (ISP)

Separate service and repository interfaces to avoid bloated contracts.

---

# 5. Dependency Inversion Principle (DIP)

High-level modules depend on abstractions, not concrete implementations.

---

# 6. Mistakes Observed

Early Design Risk:
Service directly referencing repository implementation.

Correction:
Injected repository via interface.

---

# 7. Learning Outcome

SOLID principles prevent structural decay.
They are architectural guardrails, not optional guidelines.
