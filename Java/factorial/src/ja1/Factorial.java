/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ja1;

/**
 *
 * @author nande
 */
public class Factorial {
	public Integer Do(Integer n){
		if (n<=1){
			return n;
		}else{
			return n*Do(n-1);
		}
	}
}
