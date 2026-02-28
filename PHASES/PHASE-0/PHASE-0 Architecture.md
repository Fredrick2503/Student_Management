# PHASE 0 – ARCHITECTURAL DESIGN DECISION DOCUMENT

---

# 1. Architectural Style Selection

Selected Architecture: Layered (N-Tier Architecture)

Presentation → Service → Repository → Database

---

# 2. Why Layered Architecture?

## 2.1 Separation of Responsibilities

Each layer handles one domain of responsibility.

## 2.2 Dependency Direction

Upper layers depend only on abstractions of lower layers.

## 2.3 Replaceability

Database engine or UI can change without altering business logic.

---

# 3. Architectural Constraints

* No cross-layer shortcuts
* No business logic in repository
* No SQL logic in service

---

# 4. Theoretical Foundations

## Layered Architecture Theory

Promotes maintainability by isolating concerns.

## Clean Architecture Influence

Inner layers must not depend on outer layers.

## Modularity Principle

System decomposed into independently manageable units.

---

# 5. Risks Considered

* Tight coupling risk
* Circular dependencies
* Transaction leakage across layers

---

# 6. Learning Outcomes

* Architecture is a constraint system.
* Proper layering reduces cognitive load.
* Clear dependency flow simplifies debugging.
