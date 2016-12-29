

/*
 * Script Name: RenameDbFields
 * Version: 1.0.0
 * Author: Stefan Hauptmann
 * Purpose: Rename the fields of the OfficeBase field name schema to the default Spring schema
 * Date: 22.11.2016
 */

function main() {
	
	var otElement = 4;
	var otPackage = 5;
	
	var tpFilter = "Table";
	
	// Show the script output window
	Repository.EnsureOutputVisible( "Script" );
	
	//var thePackage = Repository.GetTreeSelectedPackage();
	
	Session.Output( "JavaScript RENAME DATABASE FIELDS" );
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
			
			if( elem != null && elem.MetaType == tpFilter ) {
				processTable(elem);
			}else{
				Session.Output("Skipping " + elem.MetaType + ": " + elem.Name + " (stereotype=" + elem.Stereotype + ")");
			}//fi
		}//for
	}
}

function filterTable(theElement) {
	
	if( theElement != null && theElement.MetaType == tpFilter ) {
		processTable(theElement);
	}
}

function processTable(table) {
	Session.Output( "Working " + table.MetaType + ": " + table.Name + " (attributes=" + table.Attributes.Count + ")");
	
	for(var i=0;i<table.Attributes.Count;i++) {
		
		//The element of the package
		var attr = table.Attributes.GetAt(i);
		
		if( attr != null && attr.Stereotype == "column" ) {
			var name = attr.Name;
			var p = name.indexOf("_");
			if(p>-1) {
				var temp = name.substring(p+1);
				
				attr.Name = temp;
				attr.Update();
				//Session.Output(name + " -> " + temp);
			}
		}else{
			Session.Output("Skipping " + attr.MetaType + ": " + attr.Name + " (stereotype=" + attr.Stereotype + ")");
		}//fi
	}//for
}

main();