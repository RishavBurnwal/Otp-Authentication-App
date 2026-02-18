# 🔐 OTP Authentication App

A minimal full-stack authentication system demonstrating a secure OTP-based login flow using Spring Boot and React. This project focuses on authentication logic, API design, and controlled access handling rather than UI complexity or database integration.

---

## 🚀 Overview

- User submits email (or phone number)
- Server generates a One-Time Password (OTP)
- User verifies the OTP
- Maximum 3 incorrect attempts allowed
- Automatic 10-minute temporary block after limit
- On successful verification, a token is issued
- Authenticated user can access protected endpoint

---

## 🛠 Tech Stack

**Backend:** Java, Spring Boot, REST APIs  
**Frontend:** React (Vite), React Router, Plain CSS  

---

## ⚙️ Implementation Details

- Clean separation between Controller and Service layers  
- OTP, retry attempts, block timing, and tokens stored in memory  
- OTP delivery mocked by printing in backend console  
- No database used to keep setup lightweight and simple  
- Minimal UI to emphasize backend authentication logic  

---

## ✨ Features

- OTP generation and verification  
- 3-attempt security restriction  
- 10-minute temporary blocking mechanism  
- Token-based authentication  
- Protected API endpoint (`/auth/me`)  
- Clean and simple user interface  

---

## 📡 API Endpoints

- `POST /auth/request-otp`  
- `POST /auth/verify-otp`  
- `GET /auth/me`  

---

## ▶️ How to Run

**Backend**
1. Open the backend project in your IDE  
2. Run the Spring Boot application  
3. Server starts at: `http://localhost:8080`  

**Frontend**
1. Navigate to the frontend directory  
2. Run `npm install`  
3. Run `npm run dev`  
4. Application runs at: `http://localhost:5173`  

---

## 📌 Assumptions

- OTP becomes invalid when a new OTP is generated  
- Token is a mock string stored in memory  
- No advanced rate limiting beyond OTP attempt restriction  
- Users are auto-created on first login attempt  

---

## 👨‍💻 Author

**Rishav Burnwal**
