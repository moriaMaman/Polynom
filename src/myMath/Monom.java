package myMath;

import java.util.Iterator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Moria Maman and Atara Zohar 
 *
 */

public class Monom  implements function{

	private double _coefficient; // 
	private int _power;

	/**
	 * @param a -represent the coefficient.
	 * @param b -represent the power.
	 * the power must be a none negative integer,if the power is negative the contractor will throw an ArithmeticException.
	 */
	public Monom (double a, int b){
		if(b<0) {
			throw new ArithmeticException("the power cant be negative");
		}
		else {
			this.set_coefficient(a);
			this.set_power(b);
		}
	}

	/**
	 * this method gets a monom that represented by a string and turns it into a monom shape,the method is also gets the monom sign ("+" or "-"). 
	 * @param s the string that represent the monom.
	 * @param c the monom sign ("+" or "-") as it shows in the polynom.
	 * @return the monom representation in a monom tape.
	 */
	protected static Monom  from_string_to_Monom(String s, char c) {
		if (s==null)
		{
			throw new RuntimeException("the String should not be null");
		}
		String newS=s.toLowerCase();//in case the string contains big 'X' 
		Monom  mon=new Monom (0,0);
		if(newS.equals("x"))//if the String contains only "x"
		{
			if(c=='-')
				mon.set_coefficient(-1);
			else
				mon.set_coefficient(1);
			mon.set_power(1);
		}
		else {
			if(newS.contains("x")==false)//if it contains a value from the tape "a"-if it has only a coefficient 
			{
				if(c=='+')
				{
					mon.set_coefficient( Double.parseDouble(s));
				}
				else {
					mon.set_coefficient( -1*Double.parseDouble(s));
				}
				mon.set_power(0);
			}
			else {//if it contains a value with "x"
				String[] Mono = newS.split("x");
				if(Mono[0].equals("")) {//if the coefficient=1/-1, and does not appear in the string
					if(c=='-')
						mon.set_coefficient(-1);
					else
						mon.set_coefficient(1);
				}
				else {//if there is a representation of the coefficient in the String 
					if(c =='+') {
						if(newS.contains("*")==false) {
							mon.set_coefficient( Double.parseDouble(Mono[0]));
						}
						else {//if the string doesn't contains "*"
							mon.set_coefficient(Double.parseDouble((Mono[0]).substring(0,Mono[0].length()-1)));
						}
					}
					else {
						if(c =='-') {
							if(newS.contains("*")==false) {
								mon.set_coefficient( -1*Double.parseDouble(Mono[0]));
							}
							else {
								mon.set_coefficient(-1*Double.parseDouble((Mono[0]).substring(0,Mono[0].length()-1)));
							}
						}
					}
				}
				if(Mono.length==1)//if the string doesn't contains the power
				{
					mon.set_power(1);
				}
				else {
					mon.set_power(Integer.parseInt(Mono[1].substring (1)));
				}
			}
		}
		return mon;
	}

	public Monom(Monom  ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public int get_power() {
		return _power;
	}
	/**
	 * @return a new monom which is the derivative of this monom.
	 * if the power is "0", the function will return the zero monom
	 */

	public Monom  Derivative() {
		if(this._power == 0) {
			Monom  mon=new Monom (0,0);
			return mon;
		}
		else {
			Monom  mon=new Monom (this._coefficient*this._power,((this._power)-1));
			return mon;
		}
	}
	/**
	 * this method gets monom-m  and add m to this Monom. 
	 * the two monoms must be with the same power,if the powers are not equal the function will throw an ArithmeticException.
	 * @param m
	 */

	public void add (Monom  m) {
		if(m.get_power()!=this.get_power())
		{
			throw new ArithmeticException("the monoms cant be added,the two powers are different");
		}
		else {
			this.set_coefficient(((10*m.get_coefficient())+(10*this.get_coefficient()))/10);
		}

	}
	/**
	 * this method gets monom-m  and subtract m from this Monom. 
	 * the two monoms must be with the same power,if the powers are not equal the function will throw an ArithmeticException.
	 * @param m 
	 */
	public void sub (Monom  m) {
		if(m.get_power()!=this.get_power())
		{
			throw new ArithmeticException("the monoms cant be sub,the two powers are different");
		}
		else {
			this.set_coefficient(((10*this.get_coefficient())-(10*m.get_coefficient()))/10);
		}
	}
	/**
	 * this function gets a monom and multiply the monom in this monom 
	 * @param m 
	 */
	public void multiply (Monom  m) {
		this.set_power((this.get_power()+m.get_power()));
		this.set_coefficient(((10*this.get_coefficient())*(10*m.get_coefficient()))/100);
	}

	public double get_coefficient() {
		return _coefficient;
	}

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	/**
	 * 
	 * @param p the power
	 * the power must be a none negative integer,if the power is negative the function will throw an ArithmeticException.
	 */
	private void set_power(int p) {
		if(p<0) {
			throw new ArithmeticException("the power cant be negative");
		}
		else {
			this._power = p;
		}
	}
	/**
	 * this function return this monom value at x.
	 */

	@Override
	public double f(double x) {
		return (_coefficient *(Math.pow(x, _power)));
	}
	public boolean equals(Monom m) {
		return (this.get_coefficient()==m.get_coefficient()&&this.get_power()==m.get_power());
	}
	/**
	 * this method print the monom in this form:"ax^b".
	 * if the power=0 the method will print only the coefficient.
	 * if the power of x is 1 the method will print in this form:  "ax".
	 * if the coefficient=1 the method will print in this form: "x^b".
	 * if the coefficient=1 and the power=1 the method will print only the x.
	 */
	public String toString()
	{
		
		if (this.get_power()==1 && this.get_coefficient()==1) {
			String ans ="x";
			return ans;
		}
		if (this.get_power()==1 && this.get_coefficient()==-1) {
			String ans ="-x";
			return ans;
		}

		if (this.get_coefficient()==1) {
			if(this.get_power()==0)
			{
			String ans=""+this.get_coefficient();
			return ans;
			}
			else {
			String ans ="x^"+this.get_power();
			return ans;
			}
		}
		if (this.get_coefficient()==-1) {
			if(this.get_power()==0)
			{
			String ans=""+this.get_coefficient();
			return ans;
			}
			else {
			String ans ="-x^"+this.get_power();
			return ans;
			}
		}
		
		

		if (this.get_power() ==0) {
			String ans = ""+this.get_coefficient();
			return ans;
		}

		if (this.get_power()==1) {
			String ans =this.get_coefficient()+"x";
			return ans;
		}
		String ans = this.get_coefficient()+"x^"+this.get_power();
		return ans;
	}
}