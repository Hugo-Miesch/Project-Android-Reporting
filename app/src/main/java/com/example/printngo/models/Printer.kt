package com.example.printngo.models

data class Printer(
    val id: Int,
    val name: String,
    val status: String,
    val description: String,
    val imageName: String
)

data class PrinterDetail(
    val id: Int,
    val name: String,
    val status: String,
    val description: String,
    val imageName: String,
    val history: List<PrintJob>
)

data class PrintJob(
    val file: String,
    val date: String,
    val duration: String,
    val result: String
)

