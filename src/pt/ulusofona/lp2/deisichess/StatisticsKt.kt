package pt.ulusofona.lp2.deisichess

import pt.ulusofona.lp2.deisichess.pieces.Piece

class StatisticsKt {
    companion object {
        @JvmStatic
        fun getStatsCalculator(type: StatType): (GameManager) -> ArrayList<String> {
            return when (type) {
                StatType.TOP_5_CAPTURAS -> ::topCincoCapturas
                StatType.PECAS_MAIS_BARALHADAS -> ::pecaMaisBaralhada
                StatType.TOP_5_PONTOS -> ::topCincoPontos
                StatType.PECAS_MAIS_5_CAPTURAS -> ::pecasMaisCincoCapturas
                StatType.TIPOS_CAPTURADOS -> ::tiposCapturados
            }

        }

        fun topCincoCapturas(gameManager: GameManager): ArrayList<String> {
            return ArrayList(gameManager.board.pieces()
                    .sortedByDescending { it.numCapturas }
                    .take(5)
                    .map { "${it.nickName} (${teamNameToString(it.team)}) fez ${it.numCapturas} capturas" })
        }

        fun pecaMaisBaralhada(gameManager: GameManager): ArrayList<String> {
            return ArrayList(gameManager.board.pieces()
                    .filter { it.numInvalidMoves > 0 }
                    .sortedByDescending { it.numInvalidMoves.toDouble() / (it.numInvalidMoves + it.numValidMoves)  }
                    .take(3)
                    .map { "${it.team}:${it.nickName}:${it.numInvalidMoves}:${it.numValidMoves}" })
        }

        fun topCincoPontos(gameManager: GameManager): ArrayList<String> {
            return ArrayList(gameManager.board.pieces()
                    .filter { it.capturedPoints > 0 }
                    .sortedByDescending { it.capturedPoints }
                    .take(5)
                    .map { "${it.nickName} (${teamNameToString(it.team)}) tem ${it.capturedPoints} pontos" })
        }

        fun pecasMaisCincoCapturas(gameManager: GameManager): ArrayList<String> {
            return ArrayList(gameManager.board.pieces()
                    .filter { it.numCapturas > 5 }
                    .map { "${teamNameToString(it.team)}:${it.nickName}:${it.numCapturas}" })
        }

        fun tiposCapturados(gameManager: GameManager): ArrayList<String> {
            return ArrayList(gameManager.nameOfPiecesCaptured)
        }

        private fun teamNameToString(teamId: Int): String {
            return if (teamId == Piece.BLACK_TEAM) {
                "PRETA"
            } else {
                "BRANCA"
            }
        }
    }
}