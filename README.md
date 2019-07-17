# This file describes the test setup for commerce

1. Copy the jmeterperftest directory under user home.

## Hybris test setup
1. Extract/ Copy the bigelectronicsaddon into the hybris/bin/custom directory. If the directory doesnt exist, create one.
2. Add the bigelectronicsaddon into the localextensions.xml
3. Install the addon on the storefront: ant addoninstall -Daddonnames="bigelectronicsaddon" -DaddonStorefront.yacceleratorstorefront="yacceleratorstorefront"
4. ant all initialize && ./hybrisserver.sh

## Jmeter setup
1. Download and install the latest version of jmeter.
2. Edit jmeter.properties (location: /usr/local/Cellar/jmeter/5.0/libexec/bin/jmeter.properties on Mac , /home/JmeterTestLinuxVMUser/apache-jmeter-5.1/bin/jmeter.properties in Linux )
Set javascript.use_rhino=true
3. Extract/Copy the jmeterperftest under user home.
4. Choose the store you want to test → apparel / electronics / <custom>. Modify value of inputDataFolder property
5. The report generation properties have already been added to the user.properties. 
6. Thats it! We are all set to start the tests!

## Performance tests execution

### Execute the tests
./jmeter -q ~/jmeterperftest/resources/jmeter/webshop_user.properties -n -t ~/jmeterperftest/resources/jmeter/AcceleratorTestPlan.jmx

### Generate report
./jmeter -g ~/jmeterperftest/resources/jmeter/electronics/results/<results.csv> -o ~/jmeterperftest/resources/jmeter/electronics/results/HTMLReports -q ~/jmeterperftest/resources/jmeter/webshop_user.properties

NOTE that the above mentioned commands are specific to a b2c webshop test. 

For backoffice, use the property file backoffice_user.properties and the BackOfficeTestPlan.jmx

