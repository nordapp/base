

/*
 * Script Name: AddDbColumns
 * Version: 1.0.0
 * Author: Stefan Hauptmann
 * Purpose: Adding the fields 'uuid' and 'history' to a database table and removing the fields 'id' and 'up'
 * Date: 20.11.2016
 */

function main() {
	
	var otElement = 4;
	var otPackage = 5;
	
	// Show the script output window
	Repository.EnsureOutputVisible( "Script" );
	
	//var thePackage = Repository.GetTreeSelectedPackage();
	
	Session.Output( "JavaScript ADD/UPDATE DATABASE FIELDS" );
	Session.Output( "=======================================" );
	
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
	
	Session.Output( "Processing finished" );
}

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
				processTable(elem);
			}else{
				Session.Output("Skipping " + elem.MetaType + ": " + elem.Name + " (stereotype=" + elem.Stereotype + ")");
			}//fi
		}//for
	}
}

function filterTable(theElement) {
	
	if( theElement != null && theElement.MetaType == "Table" ) {
		processTable(theElement);
	}
}

function processTable(table) {
	Session.Output( "Working " + table.MetaType + ": " + table.Name + " (attributes=" + table.Attributes.Count + ")");
	var p = 0;
	
	p = findByName( table, "guid" );
	if( p == -1 ) {
		var fld = table.Attributes.AddNew("guid", "bigint");
		fld.Stereotype = "column";
		//fld.isUnique = true;
		//fld.Length = 36;
		fld.Update();
	}
	
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

function findByName(table, columnName) {
	
	//search
	for(var i=0;i<table.Attributes.Count;i++) {
		
		//The element of the package
		var attr = table.Attributes.GetAt(i);
		
		if( attr != null
				&& attr.Stereotype == "column"
				&& attr.Name == columnName ) {
			return i;
		}//fi
	}//for
	
	return -1;
}

main();