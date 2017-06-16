/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Random;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author User
 */


public class EvenGenerator implements NumberGenerator {
//public class EvenGenerator implements Serializable {
 
	
	public Integer generateNumber() {
		Integer genNum = new Random().nextInt();
		genNum += genNum % 2;
		return genNum;
	}
}