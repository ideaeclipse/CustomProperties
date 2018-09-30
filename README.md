# CustomProperties
## Use cases/Features
* This util allows you to have a preset amount of keys that require you to input data for. I.e. a static file directory which could differ from system to system. An admin user that differs from user to user.
* This util allows you to be prompted for input and store that data into a file.
* Checks to see if you updated the keylist from the last time the program launched and will ask for more input on only the specific missing keys

##How to use
* To use the Custom Properties util all you need to do is create a properties object and send it a list of keys that require values
```java
Properties properties = new Properties(new String[]{"key1", "key2"});
properties.start();
```
* This example will ask you for two keys (key1,key2)
* After getting the input it stores the corresponding key and value pair into a config.properties file in your src directory
* Then the properties object works like a map
```java
System.out.println(properties.getProperty("key1"));
System.out.println(properties.getProperty("key2"));
```
* This will print out the two values you inputted for key1 and key2