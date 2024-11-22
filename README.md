<p align="center">
   <img src="ReadMe/logo.png" alt="FitSpace Logo" width="350">
</p>

# FitSpace: FDC Booking Hub 🏀🏐
  **FitSpace: FDC Booking Hub** is a console-based Java application for students and employees of Batstate-U Alangilan Campus, allowing them to efficiently reserve gym facilities at the Fitness Development Center (FDC). Users can view available facilities, select a date, and book a time slot. The system also tracks facility usage, making it easier to monitor their condition and contact users in case of damage. Admins can manage users, reservations, and facilities, improving the organization and accessibility of the FDC’s recreational spaces.
<br>

## 🏋️‍♂️ Summary of Sections
-  [1.  Application of OOP](#proj_oop)
-  [2.  Alignment with SDG](#proj_sdg)
-  [3. Usage Guidelines](#proj_guidelines)
-  [4. Technical Solution Framework](#proj_techframework)
-  [5. Project Developer](#proj_developer)
<br>



## <a id = "proj_oop"> 🏋️‍♂️ Application of OOP </a> 
**FitSpace** implements core **Object-Oriented Programming (OOP) principles** to ensure efficient user management and system functionality through the following OOP principles:

---
## ☕ **Encapsulation**  
   - **Private Fields**: The User class uses private fields such as firstName, password, and uniqueId, while the Facility class uses private fields like facilityID, name, and status. Similarly, in the Admin class, the fields adminID and adminPass are kept private. This ensures that sensitive information is not directly accessible from outside the class, protecting the integrity of the data.
   - **Public Methods**:  Methods like registerUser(), loginUser(), and getters control access to these fields, ensuring data integrity and security by preventing direct modification of the object's internal state. In Admin.java, methods like getAdminID(), getAdminPass(), and validateCredentials() allow controlled access to the adminID and adminPass fields. The validateCredentials() method encapsulates the logic of comparing the provided credentials with the stored ones, securing the authentication process.
---
## ☕ **Inheritance**  
   - **Base Class**: The `Student` and `Employee` classes extend the `User` base class, inheriting common properties (`userId`, `uniqueId`, `password`) and methods (`registerUser()`, `loginUser()`, `displayCommonHeader()`).  
   - **Customization**: These subclasses allow customization of behavior while reusing common functionality from the `User` class.
---
## ☕ **Polymorphism**  
   - **Overridden Methods**: The `Student` and `Employee` classes override methods like `registerUser()` and `loginUser()` to implement specific behaviors for each user type.  
   - **Dynamic Method Selection**: The `getUserType()` method returns the correct object based on user input, and Java dynamically selects the appropriate method based on the object type.
---
## ☕ **Abstraction**  
   - **Abstract Class**: The `User` class is abstract, containing shared methods like `commonRegistration()` and `commonLogin()`. These methods handle functionality such as database interactions and password validation.  
   - **Abstract Methods**: Methods like `displayUserMenu()`, `registerUser()`, and `loginUser()` are left unimplemented, forcing the subclasses (`Student`, `Employee`) to define their specific implementations. This ensures that each user type can define its own actions while following a common structure.
<br>

## <a id = "proj_sdg"> 🏋️‍♂️ Alignment with SDGs </a> 

**FitSpace** contributes to the **United Nations' Sustainable Development Goals (SDGs)** by promoting physical well-being and quality education through the following SDGs:

### 🌍**SDG 4: Quality Education**  
> FitSpace helps improve access to quality education by providing a platform for students and employees to reserve gym facilities for both physical activities and academic purposes. The FDC is used not only for fitness training but also for classes and exams, creating a versatile learning environment. By integrating educational and fitness facilities, FitSpace enhances the student experience, supporting both academic success and overall health.

### 🌍**SDG 3: Good Health and Well-being**  
> FitSpace promotes the health and well-being of students and employees by making it easier to reserve gym facilities for fitness and recreation. Access to well-maintained fitness centers encourages regular exercise, improving physical health, reducing stress, and boosting mental well-being. By offering convenient access to these resources, the platform contributes to the overall health of the university community.
<br>


## <a id="proj_guidelines"> 🏋️‍♂️ Usage Guidelines </a>

**FitSpace** offers the following **key features** alongside their detailed **usage guidelines:**

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
   3. Upon successful login, you will see list of options corresponding to your role (Student or Employee).

### 🏸 3. **Admin Dashboard**  
   **Guideline:** Access the **Admin Interface** to manage users, reservations, and facilities.  
   **Steps:**  
   1. Enter **Admin ID** and **password** (provided by the system).  
   2. Upon successful login, you will see options to manage:  
      - **Manage Users**  
      - **Manage Reservations**  
      - **Manage Facilities**  
   3. Choose the appropriate option to perform actions.

### 🏸 4. **Reservation Management**  
   **Guideline:** **Students** and **Employees** can make, view, and manage their reservations.  
   **Steps:**  
   1. Log in to your account.  
   2. Navigate to the **Reservation** section.  
   3. Select a facility and choose an available time slot.  
   4. Confirm your reservation and view your **reservation history**.  
   5. You can cancel an active and confirmed reservation from the **reservation history**.

### 🏸 5. **Admin Control**  
   **Guideline:** **Admins** have the ability to manage users, reservations, and facilities, and modify their statuses.  
   **Steps:**  
   1. Log in to the **Admin Dashboard**.  
   2. Choose the appropriate section:  
      - **Manage Users**: Add, update, or remove users.  
      - **Manage Reservations**: Approve, reject, or cancel reservations.  
      - **Manage Facilities**: Add, remove, or modify the status of facilities.

### 🏸 6. **Log-out**  
   **Guideline:** **Users**, **Employees**, and **Admins** can log out from the system at any time.  
   **Steps:**  
   1. Click on **Log-out** from any screen when you are finished.  
   2. You will be securely logged out of the system.

---

## 🏸 **Additional Guidelines**:
- **Security**: Always use a strong password and change it regularly to keep your account secure.
- **Fair Usage**: Ensure that reservations are made fairly, respecting others' access to facilities.
- **Availability**: Facilities are subject to availability and will be updated in real-time for users to view.

---
The **FitSpace** project is organized in a structured directory format to ensure efficient management of the application’s components. Below is the directory structure that outlines the organization of files and folders:

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


<br>


## <a id="proj_techframework"> 🏋️‍♂️ **Technical Solution Framework** </a> 

### **Core Technologies**

- **💻 Java**: The primary programming language used to develop the application, providing the backbone for the system's logic and functionality.
  
- **🔧 Visual Studio Code**: The integrated development environment (IDE) chosen for writing, debugging, and testing the application's code.

- **🗄️ MySQL**: The relational database management system (RDBMS) used to store and manage critical data, including user profiles, reservations, and facility details.
  
  - **🖥️ MySQL Workbench**: The powerful tool utilized for managing the MySQL database, running queries, and visualizing the database structure.

<br>

## <a id="proj_developer"> 🏋️‍♂️ **Project Developer** </a>

I am a **second-year BS Computer Science student** from **CS-2101** at Batangas State University. I created this project as part of my course **CS-211: Object-Oriented Programming (OOP)**. Below are my contact details:

<table>
  <thead>
    <tr>
      <th></th>
      <th>Name</th>
      <th>GitHub</th>
      <th>E-mail</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><img src="ReadMe/Anda.jpg" alt="Anthonina Dhapniella C. Vael" width="50" height="50" style="border-radius:50%;"></td>
      <td><a href="https://github.com/andavael" target="_blank">Anthonina Dhapniella C. Vael</a></td>
      <td><a href="https://github.com/andavael" target="_blank">andavael</a></td>
      <td><a href="mailto:23-04485@g.batstate-u.edu.ph">23-04485@g.batstate-u.edu.ph</a></td>
    </tr>
  </tbody>
</table>

---

Thank you for exploring my **FitSpace** project! Feel free to check out the code on my GitHub or reach out to me for any queries.

<br>
