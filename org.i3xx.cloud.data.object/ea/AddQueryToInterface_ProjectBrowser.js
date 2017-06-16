

/*
 * Script Name: AddDbColumns
 * Version: 1.0.1
 * Author: Stefan Hauptmann
 * Purpose: Adding the query'
 * Date: 22.12.2016
 */

var tpFilter = "Interface";

/*
 * Project Browser Script main function
 */
function main() {
	
	var otElement = 4;
	var otAttribute = 24;
	
	// Show the script output window
	Repository.EnsureOutputVisible( "Script" );
	
	//var thePackage = Repository.GetTreeSelectedPackage();
	
	Session.Output( "JavaScript ADD/UPDATE DATABASE QUERY" );
	Session.Output( "=======================================" );
	
	// Test the selection
	var treeSelectedType = Repository.GetTreeSelectedItemType();
	Session.Output( "Processing "+treeSelectedType );
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
			
			processQuery(theElement, theAttribute);
		}
	}
	
	Session.Output( "Processing finished" );
}

function filterElement(theElement) {
	if( theElement != null && theElement.MetaType == tpFilter ) {
		processQuery(theElement);
	}
}

function processQuery(theElement) {
	Session.Output( "Working " + theElement.MetaType + ": " + theElement.Name + " (methods=" + theElement.Methods.Count + ")");
	
	var elementType = theElement.Name.substring(0, ( theElement.Name.length - 10) ); //cut Repository
	var theMethod = findByName( theElement.Methods, "queryByGuidGreaterThanEqualOrderByGuidAsc" );
	
	if( theMethod == null ) {
		theMethod = theElement.Methods.AddNew("queryByGuidGreaterThanEqualOrderByGuidAsc", "long");
	}
	//theMethod.Name = "queryByGuidGreaterThanEqualOrderByGuidAsc";
	theMethod.Stereotype = "query";
	theMethod.ReturnType = "List<" + elementType + ">";
	theMethod.Update();
	
	var theParameter = findByName(theMethod.Parameters, "newVal");
	if( theParameter == null ){
		theParameter = theMethod.Parameters.AddNew( "newVal", "long" );
	}//fi
	
	theParameter.Name = "newVal";
	theParameter.Default = "";
	theParameter.IsConst = false;
	theParameter.Kind = "";
	theParameter.Type = "long";
	theParameter.Update();
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