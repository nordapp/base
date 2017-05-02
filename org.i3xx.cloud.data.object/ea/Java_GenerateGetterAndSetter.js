
/*
 * 
 * Script Name: GenerateGetterAndSetter
 * Author: Stefan Hauptmann
 * Purpose: Generate getter and setter functions from attributes
 * Date: 08.02.2017
 */
	
var tpFilter = "Class";

/*
 * Project Browser Script main function
 */
function main()
{
	var otElement = 4;
	var otAttribute = 23;
	
	// Show the script output window
	Repository.EnsureOutputVisible( "Script" );
	
	//var thePackage = Repository.GetTreeSelectedPackage();
	
	Session.Output( "JavaScript GENERATE GETTER AND SETTER" );
	Session.Output( "=======================================" );
	
	// Test the selection
	var treeSelectedType = Repository.GetTreeSelectedItemType();
	switch ( treeSelectedType )
	{
		case otElement :
		{
			// Code for when an element is selected
			var theElement = Repository.GetTreeSelectedObject();
			filterElement(theElement);
			break;
		}
		case otAttribute:
		{
			// Code for when an attribute is selected
			var theAttribute = Repository.GetTreeSelectedObject();
			var theElement = Repository.GetElementByID(theAttribute.ParentID);
			
			processAttribute(theElement, theAttribute);
		}
	}
	
	Session.Output( "Done processing" );
}

function filterElement(theElement) {
	if( theElement != null && theElement.MetaType == tpFilter ) {
		processElement(theElement);
	}
}

function processElement(theElement) {
	Session.Output( "Working " + theElement.MetaType + ": " + theElement.Name + " (attributes=" + theElement.Attributes.Count + ")");
	var p = 0;
	
	for(var i=0;i<theElement.Attributes.Count;i++) {
		
		//The element of the package
		var attr = theElement.Attributes.GetAt(i);
		if( attr != null && attr.Stereotype == "attribute" ) {
			processAttribute(theElement, attr);
		}//fi
	}//for
	
}

function processAttribute(theElement, theAttribute) {
	
	//format the name
	var name = theAttribute.Name;
	var cont = true;
	
	//do not continue if IsTransient (volatile=1)
	var styleEx = theAttribute.StyleEx;
	var arr = styleEx.split(";");
	for(var i=0;arr!=null && i<arr.length;i++) {
		if(arr[i]==null)
			continue;
		
		var brr = arr[i].split("=", 2);
		if(brr.length<2)
			continue;
		
		if(brr[0]=="volatile" && brr[1]=="1")
			cont = false;
	}//for
	
	//do not continue if IsConst and IsStatic
	if( theAttribute.IsStatic && theAttribute.IsConst ) {
		cont = false;
	}
	
	if( cont ) {
		name = name.substring(0,1).toUpperCase() + name.substring(1);
		
		Session.Output("Process attribute " + name + " generate getter and setter." );
		
		//property get
		{
			var getterName = "get" + name;
			var theMethod = findByName(theElement.Methods, getterName);
			
			if( theMethod == null ) {
				theMethod = theElement.Methods.AddNew(getterName, theAttribute.Type);
			}
			
			theMethod.ReturnType = theAttribute.Type;
			theMethod.Stereotype = "property get";
			theMethod.Code = "return " + theAttribute.Name + ";\n";
			theMethod.Update();
		}
		
		//property set
		{
			var setterName = "set" + name;
			var theMethod = findByName(theElement.Methods, setterName);
			
			if( theMethod == null ) {
				theMethod = theElement.Methods.AddNew(setterName, "void");
			}
			
			theMethod.Stereotype = "property set";
			theMethod.ReturnType = "void";
			theMethod.Code = "this." + theAttribute.Name + " = " + theAttribute.Name + ";\n";
			theMethod.Update();
			
			var theParameter = findByName(theMethod.Parameters, theAttribute.Name);
			if( theParameter == null ){
				theParameter = theMethod.Parameters.AddNew( theAttribute.Name, "" );
			}//fi
			
			theParameter.Name = theAttribute.Name;
			theParameter.Default = "";
			theParameter.IsConst = true;
			theParameter.Kind = "";
			theParameter.Type = theAttribute.Type;
			theParameter.Update();
		}
	
	}else{
		Session.Output("Skip attribute " + name + " ." );
	}//fi
}

/*
 * Finds an element in a collection by name
 *
 * @param collection The collection containing the element
 * @param name The name of the element to find
 * @return The element or null if not found
 */
function findByName(collection, name) {
	var elem = null;
	for(var i=0;i<collection.Count;i++) {
		elem = collection.GetAt(i);
		if(elem.Name == name)
			break;
		else
			elem = null;
	}//for
	return elem;
}

main();
