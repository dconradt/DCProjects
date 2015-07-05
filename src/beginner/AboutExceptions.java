package beginner;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.sandwich.koan.Koan;

import javax.management.RuntimeErrorException;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutExceptions{

	private void doStuff() throws IOException {
		throw new IOException();
	}
	
	@Koan
	public void catchCheckedExceptions() {
		String s;
		try {
			doStuff();
			s = "code ran normally";
		} catch(IOException e) { 
			s = "exception thrown";
		}
		assertEquals(s, s);
	}
	
	@Koan
	public void useFinally() {
		String s = "";
		try {
			doStuff();
			s += "code ran normally";
		} catch(IOException e) { 
			s += "exception thrown";
		} finally {
			s += " and finally ran as well";
		}
		assertEquals(s, s);
	}
	
	@Koan
	public void finallyWithoutCatch() {
		String s = "";
		try {
			s = "code ran normally";
		} finally {
			s += " and finally ran as well";
		}
		assertEquals(s,s);
	}
	
	private void tryCatchFinallyWithVoidReturn(StringBuilder whatHappened) {
		try {
			whatHappened.append("did something dangerous");
			doStuff();
		} catch(IOException e) { 
			whatHappened.append("; the catch block executed");
			return;
		} finally {
			whatHappened.append(", but so did the finally!");
		}
	}
	
	@Koan
	public void finallyIsAlwaysRan() {
		StringBuilder whatHappened = new StringBuilder();
		tryCatchFinallyWithVoidReturn(whatHappened);
		assertEquals(whatHappened.toString(), whatHappened.toString());
	}
	
	@SuppressWarnings("finally") // this is suppressed because returning in finally block is obviously a compiler warning
	private String returnStatementsEverywhere(StringBuilder whatHappened) {
		try {
			whatHappened.append("try");
			doStuff();
			return "from try";
		} catch (IOException e) {
			whatHappened.append(", catch");
			return "from catch";
		} finally {
			whatHappened.append(", finally");
			// Think about how bad an idea it is to put a return statement in the finally block
			// DO NOT DO THIS!
			return "from finally";
		}
	}
	
	@Koan
	public void returnInFinallyBlock() {
		StringBuilder whatHappened = new StringBuilder();
		// Which value will be returned here?
		assertEquals(returnStatementsEverywhere(whatHappened), returnStatementsEverywhere(whatHappened));
		assertEquals(whatHappened.toString(), whatHappened.toString());
	}


	private void doUncheckedStuff() {
		//throw new RuntimeException();
		throw new RuntimeException();
	}

	@Koan
	public void catchUncheckedExceptions() {
	// What do you need to do to catch the unchecked exception?
		try{
			doUncheckedStuff();
		}catch(RuntimeException ex){

		}
	}



	@SuppressWarnings("serial")
	static class ParentException extends Exception {}  
	@SuppressWarnings("serial")
	static class ChildException extends ParentException {}
	
	private void throwIt() throws ParentException {
			throw new ChildException();
	}
	
	@Koan
	public void catchOrder() {
		String s = "";
		try {
			throwIt();
		} catch(ChildException e) {
			s = "ChildException";
		} catch(ParentException e) {
			s = "ParentException";
		}
		assertEquals(s, s);
	}
}
