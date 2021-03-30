# Entreprise-Management
It is a spring boot application for the management of employees in a company.

-creation of a company with the following attributes:

      company name: mandatory, min length = 5, max length = 100
      siren: obligatory
      siret: compulsory
      address: optional, if entered min length = 5, max length = 100
-creation of employees of a company with the following attributes:

      name: mandatory, min length = 5, max length = 100
      first name: mandatory, min length = 5, max length = 100
      social security number: mandatory
      date of hire: compulsory
      type of contract: CDI, CDD or work-study
      salary: optional, if entered> 0

*Updating company information

*Updating employee information

*Search the employees of a company by date of hire, type of contract and / or salary

*Min, max and average salary paid by a company by type of contract



Management rules:

1-an employee cannot change from CDI or CDD to work-study contract

2-an employee can only belong to one company

# Run Spring Boot application

1-Clone the repository
git clone https://github.com/Samirbouhlel/Entreprise-Management.git

2-Configure database
then configure the database I'm using MySQL database you can change it in the application properties and the pom.xml

3-get in the project and run this cmd

mvn spring-boot:run

# Test API'S
To test api's you can go to the url : http://localhost:8080/swagger-ui.html

this url is secured by Spring Security:

login:admin

password:admin
