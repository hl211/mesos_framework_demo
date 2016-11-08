Hello,

This is a simple example framework accompanying a blog post that can be found here: http://www.opencredo.com/2015/02/16/write-mesos-framework/

Build
-----

```
mvn clean package
```

This will produce the artefact: target/example-framework-1.0-SNAPSHOT-jar-with-dependencies.jar


Run
---


Run the jar:

```
java -Djava.library.path=/home/hl/mesos_install/lib  -jar target/example-framework-1.0-SNAPSHOT-jar-with-dependencies.jar 127.0.0.1:5050
``` 

NOTE
---
  -Djava.library.path=/home/hl/mesos_install/lib  is  install path, it is configure  --prefix=PATH 
   在 /mesos_framework_demo/src/main/java/org/opencredo/mesos/MainRunner.java  中的path
  static String path = "/home/hl/mesos_install/target/example-framework-1.0-SNAPSHOT-jar-with-dependencies.jar";
  change path  is absoulte  path,that is jar package.

*You'll need to point java to the mesos shared library, which is usually found in /usr/local/lib* 
