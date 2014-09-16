Manufacturing Bill of Material Data Services
============================================

### How to build this project

1. Clone this repository to git home

2. Build the project
 
```sh
mvn clean install
```

### To run this code, you need to do: 

1. Install [neo4j](http://neo4j.com/artifact.php?name=neo4j-community_windows_2_1_3.exe) database.

2. Copy all jar files from /db/plugin directory to /NEO4J_HOME/plugins and restart the server.

3. To load the example data into database, you have two options:

```
    a) Install gremlin console, connect to the neo4j database running instance and run fabrica-db.groovy script:

    $ cd db
    $ gremlin.bat
    $ gremlin> \l fabrica-db.groovy

    b) Using POSTMAN Rest Client Chrome Plugin or other rest client, import the request collection from db/fabrica-db.postman and run the script "Load Example Database" to load the data. This option needs [Neo4J Gremlin Extension Plugin](http://neo4j-contrib.github.io/gremlin-plugin) done in step 2.
```

4. Run the app:

```
$ cd app
$ npm install
$ bower install
$ grunt serve
```

Graph Data Model
================

To work with graph vertex and edges see a view of the data:

![Alt text](docs/Processo%20de%20Fabricação.png?raw=true "Fabrica")
