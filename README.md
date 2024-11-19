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
   - **Private Fields**: The `User` class uses private fields such as `firstName`, `password`, and `uniqueId`, while the `Facility` class uses private fields like `facilityID`, `name`, and `status`.  
   - **Public Methods**: Methods like `registerUser()`, `loginUser()`, and getters control access to these fields, ensuring data integrity and security by preventing direct modification of the object's internal state.
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

**FitSpace** offers the following **key features:**

#### 1. **User Registration**  
   > &nbsp;&nbsp;&nbsp;&nbsp;Register as a **Student** or **Employee** by providing your name, role, and password.

#### 2. **User Login**  
   > &nbsp;&nbsp;&nbsp;&nbsp;Log in using your stored credentials for **Students** or **Employees**.

#### 3. **Admin Dashboard**  
   > &nbsp;&nbsp;&nbsp;&nbsp;Access the **Admin Interface** to manage users, reservations, and facilities.

#### 4. **Reservation Management**  
   > &nbsp;&nbsp;&nbsp;&nbsp;**Students** and **Employees** can make, view, and manage their reservations.

#### 5. **Admin Control**  
   > &nbsp;&nbsp;&nbsp;&nbsp;**Admins** have the ability to manage users, reservations, and facilities, and modify their statuses.

#### 6. **Log-out**  
   > &nbsp;&nbsp;&nbsp;&nbsp;**Users**, **Employees**, and **Admins** can log out from the system at any time.

<br>


## <a id="proj_techframework"> 🏋️‍♂️ Technical Solution Framework </a> 
- **Java**: The main programming language used to build the application.
- **Visual Studio Code**: The IDE for writing and debugging the code.
- **MySQL**: The database used to store user data, reservations, and facility information.
  - **MySQL Workbench**: The tool used to manage and run SQL queries on the database.
<br>

## <a id="proj_developer"> 🏋️‍♂️ Project Developer </a>
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



