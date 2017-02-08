

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class io {
	public static void print(String s){
		 System.out.println(s);
	}
	public static void print(Object s){
		 System.out.println(s.toString());
	}
	public Scanner sc(){
		return new Scanner(System.in);
	}
	public int scan_int(){
		Scanner sc = this.sc();
		int i = sc.nextInt();
		return i;
		
	}
	public String scan_string(){
		Scanner sc = this.sc();
		String i = sc.nextLine();
		return i;
		
	}
	public float scan_float(){
		Scanner sc = this.sc();
		float i = sc.nextFloat();
		return i;
		
	}
	public char scan_char(int index){
		char i = this.scan_string().charAt(index);
		return i;
		
	}
	public static Optional pscan(String ph,String scan_quoi) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException{
		 	Object[] obj = {};// for method1()
	        // Object[] obj={"hello"}; for method1(String str)
	        // Object[] obj={"hello",1}; for method1(String str,int number)
	        // Step 2) Create a class array which will hold the signature of the
	        // method being called.
	        Class<?> params[] = new Class[obj.length];
	        for (int i = 0; i < obj.length; i++) {
	            if (obj[i] instanceof Integer) {
	                params[i] = Integer.TYPE;
	            } else if (obj[i] instanceof String) {
	                params[i] = String.class;
	            } else if (obj[i] instanceof Double) {
	                params[i] = Double.TYPE;
	            }
	            // you can do additional checks for other data types if you want.
	        }

	        String methoName = "scan_"+scan_quoi; // methodname to be invoked
	        String className = "lulu.io.io_helper";// Class name
	        Class<?> cls = null;
			try {
				cls = Class.forName(className);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Object _instance = cls.newInstance();
	        Method myMethod = cls.getDeclaredMethod(methoName, params);
	        print(ph);
	        try {
	        	return Optional.of(myMethod.invoke(_instance, obj));
	        }catch (InputMismatchException | InvocationTargetException e){
	        	return Optional.empty();
	        }
	}
	public Object pscan(String ph,String scan_quoi,int index ) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
	 	// Object[] obj = {};// for method1()
        // Object[] obj={"hello"}; for method1(String str)
         Object[] obj={index}; //for method1(String str,int number)
        // Step 2) Create a class array which will hold the signature of the
        // method being called.
        Class<?> params[] = new Class[obj.length];
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] instanceof Integer) {
                params[i] = Integer.TYPE;
            }
            // you can do additional checks for other data types if you want.
        }

        String methoName = "scan_"+scan_quoi; // methodname to be invoked
        String className = "lulu.io.io_helper";// Class name
        Class<?> cls = null;
		try {
			cls = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Object _instance = cls.newInstance();
        Method myMethod = cls.getDeclaredMethod(methoName, params);
        print(ph);
        return myMethod.invoke(_instance, obj);
}
}
