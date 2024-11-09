package com.example.amphibians.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.model.Amphibian
import com.example.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    modifier: Modifier = Modifier,
    contentPadding:PaddingValues = PaddingValues(0.dp)
){

    when(amphibiansUiState) {
        is AmphibiansUiState.Loading -> {}
        is AmphibiansUiState.Success -> AmphibiansList(contentPadding = contentPadding, amphibiansList = amphibiansUiState.amphibiansList)
        is AmphibiansUiState.Error -> {}
    }
}

@Composable
fun AmphibiansList(amphibiansList:List<Amphibian>, contentPadding: PaddingValues) {
    LazyColumn(contentPadding = contentPadding){
        items(amphibiansList){
            AmphibiansCard(it)
        }
    }
}

@Composable
fun AmphibiansCard(amphibian:Amphibian) {
    Card(modifier = Modifier.padding(8.dp).fillMaxWidth()){
        Column() {
            Text(
                text = amphibian.name + " " + amphibian.type,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth())
            Text(text = amphibian.description,
                modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    AmphibiansTheme {
        val mockData = List(10) { Amphibian("$it", "fff","ssss","sss") }
//        AmphibiansList(mockData)
    }
}