# ExamenSOSAFE

La prueba consiste en una aplicación móvil para buscar y guardar puntos de interés, donde:

- Debe poseer una vista para buscar puntos de interés, que debe estar conformada por un mapa y un buscador. Para buscar los POI se debe usar el endpoint [Nearby search request de Google Maps](https://developers.google.com/places/web-service/search?hl=es-419#PlaceSearchRequests) (Vía Http, sin uso del SDK); cuyos resultados deben ser mostrados en el mapa en forma de markers (a elección). La búsqueda debe ser basada en las coordenadas del posicionamiento del mapa y keywords ingresados en el buscador
- Se debe crear una vista de detalles de los puntos de interés, donde muestre:
- Nombre del punto de interés
- Dirección
- Imagen referencia
- Rating (si lo tiene)
- Listado de las primeras 5 *Reviews* junto con la imagen del *Reviewer* (Si lo tiene) [https://developers.google.com/places/web-service/details?hl=es-419](https://developers.google.com/places/web-service/details?hl=es-419)
- Mapa con la ubicación
- Botón para guardar/borrar (Guardar como favorito)
- La opción de Guardar o borrar de favoritos, debe gatillar el guardado o el borrado de los datos en base de datos local
- Crear una vista que muestre el listado de los puntos de interés guardados, donde se pueda acceder a cada uno de los ítems, y este navegue hacia la vista del detalle del mismo.
- La vista del detalle, debe cargar la información de acuerdo donde proviene, es decir, si viene del buscador y este dato no existe como guardado en favorito, debe cargar los datos provenientes del request al API, pero si este proviene del listado (ítems guardados localmente) debe recuperar los datos de la BD local.

Se uso arquitectura limpia además de lo siguiente:

- Coroutines
- MVVM
- Extension Functions
- Dagger Hilt
- Retrofit
- Room
- Navigation Components


# Arquitectura

![arquitectura](https://camo.githubusercontent.com/e1459518188f17c1fa6a30570ca5d21530975f9e/68747470733a2f2f646576656c6f7065722e616e64726f69642e636f6d2f746f7069632f6c69627261726965732f6172636869746563747572652f696d616765732f66696e616c2d6172636869746563747572652e706e67)


# Imágenes

![subir1](https://user-images.githubusercontent.com/39350582/92075564-bcf82100-ed86-11ea-8afb-463abc44da00.png)
![subir2](https://user-images.githubusercontent.com/39350582/92075567-bec1e480-ed86-11ea-8df4-232dbf75c689.png)
![subir3](https://user-images.githubusercontent.com/39350582/92075570-bff31180-ed86-11ea-93fb-4c07a1fc546c.png)
![subir4](https://user-images.githubusercontent.com/39350582/92075574-c1243e80-ed86-11ea-9a3b-6199b284ba0f.png)
![subir5](https://user-images.githubusercontent.com/39350582/92075578-c2556b80-ed86-11ea-96b3-f1dc9d4b1af5.png)

Espero haber cumplido todas las espectativas :smile:
