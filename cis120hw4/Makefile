# DO NOT EDIT THIS FILE

SUBMIT  := src/AdvancedManipulations.java src/Pixel.java src/SimpleManipulations.java test/MyPixelTest.java test/MyTest.java

JAVA	:= java
JAVAC	:= javac
JUNIT   := /home/codio/bin/junit-platform-console-standalone-1.6.1.jar

DEPENDS := \
	src/AdvancedManipulations.java \
	src/ColorMap.java \
	src/Effects.java \
	src/GUI.java \
	src/Pixel.java \
	src/PixelPicture.java \
	src/PointQueue.java \
	src/SimpleManipulations.java

TESTS_SRC := \
	test/ManipulateTest.java \
	test/MyPixelTest.java \
	test/MyTest.java \

TESTS := \
	bin/ManipulateTest.class \
	bin/MyPixelTest.class \
	bin/MyTest.class \


HWNAME := hw04
ts := $(shell /bin/date "+%Y-%m-%d-%H:%M:%S")

ZIPNAME := $(HWNAME)-submit($(ts)).zip

.PHONY: all GUI clean zip checkstyle test

all:	

bin:
	mkdir bin

GUI : $(DEPENDS) bin
	$(JAVAC) -d bin $(DEPENDS)

run% : bin/GUI.class
	java -cp bin GUI

zip: $(SUBMIT)
	zip '$(ZIPNAME)' $(SUBMIT)
	
test: GUI $(TESTS_SRC) $(JUNIT) bin 
	$(JAVAC) -d bin -cp $(JUNIT):bin/ $(TESTS_SRC)
	$(JAVA) -jar $(JUNIT) --classpath bin --scan-class-path --disable-banner

checkstyle: ~/bin/checkstyle.sh ~/bin/cis120-checkstyle-config.xml ~/bin/checkstyle-8.12-all.jar
	cd ~/bin && ./checkstyle.sh

clean:
	rm -f src/*.class test/*.class bin/*
	rm -rf *.zip
