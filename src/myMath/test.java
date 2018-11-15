package myMath;

import java.lang.invoke.WrongMethodTypeException;
import java.util.Iterator;

public class test {

	public static void main(String[] args) {
		
		  Polynom p2 = new Polynom("0.2x^4-1.5x^3+3.0x^2-x-5");
	        Polynom_able divPoly=p2.derivative();
	        System.out.println(divPoly.root(0.0, 0.25, 0.000000000000001));
		System.out.println("######test1 of Monom######");
		test1();
		System.out.println("\n\n######test2######\n");
		test2();
		
	}
	
	public static void test1() {
		//zero test
		Polynom_able p1 = new Polynom();
		p1.add(new Monom(0.2,0));
		p1.add(new Monom(-0.2,0));
		p1.add(new Monom(3,2));
		p1.add(new Monom(-1,2));
		p1.add(new Monom(-2,2));
		
		System.out.println("zero:"+p1.toString());
		
		Polynom_able p2 = new Polynom("3x^2-3x^2");//check if the zero also work from string
		System.out.println("get zero:"+p2.toString());
		Polynom poly=new Polynom("-1+x");
		System.out.println("poly:"+poly);
		System.out.println("get true:"+p2.equals(p2));//check if zero = zero
		
		Polynom_able p3 = new Polynom("2x^2-7.2x+0.4");
		Polynom_able p4 = new Polynom("0.4-7.2x+2x^2");
		System.out.println("get true:"+p3.equals(p4));//check is it equal even if didn't write the same
		Polynom pp = new Polynom("0.2x^4-1.5x^3+3.0x^2-x-5");
		Polynom_able divPoly=pp.derivative();
	    System.out.println(divPoly.root(0.0, 0.25, 0.000000000000001));
		
		//polynom test
		p2.add(new Monom(3.2,2));
		p2.add(new Monom(4,7));
		p2.add(new Monom(3.1,1));
		p2.add(new Monom(-2,0));
		p2.add(new Monom(-3.4,5));
		
		System.out.println("get this polynom \"-2+3.1x+3.2x^2-3.4x^5+4x^7\":");
		System.out.println("polynom is: "+p2);
		
		//copy 1 test
		p1=p2.copy();
		System.out.println("get the same polynom as:"+p2);
		System.out.println("p1:"+p1);
		
		//copy 2 test
		p1=new Polynom(p2.toString());
		System.out.println("get the same polynom as:"+p2);
		System.out.println("p1:"+p1);	

		//equals test
		System.out.println("get true:"+p3.toString().equals(p4.toString()));
		
		//add monom test
		Monom m1=new Monom(2,3);
		Monom m2=new Monom(5,3);
		m1.add(m2);
		System.out.println("get : 7x^3");
		System.out.println(m1);
		
		//multiply Monom test 
		Monom m3=new Monom(2,3);
		Monom m4=new Monom(5,5);
		m3.multiply(m4);
		System.out.println("get : 10x^8");
		System.out.println(m3);
		
		//test sub Monom
		Monom m5=new Monom(2,3);
		Monom m6=new Monom(5,3);
		m5.sub(m6);
		System.out.println("get : -3x^3");
		System.out.println(m5);
		
		//test derivative Monom
		Monom m10=new Monom(0,0);
		Monom m7=new Monom(2,3);
		 m10=m7.Derivative();
		System.out.println("get : 6x^2");
		System.out.println("m10:"+m10);
		
		//Iterator test
		System.out.println("get \"0, 1, 2, 5, 7\":");
		System.out.println("p2:"+p2);
		Iterator<Monom> iterator = p2.iteretor();
		while(iterator.hasNext()){
			System.out.print(iterator.next().get_power() + ", ");
		}
		System.out.println();
		
	}
	
	public static void test2()  {
		
		Polynom_able p1 = new Polynom("3.2x^4+5x^2-2x+7");
		Polynom_able p2 = new Polynom("5.3x^8+8.1x^2-3x+0.9");
		
		//add
		System.out.println("p1:"+p1);
		System.out.println("p2:"+p2);
		p1.add(p2);
		System.out.println("get \"5.3x^8+3.2x^4+13.1x^2-5x+7.9\":");
		System.out.println(p1);
		
		//sub
		Monom m1=new Monom(-0.2,0);
		Monom m6=new Monom(-0.2,0);
		Monom m2=new Monom(4.1,1);
		Monom m3=new Monom(-3,2);
		Monom m4=new Monom(2.1,1);
		Monom m5=new Monom(-1,3);
		Polynom_able p3 = new Polynom();
		Polynom_able p4 = new Polynom();
		p3.add(m1);
		p3.add(m2);
		p3.add(m3);
		p4.add(m6);
		p4.add(m4);
		p4.add(m5);
		p3.substract(p4);
		System.out.println("get:2x-3x^2+x^3");
		System.out.println(p3);

		//multiply
		p3.multiply(p4);
		System.out.println("get \"-0.4x+4.8x^2-6.5x^3+0.1x^4+3x^5-x^6x\":");
		System.out.println(p3);
		
		//derivative
		System.out.println("get \"-5.0+26.2x+12.8x^3+42.4x^7\":");
		System.out.println(p1.derivative());
		
		System.out.println("get \"-3.0+16.2x+42.4x^7\":");
		System.out.println(p2.derivative());
		
		//f(x)
		System.out.println(p1);
		System.out.println(p2);
		System.out.println("get 35143.3:" + p1.f(3));
		System.out.println("get 34838.1:" + p2.f(3));
		
		
		
		//root and area
		double eps =0.0001;
		System.out.println("\nroot\n");
		Polynom_able p5 = new Polynom("3.2x^2-7+2x^5");
		Polynom_able p6 = new Polynom("x^2+7");
		
		
		//root p1
		System.out.println("get something close to 1:" + p5.root(-2, 2, eps));
		
		Polynom newPoly=new Polynom("2x^2-8X+x^3");
		System.out.println("get something close to:-4.02 "+newPoly.root(-5.03, -3.01, 0.00001));
		
		eps = 0.00001;
		System.out.println("\narea\n");
		//area
		System.out.println("get something close to 46.66667:" + p6.area(3, 5, eps));
		
		//check what happened if i put a valid string to the polynom
		Polynom_able p7 = new Polynom("3+++++x");
		System.out.println(p7);
	}

}
