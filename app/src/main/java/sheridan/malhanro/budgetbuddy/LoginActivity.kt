package sheridan.malhanro.budgetbuddy

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import sheridan.malhanro.budgetbuddy.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var actionBar: ActionBar
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)

        actionBar= supportActionBar!!
        actionBar.title= "Login"

        progressDialog = ProgressDialog(this)
//        progressDialog.setTitle("Please Wait...")
//        progressDialog.setMessage("Creating Account...")
        progressDialog.setCanceledOnTouchOutside(false)
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.textView4.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        binding.next.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))

           // validateData()
        }

    }

    private fun validateData(){
        email = binding.editText2.text.toString().trim()
        password = binding.editText3.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editText2.error = "Invalid Format"
        }
        else if(TextUtils.isEmpty(password)){
            binding.editText3.error = "Required"
        }
        else{
            firebaseLogin()
        }

    }

    private fun firebaseLogin() {
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this,"Logged in as $email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, AddBudgetActivity::class.java))
                finish()
            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
                Toast.makeText(this, "Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }


    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null)
        {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}