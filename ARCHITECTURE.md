# ARCHITECTURE DOCUMENTATION

---

# 1. Architectural Style

The system follows a layered (n-tier) architecture.

Layers:

1. Presentation Layer
2. Service Layer
3. Repository Layer
4. Database Layer

Dependency direction strictly flows downward.

---

# 2. Responsibility Segregation

Presentation Layer:

* Handles HTTP requests
* Converts JSON to objects

Service Layer:

* Applies business rules
* Validates input
* Manages transactions

Repository Layer:

* Executes SQL queries
* Maps ResultSet to domain objects

Database Layer:

* Stores persistent state

---

# 3. Design Patterns Used

Repository Pattern:
Abstracts persistence logic.

Dependency Injection:
Service depends on repository abstraction.

Layered Architecture Pattern:
Separation of concerns by responsibility.

---

# 4. Dependency Management

Maven manages dependencies.
Dependency conflicts were analyzed using dependency tree inspection.

---

# 5. Architectural Risks Identified

* Tight coupling risk
* Transaction mismanagement
* Version mismatch between libraries

Mitigation strategies were applied in each phase.

---

# 6. Architectural Learning

* Architecture defines maintainability
* Abstractions reduce ripple effects
* Proper layering improves debuggability

This architecture enforces discipline and clarity across the codebase.
