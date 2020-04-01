include ../java.mk

TESTCLASSFILES = NaiveStringMatchTest.class RKStringMatchTest.class KMPStringMatchTest.class
CLASSFILES = StringMatch.class NaiveStringMatch.class RKStringMatch.class KMPStringMatch.class StringMatchTest.class ../utils/OpCounter.class StringMatchCount.class $(TESTCLASSFILES)

CLASSPATHS += ../utils

all: $(CLASSFILES)

test: all
	(((($(JAVA) -Xss10m $(CP) $(CLASSPATH) org.junit.runner.JUnitCore NaiveStringMatchTest; echo $$? >&3) | egrep -v \(org.junit\|sun.reflect\|java.lang.reflect\) >&4) 3>&1) | (read xs; exit $$xs)) 4>&1
	(((($(JAVA) -Xss10m $(CP) $(CLASSPATH) org.junit.runner.JUnitCore RKStringMatchTest; echo $$? >&3) | egrep -v \(org.junit\|sun.reflect\|java.lang.reflect\) >&4) 3>&1) | (read xs; exit $$xs)) 4>&1
	(((($(JAVA) -Xss10m $(CP) $(CLASSPATH) org.junit.runner.JUnitCore KMPStringMatchTest; echo $$? >&3) | egrep -v \(org.junit\|sun.reflect\|java.lang.reflect\) >&4) 3>&1) | (read xs; exit $$xs)) 4>&1

count: all
	$(JAVA) $(CP) $(CLASSPATH) StringMatchCount

clean:
	-rm -f $(TESTCLASSFILES) test.xml

%.class: %.java
	$(JAVAC) $(CP) $(CLASSPATH) $^

.PHONY: test all clean
