# MarvelHeroes

La aplicación se compone de 2 pantallas:

<ul>
<li>
<p>Lista de personajes</p>
</li>
<li>
<p>Detalle de personaje</p>
</li>
</ul>

MVVM, Single Activity, Android Architecture Components: Activity/Fragment -> ViewModel -> Repository.

Patrón Repository con clean architecture para acceder a la API de Marvel.

Retofit para hacer llamadas a la API de Marvel.

Unit test

<h3>Instalación</h3>

Para el correcto funcionamiento del proyecto debemos solicitar nuestras api key en la web: https://developer.marvel.com/.
Una vez que tenemos dichas api keys debemos incluirlas en el archivo <b>local.properties</b> de la siguiente forma:<br>
<br>
PUBLIC_KEY="nuestra clave pública"</br>
PRIVATE_KEY="nuestra clave privada"</br>

De no incluir las api key el proyecto no compilará y no podremos hacer uso del mismo.
