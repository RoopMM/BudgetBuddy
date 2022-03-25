package sheridan.malhanro.budgetbuddy

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import sheridan.malhanro.budgetbuddy.databinding.ActivityMainBinding

class AddBudgetActivity :AppCompatActivity(){
    private lateinit var actionBar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        actionBar = supportActionBar!!

        actionBar.title = "Add Budget"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
    }

        override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}