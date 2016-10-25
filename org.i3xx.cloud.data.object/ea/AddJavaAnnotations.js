

/*
 * This code has been included from the default Project Browser template.
 * If you wish to modify this template, it is located in the Config\Script Templates
 * directory of your EA install path.   
 * 
 * Script Name:
 * Author:
 * Purpose:
 * Date:
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
			
			Session.Output( "Working on element '" + elem.Name + "'");
			
			var annot = null;
			for(var k=0;k<elem.TaggedValues.Count;k++) {
				
				//The TaggedValue
				var tag = elem.TaggedValues.GetAt(k);
				
				if(tag.Name=="annotations") {
					annot = tag;
				}//fi
			}//for
			
			var stmt = "@Entity @Table(indexes={@Index(name=\"uuid_idx\", columnList=\"uuid\")})";
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
	}
}

main();