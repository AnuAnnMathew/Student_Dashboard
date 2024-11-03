package com.studentdashboard.screens.home.parkingspots

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.studentdashboard.data.models.ParkingSpot
import com.studentdashboard.data.models.ParkingSpotType

/**
 * Displays a parking spot item in the list.
 *
 * @param modifier Modifier to be applied to the component.
 * @param item The [ParkingSpot] data to be displayed.
 * @param itemIndex The index of the item in the list.
 * @param itemsSize The total number of items in the list.
 */
@Composable
fun ParkingSpotItemUI(
    modifier: Modifier = Modifier,
    item: ParkingSpot,
    itemIndex: Int,
    itemsSize: Int
) {
    val cornerSize = 16.dp
    val surfaceShape = remember(itemsSize, itemIndex) {
        when {
            itemIndex == 0 && itemsSize == 1 -> RoundedCornerShape(cornerSize)
            itemIndex == 0 -> RoundedCornerShape(
                topEnd = cornerSize,
                topStart = cornerSize,
                bottomEnd = 0.dp,
                bottomStart = 0.dp
            )
            itemIndex > 0 && itemIndex < itemsSize - 1 -> RectangleShape
            else -> RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomEnd = cornerSize,
                bottomStart = cornerSize
            )
        }
    }

    Surface(
        modifier = modifier,
        shape = surfaceShape,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.location,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(.7f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.3f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.End)
            ) {
                ParkingStatusUI(
                    parkingSpotType = ParkingSpotType.Visitor,
                    count = item.visitor,
                    modifier = Modifier.weight(.5f)
                )
                ParkingStatusUI(
                    parkingSpotType = ParkingSpotType.Reserved,
                    count = item.reserved,
                    modifier = Modifier.weight(.5f)
                )
            }
        }
    }
}

/**
 * Displays the status of a parking spot.
 *
 * @param modifier Modifier to be applied to the status UI component.
 * @param parkingSpotType The type of parking spot (Visitor or Reserved).
 * @param count The number of available spots of this type.
 */
@Composable
private fun ParkingStatusUI(
    modifier: Modifier = Modifier,
    parkingSpotType: ParkingSpotType,
    count: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(
                    color = parkingSpotType.backgroundColor,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = parkingSpotType.identifyingLetter,
                style = MaterialTheme.typography.labelSmall,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }

        Text(
            text = count.toString(),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold
        )
    }
}
