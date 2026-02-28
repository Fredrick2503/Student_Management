# COMPLETE ERROR & DEBUGGING JOURNAL

---

# 1. NullPointerException

Cause:
Uninitialized collections.

Resolution:
Initialized in constructor.

Lesson:
Always initialize object state.

---

# 2. Duplicate Detection Failure

Cause:
Improper equals/hashCode.

Resolution:
Override both methods consistently.

Lesson:
Equality contract is critical for hash-based collections.

---

# 3. Raw Type Warning

Cause:
Missing generics.

Resolution:
Added generic type parameters.

Lesson:
Compile-time safety prevents runtime bugs.

---

# 4. Transaction Rollback Failure

Cause:
Missing rollback in catch block.

Resolution:
Explicit rollback handling added.

Lesson:
Transactions must always handle failure cases.

---

# 5. Dependency Conflict (Jackson Version Mismatch)

Error:
NoSuchMethodError during Swagger initialization.

Cause:
Incompatible jackson-core and jackson-databind versions.

Resolution:
Aligned dependency versions in Maven.

Lesson:
Always inspect dependency tree for version conflicts.

---

# 6. Debugging Techniques Used

* Stack trace analysis
* Dependency tree inspection
* Logging with SLF4J
* Step-through debugging

---

# 7. Engineering Insight

Every error improved architectural understanding.
Warnings were treated as future bug indicators.
