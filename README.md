# Customer Onboarding & Workflow Automation Platform

An enterprise-style application that digitizes and automates customer onboarding using **Appian workflows**, a **Spring Boot backend**, and an **Oracle database**. This project simulates real-world onboarding systems used in banking, fintech, and large enterprises.

---

## 🚀 Project Overview

The platform enables organizations to replace manual onboarding processes with an automated, auditable, and role-driven workflow. Customer data is captured via Appian forms, validated and processed by Spring Boot REST APIs, and persisted in Oracle with full approval traceability.

---

## 🎯 Key Features

* End-to-end digital customer onboarding
* Role-based approval workflow (Ops → Manager → Compliance)
* Backend validations and business rules enforcement
* Real-time onboarding status tracking
* Audit trail for all approval actions
* Clean separation between workflow and business logic layers

---

## 🏗️ System Architecture

```
[ Appian (UI & Workflow) ]
           |
           | REST APIs
           v
[ Spring Boot Backend ]
           |
           | JPA / JDBC
           v
[ Oracle Database ]
```

---

## 🧰 Technology Stack

| Layer         | Technology                    |
| ------------- | ----------------------------- |
| Workflow & UI | Appian (SAIL, Process Models) |
| Backend       | Java, Spring Boot             |
| APIs          | REST (JSON)                   |
| Database      | Oracle                        |
| ORM           | Spring Data JPA               |
| API Testing   | Postman                       |
| Documentation | Swagger / OpenAPI             |

---

## 📌 Functional Scope

### Customer Onboarding

* Capture customer details (name, email, phone)
* Validate mandatory fields and data formats
* Prevent duplicate customer submissions

### Approval Workflow

* Multi-level approval process
* Role-based access control
* Approve / Reject with comments

### Status Tracking

* Lifecycle states:

  * NEW
  * IN_REVIEW
  * APPROVED
  * REJECTED
* Status visible in Appian dashboards

### Audit & Compliance

* Complete approval history
* Timestamped actions with role attribution

---

## 🗄️ Database Design

### CUSTOMER_ONBOARDING

* customer_id (PK)
* full_name
* email
* phone
* onboarding_status
* created_at
* updated_at

### APPROVAL_HISTORY

* approval_id (PK)
* customer_id (FK)
* role
* action (APPROVE / REJECT)
* comments
* action_timestamp

---

## 🔌 API Endpoints

### Submit Onboarding Request

```
POST /api/onboarding
```

### Get Onboarding Status

```
GET /api/onboarding/{id}
```

### Approve / Reject Request

```
POST /api/onboarding/{id}/action
```

APIs handle validation, business logic enforcement, status transitions, and audit logging.

---

## 🔄 Appian Workflow Design

### Appian Components Used

* SAIL Interfaces (Forms)
* Process Models (Workflow orchestration)
* Records (Customer & Approval data)
* Groups (Role-based access)
* REST Connected Systems

### Workflow Steps

1. Customer submission
2. Operations review
3. Manager approval
4. Compliance check
5. Final onboarding decision

---

## 🧪 Testing Strategy

* Unit testing for service layer
* API testing using Postman
* Appian workflow validation
* End-to-end onboarding scenario testing

---

## 📦 Deliverables

* Spring Boot backend source code
* Oracle database schema scripts
* Appian process models and interfaces
* API documentation (Swagger)
* Demo-ready onboarding workflow

---

## ✅ Success Criteria

* Seamless onboarding flow from submission to approval
* Accurate role-based access enforcement
* Real-time status synchronization
* Reliable audit trail for compliance

---

## 🔮 Future Enhancements

* JWT-based authentication and authorization
* Email notifications on approval/rejection
* External KYC provider integration
* Event-driven architecture using Kafka
* Dockerized deployment

---

## 👤 Author

**Shubham Pathare**
Software Engineer | Backend & Workflow Automation

---

⭐ If you find this project useful, feel free to star the repository and explore the implementation.

