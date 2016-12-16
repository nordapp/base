

/*
 * Script Name: AddJavaAnnotations
 * Author: Stefan Hauptmann
 * Purpose: Adding the annotation @Table to the generated java model
 * Date: 22.11.2016
 *
 * The annotation is used by the code generating template to annotate the java class. This annotation
 * causes SpringBoot to generate the index for the fields noted by the annotation.
 *
 * The index of uuid is built by the @Id annotation.
 */

function main() {
	
	// Show the script output window
	Repository.EnsureOutputVisible( "Script" );
	
	var thePackage = Repository.GetTreeSelectedPackage();
	
	Session.Output( "JavaScript MANAGE ATTRIBUTES/METHODS EXAMPLE" );
	
if ( thePackage != null && thePackage.PackageID != 0 )
	{
		
		Session.Output( "JScript MANAGE ELEMENTS EXAMPLE" );
		Session.Output( "=======================================" );
		Session.Output( "Working on package '" + thePackage.Name + "' (ID=" +
			thePackage.PackageID + ")" );
		
		for(var i=0;i<thePackage.Elements.Count;i++) {
			
			//The element of the package
			var elem = thePackage.Elements.GetAt(i);
			var tableName = elem.Name.toLowerCase();
			
			Session.Output( "Working on element table '" + tableName + "'");
			
			var annot = null;
			for(var k=0;k<elem.TaggedValues.Count;k++) {
				
				//The TaggedValue
				var tag = elem.TaggedValues.GetAt(k);
				
				if(tag.Name=="annotations") {
					annot = tag;
				}//fi
			}//for
			
			var stmt = "@Entity @Table(name=\""+tableName+"\", indexes={@Index(name=\"history_idx\", columnList=\"history\")})";
			if(annot==null) {
				//Add annotation
				annot = elem.TaggedValues.AddNew("annotations", stmt);
				annot.Update();
				Session.Output( "Adding annotation '" + annot.Name + "', '" + annot.Value + "'");
				elem.TaggedValues.refresh;
			}else{
				annot.Value = stmt
				annot.Update();
				Session.Output( "Updateing annotation '" + annot.Name + "', '" + annot.Value + "'");
				elem.TaggedValues.refresh;
			}//fi
			
		}//for
	}//fi
	
	Session.Output( "Done working '" + thePackage.Name + "' (ID=" +
			thePackage.PackageID + ")" );
}

main();