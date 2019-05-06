<img src="https://raw.githubusercontent.com/Sebastian-Noren/PoliceSystem/database_connection/src/main/resources/image/Swepust2.png" alt="PGIS logo" width="50%"/>
<hr>
A didactic software system designed by students during project course 2 at Kristianstad University.
<br><br>

### Introduction

The system will be used by the police station and the services they provide e.g. passports or receiving
detained persons, but also by patrolling officers to administrate speeding tickets or report incidents.
The graphical interface is designed and implemented with JavaFX, a standalone library for Java
development. The logic and the corresponding database are written in Java and SQL. A user-friendly
interface combined with the enhanced mobility of the platform is key to keeping the patrolling officers
on the street and not in front of the computer.
<br><br>

### PGIS Code Style and Coding Conventions

The code convention for this project has the following purposes:
<br>
- To create a consistent look.
- Enables the readers to focus on content and thus understand the code better.
- During development the different team members can work or help out with all 
the parts of the code faster and easier with a consistent layout.
- To create a self commenting code where the purpose of the code can be read and
understood by reading the code.
<br><br>

#### Naming conventions

1. **Classes and Interfaces**
    * Class names should be nouns in mixed case with the **first** letter of each internal word capitalised.
    * Interface names should **start** with a capital character. The name should represent a collection of the classes        implementing it
    * The name should be as descriptive as possible of the class functionality.
    * Whole words should be used and not acronyms or abbreviations.
    <br>
    Example:
    <br>
    
    ```java
        public interface ISendable {
            
        }
        public class PrintDocument implements ISendable {
        
        }
    ```
    
2. **Methods**
    * Methods should be a verb describing a noun in camel casing. 
    * The name should be as descriptive as possible of the functionality of the method.
    <br>
    Example:
    <br>
    
    ```java
        void sendToPrinter(Document document);
        void createPdf(Document document);
    ```
    
3. **Variables**
    * The name should be short yet meaningful and not an acronym or abbreviation.
    * Should **not** start with underscore ('_') or dollar sign '$' characters.
    * Only temporary variables can have one-character names.
    * Common temporary integer variables should be name, i, k, m, n and c, d, e for characters.
    <br>
    Example:
    <br>
    
    ```java
        int port = 3306;
        boolean isSent = false;
        int i = 0;
    ```
    
4. **Constant Variables**
    * Should all be **uppercase** seperated by underscore.
    <br>
    Example:
    <br>
    
    ```java
        static final int MAX_QUE_SIZE = 10;
    ```
    
5. **Packages**
    * All package names are in **all-lowercase letters**
    * The top level package name is pust.
    * A package name with more than one word in should be seperated by underscore.
    <br>
    Example:
    <br>
    
    ```java
        pust.model.database_functionality
    ```
    
6.  **GitHub**
    * Use snake_casing when naming the branches. The name should be somewhat short but still descriptive.
<br><br>

#### Structure

1. **Classes** - *The Single Responsibility Principle* 
    * The class should only do one specific task, the word "and" should **not** be needed to describe it.
    * Should not just hold methods. Ideally they should be represented as an "object", not a place
    to store code.
2. **Methods** - *Functional cohesion* 
    * Should only perform one typ of task even if calling several other methods in the body.
    * The flow of the methods in a class should be readable from top to bottom. A reader should be able
    to read down the page and understand what is happening.
    <br>
    Example:
    <br>
    
    ```java
        int add(int x, int y) {
        return x + y;
        } 
    ```
    
3.  **Variables**
    * One variable declaration on each line.
    * Initialization of variables should not be in their declaration, but in a constructor or static constructor.
4.  **GitHub**
    * Each commit message should be as descriptive as possible.
    * Each branch created should only represent one feature or task.
    * Have clean commits, if you are going to do formatting, do it in another commit. Other contributors should be able
    to see clean diffs and understand what is happening.
    * **Never** commit anything with "System.out.print", these should only be used inside your development environment,
    or within a debug mode.
    * If a bug is found make sure to report this as an issue.
    * **Never** merge anything or push anything directly to the master branch without the developing team's approval. 
    * When creating a new branch make sure to branch it off the develop branch.
    
    ```java
        git checkout -b new_feature develop
    ```   
     
5.  **Comments**
    * The code should be self commentating as much as possible.
    * A one sentence comment should use **line comment**.
    * The end bracket of a very long code block can be commented with //end *code block name*
    * A longer description of a class or a method should use **block comment**. The star in the block comment should 
    be inlined and **one blankspace** between the star and the following word
    * The description of a class should be done below the imports but before the class declaration.
    The description can, but does not have to, consist of *block tags* e.g. @param. 
    <br>
    Example:
    <br>
    
    ```java
        package pust.model.entity;
        import org.junit.*;
        
        /*
         * This class represents a person object. 
         * @param age the age of the person
         */ 
         
         public class Person {
         
            private int age;
         }
    ```
    
    * A block comment description of a method should be done within the method with one whitespace 
    between the method name and the top of the block comment and one whitespace after.
    <br>
    Example:
    <br>
    
    ```java
         public int calculateAge(int age, int height) {
            
            /*
             * This method takes the age and height as parameter, calculates the age based on the height and age
             * and returns the calculated value 
             */
             
             return age * height;   
         }
    ```    
    
6.  **Access modifier**
    * The correct access modifier **MUST** be used **EVERY TIME**.
7.  **Layout**
    * The first curly bracket should be in-lined and **not** on a new line. 
    * One whitespace between class- and variable declaration except for the Logger object which is directly under the class.
    * One whitespace after a methods last curly bracket before the next line of code begins.
    * One whitespace before every line comment.
    * One blank space after the method parentheses before the first curly bracket. 
    * Before and after each =, +, -, / and * a blank space is inserted. 
    * No extra whitespaces between curly brackets that does not contain any code segment.
    * **Always** use CTRL + L, it can not be done to often. A bare minimum is before every commit.
8.  **Logging**
    * In the start of every class a Logger object should be created, atleast in the classes implementing try-catch.
    * In every catch the exception should be sent to the log.
    * The level of logging can differ depending on the work done. An exception should always be logged as **Level.SEVERE**.
    * FINE, FINER, FINEST can be used for debugging purposes.
    <br>
    Example:
    <br>
    
    ```java
         public class SomeClass {
             private static final Logger LOGGER = Logger.getLogger(SomeClass.class.getName());
             
             try {
               ...
             } catch (Exception ex) {
               LOGGER.log(Level.SEVERE, ex.toString(), ex);
             }
         }
    
    ```
9.  **Complete example**
    <br>
    
    ```java
        package pust.model.entity;
        import org.junit.*;
        
        /*
         * This class represents a person object. 
         * @param age the age of the person based on the height
         */ 
         
         public class Person implements Walkable{
            private static final Logger LOGGER = Logger.getLogger(Person.class.getName());
         
            private String name;
            private int age;
            private int height;
                        
            public Person(String name, int age, int height) {
            this.name = name;
            this.height = height;
            // Set the age to the calculated age
            this.age = calculateAge(age, height);
                       
            @Override
            public void walk(){
               try {
               
               } catch (Exception ex) {
                  LOGGER.log(Level.SEVERE, ex.toString(), ex);
               }
            }
            
            private int calculateAge(int age, int height) {
                        
               /*
                * This method takes the age and height as parameter and calculates the age 
                * based on the height and age and returns the calculated value 
                */
                
                return age * height;   
             }
             
            private String getName() {
                return name;
            }
         }
    ```
    
9.  **General Practices**
    * A efficiency program will always be of higher priority. Even if something can be done in a day, take the 
    time to make a program/logic that will last more then the time expected, and that will take into account 
    unseen errors.
    * The main class is for starting the program object, not for creating everything.
    * The main should have the most comments, when someone reads through the main they should be able 
    to understand what the whole program does.
<br>
<hr>
<br>
    
* > "Later equals never"
    
    "I'll clean up this code later" is a good example where it will never happen.
    
* > "Clean code always looks like it was written by someone who cares"
    
    Michael Feathers - Working Effectively with Legacy Code
    
### Credits

* [Julius Soutine](https://github.com/jsoutine) - Developer
* [Ali Muhammed](https://github.com/Alawi93) - Developer
* [Sebastian Norén](https://github.com/Sebastian-Noren) - Developer
* [Christoffer Quick](https://github.com/ChristofferQuick) - Developer

### References

* [Basic writing and formatting syntax](https://help.github.com/en/articles/basic-writing-and-formatting-syntax#styling-text)
* [Shodown Code Style and Orientations](https://github.com/showdownjs/code-style/blob/master/README.md)
* [Java Programming Style Guidelines](https://petroware.no/javastyle.html)
* [Java Naming conventions](https://www.geeksforgeeks.org/java-naming-conventions/)
* [Michael Fethers - Working Effectively with Legacy Code](https://www.amazon.com/Working-Effectively-Legacy-Michael-Feathers/dp/0131177052)
* [Robert C. Martin - Clean Code: A Handbook of Agile Software Craftsmanship](https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882/ref=as_li_ss_tl?s=books&ie=UTF8&qid=1458577041&sr=1-1&keywords=clean+code&linkCode=sl1&tag=devdaily-20&linkId=b02cce691c33e6c64b8daf0c169d39f7)
* [C# Coding Conventions (C# Programming Guide)](https://docs.microsoft.com/en-us/dotnet/csharp/programming-guide/inside-a-program/coding-conventions?fbclid=IwAR36n5e9SrXJOuM5C7Rg5zt6wOALrm9-CMmWhihok_OQmPZvaKMkMKeDTpg)'
