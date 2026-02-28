# OBJECT-ORIENTED DESIGN DOCUMENTATION

---

# 1. Encapsulation

* All entity fields declared private
* Controlled access through getters/setters
* Validation logic prevents invalid state

---

# 2. Abstraction

* Service interface hides implementation
* Repository interface hides DB operations

---

# 3. Polymorphism

* Service referenced via interface
* Allows implementation replacement

---

# 4. Inheritance

* Custom exceptions extend RuntimeException

---

# 5. Equality Contract

* equals() overridden
* hashCode() overridden
* Based on unique identifier

---

# 6. Cohesion & Coupling

High cohesion within classes.
Low coupling between layers.

---

# 7. Design Lessons

* Responsibility isolation improves maintainability
* Equality logic impacts correctness
* Interface-driven design improves scalability

This document captures theoretical and applied OOP discipline used in the system.
