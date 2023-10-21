# CSC207-Project

Link to our github: [https://github.com/LatinScribe/CSC207-Project](https://github.com/LatinScribe/CSC207-Project)

Link to our UML: [Google Drawing](https://docs.google.com/drawings/d/11WJCj5-iBdr0djCl40v0F3NTEFxRZQwrKyZO_EVWw8s/edit?usp=sharing)

**Description of the Problem Domain:**

The application will allow a user to search for a university program in the United States based on their preferences. By using an API, users will be able to find university programs by inputting their key academic metrics, such as SAT scores, financial aid requirements, GPA, and other quantiative factors that are used for university admissions. This will allow the application to recommend various university programs suited for them. Users will also be able to manually search for various programs and universities through the database provided with the application. Additionally, there will be a comparison tool present where users can add universities they want to compare or save to an individual list, before being able to compare two universities based on common metrics, like average GPA, % of students in certain fields, etc.  

--- 

**Break down of how our project works:**

1. Users:
	- Users of our program are students looking to browse universities. They can search for a university program in the United States based on their preferences and get recommendations based on their inputed information.
2. University search:
        - This will allow users to search for universities in our [database](https://collegescorecard.ed.gov/data/documentation/).
4. Recommendation system:
	- Based on the information provided by the university, use an algorithm to provide 
5. Login/Account system:
        - This system will be implemented if time/technical constriants allow (not a priority)
	- This will allow users to signup/create an account
	- It will allow users to login using their credentials (tentatively a username/email and a password)
	- Allows users to save their preferences/info, so that they can keep browsing from where they left off
	- This will require a database which will be connected with an API ([https://henrytchen.com/custom-api/grade](https://henrytchen.com/custom-api/grade))

7. Databases:
	- Database of universities
 	- Login database (if time permits)
8. APIs
	- https://collegescorecard.ed.gov/data/documentation/
	- Potentially our own API for server side storage and retrieval: https://github.com/LatinScribe/Custom-Server-API

  ---

**API Documentation and Screenshot:**
- UNI API Documentation [pdf](https://collegescorecard.ed.gov/assets/InstitutionDataDocumentation.pdf)
---
**Java Code Example:**



----
**Technical Problems and Other Questions:**

----
### Authors:
Henry "TJ" Chen, Andre Lin, Diego Pachas Ramos, Kanish Sharma, Bora Celebi
