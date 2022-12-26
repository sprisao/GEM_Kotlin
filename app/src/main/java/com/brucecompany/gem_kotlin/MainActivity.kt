
package com.brucecompany.gem_kotlin

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brucecompany.gem_kotlin.ui.theme.GEM_KotlinTheme
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user: MutableMap<String, Any> =HashMap()
        user["first"] = "Bruce"
        user["last"] = "Wayne"
        user["age"] = 30

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

        setContent {
            GEM_KotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GEM_KotlinTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun TextComponent() {
    Card(modifier =Modifier.fillMaxSize().padding(15.dp).clickable {  }) {
        Column(modifier = Modifier.padding(15.dp)) {
            /*build AnnotatedString*/
            val annotatedString = buildAnnotatedString {
                append("Hello ")
                withStyle(style = MaterialTheme.typography.bodyLarge.toSpanStyle()) {
                    append("World")
                }
            }
            Text(text = annotatedString)
            Text(text = "Hello Android!")
        }
    }
}
