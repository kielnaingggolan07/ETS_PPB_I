import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListTicket() {

    val tickets = listOf(
        Ticket("AirAsia X", "23.50 CGK", "09.10 NRT", "7 Jam 20 Menit (langsung)", "Rp6.278.500/org", true, false),
        Ticket("Multi Maskapai", "21.15 CGK", "22.30 HND", "23 Jam 15 Menit (1 transit)", "Rp7.673.000/org", false, false),
        Ticket("Multi Maskapai", "20.30 CGK", "22.30 HND", "1 Hari (1 transit)", "Rp7.673.000/org", false, false),
        Ticket("Multi Maskapai", "05.10 CGK", "22.30 HND", "15 Jam 20 Menit (1 transit)", "Rp7.693.000/org", false, false)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFE0F7FA))
    ) {
        items(tickets) { ticket ->
            TicketItem(ticket)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun TicketItem(ticket: Ticket) {
    Box(modifier = Modifier.padding(top = 5.dp).background(Color(0xFFE0F7FA))) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Airline: ${ticket.airline}",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(text = "Departure Time: ${ticket.departureTime}", fontSize = 16.sp)
                Text(text = "Arrival Time: ${ticket.arrivalTime}", fontSize = 16.sp)
                Text(text = "Duration: ${ticket.duration}", fontSize = 16.sp)
                Text(text = "Price: ${ticket.price}", fontSize = 16.sp)
                Text(text = "Direct: ${if (ticket.isDirect) "Yes" else "No"}", fontSize = 16.sp)
                Text(
                    text = "Refundable: ${if (ticket.isRefundable) "Yes" else "No"}",
                    fontSize = 16.sp
                )
            }
        }
    }
}

data class Ticket(
    val airline: String,
    val departureTime: String,
    val arrivalTime: String,
    val duration: String,
    val price: String,
    val isDirect: Boolean,
    val isRefundable: Boolean
)

@Composable
fun ListTicketPreview() {
    ListTicket()
}
