package com.example.cryptoassets.util

import java.math.BigDecimal
import java.math.RoundingMode

class BigDecimalUtils {


    companion object {

        public val PRECISAO : Int = 8


        fun ofDouble(valor : Double) : BigDecimal {
            return ofBigDecimal(
                BigDecimal(valor),
                PRECISAO
            )
        }
        fun ofString(valor : String) : BigDecimal {
            return ofBigDecimal(
                BigDecimal(valor),
                PRECISAO
            )
        }
        fun ofInt(valor : Int) : BigDecimal {
            return ofBigDecimal(
                BigDecimal(valor),
                PRECISAO
            )
        }

        fun ofBigDecimal(valor : BigDecimal) : BigDecimal {
            return ofBigDecimal(
                valor,
                PRECISAO
            )
        }

        fun ofBigDecimal(valor : BigDecimal, casas:Int) : BigDecimal {
            return valor.setScale(casas, RoundingMode.DOWN)
        }

        fun divide(valor1 : BigDecimal, valor2: BigDecimal) : BigDecimal {
            val bigDecimal = ofBigDecimal(
                valor1
            ).divide(
                ofBigDecimal(
                    valor2
                ),
                PRECISAO, RoundingMode.DOWN
            )
            return ofBigDecimal(bigDecimal)
        }

        fun valorMenorOuIgual(valor1: BigDecimal, valor2: BigDecimal): Boolean {
            return valor1 <= valor2
        }

        fun valorIgual(valor1: BigDecimal, valor2: BigDecimal): Boolean {
            return valor1 == valor2
        }

        fun valorMaiorOuIgual(valor1: BigDecimal, valor2: BigDecimal): Boolean {
            return valor1 >= valor2
        }

        fun valorEntre(valor: BigDecimal, valorInicial: BigDecimal, valorFinal:BigDecimal): Boolean {
            return valorMaiorOuIgual(
                valor,
                valorInicial
            ) && valorMenorOuIgual(
                valor,
                valorFinal
            )
        }

        fun valorMaior(valor1: BigDecimal, valor2: BigDecimal): Boolean {
            return valor1 > valor2
        }

        fun valorMenor(valor1: BigDecimal, valor2: BigDecimal): Boolean {
            return valor1 < valor2
        }

        fun zero():BigDecimal{
            return ofBigDecimal(BigDecimal.ZERO)
        }


    }

}