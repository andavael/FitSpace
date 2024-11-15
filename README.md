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

## 🏋️‍♂️ Application of OOP
### OOP Principles Applied

1. **Encapsulation**:  
   User data (e.g., first name, last name, password) are stored as private fields in classes like `Student` and `Employee`. Public getter and setter methods are provided to safely access and modify these fields, ensuring controlled access to sensitive data. Similarly, facility details like availability and type are encapsulated as private fields, with public methods enabling interaction, promoting data integrity and protection.

2. **Inheritance**:  
   A base `User` class is created to define common attributes and methods shared by both `Student` and `Employee` classes. By extending the `User` class, both `Student` and `Employee` inherit common behaviors like `registerUser()` and `loginUser()`, while allowing each subclass to implement user-specific functionalities, such as role validation.

3. **Polymorphism**:  
   The `Student` and `Employee` classes override the `registerUser()` and `loginUser()` methods from the `User` class. While these methods share the same name in the base class, their behavior differs depending on the object type (student or employee). This enables dynamic method invocation, ensuring the correct implementation is executed based on the object type.

4. **Abstraction**:  
   The `User` class is abstract, defining the common structure and behaviors like `registerUser()` and `loginUser()`. However, the actual implementation of these methods is left to the `Student` and `Employee` subclasses. This abstraction hides the internal details, offering a simplified interface for interacting with user registration and login processes.



## 🏋️‍♂️ Application of SDG

The **Fitness Development Center (FDC) Reservation Portal** contributes to the United Nations' Sustainable Development Goals (SDGs) by promoting physical well-being and economic growth through the following SDGs:

### 1. **SDG 4: Quality Education**  
   The **FDC Booking Hub** contributes to improving access to quality education by providing a platform for students and employees to book gym facilities for both physical activities and academic purposes. FDC is used not only for physical training but also for conducting classes and exams, offering students a versatile learning environment. By integrating educational facilities with a booking system, FDC supports an enhanced learning experience for students, promoting health and wellness alongside academic success.

### 2. **SDG 3: Good Health and Well-being**  
   The platform supports the health and well-being of students and employees by making it easier to reserve gym facilities for fitness and recreational purposes. Access to well-maintained fitness facilities encourages regular exercise, which improves physical health, reduces stress, and promotes mental well-being. By ensuring convenient access to these resources, the system contributes to the overall health of the university community.

