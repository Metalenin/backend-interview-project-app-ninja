# NinjaOne Backend Interview Project - Lenín González

The project is configured to use an in-memory H2 database that is volatile. If you wish to make it maintain data on application shut down, you can change the spring.database.jdbc-url to point at a file like `jdbc:h2:file:/{your file path here}`

## Starting the Application

Previous: It is necesary to have an google account to acces application.

Run the `BackendInterviewProjectApplication` class

Go to:
* http://localhost:8080/api/swagger-ui/index.html

![Alt text](Authorize.jpg?raw=true "Title")

1.-First click on the authorize button, and will be redirected to auth0Login

![Alt text](auth0Login.jpg?raw=true "Title")

2.-Log in with your google account, and following authorization page will be shown.

![Alt text](AuthorizeApp.jpg?raw=true "Title")

3.-Click on the Accept button and following popup will be displayed.

![Alt text](auth0AvailableAuthorizations.jpg?raw=true "Title")

4.-Close popup and will be redirected to swagger

![Alt text](Authorized.jpg?raw=true "Title")

Now you are authorized to use api displayed through swagger.


If loged account exists when press authorize button from step one, you will be redirected to following popup:

![Alt text](Authorize1.jpg?raw=true "Title")

Inside popup click on the authorize button, and close popup.


## H2 Console 

In order to see and interact with your db, access the h2 console in your browser.
After running the application, go to:

http://localhost:8080/h2-console

Enter the information for the url, username, and password in the application.yml:

```yml
url: jdbc:h2:mem:localdb
username: sa 
password: password
```

You should be able to see a db console now that has business classes from Repository in it.

Type:

```sql
SELECT * FROM CUSTOMER;
````

Click `Run`, you should see three rows, charged when the app starts.

## Additional Considerations

Due to lack of time following considered topics would not be able to be included:

 * Better documentation
 * Data inconsistency validations, since physical database deletion is not considered, just logical deletion.
 * Data input validations.
 * Exceptions handle, especially to control hibernate jpa data base validations and restrictions.
 * Better usage of controller status responses
 * Tests
