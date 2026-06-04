package com.gramavasathi.app.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gramavasathi.app.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostTrainingScreen(navController: NavController, viewModel: MainViewModel) {
    var currentStep by remember { mutableIntStateOf(1) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Host Readiness", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Step Indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StepIndicator(step = 1, label = "Safety", currentStep = currentStep)
                HorizontalDivider(modifier = Modifier.width(32.dp), thickness = 2.dp, color = if (currentStep > 1) MaterialTheme.colorScheme.primary else Color.Gray.copy(alpha = 0.3f))
                StepIndicator(step = 2, label = "Comfort", currentStep = currentStep)
                HorizontalDivider(modifier = Modifier.width(32.dp), thickness = 2.dp, color = if (currentStep > 2) MaterialTheme.colorScheme.primary else Color.Gray.copy(alpha = 0.3f))
                StepIndicator(step = 3, label = "Optimizer", currentStep = currentStep)
            }

            Spacer(modifier = Modifier.height(24.dp))

            when (currentStep) {
                1 -> ReadinessChecklist(viewModel, listOf(1, 2, 5))
                2 -> ReadinessChecklist(viewModel, listOf(3, 4))
                3 -> ListingOptimizerSection(viewModel)
            }

            Spacer(modifier = Modifier.weight(1f))

            // Navigation Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (currentStep > 1) {
                    OutlinedButton(onClick = { currentStep-- }) {
                        Text("Previous")
                    }
                } else {
                    Spacer(modifier = Modifier.width(1.dp))
                }

                if (currentStep < 3) {
                    Button(onClick = { currentStep++ }) {
                        Text("Next")
                    }
                } else {
                    Button(
                        onClick = { navController.popBackStack() },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
                    ) {
                        Text("Finish Setup")
                    }
                }
            }
        }
    }
}

@Composable
fun StepIndicator(step: Int, label: String, currentStep: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            modifier = Modifier.size(32.dp),
            shape = RoundedCornerShape(16.dp),
            color = if (step <= currentStep) MaterialTheme.colorScheme.primary else Color.Gray.copy(alpha = 0.2f)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = step.toString(),
                    color = if (step <= currentStep) Color.White else Color.Gray,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Text(text = label, style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
fun ReadinessChecklist(viewModel: MainViewModel, itemIds: List<Int>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Readiness Score: ${viewModel.readinessScore}%",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            LinearProgressIndicator(
                progress = { viewModel.readinessScore / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            viewModel.checklistItems.filter { it.id in itemIds }.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = item.isChecked,
                        onCheckedChange = { viewModel.toggleChecklistItem(item.id) }
                    )
                    Text(text = item.title, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

@Composable
fun ListingOptimizerSection(viewModel: MainViewModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.05f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Simulated AI Listing Optimizer",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                "Describe your farm in simple words, and let our AI transform it into a professional listing.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            OutlinedTextField(
                value = viewModel.rawDescription,
                onValueChange = { viewModel.rawDescription = it },
                modifier = Modifier.fillMaxWidth().height(120.dp),
                placeholder = { Text("e.g., my farm has cows and a river near it, very quiet place") },
                shape = RoundedCornerShape(12.dp)
            )

            Button(
                onClick = { viewModel.optimizeListing() },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                enabled = !viewModel.isOptimizing && viewModel.rawDescription.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
            ) {
                if (viewModel.isOptimizing) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                } else {
                    Icon(Icons.Default.AutoFixHigh, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Optimize with AI")
                }
            }

            AnimatedVisibility(visible = viewModel.optimizedDescription.isNotEmpty()) {
                Column(modifier = Modifier.padding(top = 16.dp)) {
                    Text("Optimized Result:", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.tertiary)
                    Surface(
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                        color = Color.White
                    ) {
                        Text(
                            text = viewModel.optimizedDescription,
                            modifier = Modifier.padding(12.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 22.sp
                        )
                    }
                }
            }
        }
    }
}
