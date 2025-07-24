# 🍽️ Restaurant Review API

This is the backend API for a full-stack restaurant review app, built with **Spring Boot** and designed to support user authentication, allergy-aware reviews, and restaurant metadata. It powers a React frontend and provides RESTful endpoints to manage users, reviews, and restaurant data.

---

## 🌐 Overview

This REST API allows users to:

- 🔐 Register and authenticate
- 🧾 Submit and manage restaurant reviews (with allergy-specific scoring)
- 🍴 Browse restaurants by zip code or genre
- ✅ Support an approval system for both user accounts and reviews

---

## ⚙️ Tech Stack

- **Java 17+**
- **Spring Boot**
- **Hibernate / JPA** (ORM)
- **PostgreSQL** (pluggable)
- **JWT-based Authentication**
- **Deployed to:** Render

---

## 📦 Data Models & ER Diagram

### Entity-Relationship Diagram
![ER-diagram](https://i.imgur.com/Rm6svDY.png)
