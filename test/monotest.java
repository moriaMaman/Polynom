import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myMath.Monom;

class monotest {

	@Test
	void monomTest() {
		Monom m= new Monom(2,3);
		if(m.get_coefficient()!=2 || m.get_power()!=3)
			fail("The constructor doesnt work well");	
	}

	@Test
	void monomNegativeTest() {
		try {
			Monom m= new Monom(2,-3);
			fail("the constructor should not accept a negative power");
		}
		catch (Exception e) {

		}
	}

	@Test
	void derivativeTest() {
		Monom m= new Monom(2,3);
		Monom m1=m.Derivative();
		if(m1.get_coefficient() !=6 || m1.get_power()!=2)
			fail(" the derivative function dosent work well ");		
	}
	@Test
	void derivative0Test() {
		Monom m= new Monom(2,0);
		Monom m1=m.Derivative();
		if(m1.get_coefficient() !=0 || m1.get_power()!=0)
			fail(" the function should return the zero Monom ");		
	}

	@Test
	void getPowerTest() {
		Monom m= new Monom(2,3);
		if(m.get_power()!=3)
			fail(" the function dosent return the right value ");
	}

	@Test
	void getcofficentTest() {
		Monom m= new Monom(2,3);
		if(m.get_coefficient()!=2)
			fail(" the function dosent return the right value ");
	}

	@Test
	void addTest() {
		Monom m= new Monom(2,3);
		Monom m1= new Monom(2,3);
		m.add(m1);
		if(m.get_coefficient() !=4 || m.get_power()!=3)
			fail(" the function dosent calculate the add of the two monoms right ");
	}

	@Test
	void subTest() {
		Monom m= new Monom(4,3);
		Monom m1= new Monom(2,3);
		m.sub(m1);
		if(m.get_coefficient()!=2 || m.get_power()!=3)
			fail(" the function dosent calculate the sub of the two monoms right ");
	}

	@Test
	void multiplyTest() {
		Monom m= new Monom(4,3);
		Monom m1= new Monom(2,5);
		m.multiply(m1);
		if(m.get_coefficient()!=8 || m.get_power()!=8)
			fail(" the function dosent calculate the Monoms multiply right ");
	}

	@Test
	void fTest() {
		Monom m= new Monom(2,3);
		double x= m.f(5); 
		if(x!= 250)
			fail("the function dosent calculate the value of the monom right ");
	}
	@Test
	void copyTest() {
		Monom m1=new Monom(3,4);
		Monom m2=new Monom(m1);
		if(m1.get_coefficient()!=m2.get_coefficient()&&m1.get_power()!=m2.get_power()) {
			fail("the copy constructor dosent work well");
		}
	}

	@Test
	void equalsTest() {
		Monom m1=new Monom(2,8);
		Monom m2=new Monom(3,8);
		if(m1.equals(m2)) {
			fail("the function dosent work well");
		}

	}

}
