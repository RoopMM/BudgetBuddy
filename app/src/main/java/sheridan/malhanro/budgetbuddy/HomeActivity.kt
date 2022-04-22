package sheridan.malhanro.budgetbuddy

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import sheridan.malhanro.budgetbuddy.databinding.ActivityHomeBinding

class HomeActivity:AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var actionBar: ActionBar
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()
        checkUser()
        binding.button2.setOnClickListener {
            startActivity(Intent(this, AddBudgetActivity::class.java))
        }

        binding.imageButton3.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null){
            val email = firebaseUser.email
        }
        else
        {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}