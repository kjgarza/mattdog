# DataCite Search

This is Search for metadata uploaded to DataCite's Metadata Store ([MDS](https://mds.datacite.org)).
It is based on [Solr](http://lucene.apache.org/solr/).

To learn more about DataCite please visit [our website](http://www.datacite.org)

To use this software please go to [http://search.datacite.org](https://search.datacite.org)

# Installation (for development only)

This a java servlet web application. You need a servlet container (e.g. tomcat). 
You also need Maven 2.2.1 and JDK 6 in your system (OpenJDK from Ubuntu
works fine).

All dependencies are managed by Maven public repositories.

## Solr Home Directory

Solr requires a home directory to store the index and some properties. 
Be aware that the user running running your servlet container must have
write access to this directory.

The content of `solr_home` has to be copied to your selected solr home directory.

All other required files will be in the war-file.

## Configure the source code

The git repository has a bunch of *.template files. 
Those files are templates for the various configuration files which
are machine specific i.e. passwords, IP addresses etc.

To customise them you need to make a copy omitting .template from
file name.

Now in such created file you need to adjust values according to your
local environment.

### solr_home/conf/solrcore.properties

MDS database specific properties.

### src/main/webapp/META-INF/context.xml

Specify the path to your preliminarily chosen solr home directory by replacing `<dir>`.

## Compiling

    mvn clean compile war:war
    
will create `target/search.war`, which is ready to be deployed. 

## Configure servlet container

All resources with write access to the index are secured. You need role `solradmin` to access them.
If you are using tomcat you can use the following content for `tomcat-users.xml`: 

    <tomcat-users>
      <user username="<user>" password="<pass>" roles="solradmin"/>
    </tomcat-users>

# Running

After deploying the following resources are of interest:

* `/ui` - search user interface
* `/admin` - admin interface

Data from MDS is imported via Solr's DataImportHandler. You can access it via admin interface.
Another option especially useful for cron jobs is `scripts/solr-client`. Simply try

    scripts/solr-client import delta
    
for a delta import of MDS metadata. See `solr-client help` for usage.