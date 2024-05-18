package com.example.mylogin

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketBookingScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var departure by remember { mutableStateOf("Jakarta") }
    var destination by remember { mutableStateOf("Jakarta") }
    var children by remember { mutableStateOf(0) }
    var adults by remember { mutableStateOf(0) }
    var date by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var classType by remember { mutableStateOf("Eksekutif") }

    val departureOptions = listOf("Medan", "Surabaya", "Jakarta", "Bandung")
    val destinationOptions = listOf("Medan", "Surabaya", "Jakarta", "Bandung")
    val classOptions = listOf("Eksekutif", "Bisnis", "Ekonomi")

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val context = LocalContext.current

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date = "$dayOfMonth/${month + 1}/$year"
        }, year, month, day
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F7FA)) // Mengatur warna latar belakang untuk seluruh konten
            .padding(15.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .shadow(1.dp, RoundedCornerShape(2.dp))
                .padding(15.dp)
                .padding(top = 10.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp), // Tambahkan padding vertikal agar teks tidak terlalu menempel ke atas
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Pemesanan Tiket",
                    fontSize = 24.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Mohon isi data sesuai dengan KTP Anda",
                fontSize = 17.sp,
                color = Color.Gray,
                modifier = Modifier
                    .background(
                        Color(0xFFB3E5FC),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(15.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nama Penumpang") },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            DropdownMenu(
                label = "Keberangkatan",
                options = departureOptions,
                selectedOption = departure,
                onOptionSelected = { departure = it }
            )
            Spacer(modifier = Modifier.height(8.dp))

            DropdownMenu(
                label = "Tujuan",
                options = destinationOptions,
                selectedOption = destination,
                onOptionSelected = { destination = it }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Anak-anak",
                        fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding())
                    {
                        IconButton(onClick = { if (children > 0) children-- }) {
                            Icon(Icons.Default.Delete, contentDescription = null)
                        }
                        Text(text = "$children")
                        IconButton(onClick = { children++ }) {
                            Icon(Icons.Default.Add, contentDescription = null)
                        }
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Dewasa",
                        fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { if (adults > 0) adults-- }) {
                            Icon(Icons.Default.Delete, contentDescription = null)
                        }
                        Text(text = "$adults")
                        IconButton(onClick = { adults++ }) {
                            Icon(Icons.Default.Add, contentDescription = null)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            DropdownMenu(
                label = "Tipe / Kelas",
                options = classOptions,
                selectedOption = classType,
                onOptionSelected = { classType = it }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("Tanggal Berangkat") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { datePickerDialog.show() }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Nomor Telp / HP") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                                navController.navigate("ticket")
                          },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Pesan Sekarang")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedOption,
            onValueChange = { },
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}


