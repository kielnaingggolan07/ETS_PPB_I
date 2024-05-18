package com.example.mylogin

import ListTicketPreview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material3.TextField
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color



@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC))
            .padding(16.dp)
    ) {
        Text(
            text = "Selamat Datang",
            fontSize = 22.sp,
            color = Color.White
        )
        Text(
            text = "Hesekiel Nainggolan",
            fontSize = 26.sp,
            color = Color.White,
            modifier = Modifier.padding(top = 0.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Mau pergi kemana?") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = CircleShape)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo2), // Ganti dengan sumber gambar Anda
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
//            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = "Easy Booking: Happy Travel",
                fontSize = 20.sp,
                modifier = Modifier.padding(0.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        TransportOption(navController = navController, title = "Pesawat", description = "Transportasi Udara", iconId = R.drawable.pesawat2)
        Spacer(modifier = Modifier.height(10.dp))
        TransportOption(navController = navController, title = "Kapal Laut", description = "Transportasi Laut", iconId = R.drawable.kapal3)
        Spacer(modifier = Modifier.height(10.dp))
        TransportOption(navController = navController, title = "Kereta Api", description = "Transportasi Laut", iconId = R.drawable.api2)
        Spacer(modifier = Modifier.height(10.dp))
        TransportOption(navController = navController, title = "Bus", description = "Transportasi Laut", iconId = R.drawable.bis)
    }
}

@Composable
fun TransportOption(navController: NavHostController, title: String, description: String, iconId: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = CircleShape)
            .padding(16.dp)

    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    navController.navigate("TicketBooking")
                }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = title,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.clickable {
                navController.navigate("TicketBooking")
            })
            Text(
                text = description,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.clickable {
                    navController.navigate("TicketBooking")
                })
        }
    }
}


@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController = navController) }
        composable("home") { HomeScreen(navController = navController) }
        composable("TicketBooking") { TicketBookingScreen(navController = navController)  }
        composable("ticket") {ListTicketPreview()}
    }
}
