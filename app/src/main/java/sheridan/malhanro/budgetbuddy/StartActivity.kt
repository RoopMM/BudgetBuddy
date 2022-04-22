package sheridan.malhanro.budgetbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import sheridan.malhanro.budgetbuddy.R
import sheridan.malhanro.budgetbuddy.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.imageButton2.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}