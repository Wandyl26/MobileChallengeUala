package com.example.mobilechallengeuala.view.composable

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobilechallengeuala.model.domain.CityDomain
import com.example.mobilechallengeuala.viewmodel.SearchCitiesViewModel




/*
Elegí LazyColumn debido a que es la opción más robusta y recomendada para crear listas en Jetpack Compose,
especialmente cuando se manejan grandes conjuntos de datos o listas con desplazamiento vertical.
A diferencia de un Column normal, que renderiza todos los elementos de la lista de forma simultánea,
LazyColumn solo renderiza los elementos que están visibles en pantalla. Esto optimiza el rendimiento y
la eficiencia al usar menos memoria y recursos de CPU.

Lo elegí principalmente por su rendimiento:

Solo carga los elementos que se muestran en pantalla, lo que reduce el consumo de recursos y la demora en la carga de listas grandes,
y esto me ayuda mucho debido a que hay mas de 200 mil registros de ciudades en la base de datos con una unica tabla,
y el search bar consulta solo en esa tabla, aunque coloque un limite de 20 registros por consulta, por lo tanto
me preocupaba el futuro donde se necesitara ampliar ese limite.


El search bar lo elegí por comodidad, ya que me parece mas sencillo para establecer una búsqueda sensible donde se vaya actualizando
o buscando a medida que escribe.

*/


@Composable
fun SearchedCitiesNavigation(
    searchCitiesViewModel: SearchCitiesViewModel,
    onSearch: (String) -> Unit,
    onFavorite: (city: CityDomain) -> Unit) {
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "search") {
                composable("map") { MapsScreen(navController = navController) }
                composable("search") { CustomizableSearchBar(searchCitiesViewModel, onSearch, onFavorite, navController)}
            }
        }
        Configuration.ORIENTATION_LANDSCAPE -> {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "searchMap") {
                composable("searchMap") { HorizontalScreen(searchCitiesViewModel, onSearch, onFavorite) }
            }
        }

    }
}
