
/*
 * Script Name: AnalyzeObject_ProjectBrowser
 * Author: Stefan Hauptmann
 * Purpose: Getting a sight into the Enterprise Architect model
 * Date: 22.11.2016
 *
 * Take a look into the Enterprise Architect Object Model. Select an item of the
 * Project Browser and run this script to see the data stored.
 *
 * As an example, the field 'IsConst' of the 'Parameter Object' is used to store
 * the index data, whether an index is ascending (false) or descending (true)
 */

function main() {
	
	// Show the script output window
	Repository.EnsureOutputVisible( "Script" );
	
	var theObject = Repository.GetTreeSelectedObject();
	
	Session.Output( "JavaScript prints the Object '" + theObject.Name + "'." );
	
	//
	// Analyze part
	//
	if( theObject.ObjectType == 4 )
		showObject(theObject);
	else if( theObject.ObjectType == 25 )
		showMethod(theObject, "", "");
	
}

function showObject(theObject) {
	
	Session.Output( "Abstract: "+theObject.Abstract );
	Session.Output( "ActionFlags: "+theObject.ActionFlags );
	Session.Output( "Alias: "+theObject.Alias );
	Session.Output( "AssociationClassConnectorID: "+theObject.AssociationClassConnectorID );
	Session.Output( "Author: "+theObject.Author );
	Session.Output( "ClassfierID: "+theObject.ClassfierID );
	Session.Output( "ClassifierID: "+theObject.ClassifierID );
	Session.Output( "ClassifierName: "+theObject.ClassifierName );
	Session.Output( "ClassifierType: "+theObject.ClassifierType );
	Session.Output( "Complexity: "+theObject.Complexity );
	Session.Output( "CompositeDiagram: "+theObject.CompositeDiagram );
	Session.Output( "Created: "+theObject.Created );
	Session.Output( "Difficulty: "+theObject.Difficulty );
	Session.Output( "ElementGUID: "+theObject.ElementGUID );
	Session.Output( "ElementID: "+theObject.ElementID );
	Session.Output( "EventFlags: "+theObject.EventFlags );
	Session.Output( "ExtensionPoints: "+theObject.ExtensionPoints );
	Session.Output( "FQStereotype: "+theObject.FQStereotype );
	Session.Output( "IsRoot: "+theObject.IsRoot );
	Session.Output( "GenFile: "+theObject.GenFile );
	Session.Output( "Genlinks: "+theObject.Genlinks );
	Session.Output( "GenType: "+theObject.GenType );
	Session.Output( "Header1: "+theObject.Header1 );
	Session.Output( "Header2: "+theObject.Header2 );
	Session.Output( "IsActive: "+theObject.IsActive );
	Session.Output( "IsComposite: "+theObject.IsComposite );
	Session.Output( "IsLeaf: "+theObject.IsLeaf );
	Session.Output( "IsNew: "+theObject.IsNew );
	Session.Output( "IsSpec: "+theObject.IsSpec );
	Session.Output( "Locked: "+theObject.Locked );
	Session.Output( "MetaType: "+theObject.MetaType );
	Session.Output( "MiscData: "+theObject.MiscData );
	Session.Output( "Modified: "+theObject.Modified );
	Session.Output( "Multiplicity: "+theObject.Multiplicity );
	Session.Output( "Name: "+theObject.Name );
	Session.Output( "Notes: "+theObject.Notes );
	Session.Output( "Abstract: "+theObject.Abstract );
	Session.Output( "ObjectType: "+theObject.ObjectType );
	Session.Output( "PackageID: "+theObject.PackageID );
	Session.Output( "ParentID: "+theObject.ParentID );
	Session.Output( "Persistence: "+theObject.Persistence );
	Session.Output( "Phase: "+theObject.Phase );
	Session.Output( "Priority: "+theObject.Priority );
	Session.Output( "PropertyType: "+theObject.PropertyType );
	Session.Output( "PropertyTypeName: "+theObject.PropertyTypeName );
	Session.Output( "RunState: "+theObject.RunState );
	Session.Output( "Status: "+theObject.Status );
	Session.Output( "Stereotype: "+theObject.Stereotype );
	Session.Output( "StereotypeEx: "+theObject.StereotypeEx );
	Session.Output( "StyleEx: "+theObject.StyleEx );
	Session.Output( "Subtype: "+theObject.Subtype );
	Session.Output( "Tablespace: "+theObject.Tablespace );
	Session.Output( "Tag: "+theObject.Tag );
	Session.Output( "TreePos: "+theObject.TreePos );
	Session.Output( "Type: "+theObject.Type );
	Session.Output( "Version: "+theObject.Version );
	Session.Output( "Visibility: "+theObject.Visibility );
	
	Session.Output( "Object:Attributes:Count: "+theObject.Attributes.Count );
	//listAttributes(theObject, "     ", " -   ");
	Session.Output( "Object:Constraints:Count: "+theObject.Constraints.Count );
	Session.Output( "Object:ConstraintsEx:Count: "+theObject.ConstraintsEx.Count );
	Session.Output( "Object:Connectors:Count: "+theObject.Connectors.Count );
	Session.Output( "Object:CustomProperties:Count: "+theObject.CustomProperties.Count );
	//listCustomProperties(theObject, "     ", " -   ");
	Session.Output( "Object:Diagrams:Count: "+theObject.Diagrams.Count );
	Session.Output( "Object:Efforts:Count: "+theObject.Efforts.Count );
	Session.Output( "Object:Elements:Count: "+theObject.Elements.Count );
	Session.Output( "Object:EmbeddedElements:Count: "+theObject.EmbeddedElements.Count );
	Session.Output( "Object:Issues:Count: "+theObject.Issues.Count );
	Session.Output( "Object:Methods:Count: "+theObject.Methods.Count );
	listMethods(theObject, "     ", " -   ");
	Session.Output( "Object:MethodsEx:Count: "+theObject.MethodsEx.Count );
	//listMethodsEx(theObject, "     ", " -   ");
	Session.Output( "Object:Metrics:Count: "+theObject.Metrics.Count );
	Session.Output( "Object:Partitions:Count: "+theObject.Partitions.Count );
	Session.Output( "Object:Properties:Count: "+theObject.Properties.Count );
	//listProperties(theObject, "     ", " -   ");
	Session.Output( "Object:Realizes:Count: "+theObject.Realizes.Count );
	Session.Output( "Object:Requirements:Count: "+theObject.Requirements.Count );
	Session.Output( "Object:RequirementsEx:Count: "+theObject.RequirementsEx.Count );
	Session.Output( "Object:Resources:Count: "+theObject.Resources.Count );
	Session.Output( "Object:Risks:Count: "+theObject.Risks.Count );
	Session.Output( "Object:Scenarios:Count: "+theObject.Scenarios.Count );
	Session.Output( "Object:TaggedValues:Count: "+theObject.TaggedValues.Count );
	//listTaggedValues(theObject, "     ", " -   ");
	Session.Output( "Object:TaggedValuesEx:Count: "+theObject.TaggedValuesEx.Count );
	Session.Output( "Object:TemplateParameters:Count: "+theObject.TemplateParameters.Count );
	Session.Output( "Object:Tests:Count: "+theObject.Tests.Count );
}

function listMethods(theObject, tab, sub) {
	for(var i=0;i<theObject.Methods.Count;i++) {
		var theMethod = theObject.Methods.GetAt(i);
		showMethod(theMethod, tab, sub);
	}
}

function listMethodsEx(theObject, tab, sub) {
	for(var i=0;i<theObject.Methods.Count;i++) {
		var theMethod = theObject.MethodsEx.GetAt(i);
		showMethod(theMethod, tab, sub);
	}
}

function showMethod(theMethod, tab, sub) {
	Session.Output( tab + "Name:" + theMethod.Name );
	var tab = tab+sub;
	Session.Output( tab + "Abstract:" + theMethod.Abstract );
	Session.Output( tab + "Behavior:" + theMethod.Behavior );
	Session.Output( tab + "ClassifierID:" + theMethod.ClassifierID );
	Session.Output( tab + "Code:" + theMethod.Code );
	Session.Output( tab + "Concurrency:" + theMethod.Concurrency );
	Session.Output( tab + "FQStereotype:" + theMethod.FQStereotype );
	Session.Output( tab + "IsConst:" + theMethod.IsConst );
	Session.Output( tab + "IsLeaf:" + theMethod.IsLeaf );
	Session.Output( tab + "IsPure:" + theMethod.IsPure );
	Session.Output( tab + "IsQuery:" + theMethod.IsQuery );
	Session.Output( tab + "IsRoot:" + theMethod.IsRoot );
	Session.Output( tab + "IsLeaf:" + theMethod.IsLeaf );
	Session.Output( tab + "IsSynchronized:" + theMethod.IsSynchronized );
	Session.Output( tab + "MethodGUID:" + theMethod.MethodGUID );
	Session.Output( tab + "MethodID:" + theMethod.MethodID );
	Session.Output( tab + "Name:" + theMethod.Name );
	Session.Output( tab + "Notes:" + theMethod.Notes );
	Session.Output( tab + "ObjectType:" + theMethod.ObjectType );
	Session.Output( tab + "Parameters:" + theMethod.Parameters.Count );
	listParameters(theMethod, tab + "     ", " -   ");
	Session.Output( tab + "ParentID:" + theMethod.ParentID );
	Session.Output( tab + "Pos:" + theMethod.Pos );
	Session.Output( tab + "PostConditions:" + theMethod.PostConditions.Count );
	Session.Output( tab + "PreConditions:" + theMethod.PreConditions.Count );
	Session.Output( tab + "ReturnIsArray:" + theMethod.ReturnIsArray );
	Session.Output( tab + "ReturnType:" + theMethod.ReturnType );
	Session.Output( tab + "StateFlags:" + theMethod.StateFlags );
	Session.Output( tab + "Stereotype:" + theMethod.Stereotype );
	Session.Output( tab + "StereotypeEx:" + theMethod.StereotypeEx );
	Session.Output( tab + "Style:" + theMethod.Style );
	Session.Output( tab + "StyleEx:" + theMethod.StyleEx );
	Session.Output( tab + "TaggedValues:" + theMethod.TaggedValues.Count );
	listTaggedValues(theMethod, tab + "     ", " -   ")
	Session.Output( tab + "Throws:" + theMethod.Throws );
	Session.Output( tab + "Visibility:" + theMethod.Visibility );
}

function listAttributes(theObject, tab, sub) {
	for(var i=0;i<theObject.Attributes.Count;i++) {
		var theAttribute = theObject.Attributes.GetAt(i);
		showAttribute( theAttribute, tab, sub);
	}//for
}

function showAttribute(theAttribute, tab, sub) {
	Session.Output( tab + "Name:" + theAttribute.Name );
	var tab = tab+sub;
	Session.Output( tab + "Alias:" + theAttribute.Alias );
	Session.Output( tab + "AllowDuplicates:" + theAttribute.AllowDuplicates );
	Session.Output( tab + "AttributeGUID:" + theAttribute.AttributeGUID );
	Session.Output( tab + "AttributeID:" + theAttribute.AttributeID );
	Session.Output( tab + "ClassifierID:" + theAttribute.ClassifierID );
	Session.Output( tab + "Container:" + theAttribute.Container );
	Session.Output( tab + "Containment:" + theAttribute.Containment );
	Session.Output( tab + "Constraints:" + theAttribute.Constraints.Count );
	Session.Output( tab + "Default:" + theAttribute.Default );
	Session.Output( tab + "FQStereotype:" + theAttribute.FQStereotype );
	Session.Output( tab + "IsCollection:" + theAttribute.IsCollection );
	Session.Output( tab + "IsConst:" + theAttribute.IsConst );
	Session.Output( tab + "IsDerived:" + theAttribute.IsDerived );
	Session.Output( tab + "IsID:" + theAttribute.IsID );
	Session.Output( tab + "IsOrdered:" + theAttribute.IsOrdered );
	Session.Output( tab + "IsStatic:" + theAttribute.IsStatic );
	Session.Output( tab + "Length:" + theAttribute.Length );
	Session.Output( tab + "LowerBound:" + theAttribute.LowerBound );
	Session.Output( tab + "Name:" + theAttribute.Name );
	Session.Output( tab + "Notes:" + theAttribute.Notes );
	Session.Output( tab + "ObjectType:" + theAttribute.ObjectType );
	Session.Output( tab + "ParentID:" + theAttribute.ParentID );
	Session.Output( tab + "Pos:" + theAttribute.Pos );
	Session.Output( tab + "Precision:" + theAttribute.Precision );
	Session.Output( tab + "RedefinedProperty:" + theAttribute.RedefinedProperty );
	Session.Output( tab + "Scale:" + theAttribute.Scale );
	Session.Output( tab + "Stereotype:" + theAttribute.Stereotype );
	Session.Output( tab + "StereotypeEx:" + theAttribute.StereotypeEx );
	Session.Output( tab + "Style:" + theAttribute.Style );
	Session.Output( tab + "StyleEx:" + theAttribute.StyleEx );
	Session.Output( tab + "SubsettedProperty:" + theAttribute.SubsettedProperty );
	Session.Output( tab + "TaggedValues:" + theAttribute.TaggedValues.Count );
	Session.Output( tab + "TaggedValuesEx:" + theAttribute.TaggedValuesEx.Count );
	Session.Output( tab + "Type:" + theAttribute.Type );
	Session.Output( tab + "UpperBound:" + theAttribute.UpperBound );
	Session.Output( tab + "Visibility:" + theAttribute.Visibility );
}

function listParameters(theObject, tab, sub) {
	for(var i=0;i<theObject.Parameters.Count;i++) {
		var theParameter = theObject.Parameters.GetAt(i);
		showParameter(theParameter, tab, sub);
	}
}

function showParameter(theParameter, tab, sub) {
	Session.Output( tab + "Name:" + theParameter.Name );
	var tab = tab+sub;
	Session.Output( tab + "Alias:" + theParameter.Alias );
	Session.Output( tab + "ClassifierID:" + theParameter.ClassifierID );
	Session.Output( tab + "Default:" + theParameter.Default );
	Session.Output( tab + "IsConst:" + theParameter.IsConst );
	Session.Output( tab + "Kind:" + theParameter.Kind );
	Session.Output( tab + "Name:" + theParameter.Name );
	Session.Output( tab + "Notes:" + theParameter.Notes );
	Session.Output( tab + "ObjectType:" + theParameter.ObjectType );
	Session.Output( tab + "OperationID:" + theParameter.OperationID );
	Session.Output( tab + "ParameterGUID:" + theParameter.ParameterGUID );
	Session.Output( tab + "Position:" + theParameter.Position );
	Session.Output( tab + "Stereotype:" + theParameter.Stereotype );
	Session.Output( tab + "StereotypeEx:" + theParameter.StereotypeEx );
	Session.Output( tab + "Style:" + theParameter.Style );
	Session.Output( tab + "StyleEx:" + theParameter.StyleEx );
	Session.Output( tab + "TaggedValues:" + theParameter.TaggedValues.Count );
	Session.Output( tab + "Type:" + theParameter.Type );
}

function listProperties(theObject, tab, sub) {
	for(var i=0;i<theObject.Properties.Count;i++) {
		var theProperty = theObject.Properties.Item(i);
		showProperty(theProperty, tab, sub);
	}
}

function showProperty(theProperty, tab, sub) {
	Session.Output( tab + "Name:" + theProperty.Name );
	var tab = tab+sub;
	Session.Output( tab + "Alias:" + theProperty.Alias );
	Session.Output( tab + "ObjectType:" + theProperty.ObjectType );
	Session.Output( tab + "Type:" + theProperty.Type );
	Session.Output( tab + "Validation:" + theProperty.Validation );
	Session.Output( tab + "Value:" + theProperty.Value );
}

function listCustomProperties(theObject, tab, sub) {
	for(var i=0;i<theObject.CustomProperties.Count;i++) {
		var theProperty = theObject.CustomProperties.GetAt(i);
		showCustomProperty(theProperty, tab, sub);
	}
}

function showCustomProperty(theProperty, tab, sub) {
	Session.Output( tab + "Name:" + theProperty.Name );
	var tab = tab+sub;
	Session.Output( tab + "Name:" + theProperty.Name );
	Session.Output( tab + "ObjectType:" + theProperty.ObjectType );
	Session.Output( tab + "Value:" + theProperty.Value );
}

function listTaggedValues(theObject, tab, sub) {
	for(var i=0;i<theObject.TaggedValues.Count;i++) {
		var theTaggedValue = theObject.TaggedValues.GetAt(i);
		showTaggedValue(theTaggedValue,  tab, sub);
	}
}

function showTaggedValue(theTaggedValue, tab, sub) {
	Session.Output( tab + "Name:" + theTaggedValue.Name );
	var tab = tab+sub;
	Session.Output( tab + "ElementID:" + theTaggedValue.ElementID );
	Session.Output( tab + "FQName:" + theTaggedValue.FQName );
	Session.Output( tab + "Name:" + theTaggedValue.Name );
	Session.Output( tab + "Notes:" + theTaggedValue.Notes );
	Session.Output( tab + "ObjectType:" + theTaggedValue.ObjectType );
	Session.Output( tab + "OperationID:" + theTaggedValue.OperationID );
	Session.Output( tab + "PropertyGUID:" + theTaggedValue.PropertyGUID );
	Session.Output( tab + "PropertyID:" + theTaggedValue.PropertyID );
	Session.Output( tab + "Value:" + theTaggedValue.Value );
	Session.Output( tab + "HasAttributes():" + theTaggedValue.HasAttributes() );
}

main();