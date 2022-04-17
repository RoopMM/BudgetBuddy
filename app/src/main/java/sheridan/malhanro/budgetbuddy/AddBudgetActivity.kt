package sheridan.malhanro.budgetbuddy

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.firestore.FirebaseFirestore
import sheridan.malhanro.budgetbuddy.databinding.ActivityAddBinding
import sheridan.malhanro.budgetbuddy.databinding.ActivityMainBinding
import sheridan.malhanro.budgetbuddy.databinding.ActivityRegisterBinding

class AddBudgetActivity :AppCompatActivity(){
    private lateinit var actionBar: ActionBar
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        actionBar = supportActionBar!!

        actionBar.title = "Add Budget"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val budgetName = binding.budgetName.text.toString()
            val amount = binding.amount.text.toString()

            saveFirestore(budgetName, amount)

        }
    }

    private fun saveFirestore(budgetName: String, amount: String) {
        val db = FirebaseFirestore.getInstance()
        val budget: MutableMap<String, Any> = HashMap()
        budget["budgetName"] = budgetName
        budget["amount"] = amount
        db.collection("budget")
            .add(budget)
            .addOnSuccessListener {
                Toast.makeText(this@AddBudgetActivity, "Budget Added Successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this@AddBudgetActivity, "Try Again!!", Toast.LENGTH_SHORT).show()

            }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}