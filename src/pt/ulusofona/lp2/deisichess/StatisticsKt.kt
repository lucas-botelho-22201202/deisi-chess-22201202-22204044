package pt.ulusofona.lp2.deisichess

public class StatisticsKt {
    fun getStatsCalculator(type: StatType): () -> ArrayList<String> {
        return {
            // Your logic to calculate stats and return ArrayList<String> goes here
            val statsResult = arrayListOf("Stat1", "Stat2", "Stat3")
            statsResult
        }
    }

}