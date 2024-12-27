package com.example.birthday_card_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.birthday_card_compose.ui.theme.Birthday_card_composeTheme
//el paquete unit contiene las distintas unidades como scalable pixels y density-dependent pixels
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Birthday_card_composeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingImage(
                        message = stringResource(R.string.happy_birthday_text),
                        from = stringResource(R.string.signature_text),
                        //como se desconoce como ampliar el tamaño del contenedor para que
                        //el posicionamiento vertical funciones, se usa padding top
                        //alternativamente
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingText(message:String = "Default user", from:String , modifier: Modifier = Modifier){
    //los 3 elementos composables basicos en compose son column, row y box
    //cada uno de esos elemntos funcionan como contenedores para otros elemntos composables
    //es una buena practica pasar los atributos del modifier desde el parent, evitando pasarlos
    //en los parametros del column
    //Column (modifier = modifier.padding(8.dp), verticalArrangement = Arrangement.Center) {
    Column (modifier = modifier,
            verticalArrangement = Arrangement.Center) {
        Text(
            text = message,
            //indica el tamaño de la fuente, se pueden usar distintas unidades
            fontSize = 70.sp,
            //defina la altura del elemento Text
            lineHeight = 90.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        //pueden llamarse mas de una funcion composable
        Text(
            text = from,
            fontSize = 36.sp,
            color = Color.Black,
            //aqui se debe de tener cuidado de que modifier se invoca, es Modifier no el parametro modifier
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
            //esta linea asigna un modifier tal como se manda en los parametros
            //por lo que genera una nueva seccion
            //modifier = modifier.padding(8.dp).align(alignment = Alignment.End)
        )
    }

    /*
    * importante: la notacion   triling lambda es una caracteristica que permite usar un cuerpo
    * cuando se llama una funcion, encerrado en curly braces, como si fuera el pase de una funcion
    * esto siempre y cuando el ultimo parametro de funcion sea una funcion, de tal manera que el
    * siguiente codigo:
    *
    * Row(content = {Text("some text"})
    *
    * donde teniendo conocimiento de que la funcion content es el ultimo parametro, se puede
    * cambiar por
    *
    * Row {Text("Some text") }
    *
    *
    * */
}

@Composable
fun GreetingImage(message: String, from: String, modifier:Modifier = Modifier){
    val image = painterResource(R.drawable.androidparty)
    //el elemento box es util cuando se encesita apilar objetos, ademas de que
    //usar un layout con box permite configurar alineamientos especificos
    Box (modifier){
        //funcion content
        Image(
            painter = image,
            contentDescription = null,
            //permite escalar el elemento
            contentScale = ContentScale.Crop,
            alpha = 0.5F
        )
        GreetingText(
            message= message,
            from=from,
            modifier= Modifier
                .fillMaxSize()
                .padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    Birthday_card_composeTheme {
        GreetingImage(
            message = stringResource(R.string.happy_birthday_text),
            from = stringResource(R.string.signature_text)
        )
    }
}