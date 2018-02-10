#!/bin/bash

mvn clean package
java -cp target/your_jar.jar com.mycompany.app.test 
