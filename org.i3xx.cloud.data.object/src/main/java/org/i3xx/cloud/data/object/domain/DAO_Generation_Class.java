package org.i3xx.cloud.data.object.domain;


/**
 * @author Administrator
 * @version 1.0
 * @created 28-Dez-2016 16:50:11
 */
public class DAO_Generation_Class {

	private static DAO_Generation_Class daoGenerator = null;

	public DAO_Generation_Class(){

	}

	public void finalize() throws Throwable {

	}
	public Obid generateObid(){
		return ( new Obid() );
	}

	public Oblog generateOblog(){
		return ( new Oblog() );
	}

	public static DAO_Generation_Class of(){
		//singleton
		if(daoGenerator==null)
			daoGenerator = new DAO_Generation_Class();
	
		return daoGenerator;
	}
}//end DAO_Generation_Class