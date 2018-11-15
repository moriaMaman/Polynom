package myMath;

import java.lang.invoke.WrongMethodTypeException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import myMath.Monom;

/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{

	private ArrayList<Monom> polynomL;
	private  Comparator <Monom> cmpByPow= new Monom_Comperator();

	public Polynom() {//the polynom will be represented by array list.
		polynomL=new ArrayList(0);
	}
	/**
	 *  this function gets a string and make it polynom.
	 * if the function gets an illegal value it throw an runtimeException.
	 */
	public Polynom (String s) {
		this ();
		try {
			String [] monoms=s.split("\\+|\\-");//the String will split by the "+" and "-" signs 
			char [] Arithmatics=new char[monoms.length];//The array contains the arithmetic operators in the polynom,each sell represent the "sign" of the coefficient in each monom
			int index=0;//the index of the operators array 
			for (int i = 0; i < s.length(); i++) {//Initializing the operators array
				if(s.charAt(i)=='+'||s.charAt(i)=='-')
				{
					Arithmatics[index]=s.charAt(i);
					index++;
				}
			}
			if (monoms[0].equals(""))//if the first monom in the polynom has an "+" or "-" sign 
			{
				for (int i = 0; i < Arithmatics.length-1; i++) {		 
					this.add(Monom.from_string_to_Monom(monoms[i+1],Arithmatics[i]));//will send each monom and his sign to a function that turns string into a monom
				}
			}
			else//if the first monom has no sign- this certainly means that the polynom is positive
			{
				this.add(Monom.from_string_to_Monom(monoms[0],'+'));//send the first monom with a "+" sign to the "from string to monom" fanction
				for (int i = 0; i < monoms.length-1; i++) {
					this.add(Monom.from_string_to_Monom(monoms[i+1],Arithmatics[i]));// Sends the rest of the monoms to the function, each monom with his operator
				}
			}
		}
		catch(RuntimeException e) {
			throw new RuntimeException ("Illegal value");
		}
	}
/**
 * this function return this polynom value at x.
 */
	@Override
	public double f(double x) {
		double answer=0;
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext())
		{
			Monom mon=it.next();
			answer = answer + (mon.f(x));	
		}
		return answer;
	}
	/**
	 * this function get a polynom and add it to this polynom.
	 */
	@Override
	public void add(Polynom_able p1) {	
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext())
		{
			Monom mon=it.next();
			this.add(new Monom(mon));
		}
	}
	
	/**
	 *this function gets a monom-m1  and add it to this Polynom.
	 */
	@Override
	public void add(Monom m1) {
		boolean flag=true;
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext()&&flag) {
			Monom mon=it.next();
			if(mon.get_power() == m1.get_power()) {
				mon.add(m1);
				if (mon.get_coefficient() == 0)//if after the add,the monom coefficient is 0
				{
					polynomL.remove(mon);//remove the zero monom 
				}
				flag=false;
			}
		}
		if(flag) {//if there is no monom in the polynom with the same power
			if(m1.get_coefficient()!=0)
			{
				polynomL.add(m1);
				polynomL.sort(cmpByPow);
			}
		}
	}
	/**
	 * this functiom gets a polynom and substract it from this polynom.
	 */

	@Override
	public void substract(Polynom_able p1) {
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext())
		{
			Monom m=it.next();
			this.substract2(m);
		}
	}
	/**
	 * this function gets a monom and substract it from this monom.
	 */
	private void substract2(Monom m) {
		boolean flag=true;
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext()&&flag)
		{
			Monom mon=it.next();
			if(mon.get_power()==m.get_power()) {
				mon.sub(m);
				if(mon.get_coefficient() == 0) {//if after the sub,the monom coefficient is 0
					polynomL.remove(mon);//remove the zero monom
				}
				flag=false;
			}	
		}
		if (flag)//if there is no monom in the polynom with the same power
		{
			Monom newM= new Monom(-1*m.get_coefficient(),m.get_power());
			if(newM.get_coefficient()!=0)//as long as the monom is not zero
			{
				this.add(newM);
			}
		}
	}
	/**
	 *this function  gets a polynom-p1 and multiply this Polynom by p1.
	 */
	@Override
	public void multiply(Polynom_able p1) {
		Polynom poly=new Polynom();
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext()) {
			Monom mon=it.next();
			for(int i=0;i<polynomL.size();i++)
			{
				poly.add(multiply2(mon,polynomL.get(i)));
				
			}
		}
			this.polynomL.clear();
			Iterator<Monom> it2 = poly.iteretor();
			while(it2.hasNext())
			{
				this.add(it2.next());
			}
		
	}
	/**
	 * this function gets two monoms,multiplies them, and returns the result.
	 */
	private Monom multiply2 (Monom m1,Monom m2) {
		double newCof=m1.get_coefficient()*m2.get_coefficient();
		int newPow=m1.get_power()+m2.get_power();
		Monom newMon=new Monom (newCof,newPow);
		return newMon;
	}
	/**
	 *this function gets a polynom-p1 and check if this Polynom is logically equals to p1.
	 */
	@Override
	public boolean equals(Polynom_able p1) {
		Iterator<Monom> it1 = p1.iteretor();
		Iterator<Monom> it2 = this.iteretor();
		boolean flag=true;
		while(it1.hasNext()&&it2.hasNext()&&flag)
		{
			Monom mon1=it1.next();
			Monom mon2=it2.next();
			if(mon1.equals(mon2)==false)
			{
				flag=false;
			}
		}
		if(it1.hasNext()||it2.hasNext()) {//if the two polynomials are not in the same size
			flag=false;
		}
		return flag;
	}
	/**
	 *this function return true  if this is the zero Polynom-if the polynom is empty.
	 */
	@Override
	public boolean isZero() {
		if(polynomL.size() == 0)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public double root(double x0, double x1, double eps) {
		double y0=this.f(x0);
		double y1=this.f(x1);
		double x2=0;
		if( y0*y1 >0) {//if the two points are both positive or negative
			throw new RuntimeException("can't run the function between this two points");
		}
		else {  
			 while((x1-x0)>=eps) {
 				 x2 =(x0+x1)/2;
 				if (this.f(x2)*this.f(x1)>0)
					x1=x2;
				else 
					x0=x2;
 			 }
 		 }
 		 return x2;
	}
	
	/**
	 * this function returns a new polynom that is a deep copy of this Polynum.
	 */
	@Override
	public Polynom_able copy() {
		Polynom_able poly=new Polynom ();
		Iterator<Monom> it = polynomL.iterator();
		while(it.hasNext()) {
			Monom mon=it.next();
			poly.add(new Monom(mon));
		}
		return poly;
	}
	/**
	 * this function return a new Polynom which is the derivative of this Polynom.
	 */
	@Override
	public Polynom_able derivative() {
		Iterator<Monom> it = polynomL.iterator();
		Polynom_able newPoly=new Polynom();
		while(it.hasNext()) {
			Monom m= it.next();
			newPoly.add(m.Derivative());
		}
		return newPoly;
	}
	

	/**
	 * this function return the area of the polynom and above the x axis between two points.
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		double count=f(x0)*eps;
		x0 = x0+eps;
		while(x0<=x1) {
			if(f(x0)<0) {
				x0 = x0+eps;
			}
			else {
				count = count + (f(x0)*eps);
				x0 = x0+eps;
			}
			}
		return count;
	}
	
	/**
	 * this function return the area of the polynom under the x axis between two points.
	 */
	public double areaUnder(double x0, double x1) {
		double count=f(x0)*0.01;
		x0 = x0+0.01;
		while(x0<=x1) {
			if(f(x0)<0) {
				count = count + (Math.abs(f(x0))*0.01);
				x0 = x0+0.01;
			}
			else {
				x0 = x0+0.01;
			}
			}
		return count;
	}
	
	/**
	 * this function return the area of the polynom between two points.
	 */
	public double areaAll(double x0, double x1) {
		double count=f(x0)*0.01;
		x0 = x0+0.01;
		while(x0<=x1) {
			if(f(x0)<0) {
				count = count + (Math.abs(f(x0))*0.01);
				x0 = x0+0.01;
			}
			else {
				count = count + (f(x0)*0.01);
				x0 = x0+0.01;
			}
			}
		return count;
	}

	@Override
	public Iterator<Monom> iteretor(){
		return this.polynomL.iterator();
	}
	/**
	 * this method print the polynom in this form:"a_0x^b_0 +a_1x^b_1...".
	 * if the polynom is null the method will print :"0".
	 * 
	 */
	public String toString()
	{
		Iterator <Monom> it = this.polynomL.iterator();
		String ans="";
		if(it.hasNext()==false) {//if the polynom is null 
			ans="0";
		}
		else{
			ans= ans+it.next().toString();
			while(it.hasNext()){
				Monom mon=it.next();
				if(mon.get_coefficient()>0) {//if the coefficient is none negative number
					ans= ans+"+"+mon.toString();
				}
				else {
					ans= ans+mon.toString();
				}
			}
		}
		return ans;
	}
}