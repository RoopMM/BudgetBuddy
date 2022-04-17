package sheridan.malhanro.budgetbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import sheridan.malhanro.budgetbuddy.databinding.ActivityAddBinding
import sheridan.malhanro.budgetbuddy.databinding.ActivityBudgetBinding

class BudgetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBudgetBinding
    private var shopping = 100
    private var travel= 120
    private var grocery = 200
    private var house_Rent = 500
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityBudgetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button3.setOnClickListener {
            startActivity(Intent(this, ChartActivity::class.java))

        }

        readBudgetData()
      //  draw()
    }


    fun draw() {

    val arrayList = ArrayList<Int>()
    arrayList.add(shopping)
    arrayList.add(travel)
    arrayList.add(grocery)
    arrayList.add(house_Rent)

    val intent = Intent(this,ChartActivity::class.java )
    intent.putIntegerArrayListExtra("arrayList", arrayList)
    startActivity(intent)

}
    private fun readBudgetData() {
        val db = FirebaseFirestore.getInstance()
        db.collection("budget")
            .get()
            .addOnCompleteListener {
                val result: StringBuffer = StringBuffer()
                if(it.isSuccessful){
                    for(document in it.result!!){
                        result.append(document.data.getValue("budgetName")).append(" ")
                            .append(document.data.getValue("amount")).append("\n\n")
                    }
                    binding.budgetData.setText(result)
                }
            }

    }
}