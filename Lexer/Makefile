Main: *.java
	javac *.java
	
run: Main
	java Main
	
clean:
	rm *.class
	
package:
	javac -d ../Parser Lexer.java Context.java TokenList.java TokenNode.java
	javac -d ../Task2a-Type\ Checker/ Lexer.java Context.java TokenList.java TokenNode.java