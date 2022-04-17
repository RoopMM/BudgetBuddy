package sheridan.malhanro.budgetbuddy

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import sheridan.malhanro.budgetbuddy.databinding.ActivityBudgetBinding
import sheridan.malhanro.budgetbuddy.databinding.ActivityChartBinding

class ChartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrayList = intent.getIntegerArrayListExtra("arrayList")


//        val s1 = Segment("S1", arrayList?.get(0) ?: 100)
//        val s2 = Segment("S1", arrayList?.get(1) ?: 200)
//        val s3 = Segment("S1", arrayList?.get(2) ?: 100)
//        val s4 = Segment("S1", arrayList?.get(3) ?: 500)
//
//        val sf1 = SegmentFormatter(Color.BLUE)
//        val sf2 = SegmentFormatter(Color.YELLOW)
//        val sf3 = SegmentFormatter(Color.GREEN)
//        val sf4 = SegmentFormatter(Color.GRAY)
//
//        binding.pieChart.addSegment(s1, sf1)
//        binding.pieChart.addSegment(s2, sf2)
//        binding.pieChart.addSegment(s3, sf3)
//        binding.pieChart.addSegment(s4, sf4)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}