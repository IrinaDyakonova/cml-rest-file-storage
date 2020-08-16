<h1>Rest file storage</h1>
<h1>Table of Contents</h1>
<ul>
<li><a href="#project_purpose">Project purpose</a> </li>
<li><a href="#project_structure">Project structure</a> </li>
<li><a href="#for_developer">For developer</a> </li>
<li><a href="#">Author</a> </li>
</ul>
<h1 id="project_purpose">Project purpose</h1>
Creating file manager with saving data in elasticsearch.
<hr>

<p>Anyone can use existing api endpoints to manage the list of files and their tags.<p>
<p>List of available endpoints:</p>
<ul>
<li> 
Getting a list of files. Api link: GET /file?tags=tag1,tag2,tag3&page=2&size=3&q=aaa
<br>All params are optional.
</li>
<li>
Writing file to the database with automatic tagging according to the extension obtained from the file name. Api link: POST /file
<br>Endpoint params: 
<br>Name field is required. Must have String type. Max length 28 symbols and min length 6. Extension is required in file name.
<br>Size field is required. Must have Long type. Min value 0.
</li>
<li>
Removing a file from the database by ID. Api link: DELETE /file/{ID}
</li>
<li>
Adding tags to a file from the database by ID. Api link: POST /file/{ID}/tags
<br>Endpoint params: 
<br>Tags list param is required. Must be list of String type.
</li>
<li>
Removing file tags from the database. Api link: DELETE /file/{ID}/tags
<br>Endpoint params: 
<br>Tags list param is required. Must be list of String type.
</li>
</ul>
<h1 id="project_structure">Project structure</h1>
<ul>
<li>Java 11</li>
<li>Maven 3.6.0</li>
<li>maven-checkstyle-plugin 3.1.1</li>
<li>Spring Boot 2.3.1.RELEASE</li>
<li>ElasticSearch 7.8.2</li>
</ul>

<h1 id="for_developer">For developer</h1>
<ol>
<li>Open the project in your IDE.</li>
<li>Add Java SDK 11 or above in Project Structure.</li>
<li>Install ElasticSearch if you don't have it and start in cmd.</li>
<li>Run the project.</li>
</ol>
<h1 id="author">Author</h1>
Irina Dyakonova: https://github.com/IrinaDyakonova

