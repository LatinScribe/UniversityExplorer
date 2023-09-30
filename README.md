# CSC207-Project

<!-----

You have some errors, warnings, or alerts. If you are using reckless mode, turn it off to see inline alerts.
* ERRORs: 0
* WARNINGs: 0
* ALERTS: 5

Conversion time: 1.228 seconds.


Using this Markdown file:

1. Paste this output into your source file.
2. See the notes and action items below regarding this conversion run.
3. Check the rendered output (headings, lists, code blocks, tables) for proper
   formatting and use a linkchecker before you publish this page.

Conversion notes:

* Docs to Markdown version 1.0β34
* Sat Sep 30 2023 08:28:57 GMT-0700 (PDT)
* Source doc: CSC207 Design Question (Week 3)
* This document has images: check for >>>>>  gd2md-html alert:  inline image link in generated source and store images to your server. NOTE: Images in exported zip file from Google Docs may not appear in  the same order as they do in your doc. Please check the images!

----->


<p style="color: red; font-weight: bold">>>>>>  gd2md-html alert:  ERRORs: 0; WARNINGs: 0; ALERTS: 5.</p>
<ul style="color: red; font-weight: bold"><li>See top comment block for details on ERRORs and WARNINGs. <li>In the converted Markdown or HTML, search for inline alerts that start with >>>>>  gd2md-html alert:  for specific instances that need correction.</ul>

<p style="color: red; font-weight: bold">Links to alert messages:</p><a href="#gdcalert1">alert1</a>
<a href="#gdcalert2">alert2</a>
<a href="#gdcalert3">alert3</a>
<a href="#gdcalert4">alert4</a>
<a href="#gdcalert5">alert5</a>

<p style="color: red; font-weight: bold">>>>>> PLEASE check and correct alert issues and delete this message and the inline alerts.<hr></p>


Link to our github: [https://github.com/LatinScribe/CSC207-Project](https://github.com/LatinScribe/CSC207-Project)

**Description of the Problem Domain:**

Manufacturers produce multiple products. Often, these products require extensive testing and certification to be sold. In this case, these products need to be sent over to laboratories, who will get this testing and certification done. For our project, we will be helping manufacturers to find laboratories to test their products, and laboratories to find manufacturers that can help them get their business. Manufacturers will have the ability to post jobs for products to be tested, and laboratories will be able to view these jobs and fulfill them. 

**Break down of how our project works:**



1. Users:
	- Laboratories: They will have access to the job board to VIEW job postings. If there is a job posting they are interested in,  they can connect/contact the relevant manufacturer and/or accept the job. They will also have the ability to add their laboratory to our database of laboratories (this feature may be cut depending on time)
	- Manufacturers: They will have access to the job board to POST a new job posting (a request for a particular service). They will also have the ability to query our database of laboratories (this feature may be cut depending on time).
2. Login/Account system:
	- This will allow users to signup/create an account
	- It will allow users to login using their credentials (tentatively a username/email and a password)
	- When logging in, it will separate users to separate GUIs depending on account type (The job board looks different if you are a laboratory vs. a manufacturer)
	- This will require a database which will be connected with an API ([https://henrytchen.com/custom-api/grade](https://henrytchen.com/custom-api/grade))
3. Job Board:
	- A generalized database where manufacturers can post jobs to be done and laboratories can view jobs to be completed.
	- Normally sorted based on time posted
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

		

**API Documentation and Screenshot:**

[https://henrytchen.com/custom-api/grade](https://henrytchen.com/custom-api/grade)? (see github:[https://github.com/LatinScribe/Custom-Server-API](https://github.com/LatinScribe/Custom-Server-API) )

https://hoppscotch.io/Links

<img width="468" alt="Picture2" src="https://github.com/LatinScribe/CSC207-Project/assets/144297306/5866c281-bd64-42d8-a908-19d12878052e">

Alternative Hosting API: [https://pastebin.com/doc_api](https://pastebin.com/doc_api)



<p id="gdcalert3" ><span style="color: red; font-weight: bold">>>>>>  gd2md-html alert: inline image link here (to images/image3.png). Store image on your image server and adjust path/filename/extension if necessary. </span><br>(<a href="#">Back to top</a>)(<a href="#gdcalert4">Next alert</a>)<br><span style="color: red; font-weight: bold">>>>>> </span></p>


![alt_text](images/image3.png "image_tooltip")


**Java Code Example:**

Custom API



<p id="gdcalert4" ><span style="color: red; font-weight: bold">>>>>>  gd2md-html alert: inline image link here (to images/image4.png). Store image on your image server and adjust path/filename/extension if necessary. </span><br>(<a href="#">Back to top</a>)(<a href="#gdcalert5">Next alert</a>)<br><span style="color: red; font-weight: bold">>>>>> </span></p>


![alt_text](images/image4.png "image_tooltip")


Output



<p id="gdcalert5" ><span style="color: red; font-weight: bold">>>>>>  gd2md-html alert: inline image link here (to images/image5.png). Store image on your image server and adjust path/filename/extension if necessary. </span><br>(<a href="#">Back to top</a>)(<a href="#gdcalert6">Next alert</a>)<br><span style="color: red; font-weight: bold">>>>>> </span></p>


![alt_text](images/image5.png "image_tooltip")


**Technical Problems and Other Questions:**



* Can we create/edit an existing API and host it using Hoppscotch? Would that count as an API call? 
* Would using the Lab 3 API be sufficient for the requirement to use an API?
* Can we use MySQL as our database? An alternative database option would be just having a .txt or .csv application.

Link to our github: [https://github.com/LatinScribe/CSC207-Project](https://github.com/LatinScribe/CSC207-Project)
