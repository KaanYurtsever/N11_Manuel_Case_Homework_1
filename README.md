#Get a Store Name from the Excel File
##Description of Project
In this project, N11 Store Names have been imported to an Excel File. Desired store name have been exported from the Excel file and store's page opened.  
BDD test methodology is important for this project.
Cucumber, selenium and junit4 have been used. This project is created by using POM page object model design pattern.
##How to run scenario?
- Go to configuration.properties and then choose the browser type.
- Go to the runners in src and run the test.
##How to run scenario in Docker selenium grid container?
- Open the terminal and write 'docker-compose up'
- Go to configuration.properties and then chose the remote-browser type.
- Go to the runners in src and run the test.
- If you want to watch the test, go to the localhost:4444 and click sessions (password==secret)
- Use Ctrl+C for exiting the container.
##How to see the report of test?
- Write to terminal 'allure serve' to see the report.
- Note: If you use windows, you should download allure from there 'https://docs.qameta.io/allure/', and go to environment variables then add to path.

- Download the latest version as zip archive from Maven Central.

Unpack the archive to allure-commandline directory.

Navigate to bin directory.

Use allure.bat for Windows or allure for other Unix platforms.

Add allure to system PATH.
