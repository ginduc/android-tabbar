Dev Notes
=========

1. Install the supplied Android Support v4 Jar on your local maven repo:
`mvn install:install-file -Dfile=libs/android-support-v4.jar -DgroupId=com.google.android -DartifactId=support-v4 -Dversion=r12 -Dpackaging=jar`

2. Run maven command to build the entire project:
`mvn clean package`


Use Eclipse (3.6.x / Indigo)
------------

1. Install Android Maven Connector in Eclipse:
http://rgladwell.github.io/m2e-android/

2. Run maven command to generate Eclipse project:
`mvn eclipse:eclipse`

3. Add gen folder to source build path in Eclipse:
	- Right click gen folder in Eclipse Package Explorer: Build Path > Use as Source Folder

4. Fix Project Properties Builder Options:
http://www.fairtec.at/en/it-blog-mainmenu-16/168-the-type-r-is-already-defined

Use IntelliJ IDEA (12.x)
------------------------

1. Run maven command to generate Idea project:
`mvn idea:idea`

 
