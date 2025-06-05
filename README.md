# MobileChallengeUala


Para la solución del problema decidí implementar un Splash donde descargo toda la información con Retrofit y la almaceno en una BD con Room.

Esto me ayudo para implementar un Search Bar con un Lazy Column, ya que con esto puedo consultar el nombre de las ciudades y mostrar en pantalla 
el mapa de Google con ubicación cuando se da click en el item de la ciudad. También el Lazy Column me permitió implementar un IconButtom 
con el cual puedo manejar un estado de colores de azul y gris, dependiendo de si la ciudad es favorita o no. 
También me permite captar cuando se da Click y colocar la ciudad en el Item que se selecciona como favorita.

Utilice NavController para el flujo vertical y lanzar el flujo horizontal, dependiendo de la orientación que se detecte de la pantalla.

La  pantalla de información no la vi necesaria ya que cuando se lanza la ubicación en el mapa, 
se muestra esta información que se pedía en el Marker que se utiliza.
