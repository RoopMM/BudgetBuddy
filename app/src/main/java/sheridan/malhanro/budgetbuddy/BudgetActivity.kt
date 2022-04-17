package sheridan.malhanro.budgetbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import sheridan.malhanro.budgetbuddy.databinding.ActivityAddBinding
import sheridan.malhanro.budgetbuddy.databinding.ActivityBudgetBinding

class BudgetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBudgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityBudgetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        readBudgetData()
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