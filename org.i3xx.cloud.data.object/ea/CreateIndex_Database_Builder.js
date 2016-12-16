
/*
 * Script Name: AddJavaAnnotations
 * Author: Stefan Hauptmann
 * Purpose: Adding the annotation @Table to the generated java model
 * Date: 22.11.2016
 */
var otElement = 4;
var otPackage = 5;

function main() {
	
	// Show the script output window
	Repository.EnsureOutputVisible( "Script" );
	
	Session.Output( "Processing ADD INDEX (works in the Project Browser only)" );
	
	// Test the selection
	var treeSelectedType = Repository.GetTreeSelectedItemType();
	switch ( treeSelectedType )
	{
		case otElement :
		{
			// Code for when an element is selected
			var theElement = Repository.GetTreeSelectedObject();
			filterTable(theElement);
			break;
		}
		case otPackage :
		{
			// Code for when a package is selected
			var thePackage = Repository.GetTreeSelectedObject();
			searchPackages(thePackage);
			break;
		}
	}
	
	Session.Output("Processing finished.");
}

// ----------------------------------------------------------------
// Edit section
// ----------------------------------------------------------------

/*
 *  Creates the index
 *
 *  EDIT HERE TO BUILD YOUR OWN INDEX
 */
function createIndex(theObject) {
	Session.Output("Process '" + theObject.Name + "'.");
	
	var m = createMethodPk(theObject, "PK_"+theObject.Name);
	createParameter(m, "uuid", "varchar", true);
	setAttributePk(theObject, "uuid");
	
	//Index
	var m = createMethod(theObject, "history_idx");
	createParameter(m, "history", "varchar", true);
	
	
	//Description:
	
	//Creates an index
	//var m = createMethod(theObject, "uuid_idx");
	//createParameter(m, "uuid", "varchar", true);
	
	//Drops an index
	//dropMethod(theObject, "history_idx");
	
	//Makes the index unique
	//createTaggedValue(m, "property", "Unique=1;");
	//createTaggedValue(m, "Unique", "1");
}


// ----------------------------------------------------------------
// Tool section
// ----------------------------------------------------------------

/*
 *  Filters the table objects (other object does't have such indexes)
 */
function filterTable(theObject) {
	
	if( theObject != null && theObject.MetaType == "Table" ) {
		createIndex(theObject);
	}
}

/*
 *  Walk through the packages
 */
function searchPackages(thePackage) {
	
	if ( thePackage != null && thePackage.PackageID != 0 )
	{
		Session.Output( "Working " + thePackage.MetaType + ": " + thePackage.Name +
			" (ID=" + thePackage.PackageID + ", count=" +thePackage.Packages.Count + ")");
		
		for(var i=0;i<thePackage.Packages.Count;i++) {
			//The element of the package
			var elem = thePackage.Packages.GetAt(i);
			searchPackages(elem);
		}//for
		
		for(var i=0;i<thePackage.Elements.Count;i++) {
			
			//The element of the package
			var elem = thePackage.Elements.GetAt(i);
			
			if( elem != null && elem.MetaType == "Table" ) {
				createIndex(elem);
			}else{
				Session.Output("Skipping " + elem.MetaType + ": " + elem.Name + " (stereotype=" + elem.Stereotype + ")");
			}//fi
		}//for
	}
}

/*
 *  Creates the index as a method
 * 
 *  @param theObject The table to add the index
 *  @param name The name of the index
 *  @return The method or null if not found
 */
function createMethod(theObject, name) {
	var theMethods = theObject.Methods;
	
	var theMethod = findByName(theMethods, name);
	if( theMethod == null ){
		Session.Output( "  Index created '" + name + "'." );
		theMethod = theMethods.AddNew(name, "index");
	}else{
		Session.Output( "  Index updated '" + name + "'." );
	}
	theMethod.Stereotype = "index";
	theMethod.Update();
	
	return theMethod;
}

/*
 *  Drops the index as a method
 * 
 *  @param theObject The table to add the index
 *  @param name The name of the index
 */
function dropMethod(theObject, name) {
	var theMethods = theObject.Methods;
	
	var f = deleteByName(theMethods, name);
	if( f ){
		Session.Output( "  Method dropped '" + name + "'." );
		theMethods.Refresh();
	}
}

/*
 *  Creates the index as a method
 * 
 *  @param theObject The table to add the index
 *  @param name The name of the index
 *  @return The method or null if not found
 */
function createMethodPk(theObject, name) {
	var theMethods = theObject.Methods;
	
	var theMethod = findByName(theMethods, name);
	if( theMethod == null ){
		Session.Output( "  Primary Key created '" + name + "'." );
		theMethod = theMethods.AddNew(name, "PK");
	}else{
		Session.Output( "  Primary Key updated '" + name + "'." );
	}
	theMethod.Stereotype = "PK";
	theMethod.ReturnType = "";
	//theMethod.ObjectType = 49;
	theMethod.Update();
	
	return theMethod;
}

/*
 *  Sets the field (attribute) as primary
 * 
 *  @param theObject The table to edit the field
 *  @param name The name of the field
 */
function setAttributePk(theObject, name) {
	var theAttributes = theObject.Attributes;
	
	var theAttribute = findByName(theAttributes, name);
	if( theAttribute != null ){
		theAttribute.IsOrdered = true;
		theAttribute.IsStatic = true;
		theAttribute.Update();
	}
}

/*
 *  Creates the index fields
 *  
 *  @param theMethod The index (method) to add the database field
 *  @param name The name of the database field
 *  @param type The type of the field (e.g varchar, int)
 *  @param ascending The order of the index field, true is ascending, false is descending
 */
function createParameter(theMethod, name, type, ascending) {
	var theParameters = theMethod.Parameters;
	
	var theParameter = findByName(theParameters, name);
	if( theParameter == null ){
		Session.Output( "  Parameter created '" + name + "'." );
		theParameter = theMethod.Parameters.AddNew(name, "");
	}else{
		Session.Output( "  Parameter updated '" + name + "'." );
	}
	theParameter.NAME = name;
	theParameter.Default = "";
	theParameter.IsConst = ( ! ascending); //The use of this field is reverse
	theParameter.Kind = "";
	theParameter.Type = type;
	theParameter.Update();
}

/*
 *  Creates the tagged values (unique information)
 *  
 *  @param theMethod The index (method) to add the database field
 *  @param name The name of the tagged value
 *  @param value The value of the tagged value
 */
function createTaggedValue(theMethod, name, value) {
	var theTaggedValues = theMethod.TaggedValues;
	
	var theTaggedValue = findByName(theTaggedValues, name);
	if( theTaggedValue == null ){
		Session.Output( "  TaggedValue created '" + name + "'." );
		theTaggedValue = theMethod.TaggedValues.AddNew(name, "");
	}else{
		Session.Output( "  TaggedValue updated '" + name + "'." );
	}
	theTaggedValue.NAME = name;
	theTaggedValue.Value = value;
	theTaggedValue.Update();
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

/*
 * Deletes an element in a collection by name
 *
 * @param collection The collection containing the element
 * @param name The name of the element to find
 * @return True if the element is deleted or false if it isn't found or deleted
 */
function deleteByName(collection, name) {
	var elem = false;
	for(var i=0;i<collection.Count;i++) {
		elem = collection.GetAt(i);
		if(elem.Name == name){
			collection.Delete(i);
			elem = true;
			break;
		}else{
			elem = false;
		}
	}//for
	return elem;
}

main();