# base
Basic data repo to build a crm system with spring boot.

The packages can be used to attach a SQL based production system to a cloud environment. We build a new cloud based insurance portfolio management system and need some technologies to attach it to the existing legacy applications.

Most existing applications using a sql database can work together with a cloud based system. The new world uses object identifiers like uuid's to identify objects that are used and maniupulated by cloud services.

There is no need to use a new type of storage, a sql database can do the job. We prefere to store two fields, one uuid for the object identification and another uuid for the history in each database table used by the cloud services. If this is not possible or desired a key table can do the job too. If necessary, such key tables can be speed up using technologies like the redis database.

Once we have the object identifier and history we can use additional tables to store the data each object needs (or should have). Including the possibility of having links between objects, documents, large link lists or sets stored also in sql tables.

The cloud services are using a sql table view or an object oriented pattern to work with the data. There is also the possibility to use a handshake between data objects to support asynchronous processing.
