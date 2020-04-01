include ../java.mk

TESTCLASSFILES = 
CLASSFILES = ../SLList/SLList.class ListVisualiser.class ListVisualiserDriver.class $(TESTCLASSFILES)

CLASSPATHS += ../SLList

all: $(CLASSFILES)

clean:
	-rm -f $(TESTCLASSFILES) test.xml

%.class: %.java
	$(JAVAC) $(CP) $(CLASSPATH) $^

test: all
	$(JAVA) -Xss10m $(CP) $(CLASSPATH) ListVisualiserDriver cyclic


.PHONY: test all clean
