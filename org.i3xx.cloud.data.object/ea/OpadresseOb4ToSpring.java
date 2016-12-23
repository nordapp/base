package com.i3xx.cloud.data.op.domain;


/**
 * @author Administrator
 * @version 1.0
 * @created 23-Dez-2016 16:11:32
 */
public class OpadresseOb4ToSpring {

	public OpadresseOb4ToSpring(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param source
	 * @param target
	 */
	public void toSpring(IBrickData source, Opadresse target){
		target.setName ( source.getValue( "opadresse_NAME", "" ) );
		target.setAnr ( source.getValue( "opadresse_ANR", Integer.valueOf(0) ).intValue() );
		target.setOrt ( source.getValue( "opadresse_ORT", "" ) );
		target.setStrasse ( source.getValue( "opadresse_STRASSE", "" ) );
		target.setAns2m ( source.getValue( "opadresse_ANS2M", "" ) );
		target.setAnrproxy ( source.getValue( "opadresse_ANRPROXY", "" ) );
		target.setFunktion ( source.getValue( "opadresse_FUNKTION", "" ) );
		target.setGuid ( source.getValue( "opadresse_guid", Long.valueOf(0) ).longValue() );
	}
}//end OpadresseOb4ToSpring