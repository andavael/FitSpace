# FitSpace: FDC Reservation Portal
The FitSpace: FDC Reservation Portal is a console-based Java application designed for students and employees to easily reserve and access gym facilities at the Fitness Development Center, Batstate-U Alangilan Campus.

## Summary of Sections
-  [1. Project Overview](#proj_overview)
-  [2.  Application of OOP](#proj_oop)
-  [3.  Alignment withSDG](#proj_sdg)
-  [4. Usage Guidelines](#proj_guidelines)
-  [5. Technical Solution Framework](#proj_techframework)
-  [5. Project Developer](#proj_developer)
<br>

## <a id = "proj_overview"> 🏋️‍♂️ Project Overview </a> 
The FitSpace: FDC Reservation Portal is a console-based Java application designed for students and employees of Batstate-U Alangilan Campus, providing an efficient way to reserve gym facilities at the Fitness Development Center (FDC). Users can browse available facilities, select a reservation date, and choose from six standard time slots. Similarly, the system enables admins to manage users, reservations, and facility details with ease. This streamlined process aims to enhance the accessibility and organization of the campus's, specifically, FDC's recreational spaces.
<br>

## <a id="proj_oop"> 🏋️‍♂️ Application of OOP </a>
### OOP Principles Applied
1. **Encapsulation**: Implemented by storing user data (e.g., first name, last name, password) as private fields in classes like Student and Employee. Public getter and setter methods are used to access and modify these fields, ensuring controlled access to the data. Similarly, facility details like availability and type are encapsulated as private fields with public methods to interact with the data, promoting data protection and integrity.
2. **Inheritance**: Implemented by creating a base User class that contains common attributes and methods for both Student and Employee classes. The Student and Employee classes extend User, inheriting shared behavior like registerUser() and loginUser(), while adding specific functionality like user role validation.
3. **Polymorphism**:  Implemented as both Student and Employee classes override the registerUser() and loginUser() methods of the User class. Although these methods have the same name in the base class, their behavior differs based on whether the user is a student or an employee, allowing dynamic method invocation based on the object type.
4. **Abstraction**: Implemented by using the abstract User class, which defines the common structure and behavior (like registerUser() and loginUser()) but leaves the actual implementation of these methods to the Student and Employee subclasses. This hides the internal details of user registration and login, providing a simplified interface for interaction.
<br>
