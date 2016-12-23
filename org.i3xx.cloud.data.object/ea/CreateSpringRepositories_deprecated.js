

/*
 * Script Name: CreateSpringRepositories
 * Version: 1.0.0
 * Author: Stefan Hauptmann
 * Purpose: Generate the repositories used by SpringBoot from the 'SpringBoot DataRepo Model'
 * Deprecated: Replaced by transformation 'SpringDataRepository'
 * Date: 22.11.2016
 */

function main() {
	
	var otElement = 4;
	var otPackage = 5;
	
	// Show the script output window
	Repository.EnsureOutputVisible( "Script" );
	
	//var thePackage = Repository.GetTreeSelectedPackage();
	
	Session.Output( "JavaScript CREATE SPRING REPOSITORIES" );
	Session.Output( "=======================================" );
	
	// Test the selection
	var treeSelectedType = Repository.GetTreeSelectedItemType();
	switch ( treeSelectedType )
	{
		case otElement :
		{
			// Code for when an element is selected
			var theElement = Repository.GetTreeSelectedObject();
			filterClass(theElement);
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
			
			if( elem != null && elem.MetaType == "Class" ) {
				processClass(elem);
				break;
			}else{
				Session.Output("Skipping " + elem.MetaType + ": " + elem.Name + " (stereotype=" + elem.Stereotype + ")");
			}//fi
		}//for
	}
}

function filterClass(theElement) {
	
	if( theElement != null && theElement.MetaType == "Class" ) {
		processClass(theElement);
	}
}

function processClass(theElement) {
	Session.Output( "Working " + theElement.MetaType + ": " + theElement.Name + " (attributes=" + theElement.Attributes.Count + ")");
	Session.Output( theElement.GetLinkedDocument() );
	
}

function findByName(theElement, columnName) {
	
	//search
	for(var i=0;i<theElement.Attributes.Count;i++) {
		
		//The element of the package
		var attr = theElement.Attributes.GetAt(i);
		
		if( attr != null
				&& attr.Stereotype == "column"
				&& attr.Name == columnName ) {
			return i;
		}//fi
	}//for
	
	return -1;
}

main();