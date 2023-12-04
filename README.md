# UniversityExplorer

A basic Java based program that provides users with the ability to search for and get recommendations on their dream university!

This project was developed by Students at the University of Toronto. It leverages the U.S. Departement of Educations College Scorecard API to provide accurate data on a large variety of American post-secondary education institutions.

## Overview

**Description of the Problem Domain:**

Finding the right post-secondary institution for you can be challenging, there are just so many to choose from! It is one of the most life defining decisions to be made, so it is important to get it right. 

We hope to help our users narrow down their decisions, while being able to explore a wide variety of different institutions. To accomplish this, our application allows users to search a vast catalogue post-secondary institutions (currently only in The United States) based on parameters such as instituional name, or distance from a given zip-code. Users can also recieve recommendations on potential universities to consider based on their academic standing (SAT/ACT score). For more personalised recommendations, users can create their own account and profile, which will take in to account a variety of factors such as desired post-graduation salaries. 

**Program Usage Instructions:**
1) Download the source code of our project (be sure to extract it)!
2) Open our project in a JAVA IDE of your choice (using JDK of versiom 20+ is recommended).
3) Be sure to set the java folder/directory as the sources root.

Your directory should look similar to this:

![image](https://github.com/LatinScribe/UniversityExplorer/assets/127168121/f490755b-c62b-4c1d-b721-5171ba078684)

4) Run the Main.java file.
5) Viola, you should see the main menu pop up!

For advanced users, feel free to compile all the java files yourself, and simply run the main file.

## Project Details

**Break down of how our project works:**

1. Who is this for:
	- Users of our program are students looking to browse universities. They can search for post-secondary instituions in the United States based on their preferences and get recommendations based on their inputed information.
2. University search:
        - This will allow users to search for universities in our [institution database](https://collegescorecard.ed.gov/data/documentation/).
4. Recommendation system:
  	 - Based on the information provided by the user (in particular, SAT/ACT scores), provide recommendations of best fitting institutions.
6. Zip-code Search:
	- Convienient for finding insitutions close to home. Provide a zip code and find institutions within a set radius.
7. Login/Account system:
        - This allows users to signup/create an account.
	- It allows users to login using their credentials (a username and a password).
	- Allows users to save their preferences/info into a profile, so that they can enter their information just one time, and recieve more tailored recommendations.
 	- All user related data is stored on our server via our own [API](https://github.com/LatinScribe/Custom-Server-API).
8. Databases:
	- Database of users.
 	- Database of user profiles.
  	- Database of education institutions (see College Scorecard API).
9. APIs
	- College Scorecard API: https://collegescorecard.ed.gov/data/documentation/
	- Our own custom API developed by Henry to handle traffic to our server: https://github.com/LatinScribe/Custom-Server-API

  ---

**API Documentation:**

### **College Scorecard**

![image](https://github.com/LatinScribe/UniversityExplorer/assets/127168121/c3fb3b32-52bd-40be-8e1f-8c78aed8b00b)


- Full API Documentation [pdf](https://collegescorecard.ed.gov/assets/InstitutionDataDocumentation.pdf)
- Privdes Institution-level data files for 1996-97 through 2021-22 containing aggregate data for each institution. Includes information on institutional characteristics, enrollment, student aid, costs, and student outcomes.
- Initiative of the U.S. Department of Education.
- Accurate verified data.

### **Server API**
- See the full documentations on the [github page](https://github.com/LatinScribe/Custom-Server-API).
- Python based flask application that serves as the middle ware to handle HTTP requests to our server for data storage and retrieval.
- Implements a MySQL database.
- Developed by Henry to provide better data persistence thoughout our program.
---

#### **Original Project Planning**:

##### Link to our project UML: [Google Drawing](https://docs.google.com/drawings/d/11WJCj5-iBdr0djCl40v0F3NTEFxRZQwrKyZO_EVWw8s/edit?usp=sharing)
---
### Authors:
Henry "TJ" Chen, Andre Lin, Diego Pachas Ramos, Kanish Sharma, Bora Celebi
