# Design Task: User & Child Registration

**Format:** Whiteboard / draft. Focus on the design, not on production-ready code.

## Goal

Design the **REST API** and **data model** for a user registration system that
supports linking parents and children, and later unlinking when a child becomes
an adult.

You are not expected to implement anything. We want to see how you reason about
resources, relationships, endpoints, and the underlying data model.

---

## Business Requirements

1. **Register a user.**
   A person can register as a user of the system.

2. **Register a child (optional) during or after registration.**
   When registering, a user may optionally also register their child.

3. **Link an already-registered child.**
   When a spouse (a second parent) registers, they should be able to link a
   child who is **already registered** in the system, rather than creating a
   duplicate.

4. **Unlink when the child becomes an adult.**
   When a child reaches adulthood, the (now adult) child should be able to
   **unlink themselves** from a parent.

---

## What to Deliver

1. **REST API design**
   - Resources and URI structure.
   - HTTP methods and status codes for the key operations.
   - Request/response payload shapes (rough JSON is fine).
   - How you model the operations: register user, register child, link existing
     child to a parent, unlink child from a parent.

2. **Data model**
   - Entities, key attributes, and relationships (cardinality).
   - How the parent–child relationship is stored.
   - Any constraints or keys that enforce the rules above.
