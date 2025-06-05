# MobileChallengeUala


Para la solución del problema decidí implementar un Splash donde descargo toda la información con Retrofit y la almaceno en una BD con Room.

Implemente un Search Bar con un Lazy Column, ya que con esto puedo consultar el nombre de las ciudades y
mostrar en pantalla el mapa de Google con la ubicación cuando se da click en el item de la ciudad. 
También el Lazy Column me permitió implementar un IconButtom con el cual puedo manejar un estado de colores azul y gris, 
dependiendo de si la ciudad es favorita o no. También me permite captar cuando se da Click y colocar la ciudad en el Item que se selecciona como favorita.

Para las consultas se utilizaron view models junto con live data para mostrar los resultados en el lazy column a medida que el usuario va escribiendo.

Utilice NavController para el flujo vertical y lanzar el flujo horizontal, dependiendo de la orientación que se detecte de la pantalla.

Se utilizo dagger hilt para la inyección de dependencias.

La  pantalla de información no la vi necesaria ya que cuando se lanza la pantalla con la ubicación marcara,
se muestra la información que se pedía en la pantalla de información en el Marker que se utiliza en google maps.