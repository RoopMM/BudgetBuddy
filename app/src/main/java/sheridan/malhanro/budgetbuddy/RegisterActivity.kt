package sheridan.malhanro.budgetbuddy

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text

class RegisterActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var databaseReference : DatabaseReference? = null
    var database: FirebaseDatabase?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")
        registerUser()
    }

    private fun registerUser()
    {
        val btn = findViewById(R.id.register) as Button
        val email = findViewById(R.id.email_et) as EditText
        val username = findViewById(R.id.username_et) as EditText
        val password = findViewById(R.id.password_et) as EditText
        val confirmPassword = findViewById(R.id.confirmPassword_et) as EditText

        btn.setOnClickListener{
            if(TextUtils.isEmpty(email.text.toString())){
                email.setError("Required")
                return@setOnClickListener
            }
            else if(TextUtils.isEmpty(password.text.toString())){
                password.setError("Required")
                return@setOnClickListener
            }
            else if(TextUtils.isEmpty(confirmPassword.text.toString())){
                confirmPassword.setError("Required")
                return@setOnClickListener
            }
            else if(TextUtils.isEmpty(username.text.toString())){
                username.setError("Required")
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        val currentUser = auth.currentUser
                        val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                        currentUserDb?.child("username")?.setValue(username.text.toString())

                        finish()
                    }
                    else
                    {
                        Toast.makeText(this@RegisterActivity, "Sign Up Failed", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

}