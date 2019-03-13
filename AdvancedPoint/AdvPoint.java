
/*
 * 190
 * 190_Ch7_AdvPoint
 * 190_Ch7_AdvPoint.java
 * I Denney 
 */

public class AdvPoint {
  
  private static final double EPS=.001;
  private double x,y;
  private String name;
  
  public AdvPoint() {
    this.name="";
    this.x=0;
    this.y=0;
  }
  public AdvPoint(String name,double x,double y) {
    setName(name);
    setX(x);
    setY(y);
  }
  
  public void set(String name, double x, double y) {
    setName(name);
    setX(x);
    setY(y);
  }
  public void setName(String value) {
    this.name=value;
  }
  public void setX(double value) {
    this.x=value;
  }
  public void setY(double value) {
    this.y=value;
  }
  
  public String getName() {
    return this.name;
  }
  public double getX() {
    return this.x;
  }
  public double getY() {
    return this.y;
  }
  
  public void display() {
    System.out.println(toString());
  }
  
  public static boolean equals(AdvPoint point1,AdvPoint point2) {
    boolean result=false;
    
    if (point1!=null&&point2!=null) {
      if (
          (
           (point1.getName()==null&&point2.getName()==null)||
           (
            (point1.getName()!=null&&point2.getName()!=null)&&
            (point1.getName()==point2.getName())
           )
          )&&
          isEqual(point1.getX(),point2.getX())&&
          isEqual(point1.getY(),point2.getY())) {
        result=true;
      }
    } else if (point1==null&&point2==null) {
      result=true;
    }
    
    return result;
  }
  public boolean equals(AdvPoint value) {
    boolean result=false;
    
    result=equals(this,value);
    
    return result;
  }
  private static boolean isEqual(double val1,double val2) {
    boolean result=false;
    
    if (Math.abs(val1-val2)<EPS) {
      result=true;
    }
    
    return result;
  }
  
  public boolean isOrigin(AdvPoint value) {
    boolean result=false;
    
    if (value!=null) {
      double drift=0;
      
      drift+=(Math.abs(this.getX()-value.getX()));
      drift+=(Math.abs(this.getY()-value.getY()));
      
      if (drift<=EPS) { result=true; }
    }
    
    return result;    
  }
  
  public static AdvPoint add(AdvPoint point1,AdvPoint point2) {
    
    AdvPoint thisPoint=null;
    String name="";
    
    if (point1!=null&&point2!=null) {
      name=point1.getName()+point2.getName();
    } else if (point1!=null) {
      name=point1.getName();
    } else if (point2!=null) {
      name=point2.getName();
    } 
    
    thisPoint=add(name,point1,point2);
    
    return thisPoint;
    
  }
  public static AdvPoint add(String name,AdvPoint point1,AdvPoint point2) {
    
    AdvPoint thisPoint=null;
    
    if (point1!=null&&point2!=null) {
      thisPoint=new AdvPoint();
      thisPoint=new AdvPoint(
                             name,
                             point1.getX()+point2.getX(),
                             point1.getY()+point2.getY()
                            );
    } else if (point1!=null) {
      thisPoint=new AdvPoint();
      thisPoint=new AdvPoint(
                             name,
                             point1.getX(),
                             point1.getY()
                            );      
    } else if (point2!=null) {
      thisPoint=new AdvPoint(
                             name,
                             point2.getX(),
                             point2.getY()
                            );
    } 
    
    return thisPoint;
    
  }
  
  public String toString() {
    String result="";
    
    result="(" + this.x + ", " + this.y + ")";
    
    return result;
  }
}