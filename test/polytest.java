import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import myMath.Monom;
import myMath.Polynom;
import myMath.Polynom_able;

class polytest {

	static Polynom_able p1;
	static Polynom_able p2;
	static Polynom_able p3;
	static Polynom_able p4;
	static Polynom_able p5;
	static Polynom_able p6;
	static Polynom_able p7;
	static Polynom_able p8;
	static Polynom_able p9;
	static Polynom_able p10;
	static Monom m1;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		p1=new Polynom();
		p1.add(new Monom(3,2));
		p1.add(new Monom(-6,0));
		p1.add(new Monom(5,3));
		p1.add(new Monom(3,1));
		p1.add(new Monom(7,5));

		p2=new Polynom();
		p2.add(new Monom(3,2));
		p2.add(new Monom(-6,0));
		p2.add(new Monom(5,3));
		p2.add(new Monom(3,1));
		p2.add(new Monom(7,5));

		p3 = new Polynom("2x^2-7x+0.4");

		p4 = new Polynom("-6+3x+3x^2+5x^3+7x^5");

		p5 = new Polynom("-6+3x+3x^2+5x^3+7x^5");
		
		p6=new Polynom();
		p6.add(new Monom(3,2));
		p6.add(new Monom(-6,0));
		
		p7 = new Polynom("2x^2-7x+2");
		
		p8 = new Polynom("-8x^3+9");
		
		p9=new Polynom();
		p9.add(new Monom(3,2));
		p9.add(new Monom(-6,0));
		p9.add(new Monom(5,3));
		
		p10 = new Polynom("3x^2-6+5x^3");
		

		m1=new Monom(2,3);
	}

	@Test
	void fTest() {
		double x= p1.f(1);
		if(x!=12)
			fail("the function dosent return the right valus");
		double x2= p3.f(2);
		if(x2!=-5.6)
			fail("the function dosent return the right valus");	
	}
	@Test
	void addPTest() {
		p2.add(p3);
		String polyS="-5.6-4.0x+5.0x^2+5.0x^3+7.0x^5";
		if(!polyS.equals(p2.toString()))
		{
			fail("the function dosent work well");
		}
	}

	@Test
	void addMTest() {
		p4.add(m1);
		String polyS="-6.0+3.0x+3.0x^2+7.0x^3+7.0x^5";
		if(!polyS.equals(p4.toString()))
		{
			fail("the function dosent work well");
		}
	}

	@Test
	void substractPTest() {
		p5.substract(p3);
		String polyS="-6.4+10.0x+x^2+5.0x^3+7.0x^5";
		if(!polyS.equals(p5.toString()))
		{
			fail("this function dosent work well");
		}
	}

	@Test
	void multiplyTest() {
         p6.multiply(p7);
         String polyS="-12.0+42.0x-6.0x^2-21.0x^3+6.0x^4";       
         if(!polyS.equals(p6.toString()))
 		{
 			fail("the function dosent calculate the Polynoms multiply right  ");
 		}
	}
	
	@Test
	void zeroTest() {
		Polynom_able p1 = new Polynom("3x^2-3x^2");
		Polynom_able p2 = new Polynom("7");
		Polynom_able derpoly= p2.derivative();
		boolean iszerop1=p1.isZero();
		boolean iszerop2=derpoly.isZero();
		if(!iszerop1 || !iszerop2)
			fail("the function dosent work well");
	}
	
	@Test
	void derivativeTest() {
		Polynom_able derpoly= p3.derivative();
		String polyS="-7.0+4.0x"; 
		if(!polyS.equals(derpoly.toString()))
 		{
 			fail("the derivative function is not working well");
 		}	
	}
	
	@Test
	void areaTest() {
		Polynom_able poly=new Polynom("7+x^2")	;	
		double area=poly.area(3, 5, 0.00001);
		if(Math.abs(area - 46.666906666606025) > 0.00001) {
 			fail("the function dosent work well");
		}
	}
	
	@Test
	void rootTest() {
		Polynom_able poly=new Polynom("-7+3.2x^2+2x^5");
		double root=poly.root(-2, 2, 0.00001);
		if((Math.abs(root-1.0956802368164062))>0.00001 ) {
 			fail("the function dosent work well");
		}
	}
	@Test
	void equalTest()
	{
		boolean equal=p9.equals(p10);
		if(!equal)
			fail("The two polynom are equal, there for the should return 'True'");
	}
	
	@Test
	void copyTest()
	{
		Polynom_able copypoly= p9.copy();
		String polyS="-6.0+3.0x^2+5.0x^3"; 
		if(!polyS.equals(copypoly.toString()))
 		{
 			fail("the function should return 'true' -the two polynoms should have the same value");
 		}		
	}


}
