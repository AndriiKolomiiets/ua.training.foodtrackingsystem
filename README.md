# Project name - Food Tracking System
## Project description:
Webapp for Food Tracking and weight control. User selects food (name, count. proteins, fats, carbohydrates), 
to count calories. Food can be chosen from the list. Also user can add its own food (name, calories, count,
proteins, lipids, carbohydrates). If user exceeded its norm of calories, the system will inform him about this
and write exact number of calories. The norm of calories is calculated from the parameters of the client 
(age, height, weight, lifestyle, etc.).
# Developer:
Andrii Kolomiiets
# Mentor:
Maksym Liashenko

# How to run project:
1. Install maven http://www.apache-maven.ru/install.html
2. In project directory open PowerShell/CMD
3. Enter command "mvn tomcat7:run"
4. Open browser and follow the link http://localhost:8888/fts/

Unfortunately, project can be ran only with IDE. 
War version for Maven was modified from master branch, but doesn't run.