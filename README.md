# CSC207-Project

Link to our github: [https://github.com/LatinScribe/CSC207-Project](https://github.com/LatinScribe/CSC207-Project)

**Description of the Problem Domain:**

Manufacturers produce products (electronics, children's toys, chemicals, you name it). Often, these products require government mandated validation and certification prior to sale. This is where laboratories come into the picture, as they provide services to obtain the necessary product certifications. For our project, we will be building a platform to connect manufacturers and laboratories with each other to facilitate this process. Manufacturers will have a one stop shop to find the laboratory that best suits their needs, or to simply post their service request and allow interested laboratories to connect directly with them. Laboratories will have an opportunity to reach a wider customer base, and to bid on service requests/contracts posted by manufacturers.

--- 

**Break down of how our project works:**



1. Users:
	- Laboratories: They will have access to the job board to VIEW job postings. If there is a job posting they are interested in, they can connect/contact the relevant manufacturer and/or accept the job. They will also have the ability to add their laboratory to our database of laboratories (this feature may be cut depending on time)
	- Manufacturers: They will have access to the job board to POST a new job posting (a request for a particular service). They will also have the ability to query our database of laboratories (this feature may be cut depending on time).
2. Login/Account system:
	- This will allow users to signup/create an account
	- It will allow users to login using their credentials (tentatively a username/email and a password)
	- When logging in, it will separate users to separate GUIs depending on account type (The job board looks different if you are a laboratory vs. a manufacturer)
	- This will require a database which will be connected with an API ([https://henrytchen.com/custom-api/grade](https://henrytchen.com/custom-api/grade))
3. Job Board:
	- A generalized database where manufacturers can post jobs they seek a laboratory to fulfill, and laboratories can view available jobs and contact the manufacturers of relevant job postings (either a button to press or just leaving contact information).
	- Default: sorted based on time posted
	- Can also be sorted based on other methods such as name (if time permits).
4. Database of Laboratories (If time permits)
	- This will allow laboratories to add their laboratory to our database so that manufacturers can search for them. In their posting to our database, they can include a basic description of the services they provide and other pertinent information (such as location, specializations, etc.)

    	Example of a laboratory:

   ![Picture1](https://github.com/LatinScribe/CSC207-Project/assets/144297306/f4747069-b912-42c4-962f-6064b8bd9e78)



	- Allow manufacturer to search for laboratories (ideally with some sort of sorting/categorisation)
5. News/information searching (last thing we will do, if time permits)
	- This will allow manufacturers/laboratories to view the latest news on changes in certification/requirements made by regulatory bodies (such as the Environmental Protection Agency -EPA). Manufacturers and laboratories will want to stay up to date on changes in the regulatory environment and how it could impact their products/services. 
	- This will require the implementation of a web scraper, which we plan on using the Jsoup API to accomplish (see APIs)
6. Databases:
	- Database for login system
	- Database for job postings
	- Database for laboratories (if time permits)
7. APIs
	- A database storage and retrieval system. Our <span style="text-decoration:underline;">current plan</span> is to use a modified version of the Lab3 API to accomplish the goal (see:[https://github.com/LatinScribe/Custom-Server-API](https://github.com/LatinScribe/Custom-Server-API)) . This custom API will allow access to the web-based database, similar to in Lab 3. The major change will be that we plan on switching the mongolDB implementation to a mySQL based implementation. As a backup plan, we are exploring potentially using the pastebin API to accomplish the task ([https://pastebin.com/doc_api](https://pastebin.com/doc_api)). As a last resort, we will turn to using .txt and .csv files to store data either locally or on the web (depending on what works)
	- For the news/web scraping component (if time permits), we intend to use the Jsoup API to retrieve relevant data from websites.


  ---

**API Documentation and Screenshot:**

[https://henrytchen.com/custom-api/grade](https://henrytchen.com/custom-api/grade)? 

(see github:[https://github.com/LatinScribe/Custom-Server-API](https://github.com/LatinScribe/Custom-Server-API) )

Accessible using [https://hoppscotch.io](https://hoppscotch.io) - just like in lab3 

<img width="468" alt="Picture2" src="https://github.com/LatinScribe/CSC207-Project/assets/144297306/5866c281-bd64-42d8-a908-19d12878052e">

Alternative Hosting API: [https://pastebin.com/doc_api](https://pastebin.com/doc_api)

![Picture3](https://github.com/LatinScribe/CSC207-Project/assets/144297306/84107bac-61f0-4ce0-a31d-c2b529593c6b)

Jsoup (WebScraper) [API Documentation](https://jsoup.org/ )

----
**Java Code Example:**

Custom API [found in src/main/java/api/MongoGradeDB.java]

![Picture4](https://github.com/LatinScribe/CSC207-Project/assets/144297306/9a7c24f7-b4a0-4f68-91e9-3ef5e5280e96)

Output

![Picture5](https://github.com/LatinScribe/CSC207-Project/assets/144297306/47995d47-7992-402a-b145-071225925bdd)

----
**Technical Problems and Other Questions:**


* Can we create/edit an existing API and host it ourselves - it will be accessed via hopscotch.io just like in lab3? Would that count as an API call? 
* Would using the Lab 3 API be sufficient for the requirement to use an API?
* A potential challenge would be switching from mongoDB to MySQL, but it should not be too difficult as the fundamental idea is still similar. This does not really impact the actual java code since the API interface is still the same, but the behind the scene aspect of the API will have to change.
* Another note is that much of our project is time dependent, and is flexible so that we should be able to implement the core features (login system + job board) and branch out to implement other features depending on time

----
###Authors:
Henry "TJ" Chen, Andre Lin, Diego Pachas Ramos, Kanish Sharma, Bora Celebi
