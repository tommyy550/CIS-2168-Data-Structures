package TextEditor;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Editor
{
  private LLComp<String> theText;
  private String prompt;
  private enum Keywords  {READ, SAVE, LIST, RESEQUENCE, QUIT, EXIT, UNDEFINED;};
  private Scanner console;


  public Editor()
  {
    this.theText = new LLComp<String>();
    this.prompt = ">";
    this.console = new Scanner(System.in);
  }

  /*public String getPrompt()
  {
  }*/


  public void setPrompt(String p)
  {
  }

  private static boolean isInt(String s) // see if a string represents
  {                                      // an integer.
    boolean retval = false;
    try
    {
      Integer.parseInt(s);
      retval = true; 
    }
    catch (NumberFormatException e)
    {
      retval = false;
    }
    return retval;
  }
 
  public void process()
  {
    boolean done = false;
    String line;
    while (!done)
    {
      System.out.print(this.prompt);
      line = console.nextLine().toUpperCase(); // Work only with upper case
      String splitString[] = line.split(" ", 2);
// at this point, the line that was read in has been split into two
// arrays.  splitString[0] contains the first token, splitString[1] 
// contains all the rest of the line.

//At this point, you need to decide whether this is a command or
//a line of text to be entered.
      if (this.isInt(splitString[0]))
      {
// Here we have a line of text to be entered.  Write the code to
//insert it into the LLComp named theText.
    	  theText.insertInOrder(splitString[0],splitString[1]);
      }
      else //otherwise, it is a command, so call doCommand to perform it.
        done = this.doCommand(splitString[0]);
    }
  }

  private boolean doCommand(String com)
  {
    boolean retval = false;
    Keywords command;
//This first bit takes the string in the first word of the line
//and turns it into one of the manifest constants of the 
//enumerated data type.  This makes it fairly easy to add new
//commands later.
    try
    {
      command = Keywords.valueOf(com);// command is a Keywords and can
    }                                 // can be used as the target of a switch.
    catch (IllegalArgumentException e)
    {
      command = Keywords.UNDEFINED; //An undefined Keywords will cause
    }                               //an exception. 
    switch (command)
    {
case READ: this.read();
           break;
case SAVE: this.save();
           break;
case LIST: this.list();
           break;
case RESEQUENCE: this.resequence();
           break;
case QUIT:
case EXIT: retval = true; 
           break;
case UNDEFINED: System.out.println("Undefined command:" + com);
    }
    return retval;
  }

// You need to implement the following routines.
  
  private void read()
  {
	  System.out.println(theText.read());
  }

  private void save()
  {
	  try {
			File file = new File("lab5testing");
			FileWriter fileWriter = new FileWriter(file);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.print(theText);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
  
  private void list()
  {
	 System.out.println(theText.toString());
  }

  private void resequence()
  {
	theText.resenquence();
	System.out.println(theText.toString());
	
  }

  public static void main(String args[])
  {
    Editor e = new Editor();
    e.process();

  }
}

