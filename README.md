
# FitSpace: FDC Booking Hub 🏀🏐
Welcome to the **FitSpace: FDC Booking Hub Repository**! This repository contains the complete code and resources for this Java console application. The project is designed for students and employees of Batangas State University - Alangilan Campus, allowing them to efficiently reserve gym facilities at the Fitness Development Center.

Explore this repository to learn more about how the system works and how I applied object-oriented programming concepts to create a functional and efficient booking system! 💻🏋

# 🏸 Summary of Sections
💻 [**1. Project Overview**](#proj_overview)  
💻 [**2. Application of OOP**](#proj_oop)  
💻 [**3. Alignment with SDG**](#proj_sdg)  
💻 [**4. Usage Guidelines**](#proj_guidelines)  
💻 [**5. Project Development**](#proj_dev)  
💻 [**6. Acknowledgement**](#proj_acknowledge)  
💻 [**7. Project Developer**](#proj_developer)  
<br>

# <a id = "proj_overview"> 🏸 Project Overview </a> 
 **FitSpace: FDC Booking Hub** is a Java console program designed for students and employees of **Batstate-U Alangilan Campus**. It streamlines facility reservations at the **Fitness Development Center**, allowing users to view available facilities, book slots, and track usage. The system ensures organized management of reservations, monitors facility conditions, and enables quick communication for maintenance. Admins can manage users, reservations, and facilities, improving access and fostering a more active campus community.

---
### 🌟 Project Objectives 🌟  
&nbsp;&nbsp;&nbsp;&nbsp;🎯 **Convenience:** Simplify the reservation process for gym facilities, saving time and effort.  
&nbsp;&nbsp;&nbsp;&nbsp;🎯 **Organization:** Keep a structured record of facility usage and reservations.  
&nbsp;&nbsp;&nbsp;&nbsp;🎯 **Monitoring:** Assist in tracking the condition and availability of gym facilities.  
&nbsp;&nbsp;&nbsp;&nbsp;🎯 **Communication:** Enable efficient user notification in case of issues or maintenance needs.  
&nbsp;&nbsp;&nbsp;&nbsp;🎯 **Admin Support:** Provide tools for admins to manage users, facilities, and reservations effectively.
<br>

---

# <a id = "proj_oop"> 🏸 Application of OOP </a> 
**FitSpace: FDC Booking Hub** is built around the fundamental principles of **Object-Oriented Programming (OOP)**, ensuring both efficient functionality and smooth user experience. By applying these principles, the system achieves a scalable and organized structure that facilitates easy management and smooth operation of the booking process. 

## ☕ **Encapsulation** ☕

| **Concept**            | **Description**                                                                                       |
|------------------------|-------------------------------------------------------------------------------------------------------|
| **Private Fields**      | The `User` class uses private fields like `firstName`, `password`, and `uniqueId`. Similarly, the `Facility` class uses `facilityID`, `name`, and `status`. The `Admin` class keeps fields like `adminID` and `adminPass` private to protect sensitive information. |
| **Public Methods**      | Methods like `registerUser()`, `loginUser()`, and getters control access to private fields, ensuring data integrity and security by preventing direct modification of the object's internal state. In `Admin.java`, methods like `getAdminID()` allow controlled access to private fields. |

---

## ☕ **Inheritance** ☕

| **Concept**             | **Description**                                                                                       |
|-------------------------|-------------------------------------------------------------------------------------------------------|
| **Base Class**          | The `Student` and `Employee` classes extend the `User` base class, inheriting common properties (`userId`, `uniqueId`, `password`) and methods (`registerUser()`, `loginUser()`, `displayCommonHeader()`). |
| **Customization**       | Subclasses like `Student` and `Employee` customize behavior while reusing common functionality from the `User` class. |

---

## ☕ **Polymorphism** ☕

| **Concept**             | **Description**                                                                                       |
|-------------------------|-------------------------------------------------------------------------------------------------------|
| **Overridden Methods**  | The `Student` and `Employee` classes override methods such as `registerUser()` and `loginUser()` to implement specific behaviors for each user type. |
| **Dynamic Method Selection** | The `getUserType()` method dynamically selects the appropriate method based on the object type. |

---

## ☕ **Abstraction** ☕

| **Concept**             | **Description**                                                                                       |
|-------------------------|-------------------------------------------------------------------------------------------------------|
| **Abstract Class**      | The `User` class is abstract, containing shared methods like `commonRegistration()` and `commonLogin()` that handle functionality such as database interactions and password validation. |
| **Abstract Methods**    | Methods like `displayUserMenu()`, `registerUser()`, and `loginUser()` are unimplemented, forcing subclasses (`Student`, `Employee`) to define their specific implementations. This ensures flexibility in user-specific actions while maintaining a common structure. |
<br>

---

# <a id = "proj_sdg"> 🏸 Alignment with SDGs </a> 

**FitSpace** contributes to the **United Nations' Sustainable Development Goals (SDGs)** by promoting physical well-being and quality education through the following SDGs:

### 🌍**SDG 4: Quality Education**  
> FitSpace **enhances access to education** by allowing students and employees to reserve gym facilities for both fitness and academic purposes, such as classes and exams. This integration of educational and fitness spaces improves the student experience, supporting academic success and overall well-being.

### 🌍**SDG 3: Good Health and Well-being**  
> FitSpace **promotes the health and well-being** of students and employees by simplifying access to gym facilities, encouraging regular exercise, reducing stress, and boosting mental health, ultimately supporting the overall well-being of the university community.
<br>


## <a id="proj_guidelines"> 🏋️‍♂️ Usage Guidelines </a>
**FitSpace** offers the following **key features** alongside their detailed **usage guidelines:**

---

### 🏸 1. **User Registration**  
   **Guideline:** Register as a **Student** or **Employee** by providing your full name, role, and password.  
   **Steps:**  
   1. Choose your role (**Student** or **Employee**).  
   2. Provide your full name and role-specific ID (SR code for students, employee ID for employees).  
   3. Set a secure password.  
   4. Submit your registration form.

### 🏸 2. **User Login**  
   **Guideline:** Log in using your stored credentials for **Students** or **Employees**.  
   **Steps:**  
   1. Enter your **userID** (SR code for students, employee number for employees).  
   2. Enter your **password**.  
   3. Upon successful login, you will see a list of options corresponding to your role (Student or Employee).

### 🏸 3. **Admin Dashboard**  
   **Guideline:** Access the **Admin Interface** to manage core elements of the system, such as users, reservations, and facilities.  
   **Steps:**  
   1. Log in using the **Admin ID** and **password** (provided by the system).  
   2. Upon successful login, the Admin Dashboard provides the following options:  
      - **Manage Users**: Add, update, or remove user accounts (students and employees).  
      - **Manage Reservations**: View, approve, or cancel reservations.  
      - **Manage Facilities**: Add, remove, or modify facilities and their statuses.

### 🏸 4. **Reservation Management**  
   **Guideline:** **Students** and **Employees** can make, view, and manage their reservations.  
   **Steps:**  
   1. Log in to your account.  
   2. Navigate to the **Reservation** section.  
   3. Select a facility and choose an available time slot.  
   4. Confirm your reservation and view your **reservation history**.  
   5. You can cancel an active and confirmed reservation from the **reservation history**.

### 🏸 5. **Log-out**  
   **Guideline:** **Users**, **Employees**, and **Admins** can log out from the system at any time.  
   **Steps:**  
   1. Click on **Log-out** from any screen when you are finished.  
   2. You will be securely logged out of the system.

---
## 🌟 **Additional Guidelines** 🌟
**🔻 Security**: Always use a strong password and change it regularly to keep your account secure.  
**🔻 Fair Usage**: Ensure that reservations are made fairly, respecting others' access to facilities.  
**🔻 Availability**: Facilities are subject to availability and will be updated in real-time for users to view.

---
<br>


# <a id="proj_dev"> 🏸 **Project Development** </a> 
The development of FitSpace: FDC Booking Hub involved careful planning, implementation, and organization. The project follows a well-structured directory format to ensure efficient management and easy navigation of its components.

---
## 📁**Directory Organization**📁
**Here’s an overview of how the project files are organized:**  
```
FDC Booking Hub/
├── .vscode/
│   └── settings.json
├── database/
│   └── schema.sql
├── lib/
│   └── mysql-connector-j-9.1.0.jar
├── src/
│   ├── main.java
│   ├── utils/
│   │   ├── admin/
│   │   │   ├── admin.java
│   │   │   └── adminManager.java
│   │   ├── database/
│   │   │   └── DBConnection.java
│   │   ├── facilities/
│   │   │   ├── facility.java
│   │   │   └── facilityManager.java
│   │   ├── reservation/
│   │   │   ├── reservation.java
│   │   │   ├── reservationManager.java
│   │   │   └── cancellation.java
│   │   └── users/
│   │       ├── user.java
│   │       ├── userManager.java
│   │       ├── employee.java
│   │       └── student.java
```
## ☕**Classes Overview**☕
**The application consists of well-defined classes, each responsible for specific functionalities:**
| **Class Name**                | **Description**                                                                                          |
|-------------------------------|----------------------------------------------------------------------------------------------------------|
| `main.java`                    | Main entry point of the application, responsible for initializing and running the program.                |
| `admin.java`                   | Class representing an admin user, containing methods and attributes for admin functionality.              |
| `adminManager.java`            | Manages admin-related operations, such as admin login, registration, and management of facilities/users.  |
| `DBConnection.java`            | Handles the connection between the Java application and the database, ensuring smooth data transactions.  |
| `facility.java`                | Represents a facility in the FDC, with attributes such as facility name, type, and availability.          |
| `facilityManager.java`         | Manages the facilities, including adding, removing, and updating facility data.                          |
| `reservation.java`             | Represents a reservation made by users, storing information like the user, facility, and reservation time. |
| `reservationManager.java`      | Manages all reservation-related operations, including creating, viewing, and canceling reservations.       |
| `cancellation.java`            | Handles the cancellation of reservations, including updating the database and notifying users.            |
| `user.java`                    | Abstract class for common user properties and behaviors, shared by both students and employees.           |
| `userManager.java`             | Manages user-related operations, such as registration, login, and user data management.                   |
| `employee.java`                | Represents an employee user, extending the `user.java` class and adding specific functionalities.         |
| `student.java`                 | Represents a student user, extending the `user.java` class and adding student-specific functionalities.   |

## 💻**Technical Solution Framework**💻
**To build this project, the following tools and technologies were used:**
- **🔻 Java**: The primary programming language used to develop the application, providing the backbone for the system's logic and functionality.
  
- **🔻 Visual Studio Code**: The integrated development environment (IDE) chosen for writing, debugging, and testing the application's code.

- **🔻 MySQL**: The relational database management system (RDBMS) used to store and manage critical data, including user profiles, reservations, and facility details.
  
- **🔻 MySQL Workbench**: The powerful tool utilized for managing the MySQL database, running queries, and visualizing the database structure.
---

## <a id="proj_acknowledge"> 🏸 **Acknowledgement** </a>
I sincerely thank **Ms. Fatima Marie P. Agdon**, my instructor for **CS 211: Object-Oriented Programming (OOP)**, for her unwavering guidance and support throughout this project. Her commitment to teaching and encouragement greatly contributed to my understanding of object-oriented programming and its practical applications.  

The development of **FitSpace: FDC Booking Hub** was made possible through her mentorship, which inspired me to think creatively, design efficiently, and code purposefully.  

Thank you for being a remarkable mentor and a source of motivation! 💝💻
<br>

## <a id="proj_developer"> 🏸 **Project Developer** </a>
<p align="center">
   <img src="ReadMe/Developer.png" alt="Developer="max-width: 100%; height: auto;">
</p>

I am a **second-year BS Computer Science student** from **CS-2101** at Batangas State University. This project, **FitSpace: FDC Booking Hub**, was created as part of my course **CS-211: Object-Oriented Programming (OOP)**.

As a **student-athlete**, I deeply value the balance between academics and athletics. The challenges I’ve faced in managing time and access to facilities inspired me to develop **FitSpace**, a platform designed to make gym facility reservations seamless and efficient. My goal was to create a system that fosters both physical well-being and academic success by ensuring students and employees can easily access and utilize fitness spaces.

---

### 🌟 **Contact Details** 🌟

&nbsp;&nbsp;&nbsp;&nbsp;📜 **Name:** [Anthonina Dhapniella C. Vael](https://github.com/andavael)  
&nbsp;&nbsp;&nbsp;&nbsp;💻 **GitHub:** [github.com/andavael](https://github.com/andavael)  
&nbsp;&nbsp;&nbsp;&nbsp;📧 **Email:** [23-04485@g.batstate-u.edu.ph](mailto:23-04485@g.batstate-u.edu.ph)  

---

Thank you for exploring **FitSpace**! This project reflects my passion for both **technology** and **sports**, and I hope it serves as a valuable tool for users. Feel free to reach out to me for any queries or collaboration opportunities. 🏐💻
