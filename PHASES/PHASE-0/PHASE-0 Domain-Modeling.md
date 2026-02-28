# PHASE 0 – DOMAIN MODELING & STUDENT ENTITY DESIGN

---

# 1. Identifying the Core Domain Entity

The system revolves around a single aggregate root: Student.

An entity is defined as an object with:

* Unique identity
* Lifecycle
* Mutable state

Student satisfies all three conditions.

---

# 2. Attribute Design

Proposed Fields:

* id (unique identifier)
* name
* email
* department

Design Decision:
ID must be immutable once assigned to preserve identity integrity.

---

# 3. Equality Contract Design

Equality must rely on identity (id).

Rules Applied:

* Override equals()
* Override hashCode()
* Maintain consistency

Why?
Hash-based collections require deterministic hashing behavior.

---

# 4. Theoretical Concepts Applied

## Entity vs Value Object

Student classified as Entity due to identity.

## Identity Stability Principle

Identity must not change during object lifecycle.

## Defensive Programming

Validation inside setters prevents invalid state.

---

# 5. Early Errors & Corrections

Error:
Mutable identity field.

Fix:
Removed setter for ID.

Error:
Missing hashCode override.

Fix:
Implemented consistent hashCode.

---

# 6. Learning Outcomes

* Identity defines object equality.
* Poor equality design leads to subtle logical bugs.
* Domain modeling impacts entire system structure.
