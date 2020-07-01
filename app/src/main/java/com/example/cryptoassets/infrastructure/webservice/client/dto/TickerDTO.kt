package com.example.cryptoassets.infrastructure.webservice.client.dto

import com.fasterxml.jackson.annotation.JsonRootName


@JsonRootName(value = "ticker")
data class TickerDTO(val last: String?=null)