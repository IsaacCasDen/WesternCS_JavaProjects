
/*
 * 190
 * 190_Ch7_TestAdvPoint
 * 190_Ch7_TestAdvPoint.java
 * I Denney 
 */

public class TestAdvPoint {

  public static boolean verbose=true;
  
  private static boolean statusTestA_Constructor=false;
  private static String textTestA_Constructor="Test Constructor (w/o parameters)";
  
  private static boolean statusTestB_Constructor=false;
  private static String textTestB_Constructor="Test Constructor (w parameters)";
  
  private static boolean statusTestC_set=false;
  private static String textTestC_set="Test Method (set)";
  
  private static boolean statusTestC_setName=false;
  private static String textTestC_setName="Test Method (setName)";
  
  private static boolean statusTestC_setX=false;
  private static String textTestC_setX="Test Method (setX)";
  
  private static boolean statusTestC_setY=false;
  private static String textTestC_setY="Test Method (setY)";
  
  private static boolean statusTestC_getName=false;
  private static String textTestC_getName="Test Method (getName)";
  
  private static boolean statusTestC_getX=false;
  private static String textTestC_getX="Test Method (getX)";
  
  private static boolean statusTestC_getY=false;
  private static String textTestC_getY="Test Method (getY)";
  
  private static boolean statusTestC_isOrigin=false;
  private static String textTestC_isOrigin="Test Method (isOrigin)";
  
  private static boolean statusTestC_add=false;
  private static String textTestC_add="Test Method (add)";
  
  private static boolean statusTestC_display=false;
  private static String textTestC_display="Test Method (display)";
  
  private static boolean statusTestC_equals=false;
  private static String textTestC_equals="Test Method (equals)";
  
  private static boolean statusTestC_toString=false;
  private static String textTestC_toString="Test Method (toString)";
  
  public static void main(String[] args) {
   
    boolean testStatus=runTests();
    String output="";
    
    if (testStatus) {
      output="The object passed testing of all expected methods and constructors";
      if (verbose) {
        output+="\nThe following tests were run: ";
        if (statusTestA_Constructor) { output+="\n" + textTestA_Constructor; }
        if (statusTestB_Constructor) { output+="\n" + textTestB_Constructor; }
        if (statusTestC_set) { output+="\n" + textTestC_set; }
        if (statusTestC_setName) { output+="\n" + textTestC_setName; }
        if (statusTestC_setX) { output+="\n" + textTestC_setX; }
        if (statusTestC_setY) { output+="\n" + textTestC_setY; }
        if (statusTestC_getName) { output+="\n" + textTestC_getName; }
        if (statusTestC_getX) { output+="\n" + textTestC_getX; }
        if (statusTestC_getY) { output+="\n" + textTestC_getY; }
        if (statusTestC_isOrigin) { output+="\n" + textTestC_isOrigin; }
        if (statusTestC_add) { output+="\n" + textTestC_add; }
        if (statusTestC_display) { output+="\n" + textTestC_display; }
        if (statusTestC_equals) { output+="\n" + textTestC_equals; }
        if (statusTestC_toString) { output+="\n" + textTestC_toString; }
      }
    }else {
      output="The object failed testing of one or more expected methods and constructors";
      if (verbose) {
        output+="\nThe following tests failed or were unable to be run: ";
        if (!statusTestA_Constructor) { output+="\n" + textTestA_Constructor; }
        if (!statusTestB_Constructor) { output+="\n" + textTestB_Constructor; }
        if (!statusTestC_set) { output+="\n" + textTestC_set; }
        if (!statusTestC_setName) { output+="\n" + textTestC_setName; }
        if (!statusTestC_setX) { output+="\n" + textTestC_setX; }
        if (!statusTestC_setY) { output+="\n" + textTestC_setY; }
        if (!statusTestC_getName) { output+="\n" + textTestC_getName; }
        if (!statusTestC_getX) { output+="\n" + textTestC_getX; }
        if (!statusTestC_getY) { output+="\n" + textTestC_getY; }
        if (!statusTestC_isOrigin) { output+="\n" + textTestC_isOrigin; }
        if (!statusTestC_add) { output+="\n" + textTestC_add; }
        if (!statusTestC_display) { output+="\n" + textTestC_display; }
        if (!statusTestC_equals) { output+="\n" + textTestC_equals; }
        if (!statusTestC_toString) { output+="\n" + textTestC_toString; }
      }
    }
    
    print(true,output);
  }
  
  private static boolean runTests() {
    if (runTestA()&&runTestB()) {return runTestC();} else {return false;}
  }
    
  private static boolean runTestA() {
    boolean result=false;
    
    AdvPoint thisPoint=new AdvPoint(); statusTestA_Constructor=true;
    
    result=statusTestA_Constructor;
    
    return result;
  }
  private static boolean runTestB() {
    boolean result=false;
    
    AdvPoint thisPoint=new AdvPoint("A",1.4,2.7); statusTestB_Constructor=true;
    
    result=statusTestB_Constructor;
    
    return result;
  }
  private static boolean runTestC() {
    
    boolean result=false;
    String oldName="A";
    double oldX=1.7;
    double oldY=4.6;
    String newName="B";
    double newX=5.2;
    double newY=8.3;
    
    String sVal="";
    double dVal=0;
    
    AdvPoint thisPoint=new AdvPoint(oldName,oldX,oldY);
    
    sVal=thisPoint.getName();   if (sVal==oldName) { statusTestC_getName=true; }
    dVal=thisPoint.getX();      if (dVal==oldX) {statusTestC_getX=true; }
    dVal=thisPoint.getY();      if (dVal==oldY) { statusTestC_getY=true; }
    thisPoint.setName(newName); if (thisPoint.getName()==newName) { statusTestC_setName=true; }
    thisPoint.setX(newX);       if (thisPoint.getX()==newX) { statusTestC_setX=true; }
    thisPoint.setY(newY);       if (thisPoint.getY()==newY) { statusTestC_setY=true; }
    thisPoint.set(oldName,oldX,oldY); if (thisPoint.getName()==oldName&&thisPoint.getX()==oldX&&thisPoint.getY()==oldY) { statusTestC_set=true; }
    thisPoint.display();    statusTestC_display=true;
    
    if (
        new AdvPoint(oldName,oldX,oldY).equals(new AdvPoint(oldName,oldX,oldY))&&
        !new AdvPoint(oldName,oldX,oldY).equals(new AdvPoint(newName,oldX,oldY))&&
        !new AdvPoint(oldName,oldX,oldY).equals(new AdvPoint(newName,oldX,newY))&&
        !new AdvPoint(oldName,oldX,oldY).equals(new AdvPoint(newName,newX,oldY))&&
        !new AdvPoint(oldName,oldX,oldY).equals(new AdvPoint(newName,newX,newY))&&
        !new AdvPoint(oldName,oldX,oldY).equals(new AdvPoint(oldName,newX,oldY))&&
        !new AdvPoint(oldName,oldX,oldY).equals(new AdvPoint(oldName,newX,newY))&&
        !new AdvPoint(oldName,oldX,oldY).equals(new AdvPoint(oldName,oldX,newY))
          )
          {
      statusTestC_equals=true;
    } 
    
    if ( 
        new AdvPoint(oldName,oldX,oldY).isOrigin(new AdvPoint(newName,oldX,oldY))&&
        !new AdvPoint(oldName,oldX,oldY).isOrigin(new AdvPoint(oldName,newX,oldY))&&
        !new AdvPoint(oldName,oldX,oldY).isOrigin(new AdvPoint(oldName,oldX,newY))&&
        !new AdvPoint(oldName,oldX,oldY).isOrigin(new AdvPoint(oldName,newX,newY))
       ) 
    { 
      statusTestC_isOrigin=true; 
    }
    
    thisPoint=new AdvPoint(oldName,oldX,oldY);
    AdvPoint thatPoint=new AdvPoint(newName,newX,newY);
    AdvPoint testPoint=AdvPoint.add(thisPoint,thatPoint);
    
    if (
        testPoint.getName().equals(thisPoint.getName()+thatPoint.getName())&&
        testPoint.getX()==thisPoint.getX()+thatPoint.getX()&&
        testPoint.getY()==thisPoint.getY()+thatPoint.getY()
       ) {
      statusTestC_add=true;
    } 
    
    if (thisPoint.toString()!="") { statusTestC_toString=true; }
    
    result=(
            statusTestC_getName&&
            statusTestC_getX&&
            statusTestC_getY&&
            statusTestC_setName&&
            statusTestC_setX&&
            statusTestC_setY&&
            statusTestC_set&&
            statusTestC_isOrigin&&
            statusTestC_add&&
            statusTestC_display&&
            statusTestC_equals&&
            statusTestC_toString
           );
    
    return result;
  }
  
  private static void print(Object object) {
    print(false,object);
  }
  private static void print(boolean newLine, Object object) {
    if (newLine) {
      System.out.println(object);
    } else {
      System.out.print(object);
    }
  }
}