package sheridan.malhanro.budgetbuddy

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text
import sheridan.malhanro.budgetbuddy.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var actionBar: ActionBar
    private lateinit var progressDialog: ProgressDialog
    private var email = ""
    private var password = ""

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "Register"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
//
        progressDialog = ProgressDialog(this)
//        progressDialog.setTitle("Please Wait...")
//        progressDialog.setMessage("Creating Account...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()
//        binding.textView4.setOnClickListener {
//            startActivity(Intent(this,LoginActivity::class.java))
//        }
        binding.btn.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailEt.error = "Invalid"
        }
        else if(TextUtils.isEmpty(password)){
            binding.passwordEt.error = "Required"
        }
        else if(password.length<8){
            binding.passwordEt.error = "Minimum 8 characters required"
        }
        else{
            firebaseRegister()
        }
    }

    private fun firebaseRegister() {
        progressDialog.show()
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Registration complete for $email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()

            }
            .addOnFailureListener {e->
                Toast.makeText(this, "Registration failed due to ${e.message}", Toast.LENGTH_SHORT).show()

            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}