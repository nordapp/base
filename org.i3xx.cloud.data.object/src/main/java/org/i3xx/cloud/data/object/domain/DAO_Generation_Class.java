package org.i3xx.cloud.data.object.domain;


/**
 * @author Administrator
 * @version 1.0
 * @created 23-Dez-2016 14:16:06
 */
public class DAO_Generation_Class {

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
}//end DAO_Generation_Class