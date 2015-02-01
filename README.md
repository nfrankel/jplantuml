JPlantUML is a Java API to interact with [PlantUML](http://plantuml.com/).

I knew about 3 different ways to interact with PlantUML:

1. The [online](http://www.plantuml.com/plantuml/form) server
2. A confluence plugin
3. Installing the stuff on the local machine

*Note*: since writing this, I've learned there are [many more ways](http://plantuml.com/download.html).

I made heavy use of PlantUML while writing [Integration Testing from the Trenches](https://leanpub.com/integrationtest/) and at 
work. However, while usage (1) is the nominal one, sometimes one has to apply a different global parameter - such as `skinparam dpi
 300`,  to all previously created text files and this is just not acceptable to do it manually for ~100 source files.
   
JPlantUML is a way to achieve that handily. Just put all your source files into one directory, 
launch the batch with the desired parameters and you're good.

JPlantUML consists of 3 modules:

* `jplantuml-api` defines a super simple API, based on Java 8's [Function](http://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html) interface to be easily composable
* `jplantuml-online` uses the online server to generate the graphics
* `jplantuml-batch` builds on the previous module to generates images from the content of a folder