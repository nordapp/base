
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
	
	/*
	p = findByName( table, "id" );
	if( p > -1 ) {
		table.Attributes.DeleteAt(p, true);
	}
	p = findByName( table, "up" );
	if( p > -1 ) {
		table.Attributes.DeleteAt(p, true);
	}
	p = findByName( table, "uuid" );
	if( p == -1 ) {
		var fld = table.Attributes.AddNew("uuid", "varchar");
		fld.Stereotype = "column";
		fld.isUnique = true;
		fld.Length = 36;
		fld.Update();
	}
	p = findByName( table, "history" );
	if( p == -1 ) {
		var fld = table.Attributes.AddNew("history", "varchar");
		fld.Stereotype = "column";
		fld.Length = 36;
		fld.Update();
	}
	*/
}

function processAttribute(theElement, theAttribute) {
	
	//format the name
	var name = theAttribute.Name;
	var reg = /^[a-zA-Z]+/;
	
	if( name.search(reg) != -1 ) {
		name = name.substring(0,1).toUpperCase() + name.substring(1);
		
		Session.Output("Process attribute " + name + " generate getter and setter." );
		
		//property get
		{
			var getterName = "get" + name;
			var theMethod = findByName(theElement.Methods, getterName);
			
			if( theMethod == null ) {
				theMethod = theElement.Methods.AddNew(getterName, "String");
			}
			
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
			theParameter.Type = "String";
			theParameter.Update();
		}
	
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
